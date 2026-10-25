package bikeshare;

public class Bike {
    private final String id;
    private String station;
    private boolean rented;
    private String memberId;
    private MemberType memberType;

    public Bike(String id, String station) {
        this.id = id;
        this.station = station;
        this.rented = false;
        this.memberId = null;
        this.memberType = null;
    }

    public String getId() {
        return id;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String statusLine() {
        return id + " " + station + " " + (rented ? "rented" : "available");
    }
}
