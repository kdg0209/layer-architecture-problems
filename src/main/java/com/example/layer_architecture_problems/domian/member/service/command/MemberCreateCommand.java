package com.example.layer_architecture_problems.domian.member.service.command;

public record MemberCreateCommand(
        String memberId,
        String password,
        String name,
        String email,
        String phone
) {
}
