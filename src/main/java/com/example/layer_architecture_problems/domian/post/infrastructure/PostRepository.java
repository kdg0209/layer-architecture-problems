package com.example.layer_architecture_problems.domian.post.infrastructure;

import com.example.layer_architecture_problems.domian.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

interface PostRepository extends JpaRepository<Post, Long> {
}
