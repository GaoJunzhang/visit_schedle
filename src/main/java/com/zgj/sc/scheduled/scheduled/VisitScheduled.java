package com.zgj.sc.scheduled.scheduled;

import com.zgj.sc.scheduled.service.PostVisitRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class VisitScheduled {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostVisitRecordService postVisitRecordService;

    @Scheduled(cron = "0/5 * * * * ?")
    private void virtualVisitSend(){
        logger.info("start visitScheduled");
        System.out.println("=====mock postVistRecord start==========");
//        System.out.println(postVisitRecordService.postVisitorRecord("DA8E3B85E254"));
        System.out.println("=====mock postVistRecord end==========");
        System.out.println("=====mock postVistRecord start==========");
//        System.out.println(postVisitRecordService.postVisitorMember("EC55D68038F0"));
        System.out.println("=====mock postVistRecord end==========");
        logger.info("end visitScheduled");

    }
}
