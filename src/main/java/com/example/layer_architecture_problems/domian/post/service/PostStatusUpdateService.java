package com.example.layer_architecture_problems.domian.post.service;

import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostStatusUpdateService {

    private final PostDao postDao;

    public void statusUpdate(Long memberId, Long postId, String status) {
        var post = postDao.findByWriter(memberId, postId)
                .orElseThrow(IllegalStateException::new);

        post.updateStatus(status);
    }
}
