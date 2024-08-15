package com.example.layer_architecture_problems.domian.post.service;

import com.example.layer_architecture_problems.domian.member.dao.MemberDao;
import com.example.layer_architecture_problems.domian.post.dao.PostDao;
import com.example.layer_architecture_problems.domian.post.dto.request.PostCreateRequest;
import com.example.layer_architecture_problems.domian.post.testfixture.PostFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostCreateServiceTest {

    @Mock
    private MemberDao memberDao;

    @Mock
    private PostDao postDao;

    @InjectMocks
    private PostCreateService postCreateService;

    @Test
    void 유효하지_않은_사용자가_글을_등록하려는_경우_예외가_발생한다() {

        // given
        PostCreateRequest request = new PostCreateRequest(1L, "title", "contents");

        // mocking
        when(memberDao.findById(anyLong())).thenThrow(IllegalArgumentException.class);

        // when
        assertThatThrownBy(() -> postCreateService.create(request))
                .isInstanceOf(IllegalArgumentException.class);

        // then
        verify(postDao, never()).save(any());
    }

    @Test
    void 유효한_사용자는_정상적으로_글을_등록할_수_있다() {

        // given
        PostCreateRequest request = new PostCreateRequest(1L, "title", "contents");
        Long memberId = 1L;

        // mocking
        when(memberDao.findById(memberId)).thenReturn(Optional.of(PostFixture.createMember()));

        // when
        postCreateService.create(request);

        // then
        verify(memberDao, times(1)).findById(anyLong());
        verify(postDao, times(1)).save(any());
    }
}