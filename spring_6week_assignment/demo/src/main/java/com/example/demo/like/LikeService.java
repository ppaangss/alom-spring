package com.example.demo.like;

import com.example.demo.board.Board;
import com.example.demo.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public LikeResponseDto likeBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        likeRepository.findByUserAndBoard(user, board).ifPresent(like -> {
            throw new IllegalArgumentException("Already liked");
        });

        Like like = Like.builder()
                .user(user)
                .board(board)
                .build();

        return toDto(likeRepository.save(like));
    }

    public void unlikeBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        Like like = likeRepository.findByUserAndBoard(user, board)
                .orElseThrow(() -> new IllegalArgumentException("Like not found"));

        likeRepository.delete(like);
    }

    public List<LikeResponseDto> getLikesByBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        return likeRepository.findAllByBoard(board).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<LikeResponseDto> getLikesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return likeRepository.findAllByUser(user).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LikeResponseDto toDto(Like like) {
        return LikeResponseDto.builder()
                .id(like.getId())
                .username(like.getUser().getUsername())
                .boardTitle(like.getBoard().getTitle())
                .likedAt(like.getLikedAt())
                .build();
    }
}

