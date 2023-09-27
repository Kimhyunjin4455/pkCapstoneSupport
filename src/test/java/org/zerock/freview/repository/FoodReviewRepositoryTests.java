package org.zerock.freview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.freview.entity.FoodReview;

import java.util.stream.IntStream;

@SpringBootTest
public class FoodReviewRepositoryTests {

    @Autowired FoodReviewRepository foodReviewRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,5).forEach(i->{
            FoodReview foodReview = FoodReview.builder()
                    .restaurantName("Restaurant... " + i)
                    .content("Content... " +i)
                    .build();
            System.out.println(foodReviewRepository.save(foodReview));
        });
    }
}
