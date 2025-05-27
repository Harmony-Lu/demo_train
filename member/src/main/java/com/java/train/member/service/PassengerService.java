package com.java.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.train.common.context.LoginMemberContext;
import com.java.train.common.resp.PageResp;
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
        if (ObjectUtil.isNull(passenger.getId())) { // 新增乘车人
            // 2、获取当前登录会员的id，并设置为乘车人的memberId字段值
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            // 3、把乘车人插入数据库表
            passengerMapper.insert(passenger);
        } else { // 编辑乘车人信息：更新当前乘车人的信息
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }

    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull((req.getMemberId()))) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        LOG.info("");
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample((passengerExample));
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengerList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengerList, PassengerQueryResp.class);
        PageResp<PassengerQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
