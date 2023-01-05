package fitness.view.mainbody;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainBodyScene extends Application {
    public final static Stage mainBodyStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainBody.fxml"));
        mainBodyStage.setResizable(false);
        mainBodyStage.setScene(new Scene(root));
        if (mainBodyStage.getStyle() != StageStyle.TRANSPARENT) {
            mainBodyStage.initStyle(StageStyle.TRANSPARENT);
        }
        mainBodyStage.show();
    }
}
