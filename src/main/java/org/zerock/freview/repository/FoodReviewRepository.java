package org.zerock.freview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.freview.entity.FoodReview;

public interface FoodReviewRepository extends JpaRepository<FoodReview, Long> {
}
