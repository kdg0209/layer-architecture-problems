package com.example.layer_architecture_problems.domian.member.dto.request;

import com.example.layer_architecture_problems.domian.member.service.command.MemberCreateCommand;
import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
        @NotBlank String memberId,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String phone
) {

    public MemberCreateCommand toCommand() {
        return new MemberCreateCommand(memberId, password, name, email, phone);
    }
}
