//package com.java.train.batch.job;
//
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///*
//    适合单批应用，不适合集群
//    没法实施更改定时任务的状态和策略
// */
//@Component
//@EnableScheduling
//public class SpringBootTestJob {
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    private void test(){
//        System.out.println("SpringBootTestJob");
//    }
//}
