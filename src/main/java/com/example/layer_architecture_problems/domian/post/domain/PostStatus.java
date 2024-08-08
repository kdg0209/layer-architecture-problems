package com.example.layer_architecture_problems.domian.post.domain;

import java.util.Arrays;

public enum PostStatus {

    ACTIVE("활성화"),
    IN_ACTIVE("비활성화");

    private final String description;

    PostStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PostStatus findBy(String status) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
