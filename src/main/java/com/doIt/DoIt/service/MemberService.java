package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.MemberRepository;
import com.doIt.DoIt.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("memberService")
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member findUserByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public Member findUserByRole(String role){
        return memberRepository.findByRole(role);
    }

    public void saveMemeber(Member member){
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
}
