package com.example.board.controller;

import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<PostResponseDto> create(@RequestBody @Valid PostRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(request));
    }

    // 전체 조회
    @GetMapping
    public List<PostResponseDto> list() {
        return postService.getAllPosts();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public PostResponseDto get(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 수정
    @PutMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody @Valid PostRequestDto request) {
        return postService.updatePost(id, request);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("삭제 완료");
    }
}
