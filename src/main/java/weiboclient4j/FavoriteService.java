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
    public FavoriteService(WeiboClient2 client) {
        super(client);
    }

    public Favorites getFavorites() throws WeiboClientException {
        return getFavorites(Paging.EMPTY);
    }

    public Favorites getFavorites(Paging paging) throws WeiboClientException {
        return doGet("favorites", paging, Favorites.class);
    }

    public FavoritesIds getFavoritesIds() throws WeiboClientException {
        return getFavoritesIds(Paging.EMPTY);
    }

    public FavoritesIds getFavoritesIds(Paging paging) throws WeiboClientException {
        return doGet("favorites/ids", paging, FavoritesIds.class);
    }

    public FavoritesItem showFavorites(Id id) throws WeiboClientException {
        return doGet("favorites/show", withParams(id), FavoritesItem.class);
    }

    public Favorites getFavoritesByTags(Tid tid) throws WeiboClientException {
        return getFavoritesByTags(tid, Paging.EMPTY);
    }

    public Favorites getFavoritesByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags", paging, withParams(tid), Favorites.class);
    }

    public FavoritesTags getFavoritesTags() throws WeiboClientException {
        return getFavoritesTags(Paging.EMPTY);
    }

    public FavoritesTags getFavoritesTags(Paging paging) throws WeiboClientException {
        return doGet("favorites/tags", paging, FavoritesTags.class);
    }

    public FavoritesIds getFavoritesIdsByTags(Tid tid) throws WeiboClientException {
        return getFavoritesIdsByTags(tid, Paging.EMPTY);
    }

    public FavoritesIds getFavoritesIdsByTags(Tid tid, Paging paging) throws WeiboClientException {
        return doGet("favorites/by_tags/ids", paging, withParams(tid), FavoritesIds.class);
    }

    public FavoritesItem createFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/create", withParams(id), FavoritesItem.class);
    }

    public FavoritesItem destroyFavorites(Id id) throws WeiboClientException {
        return doPost("favorites/destroy", withParams(id), FavoritesItem.class);
    }

    public boolean destroyFavoritesBatch(Collection<Id> ids) throws WeiboClientException {
        ResultResponse response = doPost("favorites/destroy_batch",
                withParams(Id.idsParam(ids)), ResultResponse.class);

        return response.isResult();
    }

    public FavoritesItem updateFavoritesTags(Id id) throws WeiboClientException {
        return updateFavoritesTags(id, null);
    }

    public FavoritesItem updateFavoritesTags(Id id, Collection<TagName> tags) throws WeiboClientException {
        return doPost("favorites/tags/update",
                withParams(id, TagName.tagsParam(tags)), FavoritesItem.class);
    }

    public Tag updateFavoritesTagsBatch(Tid tid, TagName tagName) throws WeiboClientException {
        return doPost("favorites/tags/update_batch",
                withParams(tid, tagName), Tag.class);
    }

    public boolean destroyFavoritesTagsBatch(Tid tid) throws WeiboClientException {
        ResultResponse response = doPost("favorites/tags/destroy_batch",
                withParams(tid), ResultResponse.class);
        return response.isResult();
    }
}
