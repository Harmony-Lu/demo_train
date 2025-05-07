package com.java.train.member.controller;

import com.java.train.common.resp.CommonResp;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResp register(MemberRegisterReq req){
        long register = memberService.register(req);
        CommonResp<Long> resp = new CommonResp<>();
        resp.setContent(register);
        return resp;
    }

}
