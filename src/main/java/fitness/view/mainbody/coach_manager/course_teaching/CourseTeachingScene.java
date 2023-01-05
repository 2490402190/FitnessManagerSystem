package fitness.view.mainbody.coach_manager.course_teaching;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CourseTeachingScene extends Application {
    public final static Stage courseTeachingStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CourseTeaching.fxml"));
        courseTeachingStage.setResizable(false);
        courseTeachingStage.setScene(new Scene(root));
        if (courseTeachingStage.getStyle() != StageStyle.TRANSPARENT) {
            courseTeachingStage.initStyle(StageStyle.TRANSPARENT);
        }
        courseTeachingStage.show();
    }
}
