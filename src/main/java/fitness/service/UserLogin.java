package fitness.service;

import fitness.dao.DataBaseUtils;
import fitness.entity.User;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;

public class UserLogin {

    public static boolean checkLogin(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();

        if (!DataBaseUtils.operateByPassword(userId, password)) {
            Log.info("账号或密码错误，userId: " + userId + ",password: " + password);
            new NoticeWindow("提示", "账号或密码错误").show();
            return false;
        }
        Log.info("登录成功,userId: " + userId + ",password: " + password);
        return true;
    }
}
