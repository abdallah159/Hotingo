package solutions.hamza.hotelorders.model;

public class BookRoom {



    private String phone;
    private String duration;
    private String notes;


    public BookRoom(String phone, String duration, String notes) {
        this.phone = phone;
        this.duration = duration;
        this.notes = notes;
    }

    public BookRoom(String duration) {
        this.duration = duration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "BookRoom{" +
                "phone='" + phone + '\'' +
                ", duration='" + duration + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
