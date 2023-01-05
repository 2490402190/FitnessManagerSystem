package fitness.view.mainbody.profile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProfileScene extends Application {
    public final static Stage profileStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Profile.fxml"));
        profileStage.setResizable(false);
        profileStage.setScene(new Scene(root));
        if (profileStage.getStyle() != StageStyle.TRANSPARENT) {
            profileStage.initStyle(StageStyle.TRANSPARENT);
        }
        profileStage.show();
    }
}
