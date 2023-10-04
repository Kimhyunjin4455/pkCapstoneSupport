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

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodReviewServiceImpl implements FoodReviewService{

    private final FoodReviewRepository foodReviewRepository; // 반드시 final 사용 필요

    private final FoodReviewImageRepository imageRepository;
    @Override
    @Transactional
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
    public PageResultDTO<FoodReviewDTO, FoodReview> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("fno").descending());
        Page<FoodReview> result = foodReviewRepository.findAll(pageable);
        Function<FoodReview, FoodReviewDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }
}
