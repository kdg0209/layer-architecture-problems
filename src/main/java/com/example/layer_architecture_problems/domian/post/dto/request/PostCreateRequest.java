package com.example.layer_architecture_problems.domian.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(
        @NotNull Long memberId,
        @NotBlank String title,
        @NotBlank String contents) {
}
