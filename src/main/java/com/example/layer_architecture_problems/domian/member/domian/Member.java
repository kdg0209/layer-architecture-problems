package com.example.layer_architecture_problems.domian.member.domian;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private static final String PHONE_REGEX = "^\\d{3}-\\d{3,4}-\\d{4}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    @Tsid
    @Comment(value = "id")
    private Long id;

    @Comment(value = "사용자 아이디")
    @Column(name = "member_id", length = 50, nullable = false, updatable = false)
    private String memberId;

    @Comment(value = "사용자 비밀번호")
    @Column(name = "password", nullable = false)
    private String password;

    @Comment(value = "사용자 이름")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Comment(value = "사용자 이메일")
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Comment(value = "사용자 연락처")
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;

    @Builder
    public Member(String memberId, String password, String name, String email, String phone) {
        verifyMemberId(memberId);
        verifyPassword(password);
        verifyName(name);
        verifyEmail(email);
        verifyPhone(phone);
        this.memberId = memberId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    private void verifyMemberId(String memberId) {
        if (!StringUtils.hasText(memberId)) {
            throw new IllegalArgumentException("아이디는 공백일 수 없습니다.");
        }

        if (memberId.length() > 50) {
            throw new IllegalArgumentException("아이디의 최대 길이는 50자를 넘을 수 없습니다.");
        }
    }

    private void verifyPassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("비밀번호는 공백일 수 없습니다.");
        }
    }

    private void verifyName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("이름은 공백일 수 없습니다.");
        }
    }

    private void verifyEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("이메일은 공백일 수 없습니다.");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("옳바른 이메일 형식이 아닙니다.");
        }
    }

    private void verifyPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            throw new IllegalArgumentException("연락처는 공백일 수 없습니다.");
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("옳바른 연락처 형식이 아닙니다.");
        }
    }
}
