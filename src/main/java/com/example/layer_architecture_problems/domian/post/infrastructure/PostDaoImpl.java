package com.example.layer_architecture_problems.domian.post.infrastructure;

import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import com.example.layer_architecture_problems.domian.post.domain.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.layer_architecture_problems.domian.post.domain.QPost.post;

@Repository
@RequiredArgsConstructor
class PostDaoImpl implements PostDao {

    private final JPAQueryFactory queryFactory;
    private final PostRepository postRepository;

    @Override
    public Optional<Post> findByWriter(Long memberId, Long postId) {
        Post result = queryFactory
                .selectFrom(post)
                .where(
                        eqPostId(postId),
                        eqMemberId(memberId)
                )
                .fetchFirst();

        return Optional.ofNullable(result);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    private BooleanExpression eqMemberId(Long memberId) {
        if (memberId == null) {
            return null;
        }
        return post.member.id.eq(memberId);
    }

    private BooleanExpression eqPostId(Long postId) {
        if (postId == null) {
            return null;
        }
        return post.id.eq(postId);
    }
}
