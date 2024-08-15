package com.example.layer_architecture_problems.domian.post.controller;

import com.example.layer_architecture_problems.domian.post.dto.request.PostCreateRequest;
import com.example.layer_architecture_problems.domian.post.dto.response.PostCreateResponse;
import com.example.layer_architecture_problems.domian.post.service.PostCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostCreateService postCreateService;

    @PostMapping
    public PostCreateResponse create(@Valid @RequestBody PostCreateRequest request) {
        return postCreateService.create(request);
    }
}
