package org.zerock.freview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.entity.FoodReview;
import org.zerock.freview.repository.FoodReviewRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodReviewServiceImpl implements FoodReviewService{

    private final FoodReviewRepository repository; // 반드시 final 사용 필요
    @Override
    public Long register(FoodReviewDTO dto) {

        log.info("DTO------------------");
        log.info(dto);

        FoodReview entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getFno();
    }
}
