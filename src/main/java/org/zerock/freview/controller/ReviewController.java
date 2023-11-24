package org.zerock.freview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.freview.dto.ReviewDTO;
import org.zerock.freview.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/{fno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("fno") Long fno){
        log.info("----------------list-------------------");
        log.info("FNO: "+ fno);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfFoodReview(fno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{fno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO){
        log.info("--------------add Review----------------");
        log.info("reviewDTO: " + reviewDTO);

        Long reviewnum = reviewService.register(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{fno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum,
                                             @RequestBody ReviewDTO reviewDTO){
        log.info("------------modify Review-----------------");
        log.info("reviewDTO: " + reviewDTO);

        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{fno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){
        log.info("-------------modify removeReview-----------------");
        log.info("reviewnum: " + reviewnum);

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }


}
