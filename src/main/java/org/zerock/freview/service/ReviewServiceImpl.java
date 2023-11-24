package org.zerock.freview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.freview.dto.ReviewDTO;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.entity.Review;
import org.zerock.freview.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfFoodReview(Long fno){
        FoodReview foodReview = FoodReview.builder().fno(fno).build();
        List<Review> result = reviewRepository.findByFoodReview(foodReview);

        return result.stream().map(review -> entityToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO foodReviewDTO) {
        Review review = dtoToEntity(foodReviewDTO);
        reviewRepository.save(review);
        return review.getReviewNum();
    }

    @Override
    public void modify(ReviewDTO foodReviewDTO) {
        Optional<Review> result = reviewRepository.findById(foodReviewDTO.getReviewnum());
        if(result.isPresent()){
            Review review = result.get();
            review.changeText(foodReviewDTO.getText());

            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long reviewnum) {
        reviewRepository.deleteById(reviewnum);
    }
}
