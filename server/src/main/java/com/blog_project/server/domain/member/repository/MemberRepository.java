package com.blog_project.server.domain.member.repository;


import com.blog_project.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findbyEmail(String email);
}
