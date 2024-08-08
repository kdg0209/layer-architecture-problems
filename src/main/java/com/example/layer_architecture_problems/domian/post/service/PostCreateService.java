package com.example.layer_architecture_problems.domian.post.service;

import com.example.layer_architecture_problems.domian.member.dao.MemberDao;
import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import com.example.layer_architecture_problems.domian.post.domain.Post;
import com.example.layer_architecture_problems.domian.post.dto.request.PostCreateRequest;
import com.example.layer_architecture_problems.domian.post.dto.response.PostCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCreateService {

    private final MemberDao memberDao;
    private final PostDao postDao;

    public PostCreateResponse create(Long memberId, PostCreateRequest request) {
        var member = memberDao.findById(memberId)
                .orElseThrow(IllegalStateException::new);

        var post = Post.builder()
                .title(request.title())
                .contents(request.contents())
                .member(member)
                .build();

        postDao.save(post);

        return new PostCreateResponse(post.getId());
    }
}
