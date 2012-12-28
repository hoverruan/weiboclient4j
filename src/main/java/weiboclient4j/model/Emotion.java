package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author hover.ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emotion {
    public static final TypeReference<List<Emotion>> TYPE_EMOTION_LIST = new TypeReference<List<Emotion>>() {
    };

    private String phrase;
    private String type;
    private String url;
    private boolean hot;
    private boolean common;
    private String category;
    private String icon;
    private String value;

    // API V1 fields
    private boolean isHot;
    private boolean isCommon;
    private int orderNumber;

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public boolean isIsCommon() {
        return isCommon;
    }

    public void setIsCommon(boolean isCommon) {
        this.isCommon = isCommon;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
