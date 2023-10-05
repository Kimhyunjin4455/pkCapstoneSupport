package org.zerock.freview.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.dto.PageResultDTO;
import org.zerock.freview.entity.FoodReview;

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

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(3).build();

        PageResultDTO<FoodReviewDTO, Object[]> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());
        System.out.println("===================================");

        for(FoodReviewDTO foodReviewDTO : resultDTO.getDtoList()){
            System.out.println(foodReviewDTO);
        }

        System.out.println("===================================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));
    }
}
