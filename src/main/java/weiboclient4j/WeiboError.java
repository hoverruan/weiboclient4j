package weiboclient4j;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import static weiboclient4j.utils.StringUtils.isBlank;

/**
 * @author Hover Ruan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeiboError {
    // http://open.weibo.com/wiki/Error_code
    public static final int ERR_SYSTEM = 10001;

    public static final int ERR_SERVICE_UNAVAILABLE = 10002;

    public static final int ERR_REMOTE_SERVICE = 10003;

    public static final int ERR_IP_LIMIT = 10004;

    public static final int ERR_PERMISSION_DENIED = 10005;

    public static final int ERR_MISSING_APPKEY = 10006;

    public static final int ERR_UNSUPPORTED_MEDIA_TYPE = 10007;

    public static final int ERR_PARAMETER = 10008;

    public static final int ERR_SYSTEM_BUSY = 10009;

    public static final int ERR_JOB_EXPIRED = 10010;

    public static final int ERR_RPC = 10011;

    public static final int ERR_ILLEGAL_REQUEST = 10012;

    public static final int ERR_INVALID_WEIBO_USER = 10013;

    public static final int ERR_INSUFFICIENT_PERMISSIONS = 10014;

    public static final int ERR_MISS_REQUIRED_PARAMETER = 10016;

    public static final int ERR_INVALID_PARAMETER_VALUE = 10017;

    public static final int ERR_REQUEST_BODY_EXCEED = 10018;

    public static final int ERR_API_NOT_FOUND = 10020;

    public static final int ERR_HTTP_METHOD_NOT_SUPPORTED = 10021;

    public static final int ERR_IP_OUT_OF_RATE_LIMIT = 10022;

    public static final int ERR_USER_OUT_OF_RATE_LIMIT = 10023;

    public static final int ERR_USER_OUT_OF_RATE_LIMIT_SPECIAL = 10024;

    public static final int ERR_IDS_IS_NULL = 20001;

    public static final int ERR_UID_IS_NULL = 20002;

    public static final int ERR_USER_NOT_EXISTS = 20003;

    public static final int ERR_UNSUPPORTED_IMAGE_TYPE = 20005;

    public static final int ERR_IMAGE_TOO_LARGE = 20006;

    public static final int ERR_NO_IMAGE_IN_MULTIPART = 20007;

    public static final int ERR_CONTENT_IS_NULL = 20008;

    public static final int ERR_TOO_MUCH_IDS = 20009;

    public static final int ERR_TEXT_LONGER_THAN_140 = 20012;

    public static final int ERR_TEXT_LONGER_THAN_300 = 20013;

    public static final int ERR_SECURITY_PARAMETER = 20014;

    public static final int ERR_INVALID_ACCOUNT_IP_APP = 20015;

    public static final int ERR_UPDATE_OUT_OF_LIMIT = 20016;

    public static final int ERR_SIMILAR_CONTENT = 20017;

    public static final int ERR_CONTAINS_ILLEGAL_WEBSITE = 20018;

    public static final int ERR_DUPLICATED_CONTENT = 20019;

    public static final int ERR_CONTAINS_ADVERTISING = 20020;

    public static final int ERR_CONTAINS_ILLEGAL_CONTENT = 20021;

    public static final int ERR_IP_ABNORMAL_BEHAVIOR = 20022;

    public static final int ERR_VERIFICATION_CODE_REQUIRED = 20031;

    public static final int ERR_SUCCESS_BUT_MAY_DELAY = 20032;

    public static final int ERR_STATUS_NOT_FOUND = 20101;

    public static final int ERR_NOT_OWNER_OF_STATUS = 20102;

    public static final int ERR_CANNOT_REPOST_SELF_STATUS = 20103;

    public static final int ERR_ILLEGAL_STATUS = 20104;

    public static final int ERR_STATUS_ID_IS_NULL = 20109;

    public static final int ERR_DUPLICATED_STATUS_TEXT = 20111;

    public static final int ERR_COMMENT_NOT_FOUND = 20201;

    public static final int ERR_ILLEGAL_COMMENT = 20202;

    public static final int ERR_NOT_OWNER_OF_COMMENT = 20203;

    public static final int ERR_COMMENT_ID_IS_NULL = 20204;

    public static final int ERR_CANNOT_SEND_DIRECT_MESSAGE_TO_NOT_FOLLOWER = 20301;

    public static final int ERR_ILLEGAL_DIRECT_MESSAGE = 20302;

    public static final int ERR_NOT_OWNER_OF_DIRECT_MESSAGE = 20303;

    public static final int ERR_DIRECT_MESSAGE_NOT_FOUND = 20305;

    public static final int ERR_DUPLICATED_DIRECT_MESSAGE = 20306;

    public static final int ERR_INVALID_DIRECT_MESSAGE_ID = 20307;

    public static final int ERR_DOMAIN_NOT_FOUND = 20401;

    public static final int ERR_INVALID_VERIFIER = 20402;

    public static final int ERR_SOURCE_OR_TARGET_USER_NOT_FOUND = 20501;

    public static final int ERR_TARGET_USER_ID_OR_SCREEN_NAME_REQUIRED = 20502;

    public static final int ERR_USER_ID_SHOULD_BE_YOUR_FOLLOWER = 20503;

    public static final int ERR_CANNOT_FOLLOW_YOURSELF = 20504;

    public static final int ERR_SOCIAL_GRAPH_UPDATES_OUT_OF_RATE_LIMIT = 20505;

    public static final int ERR_ALREADY_FOLLOWED = 20506;

    public static final int ERR_PLEASE_INPUT_VERIFICATION_CODE = 20507;

    public static final int ERR_ACTION_PROHIBITED_ACCORDING_TO_USER_PRIVACY = 20508;

    public static final int ERR_QUIETLY_FOLLOW_COUNT_EXCEED = 20509;

    public static final int ERR_NOT_QUIETLY_FRIEND = 20510;

    public static final int ERR_ALREADY_QUIETLY_FOLLOWED = 20511;

    public static final int ERR_USER_IN_YOUR_BLACK_LIST = 20512;

    public static final int ERR_FRIENDS_COUNT_EXCEED = 20513;

    public static final int ERR_FOLLOWING_TOO_MANY = 20521;

    public static final int ERR_NOT_FOLLOWED = 20522;

    public static final int ERR_NOT_FOLLOWERS = 20523;

    public static final int ERR_CANCEL_FOLLOW_TOO_MANY = 20524;

    public static final int ERR_LIST_NAME_TOO_LONG = 20601;

    public static final int ERR_LIST_DESCRIPTION_TOO_LONG = 20602;

    public static final int ERR_LIST_NOT_FOUND = 20603;

    public static final int ERR_NOT_LIST_OWNER = 20604;

    public static final int ERR_ILLEGAL_LIST_NAME_OR_DESCRIPTION = 20605;

    public static final int ERR_OBJECT_ALREADY_EXISTS = 20606;

    public static final int ERR_DB_ERROR = 20607;

    public static final int ERR_LIST_NAME_DUPLICATED = 20608;

    public static final int ERR_PRIVATE_LIST_NOT_SUPPORTED = 20610;

    public static final int ERR_FAILED_CREATING_LIST = 20611;

    public static final int ERR_ONLY_PRIVATE_LIST_SUPPORTED = 20612;

    public static final int ERR_SUBSCRIBED_TOO_MANY_LISTS = 20613;

    public static final int ERR_TOO_MANY_LISTS = 20614;

    public static final int ERR_TOO_MANY_LIST_MEMBERS = 20615;

    public static final int ERR_DUPLICATED_TAG = 20701;

    public static final int ERR_TOO_MANY_TAGS = 20702;

    public static final int ERR_ILLEGAL_TAG_NAME = 20703;

    public static final int ERR_TREND_NAME_IS_NULL = 20801;

    public static final int ERR_TREND_ID_IS_NULL = 20802;

    public static final int ERR_ALREADY_IN_BLACK_LIST = 20901;

    public static final int ERR_BLACK_LIST_COUNT_EXCEED = 20902;

    public static final int ERR_CANNOT_ADD_SYSTEM_ADMINISTRATOR_TO_BLACK_LIST = 20903;

    public static final int ERR_CANNOT_ADD_YOURSELF_TO_BLACK_LIST = 20904;

    public static final int ERR_NOT_IN_BLACK_LIST = 20905;

    public static final int ERR_TAG_PARAMETER_IS_NULL = 21001;

    public static final int ERR_TAG_NAME_TOO_LONG = 21002;

    public static final int ERR_DOMAIN_PARAMETER = 21101;

    public static final int ERR_PHONE_NUMBER_HAS_BEEN_USED = 21102;

    public static final int ERR_ACCOUNT_ALREADY_BIND_PHONE_NUMBER = 21103;

    public static final int ERR_WRONG_VERIFIER = 21104;

    public static final int ERR_AUTH_FAILED = 21301;

    public static final int ERR_INVALID_USERNAME_OR_PASSWORD = 21302;

    public static final int ERR_PASSWORD_AUTH_OUT_OF_RATE_LIMIT = 21303;

    public static final int ERR_VERSION_REJECTED = 21304;

    public static final int ERR_PARAMETER_ABSENT = 21305;

    public static final int ERR_OAUTH_PARAMETER_REJECTED = 21306;

    public static final int ERR_INVALID_TIMESTAMP = 21307;

    public static final int ERR_NONCE_HAS_BEEN_USED = 21308;

    public static final int ERR_SIGNATURE_ALGORITHM_REJECTED = 21309;

    public static final int ERR_INVALID_SIGNATURE = 21310;

    public static final int ERR_UNKNOWN_CONSUMER_KEY = 21311;

    public static final int ERR_CONSUMER_KEY_REJECTED = 21312;

    public static final int ERR_CONSUMER_KEY_REQUIRED = 21313;

    public static final int ERR_TOKEN_HAS_BEEN_USED = 21314;

    public static final int ERR_TOKEN_EXPIRED = 21315;

    public static final int ERR_TOKEN_REVOKED = 21316;

    public static final int ERR_TOKEN_REJECTED = 21317;

    public static final int ERR_VERIFIER_FAILED = 21318;

    public static final int ERR_ACCESSOR_REVOKED = 21319;

    public static final int ERR_OATH2_ONLY_SUPPORTS_HTTPS = 21320;

    public static final int ERR_UNAUDITED_APP_USERS_COUNT_EXCEED = 21321;

    public static final int ERR_EXPIRED_TOKEN = 21327;

    public static final int ERR_URLS_IS_NULL = 21501;

    public static final int ERR_TOO_MANY_URLS = 21502;

    public static final int ERR_IP_IS_NULL = 21503;

    public static final int ERR_URL_IS_NULL = 21504;

    public static final int ERR_ADMINISTRATOR_PERMISSION_REQUIRED = 21601;

    public static final int ERR_CONTAINS_FORBIDDEN_WORDS = 21602;

    public static final int ERR_SEND_NOTIFICATIONS_EXCEED = 21603;

    public static final int ERR_REMIND_FAILED_WITHOUT_PERMISSION = 21701;

    public static final int ERR_INVALID_CATEGORY = 21702;

    public static final int ERR_INVALID_STATUS_CODE = 21703;

    public static final int ERR_INVALID_GEO_CODE = 21901;

    private String request;

    private String errorCode;

    private String error;

    public boolean isNotEmpty() {
        return !(isBlank(request) && isBlank(errorCode) && isBlank(error));
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCodeAsInt() {
        return Integer.parseInt(errorCode);
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", errorCode, error);
    }

    public void setError(String error) {
        this.error = error;
    }
}
