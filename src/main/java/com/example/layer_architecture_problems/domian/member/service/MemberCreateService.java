package com.example.layer_architecture_problems.domian.member.service;

import com.example.layer_architecture_problems.domian.member.dao.MemberDao;
import com.example.layer_architecture_problems.domian.member.domian.Member;
import com.example.layer_architecture_problems.domian.member.dto.request.MemberCreateRequest;
import com.example.layer_architecture_problems.domian.member.dto.response.MemberCreateResponse;
import com.example.layer_architecture_problems.domian.member.service.command.MemberCreateCommand;
import com.example.layer_architecture_problems.domian.member.service.port.AlimTalkService;
import com.example.layer_architecture_problems.domian.member.service.port.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCreateService {

    private final MemberDao memberDao;
    private final MailerService mailerService;
    private final AlimTalkService alimTalkService;

    public MemberCreateResponse create(MemberCreateCommand command) {
        var duplicatedMemberId = memberDao.isDuplicatedMemberId(command.memberId());

        if (duplicatedMemberId) {
            throw new IllegalArgumentException("이미 등록된 아이디입니다.");
        }

        var member = Member.builder()
                .memberId(command.memberId())
                .password(command.password())
                .name(command.name())
                .email(command.email())
                .phone(command.phone())
                .build();

        memberDao.save(member);
        mailerService.send(member.getEmail());
        alimTalkService.send(member.getPhone());

        return new MemberCreateResponse(member.getId());
    }
}
