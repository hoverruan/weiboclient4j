package weiboclient4j.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RawSuggestionUser {
    public static final TypeReference<List<RawSuggestionUser>> TYPE_SUGGESTION_USER_LIST =
            new TypeReference<List<RawSuggestionUser>>() {
    };

    private long uid;
    private Reason reason;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Reason {
        private Item f;
        private Item h;
        private Item c;
        private Item s;
        private Item t;

        public Item getS() {
            return s;
        }

        public void setS(Item s) {
            this.s = s;
        }

        public Item getT() {
            return t;
        }

        public void setT(Item t) {
            this.t = t;
        }

        public Item getF() {
            return f;
        }

        public void setF(Item f) {
            this.f = f;
        }

        public Item getH() {
            return h;
        }

        public void setH(Item h) {
            this.h = h;
        }

        public Item getC() {
            return c;
        }

        public void setC(Item c) {
            this.c = c;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Item {
            private String name;
            private List<Long> uid;
            private int n;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Long> getUid() {
                return uid;
            }

            public void setUid(List<Long> uid) {
                this.uid = uid;
            }

            public int getN() {
                return n;
            }

            public void setN(int n) {
                this.n = n;
            }
        }
    }
}
