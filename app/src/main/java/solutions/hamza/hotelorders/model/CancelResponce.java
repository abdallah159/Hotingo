package solutions.hamza.hotelorders.model;

import java.util.List;


public class CancelResponce {
    private final String status;

    private final String creationDate;

    private final String id;

    private final String duration;

    private final String notes;

    private final String phone;

    private final UserRoomorder userRoomorder;

    private final Room room;

    private final int v;

    public CancelResponce(String status, String creationDate, String id, String duration,
                          String notes, String phone, UserRoomorder userRoomorder, Room room, int v) {
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

    public UserRoomorder getUserRoomorder() {
        return userRoomorder;
    }

    public Room getRoom() {
        return room;
    }

    public int getV() {
        return v;
    }

    public static class UserRoomorder {
        private final String type;

        private final String img;

        private final String creationDate;

        private final String id;

        private final String name;

        private final String phone;

        private final String email;

        private final String password;

        private final int v;

        public UserRoomorder(String type, String img, String creationDate, String id, String name,
                             String phone, String email, String password, int v) {
            this.type = type;
            this.img = img;
            this.creationDate = creationDate;
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.password = password;
            this.v = v;
        }

        public String getType() {
            return type;
        }

        public String getImg() {
            return img;
        }

        public String getCreationDate() {
            return creationDate;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public int getV() {
            return v;
        }
    }

    public static class Room {
        private final List<String> imgs;

        private final String creationDate;

        private final String id;

        private final String number;

        private final int price;

        private final String desc;

        private final int v;

        public Room(List<String> imgs, String creationDate, String id, String number, int price,
                    String desc, int v) {
            this.imgs = imgs;
            this.creationDate = creationDate;
            this.id = id;
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
            return id;
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
    }
}
