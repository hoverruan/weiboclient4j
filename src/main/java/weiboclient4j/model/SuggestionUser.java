package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuggestionUser {
    private Long uid;

    private List<Reason> reasonList;

    public static List<SuggestionUser> convertFromRawSuggestionUserList(List<RawSuggestionUser> rawSuggestionUsers) {
        ArrayList<SuggestionUser> result = new ArrayList<SuggestionUser>();

        for (RawSuggestionUser rawSuggestionUser : rawSuggestionUsers) {
            result.add(convertFromRawSuggestionUser(rawSuggestionUser));
        }

        return result;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<Reason> getReasonList() {
        return reasonList;
    }

    public void setReasonList(List<Reason> reasonList) {
        this.reasonList = reasonList;
    }

    private static SuggestionUser convertFromRawSuggestionUser(RawSuggestionUser rawSuggestionUser) {
        SuggestionUser suggestionUser = new SuggestionUser();

        suggestionUser.setUid(rawSuggestionUser.getUid());
        RawSuggestionUser.Reason rawReason = rawSuggestionUser.getReason();
        List<Reason> reasonList = new ArrayList<Reason>();

        if (rawReason.getC() != null) {
            reasonList.add(new Reason(rawReason.getC(), ReasonType.C));
        }

        if (rawReason.getF() != null) {
            reasonList.add(new Reason(rawReason.getF(), ReasonType.F));
        }

        if (rawReason.getH() != null) {
            reasonList.add(new Reason(rawReason.getH(), ReasonType.H));
        }

        if (rawReason.getS() != null) {
            reasonList.add(new Reason(rawReason.getS(), ReasonType.S));
        }

        if (rawReason.getT() != null) {
            reasonList.add(new Reason(rawReason.getT(), ReasonType.T));
        }

        suggestionUser.setReasonList(reasonList);

        return suggestionUser;
    }

    public enum ReasonType {
        F, H, S, C, T
    }

    public static class Reason {
        private String name;

        private ReasonType type;

        private List<Long> uidList;

        private int numOfRelationship;

        public Reason(RawSuggestionUser.Reason.Item item, ReasonType reasonType) {
            setName(item.getName());
            setType(reasonType);
            setUidList(uidList);
            setNumOfRelationship(item.getN());
        }

        public ReasonType getType() {
            return type;
        }

        public void setType(ReasonType type) {
            this.type = type;
        }

        public int getNumOfRelationship() {
            return numOfRelationship;
        }

        public void setNumOfRelationship(int numOfRelationship) {
            this.numOfRelationship = numOfRelationship;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Long> getUidList() {
            return uidList;
        }

        public void setUidList(List<Long> uidList) {
            this.uidList = uidList;
        }
    }
}
