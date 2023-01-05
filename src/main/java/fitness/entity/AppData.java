package fitness.entity;

public class AppData {

    private static String id = "null";

    private static User user;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        AppData.id = id;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        AppData.user = user;
    }
}
