package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.MemberRepository;
import com.doIt.DoIt.dao.RoleRepository;
import com.doIt.DoIt.entity.Member;
import com.doIt.DoIt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Service("memberService")
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member findUserByUsername(String username){
        return memberRepository.findByUsername(username);
    }

    public Member findUserByName(String name){
        return memberRepository.findByName(name);
    }


    public void saveMemeber(Member member){
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        //Role memberRole = roleRepository.findByRole("admin");
        //member.setRoles(new HashSet<Role>(Arrays.asList(memberRole)));
        memberRepository.save(member);
    }
}
