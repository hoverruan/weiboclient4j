package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLBS {
    private long uid;
    private int checkinNum;
    private int tipNum;
    private int photoNum;
    private int todoNum;
    private int geoStatusesNum;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
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

    public int getGeoStatusesNum() {
        return geoStatusesNum;
    }

    public void setGeoStatusesNum(int geoStatusesNum) {
        this.geoStatusesNum = geoStatusesNum;
    }
}
