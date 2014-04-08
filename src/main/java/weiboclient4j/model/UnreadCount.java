package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnreadCount {

    // V2 fields
    private int dm;
    private int status;
    private int follower;
    private int cmt;
    private int mentionStatus;
    private int mentionCmt;
    private int group;
    private int privateGroup;
    private int notice;
    private int invite;
    private int badge;
    private int photo;
    private int msgbox;

    public int getDm() {
        return dm;
    }

    public void setDm(int dm) {
        this.dm = dm;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getCmt() {
        return cmt;
    }

    public void setCmt(int cmt) {
        this.cmt = cmt;
    }

    public int getMentionStatus() {
        return mentionStatus;
    }

    public void setMentionStatus(int mentionStatus) {
        this.mentionStatus = mentionStatus;
    }

    public int getMentionCmt() {
        return mentionCmt;
    }

    public void setMentionCmt(int mentionCmt) {
        this.mentionCmt = mentionCmt;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getPrivateGroup() {
        return privateGroup;
    }

    public void setPrivateGroup(int privateGroup) {
        this.privateGroup = privateGroup;
    }

    public int getNotice() {
        return notice;
    }

    public void setNotice(int notice) {
        this.notice = notice;
    }

    public int getInvite() {
        return invite;
    }

    public void setInvite(int invite) {
        this.invite = invite;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getMsgbox() {
        return msgbox;
    }

    public void setMsgbox(int msgbox) {
        this.msgbox = msgbox;
    }
}
