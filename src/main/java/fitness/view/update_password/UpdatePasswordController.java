package fitness.view.update_password;

import fitness.entity.AppData;
import fitness.entity.User;
import fitness.service.SendVerifyCode;
import fitness.service.UpdatePassword;
import fitness.service.check.Regulars;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdatePasswordController {
    public Button ok_button;
    public ImageView close_button;
    public PasswordField pswd_again;
    public PasswordField newpswd;
    public TextField email;
    public Button send_code_button;
    public TextField code;

    public void send_code_button_action() {
        Log.info("send_code_button_OnAction");
        if (!Regulars.checkEmail(email.getText())) {
            new NoticeWindow("提示", "请输入正确的邮箱地址").show();
            return;
        }
        SendVerifyCode.sendVerifyCode(email.getText());
        send_code_button.setDisable(true);
        UpdatePasswordCD updatePasswordCD = new UpdatePasswordCD(this);
        updatePasswordCD.start();
    }

    public void show(int waitTime) {
        Platform.runLater(() -> {
            send_code_button.setText(waitTime + "");
        });
    }

    public void renew() {
        Platform.runLater(() -> {
            send_code_button.setText("重新发送");
        });
        send_code_button.setDisable(false);//解除禁用
    }

    public void send_code_button_OnMouseEntered(MouseEvent mouseEvent) {
        send_code_button.setCursor(Cursor.HAND);
    }

    public void ok_button_action(ActionEvent actionEvent) {
        Log.info("ok_button_OnAction");
        boolean isCorrect = true;
        if (email.getText().equals("") || newpswd.getText().equals("") || pswd_again.getText().equals("")) {
            new NoticeWindow("提示", "请输入完整信息").show();
            isCorrect = false;
        } else if (code.getText().equals("")) {
            new NoticeWindow("提示", "请输入验证码").show();
            isCorrect = false;
        } else {
            if (!Regulars.checkPassword(newpswd.getText()) || !Regulars.checkPassword(pswd_again.getText())) {
                new NoticeWindow("提示", "请按正确的密码格式输入(密码为8-20位，必须包含有数字、英文)").show();
                isCorrect = false;
            }
            if (!Regulars.checkEmail(email.getText())) {
                new NoticeWindow("提示", "请输入正确的邮箱地址").show();
                isCorrect = false;
            }
            if (!pswd_again.getText().equals(newpswd.getText())) {
                new NoticeWindow("提示", "两次密码不一致").show();
                isCorrect = false;
            }
        }
        if (isCorrect) {
            User user = new User(
                    AppData.getId(),
                    newpswd.getText(),
                    null,
                    null,
                    email.getText(),
                    null,
                    null
            );
            if (UpdatePassword.updatePassword(user, code.getText())) {
                //关闭界面
                UpdatePasswordScene.updatePswdStage.close();
            }
        }
    }

    public void ok_button_OnMouseEntered(MouseEvent mouseEvent) {
        ok_button.setCursor(Cursor.HAND);
    }

    public void close_button_action(MouseEvent mouseEvent) {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }
}
