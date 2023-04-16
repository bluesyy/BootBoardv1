package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    // JPA 를 이용해서 검색기능을 추가해보자.
    // 명명규칙을 잘 지켜야함 -> 대문자 소문자
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    
}
