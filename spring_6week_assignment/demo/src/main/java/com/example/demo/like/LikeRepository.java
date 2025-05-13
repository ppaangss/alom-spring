package com.example.demo.like;

import com.example.demo.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.user.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndBoard(User user, Board board);
    List<Like> findAllByBoard(Board board);
    List<Like> findAllByUser(User user);
}
