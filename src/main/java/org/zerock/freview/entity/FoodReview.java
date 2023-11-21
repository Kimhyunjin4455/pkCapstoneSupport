package org.zerock.freview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FoodReview extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;

    @Column(length = 100, nullable = false)
    private  String restaurantName;

    @Column(length = 1500, nullable = false)
    private String content;

//    @Column(nullable = false)
//    private String upgradeContent;

    public void changeRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public void changeContent(String content){
        this.content = content;
    }

//    public void changeUpgradeContent(String upgradeContent){this.upgradeContent = upgradeContent;}

}
