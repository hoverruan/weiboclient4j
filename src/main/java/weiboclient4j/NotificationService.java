package weiboclient4j;

import weiboclient4j.model.NotificationResult;
import weiboclient4j.params.ActionUrl;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import weiboclient4j.params.TemplateId;
import weiboclient4j.params.Uid;
import weiboclient4j.utils.StringUtils;

import java.util.Collection;

/**
 * @author Hover Ruan
 */
public class NotificationService extends AbstractService {
    public NotificationService(WeiboClient client) {
        super(client);
    }

    public NotificationResult send(Collection<Uid> uids, TemplateId templateId) throws WeiboClientException {
        return send(uids, templateId, null);
    }

    public NotificationResult send(Collection<Uid> uids, TemplateId templateId, ActionUrl actionUrl)
            throws WeiboClientException {
        return send(uids, templateId, null, 0, actionUrl);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count) throws WeiboClientException {
        return send(uids, templateId, objects1, objects1Count, null, 0);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count,
                                   final String objects2,
                                   final int objects2Count) throws WeiboClientException {
        return send(uids, templateId, objects1, objects1Count, objects2, objects2Count, null, 0);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count,
                                   final String objects2,
                                   final int objects2Count,
                                   final String objects3,
                                   final int objects3Count) throws WeiboClientException {
        return send(uids, templateId, objects1, objects1Count, objects2, objects2Count, objects3, objects3Count,
                ActionUrl.EMPTY);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count,
                                   ActionUrl actionUrl) throws WeiboClientException {
        return send(uids, templateId, objects1, objects1Count, null, 0, actionUrl);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count,
                                   final String objects2,
                                   final int objects2Count,
                                   ActionUrl actionUrl) throws WeiboClientException {
        return send(uids, templateId, objects1, objects1Count, objects2, objects2Count, null, 0, actionUrl);
    }

    public NotificationResult send(Collection<Uid> uids,
                                   TemplateId templateId,
                                   final String objects1,
                                   final int objects1Count,
                                   final String objects2,
                                   final int objects2Count,
                                   final String objects3,
                                   final int objects3Count,
                                   ActionUrl actionUrl) throws WeiboClientException {
        ParameterAction objectsParamAction = new ParameterAction() {
            public void addParameter(Parameters params) {
                if (StringUtils.isNotBlank(objects1)) {
                    params.add("objects1", objects1);
                }

                if (objects1Count > 0) {
                    params.add("objects1_count", objects1Count);
                }

                if (StringUtils.isNotBlank(objects2)) {
                    params.add("objects2", objects2);
                }

                if (objects2Count > 0) {
                    params.add("objects2_count", objects2Count);
                }

                if (StringUtils.isNotBlank(objects3)) {
                    params.add("objects3", objects3);
                }

                if (objects3Count > 0) {
                    params.add("objects3_count", objects3Count);
                }
            }
        };

        return doPost("notification/send",
                withParams(Uid.uidsParam(uids), templateId, objectsParamAction, actionUrl), NotificationResult.class);
    }
}
