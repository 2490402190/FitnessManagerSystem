package fitness.entity;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Member {
    private String id;
    private String name;
    private String sex;
    private String age;
    private String contact;
    private String card;
    private String duration;
    private ImageView headPicture;

    public void initImageSize() {
        headPicture.setFitWidth(40);
        headPicture.setFitHeight(40);
    }

    public Member() {
    }

    public Member(String id, String name, String sex, String age, String contact, String card, String duration, ImageView headPicture) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.card = card;
        this.duration = duration;
        this.headPicture = headPicture;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", contact='" + contact + '\'' +
                ", card='" + card + '\'' +
                ", duration='" + duration + '\'' +
                ", headPicture=" + headPicture +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ImageView getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(ImageView headPicture) {
        this.headPicture = headPicture;
    }

}
