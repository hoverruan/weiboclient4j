package weiboclient4j.model;

import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class DomesticResult {

    private static final int MAX_RESULTS = 50;

    private boolean domestic;

    private String address;

    /**
     * json format:
     *
     * <pre>
     * {
     *   "g1": {
     *     "type": 1,
     *     "address": "台湾"
     *   },
     *   "g2": {
     *     "type": 1,
     *     "address": "河北省 衡水市 武强县"
     *   },
     *   "g3": {
     *     "type": 0,
     *     "address": ""
     *   }
     * }
     * </pre>
     *
     * @param json response json string
     * @return list of DomesticResult
     */
    public static List<DomesticResult> parseList(JsonNode json) {
        List<DomesticResult> results = new ArrayList<DomesticResult>();

        for (int i = 1; i <= MAX_RESULTS; i++) {
            String itemFieldName = String.format("g%d", i);

            if (json.has(itemFieldName)) {
                results.add(parseItem(json.get(itemFieldName)));
            } else {
                break;
            }
        }

        return results;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public void setDomestic(boolean domestic) {
        this.domestic = domestic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private static DomesticResult parseItem(JsonNode itemNode) {
        DomesticResult result = new DomesticResult();

        result.setDomestic(itemNode.get("type").asInt() == 1);
        result.setAddress(itemNode.get("address").asText());

        return result;
    }
}
