package com.example.layer_architecture_problems.domian.member.service;

import com.example.layer_architecture_problems.domian.member.service.port.MemberDao;
import com.example.layer_architecture_problems.domian.member.domian.Member;
import com.example.layer_architecture_problems.domian.member.dto.MemberCreateRequest;
import com.example.layer_architecture_problems.domian.member.dto.MemberCreateResponse;
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

    public MemberCreateResponse create(MemberCreateRequest request) {
        var duplicatedMemberId = memberDao.isDuplicatedMemberId(request.memberId());

        if (duplicatedMemberId) {
            throw new IllegalArgumentException("이미 등록된 아이디입니다.");
        }

        var member = Member.builder()
                .memberId(request.memberId())
                .password(request.password())
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();

        memberDao.save(member);
        mailerService.send(member.getEmail());
        alimTalkService.send(member.getPhone());

        return new MemberCreateResponse(member.getId());
    }
}
