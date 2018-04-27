package com.doIt.DoIt.dao;

import com.doIt.DoIt.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);
    //Member findByRole(String role);
    Member findByName(String name);

}
