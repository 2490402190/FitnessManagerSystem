package fitness.entity;

public class CourseChoice {
    private String id;
    private String memberId;
    private String memberName;
    private String courseId;
    private String courseName;
    private String count;

    public CourseChoice() {
    }

    public CourseChoice(String id, String memberId, String memberName, String courseId, String courseName, String count) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CourseChoice{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
