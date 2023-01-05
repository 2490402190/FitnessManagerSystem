package fitness.view.mainbody.coach_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CoachManagerScene extends Application {
    public final static Stage coachManagerStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CoachManager.fxml"));
        coachManagerStage.setResizable(false);
        coachManagerStage.setScene(new Scene(root));
        if (coachManagerStage.getStyle() != StageStyle.TRANSPARENT) {
            coachManagerStage.initStyle(StageStyle.TRANSPARENT);
        }
        coachManagerStage.show();
    }
}
