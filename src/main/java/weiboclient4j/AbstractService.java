package weiboclient4j;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.type.TypeReference;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import weiboclient4j.model.Url;
import weiboclient4j.model.UrlInfo;
import weiboclient4j.oauth2.SinaWeibo2AccessToken;
import weiboclient4j.params.Paging;
import weiboclient4j.params.ParameterAction;
import weiboclient4j.params.Parameters;
import static weiboclient4j.utils.JsonUtils.parseJsonObject;
import weiboclient4j.utils.StreamUtils;
import static weiboclient4j.utils.StreamUtils.closeQuietly;
import static weiboclient4j.utils.StreamUtils.newStreamWriter;
import weiboclient4j.utils.StringUtils;
import static weiboclient4j.utils.StringUtils.isNotBlank;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Hover Ruan
 */
public class AbstractService {
    public static final String API2_URL = "https://api.weibo.com/2/";

    protected static final TypeReference<List<Map<String, String>>> LIST_MAP_S_S_TYPE_REFERENCE =
            new TypeReference<List<Map<String, String>>>() {
            };

    protected static final TypeReference<List<TagActionResponse>> TYPE_TAG_ACTION_RESPONSE_LIST =
            new TypeReference<List<TagActionResponse>>() {
            };

    protected static final TypeReference<List<LongIdStringValue>> TYPE_LONG_ID_STRING_VALUE_LIST =
            new TypeReference<List<LongIdStringValue>>() {
            };

    private static final int DEFAULT_TIMEOUT = 30;

    private WeiboClient client;

    private SinaWeibo2AccessToken accessToken;

    private int connectTimeoutDuration = DEFAULT_TIMEOUT;

    private TimeUnit connectTimeoutUnit = TimeUnit.SECONDS;

    private int readTimeoutDuration = DEFAULT_TIMEOUT;

    private TimeUnit readTimeoutUnit = TimeUnit.SECONDS;

    public AbstractService(WeiboClient client) {
        this.client = client;

        client.initService(this);
    }

    public static Parameters withParams(ParameterAction... actions) {
        return Parameters.create().addAll(actions);
    }

    public static Parameters buildParams(ParameterAction[] optionalParams, ParameterAction... requireParams) {
        return Parameters.create().addAll(optionalParams).addAll(requireParams);
    }

    protected static void buildUploadRequest(OAuthRequest request, String fileParamName, File file, Parameters params)
            throws IOException {
        ByteArrayOutputStream baos = null;
        OutputStream os = null;
        DataOutputStream dos = null;

        try {
            baos = new ByteArrayOutputStream();
            os = new BufferedOutputStream(baos);
            dos = new DataOutputStream(os);

            StreamUtils.StreamWriter writer = newStreamWriter(dos);

            String boundary = "----weiboclient4j-upload" + System.currentTimeMillis();
            request.addHeader("Content-Type", "multipart/form-data; boundary=" + boundary);
            boundary = "--" + boundary;

            writeFile(writer, boundary, fileParamName, file);
            writeParameters(writer, boundary, params);
            writer.writeLine(boundary + "--").writeLine();

            dos.flush();

            request.addPayload(baos.toByteArray());
        } finally {
            closeQuietly(dos);
            closeQuietly(os);
            closeQuietly(baos);
        }
    }

