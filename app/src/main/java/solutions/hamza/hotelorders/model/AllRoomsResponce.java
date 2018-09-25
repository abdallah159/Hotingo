package solutions.hamza.hotelorders.model;

import java.util.List;


public class AllRoomsResponce {

    private final String status;

    private final String creationDate;

    private final String _id;

    private final String phone;

    private final String duration;

    private final String userRoomorder;

    private final Room room;

    private final int v;

    public AllRoomsResponce(String status, String creationDate, String _id, String phone,
                            String duration, String userRoomorder, Room room, int v) {
        this.status = status;
        this.creationDate = creationDate;
        this._id = _id;
        this.phone = phone;
        this.duration = duration;
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
        return _id;
    }

    public String getPhone() {
        return phone;
    }

    public String getDuration() {
        return duration;
    }

    public String getUserRoomorder() {
        return userRoomorder;
    }

    public Room getRoom() {
        return room;
    }

    public int getV() {
        return v;
    }

    public static class Room {
        private final List<String> imgs;

        private final String creationDate;

        private final String _id;

        private final String number;

        private final int price;

        private final String desc;

        private final int v;

        public Room(List<String> imgs, String creationDate, String _id, String number, int price,
                    String desc, int v) {
            this.imgs = imgs;
            this.creationDate = creationDate;
            this._id = _id;
            this.number = number;
            this.price = price;
            this.desc = desc;
            this.v = v;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public String getId() {
            return _id;
        }

        public String getNumber() {
            return number;
        }

        public int getPrice() {
            return price;
        }

        public String getDesc() {
            return desc;
        }

        public int getV() {
            return v;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "imgs=" + imgs +
                    ", creationDate='" + creationDate + '\'' +
                    ", _id='" + _id + '\'' +
                    ", number='" + number + '\'' +
                    ", price=" + price +
                    ", desc='" + desc + '\'' +
                    ", v=" + v +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AllRoomsResponce{" +
                "status='" + status + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", _id='" + _id + '\'' +
                ", phone='" + phone + '\'' +
                ", duration='" + duration + '\'' +
                ", userRoomorder='" + userRoomorder + '\'' +
                ", room=" + room +
                ", v=" + v +
                '}';
    }
}
