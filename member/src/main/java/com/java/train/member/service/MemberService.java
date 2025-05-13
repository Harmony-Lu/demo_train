package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.java.train.common.exception.BusinessException;
import com.java.train.common.exception.BusinessExceptionEnum;
import com.java.train.common.util.SnowUtil;
import com.java.train.member.domain.Member;
import com.java.train.member.domain.MemberExample;
import com.java.train.member.mapper.MemberMapper;
import com.java.train.member.req.MemberLoginReq;
import com.java.train.member.req.MemberRegisterReq;
import com.java.train.member.req.MemberSendCodeReq;
import com.java.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if( ObjectUtil.isNotNull(memberDB) ){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        // 雪花算法生成member的ID:全局唯一，有序增长，生成效率高
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req){
        LOG.info("MemberService.sendCode");
        String mobile = req.getMobile();
        LOG.info("手机号：{}", mobile);
        // 1、检查当前手机号是否注册过
        Member memberDB = selectByMobile(mobile);
        // 如果手机号不存在，则插入一条记录
        if( ObjectUtil.isNull(memberDB) ){
            LOG.info("手机号不存在, 插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }else{
            LOG.info("手机号已经存在, 不插入记录");
        }
        // 2、生成验证码
//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成验证码：{}", code);
        // 3、保存到短信记录表：手机号，短信验证码，有效期，是否已使用，发送时间，使用时间
        LOG.info("保存短信验证码到短信记录表");
        // 4、对接短信发送通道，发送短信
        LOG.info("对接短信发送通道");
    }

    public MemberLoginResp login(MemberLoginReq req){
        LOG.info("MemberService.login");
        String mobile = req.getMobile();
        String code = req.getCode();
        LOG.info("手机号：{}", mobile);
        // 1、检查当前手机号是否注册过
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，抛出异常
        if( ObjectUtil.isNull(memberDB) ){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 2、校验短信验证码
        if( "8888".equals(code)){
            // 验证码一致
            LOG.info("验证码一致");
        }else {
            //验证码错误
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        // 3、返回memberDB的值
        return BeanUtil.copyProperties(memberDB, MemberLoginResp.class);

        
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list= memberMapper.selectByExample(memberExample);
        if( CollUtil.isEmpty(list) ){
            return null;
        }else{
            return list.get(0);
        }
    }
}
