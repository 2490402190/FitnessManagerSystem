package fitness;

import fitness.dao.DataBaseUtils;
import fitness.view.Login.LoginScene;
import javafx.application.Application;

public class AppStartUp {
    public static void main(String[] args) {
        //连接数据库连接池
        DataBaseUtils.run();
        //登录界面
        Application.launch(LoginScene.class, args);
    }
}
