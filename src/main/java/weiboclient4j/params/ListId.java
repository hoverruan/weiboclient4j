package weiboclient4j.params;

import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public class ListId extends StringParam implements StatusService.UploadImageUrlParam {
    public ListId(String value) {
        super(value);
    }

    @Override
    protected String paramKey() {
        return "list_id";
    }
}
