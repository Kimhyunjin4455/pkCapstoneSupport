package org.zerock.freview.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.freview.entity.FoodReview;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {


    // FoodReviewRepository 인터페이스의 getListPage()메서드는 FoodReview객체와 FoodReviewImage객체를 Object[]로 반환
    @Query("select f, fi from FoodReview f " +
            "left outer join FoodReviewImage fi on fi.foodReview = f group by f"
    )
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select f, fi  from FoodReview f " +
            "left outer join FoodReviewImage fi on fi.foodReview = f" +
            " where f.fno= :fno group by fi"
    )
    List<Object[]> getFoodReviewWithAll(Long fno); // 특정 음식 리뷰  조회

    //food_review테이블에서 특정 리뷰를 삭제하려면 food_review_image테이블에서 먼저 삭제하고 food_review에서 삭제해야 함
    //이 2개의 작업은 하나의 트랜잭션으로 관리될 필요가 있음
    //위 내용의 코드 추가할 필요가 있으면 아래에 추가.


}
