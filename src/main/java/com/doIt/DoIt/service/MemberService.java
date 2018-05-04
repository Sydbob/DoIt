package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.MemberRepository;
import com.doIt.DoIt.dao.RoleRepository;
import com.doIt.DoIt.entity.Member;
import com.doIt.DoIt.entity.MemberDTO;
import com.doIt.DoIt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Service class for member entity
 * This class has all the methods involving members
 */
@Service("memberService")
@Transactional
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RoleRepository roleRepository;

    //password encoder used for password security
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member findUserByUsername(String username){
        return memberRepository.findByUsername(username);
    }

    public ArrayList<Member> getTeamMembersByTeamID(int teamid){
        ArrayList<Member> teammembers = new ArrayList<>();
        for (Member m : memberRepository.findAll()){
            if (m.getTeamID() == teamid)
            {
                teammembers.add(m);
            }
        }
        return teammembers;
    }

    public ArrayList<Member> getAllMembers(){
        ArrayList<Member> teammembers = new ArrayList<>();
        for (Member m : memberRepository.findAll()){
            teammembers.add(m);
        }
        return teammembers;
    }

    public boolean isAdmin(String username) {
        boolean isAdmin= false;
        for (Role r : memberRepository.findByUsername(username).getRoles()){
            if (r.getRoleID() == 1){
                isAdmin = true;
                break;
            }
        }
        return isAdmin;
    }

    public Member createUser(MemberDTO memberDTO, BindingResult bindingResult){
        Member member = new Member();
        member.setEmail(memberDTO.getEmail());
        member.setName(memberDTO.getName());
        member.setRoles(memberDTO.getRoles());
        member.setTeamID(memberDTO.getTeamID());
        member.setTelephone(memberDTO.getTelephone());
        member.setUsername(memberDTO.getUsername());
        member.setPassword(memberDTO.getPassword());
        return memberRepository.save(member);
    }

    public Member findUserByName(String name){
        return memberRepository.findByName(name);
    }

    public int findTeamIDByUsername(String username){
       return memberRepository.findByUsername(username).getTeamID();

    }

    public void saveMemeber(Member member){
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }
}
