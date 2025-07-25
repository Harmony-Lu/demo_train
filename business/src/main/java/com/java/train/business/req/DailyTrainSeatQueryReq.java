package com.java.train.business.req;

import com.java.train.common.req.PageReq;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DailyTrainSeatQueryReq extends PageReq {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String trainCode;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    @Override
    public String toString() {
        return "DailyTrainSeatQueryReq{" +
                "date=" + date +
                ", trainCode='" + trainCode + '\'' +
                "} " + super.toString();
    }
}
