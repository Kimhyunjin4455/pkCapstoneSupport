package org.zerock.freview.service;

import org.springframework.stereotype.Service;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.dto.PageResultDTO;
import org.zerock.freview.entity.FoodReview;


public interface FoodReviewService {
    Long register(FoodReviewDTO dto);

    PageResultDTO<FoodReviewDTO, FoodReview> getList(PageRequestDTO requestDTO);

    default FoodReview dtoToEntity(FoodReviewDTO dto){
        FoodReview entity = FoodReview.builder()
                .fno(dto.getFno())
                .restaurantName(dto.getRestaurantName())
                .content(dto.getContent())
                .build();
        return entity;
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
