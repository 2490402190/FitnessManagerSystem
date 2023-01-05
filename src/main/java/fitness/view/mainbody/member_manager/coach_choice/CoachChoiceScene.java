package fitness.view.mainbody.member_manager.coach_choice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CoachChoiceScene extends Application {
    public final static Stage coachChoiceStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CoachChoice.fxml"));
        coachChoiceStage.setResizable(false);
        coachChoiceStage.setScene(new Scene(root));
        if (coachChoiceStage.getStyle() != StageStyle.TRANSPARENT) {
            coachChoiceStage.initStyle(StageStyle.TRANSPARENT);
        }
        coachChoiceStage.show();
    }
}
