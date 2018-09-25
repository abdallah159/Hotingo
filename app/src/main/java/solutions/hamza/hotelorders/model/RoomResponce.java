package solutions.hamza.hotelorders.model;

import java.util.List;

public class RoomResponce {
    private final List<String> imgs;

    private final String creationDate;

    private final String _id;

    private final String number;

    private final int price;

    private final String desc;

    private final int v;

    public RoomResponce(List<String> imgs, String creationDate, String id, String number, int price,
                        String desc, int v) {
        this.imgs = imgs;
        this.creationDate = creationDate;
        this._id = id;
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
        return "RoomResponce{" +
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
