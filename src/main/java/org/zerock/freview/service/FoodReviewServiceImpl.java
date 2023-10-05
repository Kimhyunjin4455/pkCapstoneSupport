package org.zerock.freview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.dto.PageResultDTO;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.entity.FoodReviewImage;
import org.zerock.freview.repository.FoodReviewImageRepository;
import org.zerock.freview.repository.FoodReviewRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodReviewServiceImpl implements FoodReviewService{

    private final FoodReviewRepository foodReviewRepository; // 반드시 final 사용 필요

    private final FoodReviewImageRepository imageRepository;
    @Transactional
    @Override
    public Long register(FoodReviewDTO foodReviewDTO) {

        Map<String, Object> entityMap = dtoToEntity(foodReviewDTO);
        FoodReview foodReview = (FoodReview) entityMap.get("foodReview");
        List<FoodReviewImage> foodReviewImageList = (List<FoodReviewImage>) entityMap.get("imgList");

        foodReviewRepository.save(foodReview);

        foodReviewImageList.forEach(foodReviewImage -> {
            imageRepository.save(foodReviewImage);
        });

        return foodReview.getFno();
    }

    @Override
    public PageResultDTO<FoodReviewDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("fno").descending());
        Page<Object[]> result = foodReviewRepository.getListPage(pageable);
        Function<Object[], FoodReviewDTO> fn = (arr -> entitiesToDTO(
                (FoodReview)arr[0], (List<FoodReviewImage>) (Arrays.asList((FoodReviewImage)arr[1]))
        ));
        return new PageResultDTO<>(result, fn);
    }

//    @Override
//    public FoodReviewDTO getFoodReview(Long fno) {
//        List<Object[]> result = foodReviewRepository.getFoodReviewWithAll(fno);
//        FoodReview foodReview = (FoodReview) result.get(0)[0]; // FoodReview 엔티티는 가장 앞에 존재, 모든 row가 동일한 값
//        List<FoodReviewImage> foodReviewImageList = new ArrayList<>(); // 음식리뷰시 첨부한 이미지 개수 foodReviewImage 객체 필요
//        result.forEach(arr->{
//            FoodReviewImage foodReviewImage = (FoodReviewImage) arr[1];
//            foodReviewImageList.add(foodReviewImage);
//        });
//
//        return entitiesToDTO(foodReview, foodReviewImageList);
//    }
}
