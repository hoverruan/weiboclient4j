package weiboclient4j;

import weiboclient4j.model.Url;
import weiboclient4j.model.UrlInfo;
import weiboclient4j.params.Paging;
import weiboclient4j.params.UrlLong;
import weiboclient4j.params.UrlShort;
import weiboclient4j.utils.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author Hover Ruan
 */
public class ShortUrlService extends AbstractService {
    public ShortUrlService(WeiboClient2 client) {
        super(client);
    }

    public Url shortenUrl(UrlLong urlLong) throws WeiboClientException {
        List<Url> result = shortenUrl(CollectionUtils.newArrayList(urlLong));

        return result != null && result.size() > 0 ? result.get(0) : null;
    }

    public List<Url> shortenUrl(Collection<UrlLong> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/shorten",
                withParams(UrlLong.urlLongParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url expandUrl(UrlShort urlShort) throws WeiboClientException {
        List<Url> result = expandUrl(CollectionUtils.newArrayList(urlShort));

        return result != null && result.size() > 0 ? result.get(0) : null;
    }

    public List<Url> expandUrl(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/expand",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public List<Url> getShortUrlClicks(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/clicks",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlReferers(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/referers", withParams(urlShort), Url.class);
    }

    public Url getShortUrlLocations(UrlShort urlShort) throws WeiboClientException {
        return doGet("short_url/locations", withParams(urlShort), Url.class);
    }

    public List<Url> getShortUrlShareCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/share/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlShareStatuses(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlShareStatuses(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/share/statuses",
                paging, withParams(urlShort), Url.class);
    }

    public List<Url> getShortUrlCommentCounts(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlResponse response = doGet("short_url/comment/counts",
                withParams(UrlShort.urlShortParam(urlList)), UrlResponse.class);

        return response.getUrls();
    }

    public Url getShortUrlComments(UrlShort urlShort) throws WeiboClientException {
        return getShortUrlComments(urlShort, Paging.EMPTY);
    }

    public Url getShortUrlComments(UrlShort urlShort, Paging paging) throws WeiboClientException {
        return doGet("short_url/comment/comments",
                paging, withParams(urlShort), Url.class);
    }

    public List<UrlInfo> getShortUrlInfo(Collection<UrlShort> urlList) throws WeiboClientException {
        UrlInfoResponse response = doGet("short_url/info",
                withParams(UrlShort.urlShortParam(urlList)), UrlInfoResponse.class);

        return response.getUrls();
    }
}
