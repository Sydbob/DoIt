package com.doIt.DoIt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.doIt.DoIt.entity.Member;
import com.doIt.DoIt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /*
    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@Valid Member member, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = null;
        Member memberExists = memberService.findUserByUsername(member.getUsername());
        if (null != memberExists) {
            mav = new ModelAndView("tasks");
            mav.addObject("member", member);
        } else {
            mav = new ModelAndView("login");
            mav.addObject("message", "Username or Password is wrong!!");
        }
        return mav;
    }*/

    @RequestMapping( value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        Member member = new Member();
        modelAndView.addObject("member",member);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method =RequestMethod.POST)
    public ModelAndView createNewMember(@Valid Member member, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        Member memberExists = memberService.findUserByUsername(member.getUsername());
        if (memberExists != null){
            bindingResult
                    .rejectValue("username", "error.member", "User with such username already exists");
        }

        if (bindingResult.hasErrors()){
            modelAndView.setViewName("registration");
        }
        else{
            memberService.saveMemeber(member);
            modelAndView.addObject("successMessage", "Member has been successfully registered");
            modelAndView.addObject("member", new Member());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberService.findUserByUsername(auth.getName());
        modelAndView.setViewName("tasks");
        return modelAndView;
    }
}
