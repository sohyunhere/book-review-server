package com.example.bookreviewserver.repo;

import com.example.bookreviewserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberEmail(String memberEmail);
    Optional<Member> findByMemberId(Long memberId);
}
