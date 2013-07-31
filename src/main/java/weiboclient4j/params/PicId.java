package weiboclient4j.params;

import weiboclient4j.utils.StringUtils;

public class PicId extends StringParam {
    public PicId(String... picIdList) {
        super(StringUtils.join(picIdList, ","));
    }

    protected String paramKey() {
        return "pic_id";
    }
}
