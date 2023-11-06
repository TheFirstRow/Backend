package com.thefirstrow.dreammate.model.entity;

import com.thefirstrow.dreammate.model.Avatar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDto {

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

    public static AvatarDto fromEntity(AvatarEntity entity) {
        return new AvatarDto(
                entity.getId(),
                entity.getGender(),
                entity.getTop(),
                entity.getBottom(),
                entity.getShoes()
//                entity.getId(),
//                entity.getGender(),
//                entity.getHeadClothesNumber(),
//                entity.getTopClothesNumber(),
//                entity.getBottomClothesNumber(),
//                entity.getShoesNumber(),
//                entity.getHeadClothesColor(),
//                entity.getTopClothesColor(),
//                entity.getBottomClothesColor(),
//                entity.getShoesColor()
        );
    }

}
