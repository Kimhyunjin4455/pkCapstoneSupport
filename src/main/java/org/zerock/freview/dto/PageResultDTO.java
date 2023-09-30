package org.zerock.freview.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> { // DTO와 Entity의 타입
    private List<DTO> dtoList;
    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn){ // fn은 엔티티 객체들을 DTO로 변환하는 것을 넣을 것
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
