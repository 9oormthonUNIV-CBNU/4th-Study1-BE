package com.goormthonUniv.goormthon.controller;

import com.goormthonUniv.goormthon.domain.Post;
import com.goormthonUniv.goormthon.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.readAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.readById(id);
    }

    @PostMapping
    public Long createPost (@RequestParam String title,@RequestParam String content){
        return postService.create(title,content);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestParam String title, @RequestParam String content){
        postService.update(id,title,content);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.delete(id);
    }
}
