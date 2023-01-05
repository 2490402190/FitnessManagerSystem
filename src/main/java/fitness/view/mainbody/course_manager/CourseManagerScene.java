package fitness.view.mainbody.course_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CourseManagerScene extends Application {
    public final static Stage courseManagerStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/CourseManager.fxml"));
        courseManagerStage.setResizable(false);
        courseManagerStage.setScene(new Scene(root));
        if (courseManagerStage.getStyle() != StageStyle.TRANSPARENT) {
            courseManagerStage.initStyle(StageStyle.TRANSPARENT);
        }
        courseManagerStage.show();
    }
}
