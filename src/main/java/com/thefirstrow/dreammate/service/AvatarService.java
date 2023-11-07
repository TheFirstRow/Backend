package com.thefirstrow.dreammate.service;

import com.thefirstrow.dreammate.model.Avatar;
import com.thefirstrow.dreammate.model.entity.AvatarEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.AvatarEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarEntityRepository avatarEntityRepository;

    @Transactional
    public Avatar saveAvatar(String gender, String top, String bottom, String shoes, UserEntity user) {

        AvatarEntity avatarEntity = AvatarEntity.of(gender, top, bottom, shoes, user);

        AvatarEntity savedAvatar = avatarEntityRepository.save(avatarEntity);

        return Avatar.fromEntity(savedAvatar);
    }

    @Transactional(readOnly = true)
    public Avatar getAvatarById(Long id) {
        AvatarEntity foundAvatar = avatarEntityRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Avatar not found"));

        return Avatar.fromEntity(foundAvatar);
    }

}
