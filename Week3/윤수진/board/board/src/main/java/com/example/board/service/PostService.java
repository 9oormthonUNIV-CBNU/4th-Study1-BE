package com.example.board.service;

import com.example.board.domain.Post;
import com.example.board.dto.PostRequestDto;
import com.example.board.dto.PostResponseDto;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 게시글 작성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post();
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        Post saved = postRepository.save(post);
        return new PostResponseDto(saved);
    }

    // 전체 조회
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    // 수정
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        Post updated = postRepository.save(post);
        return new PostResponseDto(updated);
    }

    // 삭제
    public void deletePost(Long id) {
        Post post = findPost(id);
        postRepository.delete(post);
    }

    // 내부에서 Post 찾기
    private Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. id=" + id));
    }
}
