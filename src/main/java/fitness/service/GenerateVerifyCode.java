package fitness.service;

import java.util.Random;

/**
 * 生成6位的验证码
 */
public class GenerateVerifyCode {
    public static String generateVerifyCode() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            //random.nextInt(10)用来生成[0,10)的随机数
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
