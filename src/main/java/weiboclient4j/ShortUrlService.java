package weiboclient4j;

import weiboclient4j.model.Url;
import weiboclient4j.model.UrlInfo;
import weiboclient4j.params.Paging;
import weiboclient4j.params.UrlLong;
import weiboclient4j.params.UrlShort;
import weiboclient4j.utils.CollectionUtils;
import static weiboclient4j.utils.CollectionUtils.firstItem;

import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class ShortUrlService extends AbstractService {
    public ShortUrlService(WeiboClient client) {
        super(client);
    }

    public Url shortenUrl(UrlLong urlLong) throws WeiboClientException {
        return firstItem(shortenUrl(CollectionUtils.newArrayList(urlLong)));
    }

    public List<Url> shortenUrl(Collection<UrlLong> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/shorten",
                withParams(UrlLong.urlLongParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url expandUrl(UrlShort urlShort) throws WeiboClientException {
        return firstItem(expandUrl(CollectionUtils.newArrayList(urlShort)));
    }

    public List<Url> expandUrl(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/expand",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public List<Url> getClicks(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/clicks",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getReferers(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/referers", withParams(urlShort), Url.class);
    }

    public Url getLocations(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/locations", withParams(urlShort), Url.class);
    }

    public List<Url> getShareCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/share/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShareStatuses(UrlShort urlShort) throws WeiboClientException {
        return getShareStatuses(urlShort, Paging.EMPTY);
    }

    public Url getShareStatuses(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/share/statuses",
                paging, withParams(urlShort), Url.class);
    }

    public List<Url> getCommentCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/comment/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getComments(UrlShort urlShort) throws WeiboClientException {
        return getComments(urlShort, Paging.EMPTY);
    }

    public Url getComments(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/comment/comments",
                paging, withParams(urlShort), Url.class);
    }

    public List<UrlInfo> getInfo(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlInfoResponse response = doGet("short_url/info",
                withParams(UrlShort.urlShortParam(urlList)), UrlInfoResponse.class);

        return response.getUrls();
    }
}
