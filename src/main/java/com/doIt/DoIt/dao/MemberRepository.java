package com.doIt.DoIt.dao;

import com.doIt.DoIt.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
    Member findByRole(String role);

}
