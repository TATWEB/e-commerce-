package apiTests.pojos.PlacePojos;

public class OpeningHours {

    public Boolean open_now;

    public OpeningHours(){}
    public OpeningHours(Boolean open_now) {
        this.open_now = open_now;
    }

    public Boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(Boolean open_now) {
        this.open_now = open_now;
    }

    @Override
    public String toString() {
        return "OpeningHours{" +
                "open_now=" + open_now +
                '}';
    }
}
