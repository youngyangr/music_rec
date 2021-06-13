package ss.pku.music.utils;

import junit.framework.TestCase;

public class MailUtilTest extends TestCase {


    public static void main(String [] args){
        String content="非常高兴您能加入我们，您本次的验证码为";

            MailUtil.send_mail("evansg@qq.com",content+String.valueOf(Math.random()*999));
            System.out.println("邮件发送成功");


    }

}