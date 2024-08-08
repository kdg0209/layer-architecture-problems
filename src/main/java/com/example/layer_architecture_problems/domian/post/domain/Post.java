package com.example.layer_architecture_problems.domian.post.domain;

import com.example.layer_architecture_problems.domian.member.domian.Member;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Post {

    @Id
    @Tsid
    @Comment(value = "id")
    private Long id;

    @Comment(value = "게시글 제목")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Comment(value = "게시글 내용")
    @Column(name = "contents", nullable = false)
    private String contents;

    @Comment(value = "게시글 상태")
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PostStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String title, String contents, Member member) {
        verifyTitle(title);
        verifyContents(contents);
        verifyMember(member);
        this.title = title;
        this.contents = contents;
        this.status = PostStatus.ACTIVE;
        this.member = member;
    }

    private void verifyTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("제목은 공백일 수 없습니다.");
        }

        if (title.length() > 100) {
            throw new IllegalArgumentException("제목의 최대 길이는 100자를 넘을 수 없습니다.");
        }
    }

    private void verifyContents(String contents) {
        if (!StringUtils.hasText(contents)) {
            throw new IllegalArgumentException("내용은 공백일 수 없습니다.");
        }
    }

    private void verifyMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("정상적이지 않은 회원입니다.");
        }
    }
}
