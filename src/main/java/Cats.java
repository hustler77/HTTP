import com.fasterxml.jackson.annotation.JsonProperty;

public class Cats {

    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upvotes;


    public Cats(
            @JsonProperty("id") String id,
            @JsonProperty("String") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes
    ) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "Statement {" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}

