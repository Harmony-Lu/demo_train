package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.util.SnowUtil;
import com.java.train.member.domain.Passenger;
import com.java.train.member.domain.PassengerExample;
import com.java.train.member.mapper.PassengerMapper;
import com.java.train.member.req.PassengerQueryReq;
import com.java.train.member.req.PassengerSaveReq;
import com.java.train.member.resp.PassengerQueryResp;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq passengerSaveReq) {
        DateTime now = DateTime.now();
        // 1、从请求中接受乘车人信息：实体拷贝
        Passenger passenger = BeanUtil.copyProperties(passengerSaveReq, Passenger.class);
        // 2、获取当前登录会员的id，并设置为乘车人的memberId字段值
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        // 3、把乘车人插入数据库表
        passengerMapper.insert(passenger);
    }

    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if(ObjectUtil.isNotNull((req.getMemberId()))){
            criteria.andMemberIdEqualTo(req.getMemberId());
        }

        List<Passenger> passengerList = passengerMapper.selectByExample((passengerExample));
        return BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
    }
}
