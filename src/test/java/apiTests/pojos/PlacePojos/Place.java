package apiTests.pojos.PlacePojos;

import java.util.ArrayList;

public class Place {


    @Override
    public String toString() {
        return "Place{" +
                "candidates=" + candidates +
                ", status='" + status + '\'' +
                '}';
    }

    public ArrayList<Candidate> candidates;
      public String status;


    public Place(){}

    public Place(ArrayList<Candidate> candidates, String status) {
        this.candidates = candidates;
        this.status = status;
    }


    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
