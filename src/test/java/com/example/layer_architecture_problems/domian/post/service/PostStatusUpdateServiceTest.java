package com.example.layer_architecture_problems.domian.post.service;

import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import com.example.layer_architecture_problems.domian.post.testfixture.PostFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostStatusUpdateServiceTest {

    @Mock
    private PostDao postDao;

    @InjectMocks
    private PostStatusUpdateService postStatusUpdateService;

    @Test
    void 올바른_값을_사용하여_글의_상태를_변경할_수_있다() {

        // given
        Long memberId = 1L;
        Long postId = 1L;
        String status = "ACTIVE";

        // mocking
        when(postDao.findByWriter(memberId, postId)).thenReturn(Optional.of(PostFixture.createPost()));

        // when
        postStatusUpdateService.statusUpdate(memberId, postId, status);

        // then
    }

    @Test
    void 글의_작성자가_올바르지_않은_경우_상태를_변경하고자_한다면_예외가_발생한다() {

        // given
        Long memberId = null;
        Long postId = 1L;
        String status = "ACTIVE";

        // mocking
        when(postDao.findByWriter(memberId, postId)).thenThrow(IllegalStateException.class);

        // when && then
        assertThatThrownBy(() -> postStatusUpdateService.statusUpdate(memberId, postId, status))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 글의_상태를_문서에_없는_상태로_변경하고자_한다면_예외가_발생한다() {

        // given
        Long memberId = 1L;
        Long postId = 1L;
        String status = "UNKNOWN";

        // mocking
        when(postDao.findByWriter(memberId, postId)).thenReturn(Optional.of(PostFixture.createPost()));

        // when && then
        assertThatThrownBy(() -> postStatusUpdateService.statusUpdate(memberId, postId, status))
                .isInstanceOf(IllegalArgumentException.class);
    }
}