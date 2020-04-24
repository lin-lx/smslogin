package com.example.smslogin.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;
import java.util.Random;

public class TestController {

    public static void main(String[] args) throws HTTPException, IOException {
        sendSms();
    }

    private static void sendSms() throws HTTPException, IOException {
        //手机
        String phoneNumber="18850969548";
        //短信内容
        int templateId=200461;
        //验证码
        String[] params=new String[1];
        String code="";
        Random random=new Random();
        for(int i=0;i<6;i++){
            code+=random.nextInt(10);
        }
        params[0]=code;
        //短信签名
        String sign="tLain公众号";
        //发送短信核心类
        SmsSingleSender smsSingleSender=new SmsSingleSender(1400142856,"21306d751cfdf61ed433e506da242522");
        //发送短信
        SmsSingleSenderResult result=smsSingleSender.sendWithParam("86",phoneNumber,templateId,params,sign,"","");
        System.out.println(result);

    }

}
