package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.node.ArrayNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTagList {
    private long id;
    private List<Tag> tags;

    public UserTagList() {
    }

    public UserTagList(long id, List<Tag> tags) {
        this.id = id;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public static List<UserTagList> parse(ArrayNode arrayNode) {
        List<UserTagList> result = new ArrayList<UserTagList>(arrayNode.size());

        Iterator<JsonNode> elements = arrayNode.getElements();
        while (elements.hasNext()) {
            JsonNode json = elements.next();
            long userId = json.get("id").asLong();
            List<Tag> tags = Tag.parseTags(json.get("tags"));

            UserTagList userTagList = new UserTagList(userId, tags);
            result.add(userTagList);
        }

        return result;
    }
}
