package com.example.demo.like;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<LikeResponseDto> likeBoard(
            @PathVariable Long boardId,
            @RequestParam Long userId
    ) {
        return ResponseEntity.status(201).body(likeService.likeBoard(userId, boardId));
    }

    @DeleteMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<Void> unlikeBoard(
            @PathVariable Long boardId,
            @RequestParam Long userId
    ) {
        likeService.unlikeBoard(userId, boardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/boards/{boardId}/likes")
    public ResponseEntity<List<LikeResponseDto>> getLikesByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(likeService.getLikesByBoard(boardId));
    }

    @GetMapping("/api/users/{userId}/likes")
    public ResponseEntity<List<LikeResponseDto>> getLikesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(likeService.getLikesByUser(userId));
    }
}
