package com.example.layer_architecture_problems.domian.member.repository;

import com.example.layer_architecture_problems.domian.member.domian.Member;
import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<Member, Long> {
}
