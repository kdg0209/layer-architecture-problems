package com.example.layer_architecture_problems.domian.member.service;

import com.example.layer_architecture_problems.domian.member.service.port.MemberDao;
import com.example.layer_architecture_problems.domian.member.dto.MemberCreateRequest;
import com.example.layer_architecture_problems.domian.member.service.port.AlimTalkService;
import com.example.layer_architecture_problems.domian.member.service.port.MailerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberCreateServiceTest {

    @Mock
    private MemberDao memberDao;

    @Mock
    private MailerService mailerService;

    @Mock
    private AlimTalkService alimTalkService;

    @InjectMocks
    private MemberCreateService memberCreateService;

    @Test
    void 중복된_사용자_아이디가_없는_경우_정상적으로_사용자를_등록할_수_있다() {

        // given
        MemberCreateRequest request = new MemberCreateRequest("test01", "123456", "홍길동", "test@naver.com", "010-1234-5678");

        // mocking
        when(memberDao.isDuplicatedMemberId(anyString())).thenReturn(false);

        // when
        memberCreateService.create(request);

        // then
        verify(memberDao, times(1)).save(any());
        verify(mailerService, times(1)).send(anyString());
        verify(alimTalkService, times(1)).send(anyString());
    }

    @Test
    void 이미_사용자_아이디가_등록되어_있는_경우_예외가_발생한다() {

        // given
        MemberCreateRequest request = new MemberCreateRequest("test01", "123456", "홍길동", "test@naver.com", "010-1234-5678");

        // mocking
        when(memberDao.isDuplicatedMemberId(anyString())).thenReturn(true);

        // when && then
        assertThatThrownBy(() -> memberCreateService.create(request))
                .isInstanceOf(IllegalArgumentException.class);

        verify(memberDao, never()).save(any());
        verify(mailerService, never()).send(anyString());
        verify(alimTalkService, never()).send(anyString());
    }

}