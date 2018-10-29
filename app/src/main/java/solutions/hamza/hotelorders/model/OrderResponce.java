package solutions.hamza.hotelorders.model;

public class OrderResponce {
    private String status;

    private String creationDate;

    private String id;

    private String note;

    private int roomNo;

    private String user;

    private String service;

    private int v;


    public OrderResponce(String status, String creationDate, String id, String note, int roomNo,
                         String userServicesOrded, String service, int v) {
        this.status = status;
        this.creationDate = creationDate;
        this.id = id;
        this.note = note;
        this.roomNo = roomNo;
        this.user = userServicesOrded;
        this.service = service;
        this.v = v;
    }

    public OrderResponce(String note, int roomNo) {
        this.note = note;
        this.roomNo = roomNo;
    }

    public String getStatus() {
        return status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public String getUserServicesOrded() {
        return user;
    }

    public String getService() {
        return service;
    }

    public int getV() {
        return v;
    }

    @Override
    public String toString() {
        return "OrderResponce{" +
                "status='" + status + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", id='" + id + '\'' +
                ", note='" + note + '\'' +
                ", roomNo=" + roomNo +
                ", userServicesOrded='" + user + '\'' +
                ", service='" + service + '\'' +
                ", v=" + v +
                '}';
    }
}
