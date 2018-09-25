package solutions.hamza.hotelorders.model;


public class ExtendResponse {
    private final String status;

    private final String creationDate;

    private final String id;

    private final String duration;

    private final String notes;

    private final String phone;

    private final String userRoomorder;

    private final String room;

    private final int v;

    public ExtendResponse(String status, String creationDate, String id, String duration,
                          String notes, String phone, String userRoomorder, String room, int v) {
        this.status = status;
        this.creationDate = creationDate;
        this.id = id;
        this.duration = duration;
        this.notes = notes;
        this.phone = phone;
        this.userRoomorder = userRoomorder;
        this.room = room;
        this.v = v;
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

    public String getDuration() {
        return duration;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserRoomorder() {
        return userRoomorder;
    }

    public String getRoom() {
        return room;
    }

    public int getV() {
        return v;
    }
}
