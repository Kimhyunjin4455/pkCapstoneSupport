package org.zerock.freview.service;

import org.springframework.stereotype.Service;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.dto.FoodReviewImageDTO;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.dto.PageResultDTO;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.entity.FoodReviewImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public interface FoodReviewService {
    Long register(FoodReviewDTO dto);

    // FoodReviewService의 getList()는 FoodReview, FoodReviewImage를 Object[] 배열을 리스트에 담은 형태, , 각 Object[]를 FoodReviewDTO라는 하나의 객체로 처리해야만 함
    // 컨트롤러가 호출할 때 사용
    PageResultDTO<FoodReviewDTO, Object[]> getList(PageRequestDTO requestDTO);


    // FoodReview를 JPA로 처리하기 위해 FoodReviewDTO를 FoodReview객체로 변환, Map타입을 이용해 리뷰와 이미지 객체 같이 처리
    default Map<String, Object> dtoToEntity(FoodReviewDTO foodReviewDTO){
        Map<String, Object> entityMap = new HashMap<>();

        FoodReview foodReview = FoodReview.builder()
                .fno(foodReviewDTO.getFno())
                .restaurantName(foodReviewDTO.getRestaurantName())
                .content(foodReviewDTO.getContent())
                .build();


        entityMap.put("foodReview", foodReview); // foodReview 엔티티 생성 후 저장

        List<FoodReviewImageDTO> imageDTOList = foodReviewDTO.getImageDTOList();
        //FoodReviewImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<FoodReviewImage> foodReviewImageList = imageDTOList.stream().map(foodReviewImageDTO -> {
                FoodReviewImage foodReviewImage = FoodReviewImage.builder()
                        .inum(foodReviewImageDTO.getInum())
                        .path(foodReviewImageDTO.getPath())
                        .imgName(foodReviewImageDTO.getImgName())
                        .uuid(foodReviewImageDTO.getUuid())
                        .foodReview(foodReview)
                        .build();
                return foodReviewImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", foodReviewImageList);
        }

        return entityMap;
    }

    default FoodReviewDTO entityToDTO(FoodReview entity){
        FoodReviewDTO dto = FoodReviewDTO.builder()
                .fno(entity.getFno())
                .restaurantName(entity.getRestaurantName())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

    // JPA를 통해서 나오는 엔티티 객체들을 FoodReviewDTO로 변환하는 entitiesToDto()추가
    default FoodReviewDTO entitiesToDTO(FoodReview foodReview, List<FoodReviewImage> foodReviewImages){
        FoodReviewDTO foodReviewDTO = FoodReviewDTO.builder()
                .fno(foodReview.getFno())
                .restaurantName(foodReview.getRestaurantName())
                .content(foodReview.getContent())
                .regDate(foodReview.getRegDate())
                .modDate(foodReview.getModDate()).build();

        if(foodReviewImages != null && foodReviewImages.size() > 0 ){

            List<FoodReviewImageDTO> foodReviewImageDTOList = foodReviewImages.stream().map(foodReviewImage -> {

                if(foodReviewImage == null){
                    return null;
                }

                return FoodReviewImageDTO.builder().imgName(foodReviewImage.getImgName())
                        .inum(foodReviewImage.getInum())
                        .path(foodReviewImage.getPath())
                        .uuid(foodReviewImage.getUuid())
                        .build();
            }).collect(Collectors.toList());

            foodReviewDTO.setImageDTOList(foodReviewImageDTOList);
        }
// foodReviewImageDTOList가 null인 경우에도 나오게 하기 위해 위 코드로 수정, 이미지 관련 문제 발생 시 아래 내용으로 원복
//        List<FoodReviewImageDTO> foodReviewImageDTOList = foodReviewImages.stream().map(foodReviewImage -> {
//            return FoodReviewImageDTO.builder().imgName(foodReviewImage.getImgName())
//                    .inum(foodReviewImage.getInum())
//                    .path(foodReviewImage.getPath())
//                    .uuid(foodReviewImage.getUuid()).build();
//        }).collect(Collectors.toList());
//
//        foodReviewDTO.setImageDTOList(foodReviewImageDTOList);

        return foodReviewDTO;
    }

    FoodReviewDTO getFoodReview(Long fno);


}
