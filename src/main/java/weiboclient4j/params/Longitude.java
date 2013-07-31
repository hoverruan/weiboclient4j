package weiboclient4j.params;

import weiboclient4j.StatusService;

/**
 * @author Hover Ruan
 */
public class Longitude extends FloatParam implements StatusService.UploadImageUrlParam {
    public Longitude(float value) {
        super(value);
    }

    protected String paramKey() {
        return "long";
    }
}
