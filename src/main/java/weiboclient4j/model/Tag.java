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
public class Tag {
    private long id;
    private String value;
    private String tag;
    private int count;

    public Tag() {
    }

    public Tag(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public static List<Tag> parseTags(JsonNode json) {
        assert json != null;
        assert json.isArray();

        List<Tag> tags = new ArrayList<Tag>();

        for (JsonNode tagNode : (ArrayNode) json) {
            Iterator<String> fieldNames = tagNode.getFieldNames();
            if (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                String value = tagNode.get(fieldName).getValueAsText();

                tags.add(new Tag(Long.parseLong(fieldName), value));
            }
        }

        return tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}
