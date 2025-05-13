package com.example.demo.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto createBoard(BoardRequestDto dto) {
        User user = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .build();

        return toDto(boardRepository.save(board));
    }

    public BoardResponseDto updateBoard(Long id, BoardRequestDto dto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        User user = userRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Board updated = Board.builder()
                .id(board.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .createdAt(board.getCreatedAt())
                .build();

        return toDto(boardRepository.save(updated));
    }

    public BoardResponseDto getBoard(Long id) {
        return boardRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }

    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    private BoardResponseDto toDto(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getUser().getUsername())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}

