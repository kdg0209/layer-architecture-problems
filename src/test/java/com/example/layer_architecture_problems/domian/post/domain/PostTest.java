package com.example.layer_architecture_problems.domian.post.domain;

import com.example.layer_architecture_problems.domian.member.domian.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostTest {

    @Test
    void 정상적인_값을_사용하여_글을_등록화면_글의_상태는_활성화_상태이다() {

        // given
        String title = "제목입당";
        String contents = "내용입니당";
        Member member = createMember();

        // when
        Post result = Post.builder()
                .title(title)
                .contents(contents)
                .member(member)
                .build();

        // then
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getContents()).isEqualTo(contents);
        assertThat(result.getStatus()).isEqualTo(PostStatus.ACTIVE);
        assertThat(result.getMember()).isEqualTo(member);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "yuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasdyuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasd"})
    void 게시글의_제목에_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String title) {

        // given
        String contents = "내용입니당";
        Member member = createMember();

        // when && then
        assertThatThrownBy(() -> Post.builder()
                .title(title)
                .contents(contents)
                .member(member)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void 게시글의_내용에_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String contents) {

        // given
        String title = "제목입당";
        Member member = createMember();

        // when && then
        assertThatThrownBy(() -> Post.builder()
                .title(title)
                .contents(contents)
                .member(member)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비정상적인_멤버_객체를_사용하여_글을_생성하는_경우_예외가_발생한다() {

        // given
        String title = "제목입당";
        String contents = "내용입니당";
        Member member = null;

        // when && then
        assertThatThrownBy(() -> Post.builder()
                .title(title)
                .contents(contents)
                .member(member)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Member createMember() {
        return Member.builder()
                .memberId("yuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadas")
                .password("123456")
                .name("KDG")
                .email("test@naver.com")
                .phone("010-1234-5678")
                .build();
    }
}