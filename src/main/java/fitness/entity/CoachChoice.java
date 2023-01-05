package fitness.entity;

public class CoachChoice {
    private String id;
    private String memberId;
    private String memberName;
    private String coachId;
    private String coachName;

    public CoachChoice() {
    }

    public CoachChoice(String id, String memberId, String memberName, String coachId, String coachName) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.coachId = coachId;
        this.coachName = coachName;
    }

    @Override
    public String toString() {
        return "CoachChoice{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", coachId='" + coachId + '\'' +
                ", coachName='" + coachName + '\'' +
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
}
