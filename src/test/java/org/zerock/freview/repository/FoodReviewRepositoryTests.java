package org.zerock.freview.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.entity.FoodReviewImage;
import org.zerock.freview.entity.QFoodReview;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class FoodReviewRepositoryTests {

    @Autowired FoodReviewRepository foodReviewRepository;

    @Autowired FoodReviewImageRepository imageRepository;

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

//    @Test
//    public void updateTest(){
//        Optional<FoodReview> result = foodReviewRepository.findById(5L);
//
//        if (result.isPresent()){
//            FoodReview foodReview = result.get();
//            foodReview.changeRestaurantName("변경할 식당명 작성....");
//            foodReview.changeContent("변경할 리뷰 내용 작성....");
//
//            foodReviewRepository.save(foodReview); //
//        }
//    }

//    @Test
//    public void testQuery1(){ // 문제1: findAll(builder,pageable)가 인식되지 않음
//        // 지점명/내용과 같이 단 하나의 항목으로 검색하는 경우
//        // 지점명 + 내용과 같이 2개의 항목으로 검색하는 경우
//        // queryDsl 사용법: 1. BooleanBuilder 생성 2. 조건에 맞는 구문은 queryDsl에서 사용하는 Predicate타입의 함수 생성 3. BooleanBuilder에 작성된 Predicate 추가하고 실행
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("fno").descending());
//        QFoodReview qFoodReview = QFoodReview.foodReview;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression expression = qFoodReview.restaurantName.contains(keyword);
//        builder.and(expression);
//        Page<FoodReview> result = foodReviewRepository.findAll(builder,pageable);
//        result.stream().forEach(foodReview -> {
//            System.out.println(foodReview);
//        });
//    }

    @Commit
    @Transactional
    @Test
    public void insertFoods(){
        IntStream.rangeClosed(1,5).forEach(i->{
            FoodReview foodReview = FoodReview.builder()
                    .restaurantName("BBQ...new" + i)
                    .content("contentBBQ...new"+i).build();
            System.out.println("----------------------------------------");
            foodReviewRepository.save(foodReview);

            int count = (int)(Math.random()*5)+1; // 1,2,3,4

            for(int j=0; j<count; j++){
                FoodReviewImage foodReviewImage = FoodReviewImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .foodReview(foodReview)
                        .imgName("test" + j + ".jpg").build();

                imageRepository.save(foodReviewImage);
            }

            System.out.println("=========================================");
        });
    }

//    @Test
//    public void testListPage(){
//        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "fno"));
//        Page<Object[]> result = foodReviewRepository.getListPage(pageRequest);
//        for (Object[] objects : result.getContent()){
//            System.out.println(Arrays.toString(objects));
//        }
//    }
}
