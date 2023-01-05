package fitness.entity;

public class CourseTeaching {
    private String id;
    private String coachId;
    private String coachName;
    private String courseId;
    private String courseName;
    private String number;

    public CourseTeaching() {
    }

    public CourseTeaching(String id, String coachId, String coachName, String courseId, String courseName, String number) {
        this.id = id;
        this.coachId = coachId;
        this.coachName = coachName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.number = number;
    }

    @Override
    public String toString() {
        return "CourseList{" +
                "id='" + id + '\'' +
                ", coachId='" + coachId + '\'' +
                ", coachName='" + coachName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
