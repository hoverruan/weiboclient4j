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
    public TrendService(WeiboClient2 client) {
        super(client);
    }

    public List<Trend> getTrends(Uid uid, Paging paging) throws WeiboClientException {
        return doGet("trends",
                paging, withParams(uid), Trend.TYPE_TREND_LIST);
    }

    public TrendStatus getTrendStatus(TrendName trendName) throws WeiboClientException {
        return doGet("trends/is_follow",
                withParams(trendName), TrendStatus.class);
    }

    public GlobalTrendList getTrendsHourly() throws WeiboClientException {
        return getTrendsHourly(BaseApp.No);
    }

    public GlobalTrendList getTrendsHourly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/hourly",
                withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsDaily() throws WeiboClientException {
        return getTrendsDaily(BaseApp.No);
    }

    public GlobalTrendList getTrendsDaily(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/daily", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public GlobalTrendList getTrendsWeekly() throws WeiboClientException {
        return getTrendsWeekly(BaseApp.No);
    }

    public GlobalTrendList getTrendsWeekly(BaseApp baseApp) throws WeiboClientException {
        JsonNode json = doGet("trends/weekly", withParams(baseApp), JsonNode.class);

        return new GlobalTrendList(json);
    }

    public long followTrend(TrendName trendName) throws WeiboClientException {
        FollowTrendResponse response = doPost("trends/follow",
                withParams(trendName), FollowTrendResponse.class);

        return response.getTopicid();
    }

    public boolean destroyTrend(TrendId trendId) throws WeiboClientException {
        ResultResponse response = doPost("trends/destroy",
                withParams(trendId), ResultResponse.class);

        return response.isResult();
    }
}
