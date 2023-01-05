package fitness.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import fitness.entity.*;
import fitness.entity.Course;
import fitness.utils.Log;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBaseUtils {
    static ComboPooledDataSource pool;

    public static void run() {//初始化连接池
        try {
            Properties pros = new Properties();
            FileInputStream fis = new FileInputStream("E:\\idea-project\\IdeaProjects\\FitnessClubSystem\\src\\main\\resources\\JDBC.properties");
            pros.load(fis);//加载流对应的文件

            // 获取数据库连接池的实例化对象
            pool = new ComboPooledDataSource();
            // 设置连接需要的基本信息
            pool.setDriverClass(pros.getProperty("drivername"));
            pool.setJdbcUrl(pros.getProperty("url"));
            pool.setUser(pros.getProperty("user"));
            pool.setPassword(pros.getProperty("password"));
            // 设置与管理数据库连接池相关的属性
            // 设置连接池中初始连接的数量
            pool.setInitialPoolSize(10);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserById(String userId) {
        String sql = "select nickname,sex,email,birthday,head_picture from user where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, userId);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    User user = new User(
                            userId,
                            "null",
                            info.getString(1),
                            info.getString(2),
                            info.getString(3),
                            info.getString(4),
                            info.getString(5));
                    return user;
                }
                return null;
            }
        } catch (SQLException e) {
            Log.debug("getUserByID：操作失败，错误代码：" + e.getErrorCode());
            return null;
        }
    }

    public static boolean UpdateUser(User user) {

        String sql = "update user set nickname=?,sex=?,birthday=?,head_picture=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, user.getNickName());
                ps.setObject(2, user.getSex());
                ps.setObject(3, user.getBirthday());
                ps.setObject(4, user.getHeadPicture());
                ps.setObject(5, user.getUserId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("updateUserInformation：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    //账号密码登录
    public static boolean operateByPassword(String userId, String password) {
        try (Connection connection = pool.getConnection()) {
            String sql = "select id from user where id = ? and password = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, userId);
                ps.setObject(2, password);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("operateByPassword：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
        return false;
    }

    public static boolean operateByEmail(String userId, String email) {
        try (Connection connection = pool.getConnection()) {
            String sql = "select id from user where id = ? and email = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, userId);
                ps.setObject(2, email);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("operateByEmail：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
        return false;
    }

    public static boolean updatePasswordByEmail(User user) {
        String sql = "update user set password=? WHERE id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, user.getPassword());
                ps.setObject(2, user.getUserId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("updatePasswordByPassword：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,place,teacher,duration,start,ending,price,type,lesson_type,number from course";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getString(10),
                                resultSet.getString(11)
                        );
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllCourses：操作失败，错误代码：" + e.getErrorCode());
        }
        return courses;
    }

    public static boolean InsertCourse(Course course) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into course (id,name,place,teacher,duration,start,ending,price,type,lesson_type,number) values (?,?,?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ps.setObject(1, course.getId());
                ps.setObject(2, course.getName());
                ps.setObject(3, course.getPlace());
                ps.setObject(4, course.getTeacher());
                ps.setObject(5, course.getDuration());
                ps.setObject(6, course.getStart());
                ps.setObject(7, course.getEnd());
                ps.setObject(8, course.getPrice());
                ps.setObject(9, course.getType());
                ps.setObject(10, course.getLessonType());
                ps.setObject(11, course.getNumber());
                ps.executeUpdate();
                Log.info("InsertCourse：课程添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertCourse：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean DeleteCourse(String id) {
        String sql = "delete from course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteCourse：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean UpdateCourse(Course course) {
        String sql = "update course set name=?,place=?,teacher=?,duration=?,start=?,ending=?,price=?,type=?,lesson_type=?,number=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, course.getName());
                ps.setObject(2, course.getPlace());
                ps.setObject(3, course.getTeacher());
                ps.setObject(4, course.getDuration());
                ps.setObject(5, course.getStart());
                ps.setObject(6, course.getEnd());
                ps.setObject(7, course.getPrice());
                ps.setObject(8, course.getType());
                ps.setObject(9, course.getLessonType());
                ps.setObject(10, course.getNumber());
                ps.setObject(11, course.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateCourse：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Coach> getAllCoaches() {
        List<Coach> coaches = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,sex,age,contact,level,posts,head_picture from coach";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Coach coach = new Coach(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                new ImageView(new Image("file:///"+resultSet.getString(8)))
                        );
                        coach.initImageSize();
                        coaches.add(coach);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllCoaches：操作失败，错误代码：" + e.getErrorCode());
        }
        return coaches;
    }

    public static boolean UpdateCoach(Coach coach) {
        String sql = "update coach set name=?,sex=?,age=?,contact=?,level=?,posts=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, coach.getName());
                ps.setObject(2, coach.getSex());
                ps.setObject(3, coach.getAge());
                ps.setObject(4, coach.getContact());
                ps.setObject(5, coach.getLevel());
                ps.setObject(6, coach.getPosts());
                ps.setObject(7, coach.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateCoach：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean DeleteCoach(String id) {
        String sql = "delete from coach where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteCoach：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Coach> FuzzySearchCoaches(String key) {
        List<Coach> coaches = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,sex,age,contact,level,posts,head_picture from coach where id like '%" + key + "%' or name like'%" + key + "%' or posts like '%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Coach coach = new Coach(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                new ImageView(new Image("file:///"+resultSet.getString(8)))
                        );
                        coach.initImageSize();
                        coaches.add(coach);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCoaches：操作失败，错误代码：" + e.getErrorCode());
        }
        return coaches;
    }

    public static List<Equipment> getAllEquipments() {
        List<Equipment> equipments = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,place,type,price,picture,status,render_id,remark from equipment";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Equipment equipment = new Equipment(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                new ImageView(new Image("file:///" + resultSet.getString(6))),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)
                        );
                        equipment.initImageSize();
                        equipments.add(equipment);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllEquipments：操作失败，错误代码：" + e.getErrorCode());
        }
        return equipments;
    }

    public static boolean UpdateEquipment(Equipment equipment) {
        String sql = "update equipment set name=?,place=?,type=?,price=?,status=?,render_id=?,remark=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, equipment.getName());
                ps.setObject(2, equipment.getPlace());
                ps.setObject(3, equipment.getType());
                ps.setObject(4, equipment.getPrice());
                ps.setObject(5, equipment.getStatus());
                ps.setObject(6, equipment.getRenderId());
                ps.setObject(7, equipment.getRemark());
                ps.setObject(8, equipment.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateEquipment：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean DeleteEquipment(String id) {
        String sql = "delete from equipment where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteEquipment：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Equipment> FuzzySearchEquipments(String key) {
        List<Equipment> equipments = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,place,type,price,picture,status,render_id,remark from equipment where id like '%" + key + "%' or name like'%" + key + "%' or status like'%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Equipment equipment = new Equipment(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                new ImageView(new Image("file:///" + resultSet.getString(6))),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9)
                        );
                        equipment.initImageSize();
                        equipments.add(equipment);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchEquipments：操作失败，错误代码：" + e.getErrorCode());
        }
        return equipments;
    }

    public static List<Course> FuzzySearchCourses(String key) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,place,teacher,duration,start,ending,price,type,lesson_type,number from course where id like '%" + key + "%' or name like'%" + key + "%' or teacher like '%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Course course = new Course(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getString(10),
                                resultSet.getString(11)
                        );
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCourses：操作失败，错误代码：" + e.getErrorCode());
        }
        return courses;
    }

    public static boolean UpdateMember(Member member) {
        String sql = "update member set name=?,sex=?,age=?,contact=?,card=?,duration=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, member.getName());
                ps.setObject(2, member.getSex());
                ps.setObject(3, member.getAge());
                ps.setObject(4, member.getContact());
                ps.setObject(5, member.getCard());
                ps.setObject(6, member.getDuration());
                ps.setObject(7, member.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateMember：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,sex,age,contact,card,duration,head_picture from member";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Member member = new Member(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                new ImageView(new Image("file:///" + resultSet.getString(8)))
                        );
                        member.initImageSize();
                        members.add(member);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllMembers：操作失败，错误代码：" + e.getErrorCode());
        }
        return members;
    }

    public static boolean DeleteMember(String id) {
        String sql = "delete from member where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteMember：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<Member> FuzzySearchMembers(String key) {
        List<Member> members = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,name,sex,age,contact,card,duration,head_picture from member where id like '%" + key + "%' or name like'%" + key + "%' or contact like '%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        Member member = new Member(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                new ImageView(new Image("file:///" + resultSet.getString(8)))
                        );
                        member.initImageSize();
                        members.add(member);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCoaches：操作失败，错误代码：" + e.getErrorCode());
        }
        return members;
    }

    public static boolean InsertCoach(Coach coach) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into coach (id,name,sex,age,contact,level,posts,head_picture) values (?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ps.setObject(1, coach.getId());
                ps.setObject(2, coach.getName());
                ps.setObject(3, coach.getSex());
                ps.setObject(4, coach.getAge());
                ps.setObject(5, coach.getContact());
                ps.setObject(6, coach.getLevel());
                ps.setObject(7, coach.getPosts());
                ps.setObject(8, coach.getHeadPicture().getImage().impl_getUrl());
                ps.executeUpdate();
                Log.info("InsertCoach：课程添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertCoach：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean InsertMember(Member member) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into member (id,name,sex,age,contact,card,duration,head_picture) values (?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ps.setObject(1, member.getId());
                ps.setObject(2, member.getName());
                ps.setObject(3, member.getSex());
                ps.setObject(4, member.getAge());
                ps.setObject(5, member.getContact());
                ps.setObject(6, member.getCard());
                ps.setObject(7, member.getDuration());
                ps.setObject(8, member.getHeadPicture().getImage().impl_getUrl());
                ps.executeUpdate();
                Log.info("InsertMember：课程添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertMember：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean InsertEquipment(Equipment equipment) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into member (id,name,place,type,price,picture,status,render_id,remark) values (?,?,?,?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ps.setObject(1, equipment.getId());
                ps.setObject(2, equipment.getName());
                ps.setObject(3, equipment.getPlace());
                ps.setObject(4, equipment.getType());
                ps.setObject(5, equipment.getPrice());
                ps.setObject(6, equipment.getPicture().getImage().impl_getUrl());
                ps.setObject(7, equipment.getStatus());
                ps.setObject(8, equipment.getRenderId());
                ps.setObject(9, equipment.getRemark());
                ps.executeUpdate();
                Log.info("InsertEquipment：课程添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertEquipment：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getCourseById(String id) {
        String sql = "select * from course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getCourseById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getMemberById(String id) {
        String sql = "select * from member where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getMemberById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getCoachById(String id) {
        String sql = "select * from coach where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getCoachById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getEquipmentById(String id) {
        String sql = "select * from equipment where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getEquipmentById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean UpdateCourseChoice(CourseChoice courseChoice) {
        String sql = "update member_course set member_id=?,member_name=?,course_id=?,course_name=?,number=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, courseChoice.getMemberId());
                ps.setObject(2, courseChoice.getMemberName());
                ps.setObject(3, courseChoice.getCourseId());
                ps.setObject(4, courseChoice.getCourseName());
                ps.setObject(5, courseChoice.getCount());
                ps.setObject(6, courseChoice.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateCourseChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CourseChoice> getAllCourseChoices() {
        List<CourseChoice> courseChoices = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,member_id,member_name,course_id,course_name,number from member_course";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CourseChoice courseChoice = new CourseChoice(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        );
                        courseChoices.add(courseChoice);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllCourseChoices：操作失败，错误代码：" + e.getErrorCode());
        }
        return courseChoices;
    }

    public static boolean InsertCourseChoice(CourseChoice courseChoice) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into member_course (id,member_id,member_name,course_id,course_name,number) values (?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, courseChoice.getId());
                ps.setObject(2, courseChoice.getMemberId());
                ps.setObject(3, courseChoice.getMemberName());
                ps.setObject(4, courseChoice.getCourseId());
                ps.setObject(5, courseChoice.getCourseName());
                ps.setObject(6, courseChoice.getCount());
                ps.executeUpdate();
                Log.info("InsertCourseChoice：选课信息添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertCourseChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CourseChoice> FuzzySearchCourseChoices(String key) {
        List<CourseChoice> courseChoices = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,member_id,member_name,course_id,course_name,number from member_course where member_id like '%" + key + "%' or member_name like'%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CourseChoice courseChoice = new CourseChoice(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        );
                        courseChoices.add(courseChoice);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCourseChoices：操作失败，错误代码：" + e.getErrorCode());
        }
        return courseChoices;
    }

    public static boolean DeleteCourseChoice(String id) {
        String sql = "delete from member_course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteCourseChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean UpdateCoachChoice(CoachChoice coachChoice) {
        String sql = "update member_coach set member_id=?,member_name=?,coach_id=?,coach_name=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, coachChoice.getMemberId());
                ps.setObject(2, coachChoice.getMemberName());
                ps.setObject(3, coachChoice.getCoachId());
                ps.setObject(4, coachChoice.getCoachName());
                ps.setObject(5, coachChoice.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateCoachChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CoachChoice> getAllCoachChoices() {
        List<CoachChoice> coachChoices = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,member_id,member_name,coach_id,coach_name from member_coach";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CoachChoice coachChoice = new CoachChoice(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)
                        );
                        coachChoices.add(coachChoice);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllCoachChoices：操作失败，错误代码：" + e.getErrorCode());
        }
        return coachChoices;
    }

    public static boolean InsertCoachChoice(CoachChoice coachChoice) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into member_coach (id,member_id,member_name,course_id,course_name) values (?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, coachChoice.getId());
                ps.setObject(2, coachChoice.getMemberId());
                ps.setObject(3, coachChoice.getMemberName());
                ps.setObject(4, coachChoice.getCoachId());
                ps.setObject(5, coachChoice.getCoachName());
                ps.executeUpdate();
                Log.info("InsertCoachChoice：私教信息添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertCoachChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CoachChoice> FuzzySearchCoachChoices(String key) {
        List<CoachChoice> coachChoices = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,member_id,member_name,coach_id,coach_name from member_coach where member_id like '%" + key + "%' or member_name like'%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CoachChoice coachChoice = new CoachChoice(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5)
                        );
                        coachChoices.add(coachChoice);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCoachChoices：操作失败，错误代码：" + e.getErrorCode());
        }
        return coachChoices;
    }

    public static boolean DeleteCoachChoice(String id) {
        String sql = "delete from member_coach where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteCoachChoice：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean UpdateCourseTeaching(CourseTeaching courseTeaching) {
        String sql = "update coach_course set coach_id=?,coach_name=?,course_id=?,course_name=?,number=? where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, courseTeaching.getCoachId());
                ps.setObject(2, courseTeaching.getCoachName());
                ps.setObject(3, courseTeaching.getCourseId());
                ps.setObject(4, courseTeaching.getCourseName());
                ps.setObject(5, courseTeaching.getNumber());
                ps.setObject(6, courseTeaching.getId());
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("UpdateCourseTeaching：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CourseTeaching> getAllCourseTeachings() {
        List<CourseTeaching> courseTeachings = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,coach_id,coach_name,course_id,course_name,number from coach_course";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CourseTeaching courseTeaching = new CourseTeaching(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        );
                        courseTeachings.add(courseTeaching);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("getAllCourseTeachings：操作失败，错误代码：" + e.getErrorCode());
        }
        return courseTeachings;
    }

    public static boolean InsertCourseTeaching(CourseTeaching courseTeaching) {
        try (Connection connection = pool.getConnection()) {
            String sql = "insert into coach_course (id,coach_id,coach_name,course_id,course_name,number) values (?,?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, courseTeaching.getId());
                ps.setObject(2, courseTeaching.getCoachId());
                ps.setObject(3, courseTeaching.getCoachName());
                ps.setObject(4, courseTeaching.getCourseId());
                ps.setObject(5, courseTeaching.getCourseName());
                ps.setObject(6, courseTeaching.getNumber());
                ps.executeUpdate();
                Log.info("InsertCourseTeaching：授课信息添加成功");
                return true;
            }
        } catch (SQLException e) {
            Log.debug("InsertCourseTeaching：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static List<CourseTeaching> FuzzySearchCourseTeachings(String key) {
        List<CourseTeaching> courseTeachings = new ArrayList<>();
        try (Connection connection = pool.getConnection()) {
            String sql = "select id,coach_id,coach_name,course_id,course_name,number from coach_course where coach_id like '%" + key + "%' or coach_name like'%" + key + "%'";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = ps.executeQuery()) {
                    while (resultSet.next()) {
                        CourseTeaching courseTeaching = new CourseTeaching(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        );
                        courseTeachings.add(courseTeaching);
                    }
                }
            }
        } catch (SQLException e) {
            Log.debug("FuzzySearchCourseChoices：操作失败，错误代码：" + e.getErrorCode());
        }
        return courseTeachings;
    }

    public static boolean DeleteCourseTeaching(String id) {
        String sql = "delete from coach_course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                return ps.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Log.debug("DeleteCourseTeaching：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getCourseChoiceById(String id) {
        String sql = "select * from member_course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getCourseChoiceById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getCourseTeachingById(String id) {
        String sql = "select * from coach_course where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getCourseTeachingById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }

    public static boolean getCoachChoiceById(String id) {
        String sql = "select * from member_coach where id=?";
        try (Connection connection = pool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setObject(1, id);
                ResultSet info = ps.executeQuery();
                if (info.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            Log.debug("getCourseTeachingById：操作失败，错误代码：" + e.getErrorCode());
            return false;
        }
    }
}
