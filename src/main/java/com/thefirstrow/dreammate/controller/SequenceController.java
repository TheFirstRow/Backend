package com.thefirstrow.dreammate.controller;

import com.thefirstrow.dreammate.controller.request.SequenceCreateRequest;
import com.thefirstrow.dreammate.controller.request.SequenceUpdateRequest;
import com.thefirstrow.dreammate.model.entity.SequenceEntity;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.service.SequenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sequences")
public class SequenceController {
    private final SequenceService sequenceService;

    public SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }

    // 시퀀스 생성
    @PostMapping
    public ResponseEntity<Long> createSequence(@AuthenticationPrincipal UserEntity currentUser,
                                               @RequestBody SequenceCreateRequest request) {
        Long sequenceId = sequenceService.createSequence(currentUser.getEmail(), request.getStageTitle(), request.getStageContent(),
                request.getMusicMs(), request.getTargetObject(), request.getEffectNumber(), request.getCameraName(),
                request.getX(), request.getY(), request.getZ());
        return ResponseEntity.ok(sequenceId);
    }

    // 내가 만든 시퀀스 조회
    @GetMapping("/mySequences")
    public List<SequenceEntity> getMySequences(@AuthenticationPrincipal UserEntity currentUser) {
        return sequenceService.getMySequences(currentUser.getId());
    }

    // 내가 만든 시퀀스를 제외한 모든 시퀀스 조회
    @GetMapping("/otherUsersSequences")
    public List<SequenceEntity> getOtherUsersSequences(@AuthenticationPrincipal UserEntity currentUser) {
        return sequenceService.getOtherUsersSequences(currentUser.getId());
    }

    // 시퀀스 수정
    @PutMapping("/{sequenceId}")
    public ResponseEntity<SequenceEntity> updateSequence(@AuthenticationPrincipal UserEntity currentUser,
                                                         @PathVariable Long sequenceId,
                                                         @RequestBody SequenceUpdateRequest request) {
        SequenceEntity updatedSequence = sequenceService.updateSequence(currentUser.getId(), sequenceId, request.getStageTitle(),
                request.getStageContent(), request.getMusicMs(), request.getTargetObject(), request.getEffectNumber(),
                request.getCameraName(), request.getX(), request.getY(), request.getZ());
        return ResponseEntity.ok(updatedSequence);
    }

    // 시퀀스 삭제
    @DeleteMapping("/{sequenceId}")
    public ResponseEntity<Void> deleteSequence(@PathVariable Long sequenceId) {
        sequenceService.deleteSequence(sequenceId);
        return ResponseEntity.ok().build();
    }

    // 시퀀스 복사 및 저장(잘모르게뜸...)
//    @PostMapping("/copy/{sequenceId}")
//    public ResponseEntity<Long> copyAndSaveSequence(@AuthenticationPrincipal UserEntity currentUser,
//                                                    @PathVariable Long sequenceId,
//                                                    @RequestBody SequenceCopyRequest request) {
//        Long newSequenceId = sequenceService.copyAndSaveSequence(currentUser.getId(), sequenceId, request.getNewStageTitle());
//        return ResponseEntity.ok(newSequenceId);
//    }
}
