package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성
    public void write(Board board, MultipartFile file) throws IOException {

        // 프로젝트 경로 담기
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        // UUID로 파일명 담아주기
        UUID uuid = UUID.randomUUID();
        String filename = uuid + "_" + file.getOriginalFilename();

        // 경로를 지정해주고 파일이름을 지정해준다.
        File saveFile = new File(projectPath, filename);

        file.transferTo(saveFile);

        // 데이터 넣어주기
        board.setFilename(filename);
        board.setFilepath("/files/" + filename);

        boardRepository.save(board);
    }

    // 게시글 리스트
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    // 게시글 상세
    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    // 게시글 삭제
    public void boardDelete(Integer id) {

        boardRepository.deleteById(id);
    }

    // 검색 기능
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
