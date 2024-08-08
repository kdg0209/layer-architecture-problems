package com.example.layer_architecture_problems.domian.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(
        @NotBlank String title,
        @NotBlank String contents) {
}
