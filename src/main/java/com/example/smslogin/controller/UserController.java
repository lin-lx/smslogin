package com.example.smslogin.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private HttpSession httpSession;

    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "index.html";
    }

    /**
     * 进入注册页面
     * @return
     */
    @RequestMapping("/registered")
    public String register() {
        return "registered.html";
    }


    /**
     * 发送短信
     */
    private String sendSms(String phone) throws HTTPException, IOException {
        //手机
        String phoneNumber=phone;
        //短信内容
        int templateId=200461;
        //生成随机验证码
        String[] params=new String[1];
        String code="";
        Random random=new Random();
        for(int i=0;i<6;i++){
            code+=random.nextInt(10);
        }
        //将code放入session中
        httpSession.setAttribute("sms",code);
        System.out.println(code);
        params[0]=code;
        //短信签名
        String sign="tLain公众号";
        //发送短信核心类
        SmsSingleSender smsSingleSender=new SmsSingleSender(1400142856,"21306d751cfdf61ed433e506da242522");
        //发送短信
        SmsSingleSenderResult result=smsSingleSender.sendWithParam("86",phoneNumber,templateId,params,sign,"","");
        System.out.println(result);
        return code;

    }

    /**
     * 手机验证码
     * @param phone
     * @return
     * @throws HTTPException
     * @throws IOException
     */
    @RequestMapping("/sms")
    @ResponseBody
    public String smsCode(String phone) throws HTTPException, IOException {

        //发送短息
        String sms=sendSms(phone);
        String json=null;
        json="{\"message\":"+true+",\"sms\":\""+sms+"\"}";

        System.out.println(json);
        return json;

    }
}
