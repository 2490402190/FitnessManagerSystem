package fitness.entity;

public class User {
    private String userId;
    private String password;
    private String nickName;
    private String sex;
    private String email;
    private String birthday;
    private String headPicture;

    public User() {
    }

    public User(String userId,String password){
        this.userId = userId;
        this.password = password;
    }

    public User(String userId, String password, String nickName, String sex, String email, String birthday, String headPicture) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.sex = sex;
        this.email = email;
        this.birthday = birthday;
        this.headPicture = headPicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }
}
