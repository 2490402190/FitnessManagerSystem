package fitness.view.mainbody.equipment_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EquipmentManagerScene extends Application {
    public final static Stage equipmentManagerStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EquipmentManager.fxml"));
        equipmentManagerStage.setResizable(false);
        equipmentManagerStage.setScene(new Scene(root));
        if (equipmentManagerStage.getStyle() != StageStyle.TRANSPARENT) {
            equipmentManagerStage.initStyle(StageStyle.TRANSPARENT);
        }
        equipmentManagerStage.show();
    }
}
