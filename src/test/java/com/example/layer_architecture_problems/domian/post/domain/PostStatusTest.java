package com.example.layer_architecture_problems.domian.post.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostStatusTest {

    @Test
    void 문자열로_되어_있는_상태값을_ENUM_값으로_대체할_수_있다() {

        // given
        String status = "ACTIVE";

        // when
        PostStatus result = PostStatus.findBy(status);

        // then
        assertThat(result).isEqualTo(PostStatus.ACTIVE);
    }

    @Test
    void 잘못된_문자열로_상태값을_조회하는_경우_예외가_발생한다() {

        // given
        String status = "UNKNOWN";

        // when && then
        assertThatThrownBy(() -> PostStatus.findBy(status))
                .isInstanceOf(IllegalArgumentException.class);
    }
}