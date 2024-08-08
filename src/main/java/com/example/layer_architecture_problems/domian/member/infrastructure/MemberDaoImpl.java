package com.example.layer_architecture_problems.domian.member.infrastructure;

import com.example.layer_architecture_problems.domian.member.dao.MemberDao;
import com.example.layer_architecture_problems.domian.member.domian.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Optional;

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

    @Override
    public Optional<Member> findById(Long id) {
        return this.repository.findById(id);
    }

    private BooleanExpression eqMemberId(String memberId) {
        if (!StringUtils.hasText(memberId)) {
            return null;
        }

        return member.memberId.eq(memberId);
    }
}
