package com.example.layer_architecture_problems.domian.post.testfixture;

import com.example.layer_architecture_problems.domian.member.domian.Member;
import com.example.layer_architecture_problems.domian.post.domain.Post;

public class PostFixture {

    public static Member createMember() {
        return Member.builder()
                .memberId("yuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadas")
                .password("123456")
                .name("KDG")
                .email("test@naver.com")
                .phone("010-1234-5678")
                .build();
    }

    public static Post createPost() {
        return Post.builder()
                .title("title")
                .contents("contents")
                .member(createMember())
                .build();
    }
}
