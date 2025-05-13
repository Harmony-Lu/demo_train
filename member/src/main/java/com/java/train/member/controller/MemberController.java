package com.java.train.member.controller;

import com.java.train.common.resp.CommonResp;
import com.java.train.member.req.MemberLoginReq;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.req.MemberSendCodeReq;
import com.java.train.member.resp.MemberLoginResp;
import com.java.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp count(){
        int count = memberService.count();
//        CommonResp<Integer> resp = new CommonResp<>();
//        resp.setContent(count);
//        return resp;
        // 简化方式
        return new CommonResp<>(count);
    }
    // 注册接口
    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    // 发送验证码的接口
    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    // 登录的接口
    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req){
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }



}
