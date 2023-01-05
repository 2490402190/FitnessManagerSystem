package fitness.view.mainbody.member_manager.course_choice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CourseChoiceScene extends Application {
    public final static Stage courseChoiceStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CourseChoice.fxml"));
        courseChoiceStage.setResizable(false);
        courseChoiceStage.setScene(new Scene(root));
        if (courseChoiceStage.getStyle() != StageStyle.TRANSPARENT) {
            courseChoiceStage.initStyle(StageStyle.TRANSPARENT);
        }
        courseChoiceStage.show();
    }
}
