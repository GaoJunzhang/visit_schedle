package com.zgj.sc.scheduled.controller;

import com.alibaba.fastjson.JSONObject;
import com.zgj.sc.scheduled.service.PostVisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpringRestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PostVisitRecordService postVisitRecordService;
    /***********HTTP GET method*************/
    @GetMapping("/testGetApi")
    public String getJson(){
        String url="http://localhost:8089/o2o/getshopbyid?shopId=19";
        //String json =restTemplate.getForObject(url,Object.class);
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        return json;
    }

    /**********HTTP POST method**************/
    @PostMapping(value = "/testPost")
    public Object postJson(@RequestBody JSONObject param) {
        System.out.println(param.toJSONString());
        param.put("action", "post");
        param.put("username", "tester");
        param.put("pwd", "123456748");
        return param;
    }

    @PostMapping(value = "/testPostApi")
    public Object testPost() {
        String url = "http://localhost:8081/girl/testPost";
        JSONObject postData = new JSONObject();
        postData.put("descp", "request for post");
        JSONObject json = restTemplate.postForEntity(url, postData, JSONObject.class).getBody();
        return json;
    }

//    @PostMapping(value = "/randomProbe")
//    public JSONObject randomProbe(){
//        return postVisitRecordService.postVisitorRecord();
//    }
//
//    @PostMapping(value = "/randomCamera")
//    public JSONObject randomCamera() {
//        return postVisitRecordService.postVisitorMember();
//    }

}
