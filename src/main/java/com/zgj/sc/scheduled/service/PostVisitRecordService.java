package com.zgj.sc.scheduled.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgj.sc.scheduled.common.Constant;
import com.zgj.sc.scheduled.common.VTools;
import com.zgj.sc.scheduled.common.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Service
public class PostVisitRecordService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${visit.host}")
    private String hostUrl;

    public JSONObject postVisitorRecord(String mac) {
        String url = hostUrl + Constant.HOST_VIST + Constant.PROBE_HTTP;
        JSONObject postData = new JSONObject();
        int count = (int) (1 + Math.random() * (10 - 1 + 1));
        JSONArray array = new JSONArray(count);
        for (int i = 0; i < count; i++) {
            JSONObject obj = new JSONObject();
            int randomMin = (int) (1 + Math.random() * 100);
            long curSysTime = System.currentTimeMillis();
            long deadtime = curSysTime + randomMin * 60 * 60 * 1000L;
            obj.put("mac", VTools.randomMac4Qemu());
            obj.put("db", (int) (1 + Math.random() * 100) * -1);
            obj.put("begin", VTools.dateToStr(new Date(curSysTime)));
            obj.put("end", VTools.dateToStr(new Date(deadtime)));
            array.add(obj);
        }
        postData.put("time", VeDate.getStringDate());
        postData.put("macs", array);
        JSONObject object = new JSONObject();
        object.put("list", postData);
        if (StringUtils.isEmpty(mac)){

            object.put("mac", VTools.randomMac4Qemu());
        }else {
            object.put("mac", mac);
        }
        JSONObject json = restTemplate.postForEntity(url, object, JSONObject.class).getBody();
        return json;
    }

    public JSONObject postVisitorMember(String mac) {
        String url = hostUrl + Constant.HOST_VIST + Constant.CAMERA_HTTP;
        JSONObject postData = new JSONObject();
        int count = (int) (1 + Math.random() * (10 - 1 + 1));
        JSONArray array = new JSONArray(count);
        for (int i = 0; i < count; i++) {
            JSONObject obj = new JSONObject();
            obj.put("stamp",VeDate.getStringDate());
            obj.put("age",(int)(1 + Math.random() * 100));
            obj.put("gender",(int)(1 + Math.random() * 100));
            obj.put("beauty",(int)(1 + Math.random() * 100));
            obj.put("stay",(int)(1 + Math.random() * 10));
            obj.put("id",(int)(1 + Math.random() * 10));
            array.add(obj);
        }
        JSONObject object = new JSONObject();
        object.put("list", array);
        if (StringUtils.isEmpty(mac)){

            object.put("mac", VTools.randomMac4Qemu());
        }else {
            object.put("mac", mac);
        }
        JSONObject json = restTemplate.postForEntity(url, object, JSONObject.class).getBody();
        return json;
    }
}
