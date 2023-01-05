import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class ImageTest extends Application {
    @Test
    public void test1(){
        try {
            start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image = new Image("file:///E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\headpicture\\头像1.png");
        ImageView imageView = new ImageView(image);
        Image image1 = imageView.getImage();
        System.out.println(image1.impl_getUrl());
    }
}
