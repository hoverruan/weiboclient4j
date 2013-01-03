package weiboclient4j;

import org.codehaus.jackson.JsonNode;
import weiboclient4j.model.GlobalTrendList;
import weiboclient4j.model.Trend;
import weiboclient4j.model.TrendStatus;
import weiboclient4j.params.BaseApp;
import weiboclient4j.params.Paging;
import weiboclient4j.params.TrendId;
import weiboclient4j.params.TrendName;
import weiboclient4j.params.Uid;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class TrendService extends AbstractService {
    public TrendService(WeiboClient client) {
        super(client);
    }

    public List<Trend> getTrends(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("trends",
                paging, withParams(uid), Trend.TYPE_TREND_LIST);
    }

    public TrendStatus getStatus(TrendName trendName) throws WeiboClientException {
        return doGet("trends/is_follow",
                withParams(trendName), TrendStatus.class);
    }

    public GlobalTrendList getHourly() throws WeiboClientException {
        return getHourly(BaseApp.No);
    }

    public GlobalTrendList getHourly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/hourly",
                withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getDaily() throws WeiboClientException {
        return getDaily(BaseApp.No);
    }

    public GlobalTrendList getDaily(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/daily", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getWeekly() throws WeiboClientException {
        return getWeekly(BaseApp.No);
    }

    public GlobalTrendList getWeekly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/weekly", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public long follow(TrendName trendName) throws WeiboClientException {
        FollowTrendResponse response = doPost("trends/follow",
                withParams(trendName), FollowTrendResponse.class);

        return response.getTopicid();
    }

    public boolean destroy(TrendId trendId) throws WeiboClientException {
        ResultResponse response = doPost("trends/destroy",
                withParams(trendId), ResultResponse.class);

        return response.isResult();
    }
}
