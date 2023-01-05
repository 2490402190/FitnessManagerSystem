package fitness.view.Login;

import fitness.dao.DataBaseUtils;
import fitness.entity.AppData;
import fitness.entity.User;
import fitness.service.UserLogin;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;
import fitness.service.check.Regulars;
import fitness.view.mainbody.MainBodyScene;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LogInController {

    public TextField userId;
    public PasswordField password;
    public Button login_button;
    public ImageView close_button;

    @FXML
    public void login_button_action() {
        Log.info("login_button_OnAction");
        if (password.getText().equals("") || userId.getText().equals("")) {
            new NoticeWindow("提示", "请输入完整的账号密码").show();
            return;
        }
        if (!Regulars.checkUserId(userId.getText())) {
            new NoticeWindow("提示", "请输入正确的账号（账号为8~10位的数字）").show();
            return;
        }
        if (!Regulars.checkPassword(password.getText())) {
            new NoticeWindow("提示", "请输入正确的密码（密码长度为8~20位，且包含字母、数字）").show();
            return;
        }

        Log.info("账号：" + userId.getText());
        Log.info("密码：" + password.getText());

        User user = new User(
                userId.getText(),
                password.getText()
        );
        //记录当前登录账号
        AppData.setId(userId.getText());
        password.clear();//清空密码
        if (UserLogin.checkLogin(user)) {//验证正确
            //获取个人信息
            User userInfo = DataBaseUtils.getUserById(userId.getText());
            AppData.setUser(userInfo);//将个人信息保存
            //1.关闭登录界面
            LoginScene.loginStage.close();
            //2.打开主界面
            MainBodyScene mainBodyScene = new MainBodyScene();
            try {
                mainBodyScene.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    public void close_button_action() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    //设置光标图标
    public void login_button_OnMouseEntered(MouseEvent mouseEvent) {
        login_button.setCursor(Cursor.HAND);
    }

    @FXML
    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }
}