package fitness.view.mainbody;

import fitness.utils.Log;
import fitness.view.mainbody.coach_manager.CoachManagerScene;
import fitness.view.mainbody.course_manager.CourseManagerScene;
import fitness.view.mainbody.equipment_manager.EquipmentManagerScene;
import fitness.view.mainbody.member_manager.MemberManagerScene;
import fitness.view.mainbody.profile.ProfileScene;
import fitness.view.update_password.UpdatePasswordScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainBodyController implements Initializable {

    public ImageView close_button;
    public ImageView person;
    public Button courseManager;
    public Button equipManager;
    public Button memberManager;
    public Button coachManager;
    public MenuButton more;

    //初始化更多设置选项
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuItem menuItem = new MenuItem("更改密码");
        //将默认选择清空，并添加新的选择
        more.getItems().clear();
        more.getItems().add(menuItem);
        //创建事件
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                //打开更新密码界面
                UpdatePasswordScene updatePasswordScene = new UpdatePasswordScene();
                try {
                    updatePasswordScene.start(new Stage());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        //为控件添加事件
        menuItem.setOnAction(event1);
    }

    //个人
    @FXML
    public void person_button_Action() throws Exception {
        Log.info("person_button_Action");
        ProfileScene profileScene = new ProfileScene();
        profileScene.start(new Stage());
    }

    public void person_button_OnMouseEntered() {
        person.setCursor(Cursor.HAND);
    }

    //设置
    @FXML
    public void more_button_OnMouseEntered() {
        more.setCursor(Cursor.HAND);
    }


    //课程管理
    @FXML
    public void courseManager_Action() throws Exception {
        Log.info("courseManager_Action");
        CourseManagerScene courseManagerScene = new CourseManagerScene();
        courseManagerScene.start(new Stage());
    }
    public void courseManager_OnMouseEntered() {
        courseManager.setCursor(Cursor.HAND);
    }


    //设备管理
    @FXML
    public void equipManager_Action() throws Exception {
        Log.info("equipManager_Action");
        EquipmentManagerScene equipmentManagerScene = new EquipmentManagerScene();
        equipmentManagerScene.start(new Stage());
    }
    public void equipManager_OnMouseEntered() {
        equipManager.setCursor(Cursor.HAND);
    }


    //教练管理
    @FXML
    public void coachManager_Action() throws Exception {
        Log.info("coachManager_Action");
        CoachManagerScene coachManagerScene = new CoachManagerScene();
        coachManagerScene.start(new Stage());
    }
    public void coachManager_OnMouseEntered() {
        coachManager.setCursor(Cursor.HAND);
    }


    //会员管理
    @FXML
    public void memberManager_Action() throws Exception {
        Log.info("memberManager_Action");
        MemberManagerScene memberManagerScene = new MemberManagerScene();
        memberManagerScene.start(new Stage());

    }
    public void memberManager_OnMouseEntered() {
        memberManager.setCursor(Cursor.HAND);
    }


    //关闭
    @FXML
    public void close_button_action() {
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void close_button_OnMouseEntered() {
        close_button.setCursor(Cursor.HAND);
    }
}
