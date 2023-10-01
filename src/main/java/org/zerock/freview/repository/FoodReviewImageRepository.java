package org.zerock.freview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.freview.entity.FoodReviewImage;

public interface FoodReviewImageRepository extends JpaRepository<FoodReviewImage, Long> {
}
