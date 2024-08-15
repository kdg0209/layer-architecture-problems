package com.example.layer_architecture_problems.domian.member.controller;

import com.example.layer_architecture_problems.domian.member.dto.request.MemberCreateRequest;
import com.example.layer_architecture_problems.domian.member.dto.response.MemberCreateResponse;
import com.example.layer_architecture_problems.domian.member.service.MemberCreateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCreateService memberCreateService;

    @PostMapping
    public MemberCreateResponse create(@Valid @RequestBody MemberCreateRequest request) {
        return memberCreateService.create(request.toCommand());
    }
}
