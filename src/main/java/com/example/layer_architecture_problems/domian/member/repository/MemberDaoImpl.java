package com.example.layer_architecture_problems.domian.member.repository;

import com.example.layer_architecture_problems.domian.member.service.port.MemberDao;
import com.example.layer_architecture_problems.domian.member.domian.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.example.layer_architecture_problems.domian.member.domian.QMember.member;

@Repository
@RequiredArgsConstructor
class MemberDaoImpl implements MemberDao {

    private final JPAQueryFactory queryFactory;
    private final MemberRepository repository;

    @Override
    public boolean isDuplicatedMemberId(String memberId) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(eqMemberId(memberId))
                .fetchFirst();

        return result != null;
    }

    @Override
    public Member save(Member member) {
        return this.repository.save(member);
    }

    private BooleanExpression eqMemberId(String memberId) {
        if (!StringUtils.hasText(memberId)) {
            return null;
        }

        return member.memberId.eq(memberId);
    }
}
