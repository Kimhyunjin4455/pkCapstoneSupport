package org.zerock.freview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FoodReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid;
    private String imgName;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodReview foodReview;
}
