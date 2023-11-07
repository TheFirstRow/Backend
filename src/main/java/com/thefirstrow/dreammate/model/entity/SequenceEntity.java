package com.thefirstrow.dreammate.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stageTitle;
    private String stageContent;
    private String youtubeURL;
    private Long musicMs;
    private String targetObject;
    private Integer effectNumber;
    private String cameraSetting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
