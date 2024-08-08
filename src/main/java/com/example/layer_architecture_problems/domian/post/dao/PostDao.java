package com.example.layer_architecture_problems.domian.post.dao;

import com.example.layer_architecture_problems.domian.post.domain.Post;

import java.util.Optional;

public interface PostDao {

    Optional<Post> findByWriter(Long memberId, Long postId);

    Post save(Post post);
}
