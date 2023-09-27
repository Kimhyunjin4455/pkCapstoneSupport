package org.zerock.freview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.freview.entity.FoodReview;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FoodReviewRepositoryTests {

    @Autowired FoodReviewRepository foodReviewRepository;

//    @Test
//    public void insertDummies(){
//        IntStream.rangeClosed(1,5).forEach(i->{
//            FoodReview foodReview = FoodReview.builder()
//                    .restaurantName("Restaurant... " + i)
//                    .content("Content... " +i)
//                    .build();
//            System.out.println(foodReviewRepository.save(foodReview));
//        });
//    }

    @Test
    public void updateTest(){
        Optional<FoodReview> result = foodReviewRepository.findById(5L);

        if (result.isPresent()){
            FoodReview foodReview = result.get();
            foodReview.changeRestaurantName("변경할 식당명 작성....");
            foodReview.changeContent("변경할 리뷰 내용 작성....");

            foodReviewRepository.save(foodReview);
        }
    }
}
