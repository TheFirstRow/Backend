package com.thefirstrow.dreammate.model;

import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

    private Long id;
    private String gender;
    private String top;
    private String bottom;
    private String shoes;
    private UserEntity user;

    public static Avatar fromEntity(AvatarEntity entity) {
        return new Avatar(
                entity.getId(),
                entity.getGender(),
                entity.getTop(),
                entity.getBottom(),
                entity.getShoes(),
                entity.getUser()
        );
    }


//    public enum Top {
//        TOP1("상의1"),
//        TOP2("상의2"),
//        TOP3("상의3");
//
//        private final String description;
//
//        Top(String description) {
//            this.description = description;
//        }
//        public String getDescription() {
//            return description;
//        }
//    }
//    public enum Bottom {
//        BOTTOM1("하의1"),
//        BOTTOM2("하의2"),
//        BOTTOM3("하의3");
//
//        private final String description;
//
//        Bottom(String description) {
//            this.description = description;
//        }
//        public String getDescription() {
//            return description;
//        }
//    }
//    public enum Shoes {
//        SHOES1("신발1"),
//        SHOES2("신발2"),
//        SHOES3("신발3");
//
//        private final String description;
//
//        Shoes(String description) {
//            this.description = description;
//        }
//        public String getDescription() {
//            return description;
//        }
//    }
}
