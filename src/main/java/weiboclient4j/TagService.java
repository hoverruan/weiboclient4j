package weiboclient4j;

import org.codehaus.jackson.node.ArrayNode;
import weiboclient4j.model.Tag;
import weiboclient4j.model.UserTagList;
import weiboclient4j.params.Paging;
import weiboclient4j.params.TagId;
import weiboclient4j.params.TagName;
import weiboclient4j.params.Uid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class TagService extends AbstractService {
    public TagService(WeiboClient client) {
        super(client);
    }

    public List<Tag> getTags(Uid uid) throws WeiboClientException {
        return getTags(uid, Paging.EMPTY);
    }

    public List<Tag> getTags(Uid uid, Paging paging) throws WeiboClientException {
        ArrayNode arrayNode = doGet("tags", paging, withParams(uid), ArrayNode.class);

        return Tag.parseTags(arrayNode);
    }

    public List<UserTagList> getBatch(Collection<Uid> uids) throws WeiboClientException {
        ArrayNode arrayNode = doGet("tags/tags_batch",
                withParams(Uid.uidsParam(uids)), ArrayNode.class);

        return UserTagList.parse(arrayNode);
    }

    public List<Tag> getSuggestions() throws WeiboClientException {
        return getSuggestions(Paging.EMPTY);
    }

    public List<Tag> getSuggestions(Paging paging) throws WeiboClientException {
        List<LongIdStringValue> list = doGet("tags/suggestions", paging, TYPE_LONG_ID_STRING_VALUE_LIST);

        List<Tag> tags = new ArrayList<Tag>(list.size());
        for (LongIdStringValue value : list) {
            tags.add(new Tag(value.getId(), value.getValue()));
        }

        return tags;
    }

    public List<Long> create(Collection<TagName> tagNames) throws WeiboClientException {
        List<TagActionResponse> responseList = doPost("tags/create",
                withParams(TagName.tagsParam(tagNames)), TYPE_TAG_ACTION_RESPONSE_LIST);

        return TagActionResponse.toLongList(responseList);
    }

    public boolean destroy(TagId tagId) throws WeiboClientException {
        ResultResponse response = doPost("tags/destroy",
                withParams(tagId), ResultResponse.class);

        return response.isResult();
    }

    public List<Long> destroyBatch(Collection<TagId> tagIds) throws WeiboClientException {
        List<TagActionResponse> responseList = doPost("tags/destroy_batch",
                withParams(TagId.idsParam(tagIds)), TYPE_TAG_ACTION_RESPONSE_LIST);

        return TagActionResponse.toLongList(responseList);
    }
}
