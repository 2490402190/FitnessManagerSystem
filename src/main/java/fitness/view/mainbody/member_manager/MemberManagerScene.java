package fitness.view.mainbody.member_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberManagerScene extends Application {
    public final static Stage memberManagerStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemberManager.fxml"));
        memberManagerStage.setResizable(false);
        memberManagerStage.setScene(new Scene(root));
        if (memberManagerStage.getStyle() != StageStyle.TRANSPARENT) {
            memberManagerStage.initStyle(StageStyle.TRANSPARENT);
        }
        memberManagerStage.show();
    }
}
