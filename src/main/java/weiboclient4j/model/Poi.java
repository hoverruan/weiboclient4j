package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import weiboclient4j.utils.SimpleDateDeserializer;

import java.util.Date;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poi {
    private String poiid;
    private String title;
    private String address;
    private double lon;
    private double lat;
    private String category;
    private String city;
    private String province;
    private String country;
    private String url;
    private String phone;
    private String postcode;
    private String weiboId;
    private String icon;
    private String extra;
    private String rid;
    private String categorys;
    private String categoryName;
    private int checkinNum;
    private int checkinUserNum;
    @JsonDeserialize(using = SimpleDateDeserializer.class)
    private Date checkinTime;
    private int tipNum;
    private int photoNum;
    private int todoNum;

    public String getPoiid() {
        return poiid;
    }

    public void setPoiid(String poiid) {
        this.poiid = poiid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCheckinUserNum() {
        return checkinUserNum;
    }

    public void setCheckinUserNum(int checkinUserNum) {
        this.checkinUserNum = checkinUserNum;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public int getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(int checkinNum) {
        this.checkinNum = checkinNum;
    }

    public int getTipNum() {
        return tipNum;
    }

    public void setTipNum(int tipNum) {
        this.tipNum = tipNum;
    }

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }

    public int getTodoNum() {
        return todoNum;
    }

    public void setTodoNum(int todoNum) {
        this.todoNum = todoNum;
    }
}
