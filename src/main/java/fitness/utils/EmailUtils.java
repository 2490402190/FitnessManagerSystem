package fitness.utils;

import org.apache.commons.mail.HtmlEmail;

/**
 * 通过QQ邮箱发送验证码
 */
public class EmailUtils {
    public static boolean sendEmail(String receiver, String code) {
        try {
            //创建网页邮箱对象
            HtmlEmail email = new HtmlEmail();

            //基本设置
            email.setDebug(true);

            //设置为QQ邮箱作为发送主邮箱
            email.setHostName("SMTP.qq.com");
            email.setSmtpPort(587);

            //qq邮箱的验证信息
            email.setAuthentication("2490402190@qq.com", "uqpgznrrynlreahi");//"qq邮箱", "发送短信之后生成的授权码"

            //设置邮件发送人
            email.setFrom("2490402190@qq.com");//"邮件发送人填写你的qq邮箱"

            //设置邮件接收人
            email.addTo(receiver);//"邮件接收者的qq邮箱"

            //设置发送的内容
            email.setMsg("验证码:" + code + "(修改密码验证码)，你正在使用邮箱验证码修改密码，5分钟内有效，请勿泄露或转发他人。");//"发送内容"

            //设置邮箱标题
            email.setSubject("验证码");//"邮箱标题"

            //执行邮件发送
            email.send();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}