package com.example.layer_architecture_problems.domian.post.infrastructure;

import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import com.example.layer_architecture_problems.domian.post.domain.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class PostDaoImpl implements PostDao {

    private final JPAQueryFactory queryFactory;
    private final PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
