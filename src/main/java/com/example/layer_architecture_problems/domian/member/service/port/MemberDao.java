package com.example.layer_architecture_problems.domian.member.service.port;

import com.example.layer_architecture_problems.domian.member.domian.Member;

public interface MemberDao {

    boolean isDuplicatedMemberId(String memberId);
    Member save(Member member);
}
