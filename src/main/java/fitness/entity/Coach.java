package fitness.entity;

import javafx.scene.image.ImageView;

import javax.mail.Header;

public class Coach {
    private String id;
    private String name;
    private String sex;
    private String age;
    private String contact;
    private String level;
    private String posts;
    private ImageView headPicture;

    public void initImageSize() {
        headPicture.setFitWidth(40);
        headPicture.setFitHeight(40);
    }

    public Coach() {
    }

    public Coach(String id, String name, String sex, String age, String contact, String level, String posts, ImageView headPicture) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.level = level;
        this.posts = posts;
        this.headPicture = headPicture;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", contact='" + contact + '\'' +
                ", level='" + level + '\'' +
                ", posts='" + posts + '\'' +
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public ImageView getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(ImageView headPicture) {
        this.headPicture = headPicture;
    }

}
