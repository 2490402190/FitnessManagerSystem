package fitness.service;

import fitness.dao.DataBaseUtils;
import fitness.entity.User;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;

public class UpdatePassword {
    public static boolean updatePassword(User user, String verifyCode){
        String userId = user.getUserId();
        String email = user.getEmail();

        if (!SendVerifyCode.emailCheck(email, verifyCode)) {
            Log.info("验证码错误");
            new NoticeWindow("提示","验证码错误").show();
            return false;
        }

        if (!SendVerifyCode.timeCheck(verifyCode)) {
            Log.info("验证码已过期");
            SendVerifyCode.remove(verifyCode);//移除过期验证码
            new NoticeWindow("提示","验证码已过期").show();
            return false;
        }

        if (!DataBaseUtils.operateByEmail(userId, email)) {
            Log.info("账号不存在");
            new NoticeWindow("提示","账号不存在").show();
            return false;
        }
        if (DataBaseUtils.updatePasswordByEmail(user)) {
            new NoticeWindow("提示","密码更改成功").show();
            return true;
        }
        new NoticeWindow("提示", "修改失败").show();
        return false;
    }
}
