package bikeshare;

public enum MemberType {
    STUDENT("student", 2),
    STAFF("staff", 3);

    private final String label;
    private final int feePerMinute;

    MemberType(String label, int feePerMinute) {
        this.label = label;
        this.feePerMinute = feePerMinute;
    }

    public int feePerMinute() {
        return feePerMinute;
    }

    public String label() {
        return label;
    }

    public static MemberType fromText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Member type is required");
        }
        String value = text.trim().toLowerCase();
        if (value.equals("student")) {
            return STUDENT;
        }
        if (value.equals("staff")) {
            return STAFF;
        }
        throw new IllegalArgumentException("Member type must be student or staff");
    }
}