    protected static void writeFile(StreamUtils.StreamWriter writer, String boundary, String fileParamName, File file)
            throws IOException {
        writer.writeLine(boundary)
                .writeLine(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"",
                        fileParamName, file.getName()))
                .writeLine("Content-Type: " + getContentTypeFromImage(file))
                .writeLine()
                .writeFile(file);
    }

    protected static void writeParameters(StreamUtils.StreamWriter writer, String boundary,
                                          Parameters params) throws IOException {
        for (Parameters.Parameter param : params.getParameterList()) {
            writer.writeLine(boundary)
                    .writeLine("Content-Disposition: form-data; name=\"" + param.getKey() + "\"")
                    .writeLine("Content-Type: text/plain; charset=" + StringUtils.DEFAULT_ENCODING)
                    .writeLine()
                    .writeLine(param.getValue().getBytes(StringUtils.DEFAULT_ENCODING));
        }
    }

    protected static String getContentTypeFromImage(File imageFile) {
        String contentType = "image/jpeg";

        String fileName = imageFile.getName();

        if (isNotBlank(fileName)) {
            String lowerCaseFileName = fileName.toLowerCase();
            if (lowerCaseFileName.endsWith(".gif")) {
                contentType = "image/gif";
            } else if (lowerCaseFileName.endsWith(".png")) {
                contentType = "image/png";
            }
        }

        return contentType;
    }

    public WeiboClient getClient() {
        return client;
    }

    public void setAccessToken(SinaWeibo2AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public void setConnectTimeoutDuration(int connectTimeoutDuration) {
        this.connectTimeoutDuration = connectTimeoutDuration;
    }

    public void setConnectTimeoutUnit(TimeUnit connectTimeoutUnit) {
        this.connectTimeoutUnit = connectTimeoutUnit;
    }

    public void setReadTimeoutDuration(int readTimeoutDuration) {
        this.readTimeoutDuration = readTimeoutDuration;
    }

    public void setReadTimeoutUnit(TimeUnit readTimeoutUnit) {
        this.readTimeoutUnit = readTimeoutUnit;
    }

    public <T> T doGet(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, params, clazz);
    }

    public <T> T doGet(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), params, clazz);
    }

    public <T> T doGet(String path, Paging paging, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, clazz);
    }

    public <T> T doGet(String path, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), clazz);
    }

    public <T> List<T> doGet(String path, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), typeReference);
    }

    public <T> List<T> doGet(String path, Paging paging, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, typeReference);
    }

    public <T> List<T> doGet(String path, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), params, typeReference);
    }

    public <T> List<T> doGet(String path, Paging paging, Parameters params,
                             TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createGetRequest(path), paging, params, typeReference);
    }

    public <T> T doPost(String path, Paging paging, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), paging, params, clazz);
    }

    public <T> T doPost(String path, Parameters params, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), params, clazz);
    }

    public <T> T doPost(String path, Paging paging, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), paging, clazz);
    }

    public <T> T doPost(String path, Class<T> clazz) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), clazz);
    }

    public <T> List<T> doPost(String path, Paging paging, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), paging, params, typeReference);
    }

    public <T> List<T> doPost(String path, Parameters params, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), params, typeReference);
    }

    public <T> List<T> doPost(String path, Paging paging, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), paging, typeReference);
    }

    public <T> List<T> doPost(String path, TypeReference<List<T>> typeReference) throws WeiboClientException {
        return sendRequestAndGetResponseObject(createPostRequest(path), typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request,
                                                       Parameters params,
                                                       TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, Paging.EMPTY, params, typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, Paging paging,
                                                       TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, paging, Parameters.create(), typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Parameters params,
                                                       TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        if (paging != null) {
            params.add(paging);
        }

        params.appendTo(request);

        return sendRequestAndGetResponseObject(request, typeReference);
    }

    public <T> List<T> sendRequestAndGetResponseObject(OAuthRequest request, TypeReference<List<T>> typeReference)
            throws WeiboClientException {
        Response response = request.send();

        return parseJsonObject(response, typeReference);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Parameters params, Class<T> clazz)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, Paging.EMPTY, params, clazz);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Class<T> clazz)
            throws WeiboClientException {
        return sendRequestAndGetResponseObject(request, paging, Parameters.create(), clazz);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Paging paging, Parameters params, Class<T> clazz)
            throws WeiboClientException {
        if (paging != null) {
            params.add(paging);
        }

        params.appendTo(request);

        return sendRequestAndGetResponseObject(request, clazz);
    }

    public <T> T sendRequestAndGetResponseObject(OAuthRequest request, Class<T> clazz) throws WeiboClientException {
        Response response = request.send();

        return parseJsonObject(response, clazz);
    }

    public OAuthRequest createGetRequest(String path) {
        return createRequest(Verb.GET, path);
    }

    public OAuthRequest createPostRequest(String path) {
        return createRequest(Verb.POST, path);
    }

    private OAuthRequest createRequest(Verb verb, String path) {
        OAuthRequest request = new OAuthRequest(verb, getFullPath(path));
        setRequestTimeout(request);
        setRequestAccessToken(request);

        return request;
    }

    private void setRequestTimeout(OAuthRequest request) {
        request.setConnectTimeout(connectTimeoutDuration, connectTimeoutUnit);
        request.setReadTimeout(readTimeoutDuration, readTimeoutUnit);
    }

    private void setRequestAccessToken(OAuthRequest request) {
        if (accessToken != null) {
            request.addHeader("Authorization", "OAuth2 " + accessToken.getToken());
        }
    }

    public String getFullPath(String path) {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return path;
        } else {
            return API2_URL + path + ".json";
        }
    }

    protected Map<String, String> mergeSingleItemMap(List<Map<String, String>> response) {
        Map<String, String> map = new HashMap<String, String>();
        for (Map<String, String> item : response) {
            map.putAll(item);
        }

        return map;
    }

    protected ParameterAction urlParam(final URL url) {
        return new ParameterAction() {
            public void addParameter(Parameters params) {
                if (url != null) {
                    params.add("url", url.toExternalForm());
                }
            }
        };
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class TagActionResponse {
        private long tagid;

        public static List<Long> toLongList(List<TagActionResponse> responseList) {
            List<Long> result = new ArrayList<Long>(responseList.size());
            for (TagActionResponse response : responseList) {
                result.add(response.getTagid());
            }

            return result;
        }

        public long getTagid() {
            return tagid;
        }

        public void setTagid(long tagid) {
            this.tagid = tagid;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class LongIdStringValue {
        private long id;

        private String value;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class FollowTrendResponse {
        private long topicid;

        public long getTopicid() {
            return topicid;
        }

        public void setTopicid(long topicid) {
            this.topicid = topicid;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class UrlInfoResponse {
        private List<UrlInfo> urls;

        public List<UrlInfo> getUrls() {
            return urls;
        }

        public void setUrls(List<UrlInfo> urls) {
            this.urls = urls;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class UrlResponse {
        private List<Url> urls;

        public List<Url> getUrls() {
            return urls;
        }

        public void setUrls(List<Url> urls) {
            this.urls = urls;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class ResultResponse {
        private boolean result;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected static class StatusResponse {
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
