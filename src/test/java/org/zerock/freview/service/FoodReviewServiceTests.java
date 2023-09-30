package org.zerock.freview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodReviewServiceTests {
    @Autowired FoodReviewService service;

//    @Test
//    public void testRegister(){
//        FoodReviewDTO foodReviewDTO = FoodReviewDTO.builder()
//                .restaurantName("BBQ장산점")
//                .content("황금올리브 쵝오")
//                .build();
//
//        System.out.println(service.register(foodReviewDTO));
//    }
}
