package dto;

public class VisitorDTO {
    private int visitorId;
    private String name;
    private String purpose;

    public VisitorDTO() {
    }

    public VisitorDTO(int visitorId, String name, String purpose) {
        this.visitorId = visitorId;
        this.name = name;
        this.purpose = purpose;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}