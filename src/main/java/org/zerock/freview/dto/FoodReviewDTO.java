package org.zerock.freview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodReviewDTO {
    private Long fno;
    private String restaurantName;
    private String content;
    private LocalDateTime regDate, modDate;
}
