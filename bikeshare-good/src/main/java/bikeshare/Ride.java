package bikeshare;

public class Ride {
    private final String bikeId;
    private final String memberId;
    private final MemberType memberType;
    private final String station;
    private final int minutes;
    private final int fee;

    public Ride(String bikeId, String memberId, MemberType memberType, String station, int minutes, int fee) {
        this.bikeId = bikeId;
        this.memberId = memberId;
        this.memberType = memberType;
        this.station = station;
        this.minutes = minutes;
        this.fee = fee;
    }

    public String csvLine() {
        return bikeId + "," + memberId + "," + memberType.label() + "," + station + "," + minutes + "," + fee;
    }
}