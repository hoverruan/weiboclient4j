package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class Tag {
    private long id;
    private String value;

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
}
