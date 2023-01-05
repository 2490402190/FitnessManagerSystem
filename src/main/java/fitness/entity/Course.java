package fitness.entity;

public class Course {
    private String id;
    private String name;
    private String place;
    private String teacher;
    private String duration; //时长
    private String start;
    private String end;
    private String price;
    private String type;
    private String lessonType;
    private String number;

    public Course() {
    }

    public Course(String id, String name, String place, String teacher, String duration, String start, String end, String price, String type, String lessonType, String number) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.teacher = teacher;
        this.duration = duration;
        this.start = start;
        this.end = end;
        this.price = price;
        this.type = type;
        this.lessonType = lessonType;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", teacher='" + teacher + '\'' +
                ", duration='" + duration + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", lessonType='" + lessonType + '\'' +
                ", number='" + number + '\'' +
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}