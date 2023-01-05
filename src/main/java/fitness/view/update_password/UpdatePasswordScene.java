package fitness.view.update_password;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UpdatePasswordScene extends Application {
    public final static Stage updatePswdStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UpdatePassword.fxml"));
        updatePswdStage.setResizable(false);
        updatePswdStage.setScene(new Scene(root));
        if (updatePswdStage.getStyle() != StageStyle.TRANSPARENT) {
            updatePswdStage.initStyle(StageStyle.TRANSPARENT);
        }
        updatePswdStage.show();
    }
}
