package org.zerock.freview.service;

import org.zerock.freview.controller.FoodReviewDTO;
import org.zerock.freview.entity.FoodReview;

public interface FoodReviewService {
    Long register(FoodReviewDTO dto);

    default FoodReview dtoToEntity(FoodReviewDTO dto){
        FoodReview entity = FoodReview.builder()
                .fno(dto.getFno())
                .restaurantName(dto.getRestaurantName())
                .content(dto.getContent())
                .build();
        return entity;
    }
}
