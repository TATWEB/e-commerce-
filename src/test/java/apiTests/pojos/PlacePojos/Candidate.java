package apiTests.pojos.PlacePojos;

public class Candidate {


    public String formatted_address;
    public String name;
    public OpeningHours opening_hours;
    public Integer rating;

    public Candidate(){}

    public Candidate(String formatted_address, String name, OpeningHours opening_hours, Integer rating) {
        this.formatted_address = formatted_address;
        this.name = name;
        this.opening_hours = opening_hours;
        this.rating = rating;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(OpeningHours opening_hours) {
        this.opening_hours = opening_hours;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "formatted_address='" + formatted_address + '\'' +
                ", name='" + name + '\'' +
                ", opening_hours=" + opening_hours +
                ", rating=" + rating +
                '}';
    }
}
