package weiboclient4j.params;

import weiboclient4j.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class AddressCode extends StringParam {
    public AddressCode(String value) {
        super(value);
    }

    protected String paramKey() {
        throw new UnsupportedOperationException("paramKey not supported yet");
    }

    public static ParameterAction codesParam(final Collection<AddressCode> codes) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (codes != null && codes.size() > 0) {
                    List<String> codeList = new ArrayList<String>(codes.size());
                    for (AddressCode code : codes) {
                        codeList.add(code.getValue());
                    }
                    params.add("codes", StringUtils.join(codeList, ","));
                }
            }
        };
    }
}
