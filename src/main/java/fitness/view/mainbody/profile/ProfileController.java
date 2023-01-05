package fitness.view.mainbody.profile;

import fitness.dao.DataBaseUtils;
import fitness.entity.AppData;
import fitness.entity.User;
import fitness.utils.Log;
import fitness.utils.NoticeWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public TextField nickname;
    public TextField email;
    public Label userId;
    public DatePicker birthday;
    public javafx.scene.Group sex;
    public ToggleGroup Group;
    public Button ok_button;
    public RadioButton male;
    public RadioButton female;
    public Button update_button;
    public ImageView headPicture;
    public ImageView close_button;
    public ImageView head_button;
    String headPicturePath = AppData.getUser().getHeadPicture();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //裁剪图片
        headPicture.setImage(new Image("file:///" + headPicturePath));
        headPicture.setPreserveRatio(false);
        headPicture.setFitWidth(120);
        headPicture.setFitHeight(115);
        //加载个人信息
        userId.setText(AppData.getUser().getUserId());
        nickname.setText(AppData.getUser().getNickName());
        String sexJudge = AppData.getUser().getSex();
        if (sexJudge.equals("男")) {
            Group.selectToggle(male);
        } else if (sexJudge.equals("女")) {
            Group.selectToggle(female);
        }
        email.setText(AppData.getUser().getEmail());
        String date = AppData.getUser().getBirthday();
        int year = Integer.parseInt(date.substring(0, date.indexOf("-")));
        int month = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
        int day = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1, date.length()));
        birthday.setValue(LocalDate.of(year, month, day));
        //默认控件是不可编辑的
        setDisable();
    }

    public void setDisable() {
        head_button.setDisable(true);
        nickname.setDisable(true);
        nickname.setOpacity(1);
        sex.setDisable(true);
        male.setOpacity(1);
        female.setOpacity(1);
        email.setDisable(true);
        email.setOpacity(1);
        birthday.setDisable(true);
        birthday.setOpacity(1);
    }

    public void removeDisable() {
        head_button.setDisable(false);
        nickname.setDisable(false);
        sex.setDisable(false);
        email.setDisable(false);
        birthday.setDisable(false);
    }

    public void head_button_action() {
        Log.info("change_button_OnAction");
        //文件选择器
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));//初始化文件目录
        Stage stage = (Stage) head_button.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(//设置文件选择器所能打开的文件类型（利用给文件选择器对象添加扩展名过滤器功能来实现）
                new FileChooser.ExtensionFilter("图像文件", "*.jpg", "*.png", "*.bmp", "*.jpeg", "gif")
        );
        File file = fileChooser.showOpenDialog(stage);
        try {
            headPicturePath = file.getCanonicalPath();//获取打开文件的绝对路径
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //更新头像显示
        headPicture.setImage(new Image("file:///" + headPicturePath));
    }

    public void head_button_OnMouseEntered() {
        head_button.setCursor(Cursor.HAND);
    }

    public void update_button_action() {
        Log.info("ok_button_OnAction");
        removeDisable();
    }

    public void update_button_OnMouseEntered(MouseEvent mouseEvent) {
        update_button.setCursor(Cursor.HAND);
    }

    @FXML
    public void ok_button_action() {
        Log.info("ok_button_OnAction");
        setDisable();
        RadioButton sexSelected = (RadioButton) Group.getSelectedToggle();
        User userInfo = new User(
                AppData.getId(),
                null,
                nickname.getText(),
                sexSelected.getText(),
                email.getText(),
                birthday.getEditor().getText(),
                headPicturePath
        );

        AppData.getUser().setHeadPicture(headPicturePath);
        AppData.getUser().setNickName(nickname.getText());
        AppData.getUser().setEmail(email.getText());
        AppData.getUser().setSex(sexSelected.getText());
        AppData.getUser().setBirthday(birthday.getEditor().getText());
        //更新数据库
        if (DataBaseUtils.UpdateUser(userInfo)) {
            new NoticeWindow("提示", "信息修改成功").show();
        } else {
            new NoticeWindow("提示", "信息修改失败").show();
        }
    }

    public void ok_button_OnMouseEntered() {
        ok_button.setCursor(Cursor.HAND);
    }

    //关闭
    @FXML
    public void close_button_action() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }
}