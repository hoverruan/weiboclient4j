package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.type.TypeReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tag {
    public static final TypeReference<List<Tag>> TYPE_TAG_LIST = new TypeReference<List<Tag>>() {
    };

    private long id;
    private String tag;
    private int count;
    private int weight;

    public Tag() {
    }

    public Tag(long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public static List<Tag> parseTags(JsonNode json) {
        assert json != null;
        assert json.isArray();

        List<Tag> tags = new ArrayList<Tag>();

        for (JsonNode tagNode : (ArrayNode) json) {
            tags.add(parseTag(tagNode));
        }

        return tags;
    }

    public static Tag parseTag(JsonNode tagNode) {
        Tag tag = new Tag();

        Iterator<String> fieldNames = tagNode.getFieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode fieldValue = tagNode.get(fieldName);
            if ("weight".equals(fieldName)) {
                tag.setWeight(fieldValue.asInt());
            } else {
                tag.setId(Long.parseLong(fieldName));
                tag.setTag(fieldValue.asText());
            }
        }

        return tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
