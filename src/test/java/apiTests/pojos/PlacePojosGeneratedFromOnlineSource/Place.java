
package apiTests.pojos.PlacePojosGeneratedFromOnlineSource;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "candidates",
    "status"
})
@Generated("jsonschema2pojo")
public class Place {

    @JsonProperty("candidates")
    private List<Candidate> candidates = null;
    @JsonProperty("status")
    private String status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Place() {
    }

    /**
     * 
     * @param candidates
     * @param status
     */
    public Place(List<Candidate> candidates, String status) {
        super();
        this.candidates = candidates;
        this.status = status;
    }

    @JsonProperty("candidates")
    public List<Candidate> getCandidates() {
        return candidates;
    }

    @JsonProperty("candidates")
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
