package fitness.service;

import fitness.utils.EmailUtils;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

public class SendVerifyCode {
    //key：邮箱, value：验证码
    public static HashMap<String, String> emailHp = new HashMap<>();
    //key：验证码, value：时间
    public static HashMap<String, Timestamp> timeHp = new HashMap<>();

    public static void addEmailToCode(String email, String code) {
        emailHp.put(email, code);
    }

    public static void addCodeToTime(String code, Timestamp time) {
        timeHp.put(code, time);
    }

    public static String getVerifyCode(String email) {
        return emailHp.get(email);
    }

    public static Timestamp getTime(String code) {
        return timeHp.get(code);
    }

    public static void remove(String code) {
        timeHp.remove(code);
    }

    public static void sendVerifyCode(String email) {
        Log.info("开始发送邮箱验证码");
        //生成随机验证码
        String code = GenerateVerifyCode.generateVerifyCode();
        //将验证码保存
        addEmailToCode(email, code);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        addCodeToTime(code, time);

        EmailUtils.sendEmail(email, code);//调用邮箱工具类发送验证码到对方email
        new NoticeWindow("提示", "验证码已发送至" + email).show();
    }

    //检查邮箱是否与验证码对应
    public static boolean emailCheck(String email, String code) {
        if (getVerifyCode(email).equals(code)) {
            return true;
        }
        return false;
    }

    //检查验证码是否超时
    public static boolean timeCheck(String code) {
        Date startTime = getTime(code);
        Date currentTime = new Timestamp(System.currentTimeMillis());
        if (currentTime.getTime() - startTime.getTime() <= 300000) {
            return true;
        }
        return false;
    }
}
