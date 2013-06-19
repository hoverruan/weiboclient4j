package weiboclient4j;

import weiboclient4j.model.SearchSuggestionAppResult;
import weiboclient4j.model.SearchSuggestionAtUserResult;
import weiboclient4j.model.SearchSuggestionCompanyResult;
import weiboclient4j.model.SearchSuggestionSchoolResult;
import weiboclient4j.model.SearchSuggestionStatusResult;
import weiboclient4j.model.SearchSuggestionUserResult;
import weiboclient4j.model.Timeline;
import weiboclient4j.params.Paging;
import weiboclient4j.params.Query;
import weiboclient4j.params.SchoolType;
import weiboclient4j.params.SuggestionRange;
import weiboclient4j.params.SuggestionType;

import java.util.List;

/**
 * @author Hover Ruan
 */
public class SearchService extends AbstractService {
    public SearchService(WeiboClient client) {
        super(client);
    }

    public List<SearchSuggestionUserResult> searchSuggestionUsers(Query query) throws WeiboClientException {
        return searchSuggestionUsers(query, Paging.EMPTY);
    }

    public List<SearchSuggestionUserResult> searchSuggestionUsers(Query query, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/users",
                paging, withParams(query),
                SearchSuggestionUserResult.TYPE_SEARCH_SUGGESTION_USER_RESULT_LIST);
    }

    public List<SearchSuggestionStatusResult> searchSuggestionStatuses(Query query) throws WeiboClientException {
        return searchSuggestionStatuses(query, Paging.EMPTY);
    }

    public List<SearchSuggestionStatusResult> searchSuggestionStatuses(Query query, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/statuses",
                paging, withParams(query),
                SearchSuggestionStatusResult.TYPE_SEARCH_SUGGESTION_STATUS_RESULT_LIST);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query) throws WeiboClientException {
        return searchSuggestionSchools(query, Paging.EMPTY);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, Paging paging)
            throws WeiboClientException {
        return searchSuggestionSchools(query, SchoolType.All, paging);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, SchoolType schoolType)
            throws WeiboClientException {
        return searchSuggestionSchools(query, schoolType, Paging.EMPTY);
    }

    public List<SearchSuggestionSchoolResult> searchSuggestionSchools(Query query, SchoolType schoolType, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/schools",
                paging, withParams(query, schoolType),
                SearchSuggestionSchoolResult.TYPE_SEARCH_SUGGESTION_SCHOOL_RESULT_LIST);
    }

    public List<SearchSuggestionCompanyResult> searchSuggestionCompanies(Query query) throws WeiboClientException {
        return searchSuggestionCompanies(query, Paging.EMPTY);
    }

    public List<SearchSuggestionCompanyResult> searchSuggestionCompanies(Query query, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/companies",
                paging, withParams(query),
                SearchSuggestionCompanyResult.TYPE_SEARCH_SUGGESTION_COMPANY_RESULT_LIST);
    }

    public List<SearchSuggestionAppResult> searchSuggestionApps(Query query) throws WeiboClientException {
        return searchSuggestionApps(query, Paging.EMPTY);
    }

    public List<SearchSuggestionAppResult> searchSuggestionApps(Query query, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/apps",
                paging, withParams(query),
                SearchSuggestionAppResult.TYPE_SEARCH_SUGGESTION_APP_RESULT_LIST);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, SuggestionRange.All);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type, Paging paging)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, SuggestionRange.All, paging);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type,
                                                                      SuggestionRange range)
            throws WeiboClientException {
        return searchSuggestionAtUsers(query, type, range, Paging.EMPTY);
    }

    public List<SearchSuggestionAtUserResult> searchSuggestionAtUsers(Query query, SuggestionType type,
                                                                      SuggestionRange range, Paging paging)
            throws WeiboClientException {
        return doGet("search/suggestions/at_users",
                paging, withParams(query, type, range),
                SearchSuggestionAtUserResult.TYPE_SEARCH_SUGGESTION_AT_USER_RESULT_LIST);
    }

    public Timeline searchTopics(Query query) throws WeiboClientException {
        return searchTopics(query, Paging.EMPTY);
    }

    public Timeline searchTopics(Query query, Paging paging) throws WeiboClientException {
        return doGet("search/topics",
                paging, withParams(query), Timeline.class);
    }
}
