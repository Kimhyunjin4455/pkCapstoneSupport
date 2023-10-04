package org.zerock.freview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSInput;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodReviewDTO {
    private Long fno;
    private String restaurantName;
    private String content;

    @Builder.Default
    private List<FoodReviewImageDTO> imageDTOList = new ArrayList<>();
    private LocalDateTime regDate, modDate;
}
