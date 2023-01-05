package fitness.view.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginScene extends Application {
    public final static Stage loginStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        loginStage.setResizable(false);
        loginStage.setScene(new Scene(root));
        if (loginStage.getStyle() != StageStyle.TRANSPARENT) {
            loginStage.initStyle(StageStyle.TRANSPARENT);
        }
        loginStage.show();
    }
}
