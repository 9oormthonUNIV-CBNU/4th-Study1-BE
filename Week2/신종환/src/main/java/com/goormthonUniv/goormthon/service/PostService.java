package com.goormthonUniv.goormthon.service;

import com.goormthonUniv.goormthon.domain.Post;
import com.goormthonUniv.goormthon.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ReadOnlyBufferException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long create(String title, String content){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
        return post.getId();
    }

    public Post readById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> readByTitle(String title){
        return postRepository.findByTitle(title);
    }

    public List<Post> readAll(){
        return postRepository.findAll();
    }

    @Transactional
    public void update(Long id,String title, String content){
        Post post = postRepository.findById(id);
        post.setTitle(title);
        post.setContent(content);
    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id);
        postRepository.delete(post);
    }

}
