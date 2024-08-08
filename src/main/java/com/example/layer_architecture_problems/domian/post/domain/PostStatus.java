package com.example.layer_architecture_problems.domian.post.domain;

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
}
