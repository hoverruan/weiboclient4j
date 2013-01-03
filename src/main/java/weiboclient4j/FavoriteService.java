package weiboclient4j;

import weiboclient4j.model.Favorites;
import weiboclient4j.model.FavoritesIds;
import weiboclient4j.model.FavoritesItem;
import weiboclient4j.model.FavoritesTags;
import weiboclient4j.model.Tag;
import weiboclient4j.params.Id;
import weiboclient4j.params.Paging;
import weiboclient4j.params.TagName;
import weiboclient4j.params.Tid;

import java.util.Collection;

/**
 * @author Hover Ruan
 */
public class FavoriteService extends AbstractService {
    public FavoriteService(WeiboClient client) {
        super(client);
    }

    public Favorites getFavorites() throws WeiboClientException {
        return getFavorites(Paging.EMPTY);
    }

    public Favorites getFavorites(Paging paging) throws WeiboClientException {
        return doGet("favorites", paging, Favorites.class);
    }

    public FavoritesIds getIds() throws WeiboClientException {
        return getIds(Paging.EMPTY);
    }

    public FavoritesIds getIds(Paging paging) throws WeiboClientException {
        return doGet("favorites/ids", paging, FavoritesIds.class);
    }

    public FavoritesItem show(Id id) throws WeiboClientException {
        return doGet("favorites/show", withParams(id), FavoritesItem.class);
    }

    public Favorites getFavoritesByTags(Tid tid) throws WeiboClientException {
        return getFavoritesByTags(tid, Paging.EMPTY);
    }

    public Favorites getFavoritesByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags", paging, withParams(tid), Favorites.class);
    }

    public FavoritesTags getTags() throws WeiboClientException {
        return getTags(Paging.EMPTY);
    }

    public FavoritesTags getTags(Paging paging) throws WeiboClientException {
        return doGet("favorites/tags", paging, FavoritesTags.class);
    }

    public FavoritesIds getIdsByTags(Tid tid) throws WeiboClientException {
        return getIdsByTags(tid, Paging.EMPTY);
    }

    public FavoritesIds getIdsByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags/ids", paging, withParams(tid), FavoritesIds.class);
    }

    public FavoritesItem create(Id id) throws WeiboClientException {
        return doPost("favorites/create", withParams(id), FavoritesItem.class);
    }

    public FavoritesItem destroy(Id id) throws WeiboClientException {
        return doPost("favorites/destroy", withParams(id), FavoritesItem.class);
    }

    public boolean destroyBatch(Collection<Id> ids) throws WeiboClientException {
        ResultResponse response = doPost("favorites/destroy_batch",
                withParams(Id.idsParam(ids)), ResultResponse.class);

        return response.isResult();
    }

    public FavoritesItem updateTags(Id id) throws WeiboClientException {
        return updateTags(id, null);
    }

    public FavoritesItem updateTags(Id id, Collection<TagName> tags) throws WeiboClientException {
        return doPost("favorites/tags/update",
                withParams(id, TagName.tagsParam(tags)), FavoritesItem.class);
    }

    public Tag updateTagsBatch(Tid tid, TagName tagName) throws WeiboClientException {
        return doPost("favorites/tags/update_batch",
                withParams(tid, tagName), Tag.class);
    }

    public boolean destroyTagsBatch(Tid tid) throws WeiboClientException {
        ResultResponse response = doPost("favorites/tags/destroy_batch",
                withParams(tid), ResultResponse.class);
        return response.isResult();
    }
}
