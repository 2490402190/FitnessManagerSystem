package fitness.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoticeWindow implements Runnable{
    private String title, msg;
    private Stage stage;

    public NoticeWindow(String title, String msg) {
        this.title = title;
        this.msg = msg;
    }

    public void show() {
        final Stage stage = new Stage();
        final StackPane root = new StackPane();
        final Button button = new Button();
        final Button tip = new Button();

        tip.setText(msg);
        tip.setTranslateY(-30);
        tip.setStyle("-fx-background-color: null;-fx-border-color: null");

        button.setText("确定");
        button.setTranslateY(30);
        button.setDefaultButton(true);
        //添加子节点
        root.getChildren().addAll(tip, button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        stage.setTitle(title);
        stage.setScene(new Scene(root, 350, 150));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @Override
    public void run() {
        this.show();
    }
}
