package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.java.train.common.util.SnowUtil;
import com.java.train.member.domain.Passenger;
import com.java.train.member.mapper.PassengerMapper;
import com.java.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {
    private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    public void save(PassengerSaveReq passengerSaveReq) {
        DateTime now = DateTime.now();
        // 1、从请求中接受乘车人信息：实体拷贝
        Passenger passenger = BeanUtil.copyProperties(passengerSaveReq, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        // 2、把乘车人插入数据库表
        passengerMapper.insert(passenger);
    }
}
