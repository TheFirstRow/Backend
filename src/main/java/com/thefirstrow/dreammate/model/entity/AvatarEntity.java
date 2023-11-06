package com.thefirstrow.dreammate.model.entity;

import com.thefirstrow.dreammate.model.Avatar;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "\"avatar\"")
@NoArgsConstructor
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String top;
    private String bottom;
    private String shoes;
//    private Long id;
//    private String gender;
//    private String headClothesNumber;
//    private String topClothesNumber;
//    private String bottomClothesNumber;
//    private String shoesNumber;
//    private String headClothesColor;
//    private String topClothesColor;
//    private String bottomClothesColor;
//    private String shoesColor;

    public static AvatarEntity of(String gender, String top, String bottom, String shoes) {
        AvatarEntity entity = new AvatarEntity();
        entity.setGender(gender);
        entity.setTop(top);
        entity.setBottom(bottom);
        entity.setShoes(shoes);
//        entity.setGender(gender);
//        entity.setHeadClothesNumber(headClothesNumber);
//        entity.setTopClothesNumber(topClothesNumber);
//        entity.setBottomClothesNumber(bottomClothesNumber);
//        entity.setShoesNumber(shoesNumber);
//        entity.setHeadClothesColor(headClothesColor);
//        entity.setTopClothesColor(topClothesColor);
//        entity.setBottomClothesColor(bottomClothesColor);
//        entity.setShoesColor(shoesColor);
        return entity;
    }
}
