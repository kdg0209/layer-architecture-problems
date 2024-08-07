package com.example.layer_architecture_problems.domian.member.domian;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    void 정상적인_값을_사용하여_사용자를_생성할_수_있다() {

        // given
        String memberId = "yuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadas";
        String password = "123456";
        String name = "KDG";
        String email = "test@naver.com";
        String phone = "010-1234-5678";

        // when
        Member result = Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build();

        // then
        assertThat(result.getMemberId()).isEqualTo(memberId);
        assertThat(result.getPassword()).isEqualTo(password);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "yuiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasd"})
    void 사용자_아이디가_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String memberId) {

        // given
        String password = "123456";
        String name = "KDG";
        String email = "test@naver.com";
        String phone = "010-1234-5678";

        // when && then
        assertThatThrownBy(() -> Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void 사용자_비밀번호가_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String password) {

        // given
        String memberId = "test01";
        String name = "KDG";
        String email = "test@naver.com";
        String phone = "010-1234-5678";

        // when && then
        assertThatThrownBy(() -> Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void 사용자_이름이_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String name) {

        // given
        String memberId = "test01";
        String password = "uiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasd";
        String email = "test@naver.com";
        String phone = "010-1234-5678";

        // when && then
        assertThatThrownBy(() -> Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "testnaver.com", "test@navercom"})
    void 사용자_이메일이_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String email) {

        // given
        String memberId = "test01";
        String password = "uiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasd";
        String name = "홍길동";
        String phone = "010-1234-5678";

        // when && then
        assertThatThrownBy(() -> Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n", "010-111-222", "010-1234-56789", "010-1234-789"})
    void 사용자_연락처가_옯바르지_않은_값을_사용하여_생성하면_예외가_발생한다(String phone) {

        // given
        String memberId = "test01";
        String password = "uiopasdfghjkllzxcvbnmqwertyuiopasdfghjklzdasdadasd";
        String name = "홍길동";
        String email = "test@naver.com";

        // when && then
        assertThatThrownBy(() -> Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .email(email)
                .phone(phone)
                .build())
                .isInstanceOf(IllegalArgumentException.class);
    }
}