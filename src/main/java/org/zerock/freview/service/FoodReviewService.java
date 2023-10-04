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

    PageResultDTO<FoodReviewDTO, FoodReview> getList(PageRequestDTO requestDTO);

    default Map<String, Object> dtoToEntity(FoodReviewDTO foodReviewDTO){
        Map<String, Object> entityMap = new HashMap<>();

        FoodReview foodReview = FoodReview.builder()
                .fno(foodReviewDTO.getFno())
                .restaurantName(foodReviewDTO.getRestaurantName())
                .content(foodReviewDTO.getContent())
                .build();

        entityMap.put("foodReview", foodReview);

        List<FoodReviewImageDTO> imageDTOList = foodReviewDTO.getImageDTOList();

        //FoodReviewImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<FoodReviewImage> foodReviewImageList = imageDTOList.stream().map(foodReviewImageDTO -> {
                FoodReviewImage foodReviewImage = FoodReviewImage.builder()
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
}
