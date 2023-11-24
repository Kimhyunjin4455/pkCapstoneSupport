package org.zerock.freview.service;

import org.zerock.freview.dto.ReviewDTO;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getListOfFoodReview(Long fno);
    Long register(ReviewDTO foodReviewDTO);
    void modify(ReviewDTO foodReviewDTO);
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO foodReviewDTO){
        Review foodReview = Review.builder()
                .reviewNum(foodReviewDTO.getReviewnum())
                .foodReview(FoodReview.builder().fno(foodReviewDTO.getFno()).build())
                .text(foodReviewDTO.getText()).build();

        return foodReview;
    }

    default ReviewDTO entityToDTO(Review foodReview){
        ReviewDTO foodReviewDTO = ReviewDTO.builder()
                .reviewnum(foodReview.getReviewNum())
                .fno(foodReview.getFoodReview().getFno())
                .text(foodReview.getText())
                .regDate(foodReview.getRegDate())
                .modDate(foodReview.getModDate())
                .build();


        return foodReviewDTO;
    }
}
