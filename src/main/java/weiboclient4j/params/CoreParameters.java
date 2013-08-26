package weiboclient4j.params;

/**
 * @author Hover Ruan
 */
public class CoreParameters {
    public static ActionUrl actionUrl(String value) {
        return new ActionUrl(value);
    }

    public static Address address(String value) {
        return new Address(value);
    }

    public static AddressCode addressCode(String value) {
        return new AddressCode(value);
    }

    public static CapitalLetter capitalLetter(String value) {
        return new CapitalLetter(value);
    }

    public static Category category(String value) {
        return new Category(value);
    }

    public static Cid cid(long value) {
        return new Cid(value);
    }

    public static Cid cid(String value) {
        return new Cid(value);
    }

    public static City city(String value) {
        return new City(value);
    }

    public static CommentParam commentParam(String value) {
        return new CommentParam(value);
    }

    public static Content content(String value) {
        return new Content(value);
    }

    public static Country country(String value) {
        return new Country(value);
    }

    public static Domain domain(String value) {
        return new Domain(value);
    }

    public static EndBirth endBirth(long value) {
        return new EndBirth(value);
    }

    public static EndTime endTime(long value) {
        return new EndTime(value);
    }

    public static Extra extra(String value) {
        return new Extra(value);
    }

    public static Id id(long value) {
        return new Id(value);
    }

    public static Id id(String value) {
        return new Id(value);
    }

    public static Keyword keyword(String value) {
        return new Keyword(value);
    }

    public static Latitude latitude(float value) {
        return new Latitude(value);
    }

    public static ListId listId(String value) {
        return new ListId(value);
    }

    public static LocRange locRange(long value) {
        return new LocRange(value);
    }

    public static Longitude longitude(float value) {
        return new Longitude(value);
    }

    public static Mid mid(String value) {
        return new Mid(value);
    }

    public static Nickname nickname(String value) {
        return new Nickname(value);
    }

    public static Num num(long value) {
        return new Num(value);
    }

    public static Phone phone(String value) {
        return new Phone(value);
    }

    public static Pid pid(String value) {
        return new Pid(value);
    }

    public static PostCode postCode(String value) {
        return new PostCode(value);
    }

    public static Province province(String value) {
        return new Province(value);
    }

    public static Query query(String value) {
        return new Query(value);
    }

    public static Remark remark(String value) {
        return new Remark(value);
    }

    public static ScreenName screenName(String value) {
        return new ScreenName(value);
    }

    public static Section section(long value) {
        return new Section(value);
    }

    public static SourceScreenName sourceScreenName(String value) {
        return new SourceScreenName(value);
    }

    public static SourceUid sourceUid(long value) {
        return new SourceUid(value);
    }

    public static SourceUid sourceUid(String value) {
        return new SourceUid(value);
    }

    public static StartBirth startBirth(long value) {
        return new StartBirth(value);
    }

    public static StartTime startTime(long value) {
        return new StartTime(value);
    }

    public static Suid suid(long value) {
        return new Suid(value);
    }

    public static Suid suid(String value) {
        return new Suid(value);
    }

    public static TagId tagId(long value) {
        return new TagId(value);
    }

    public static TagId tagId(String value) {
        return new TagId(value);
    }

    public static TagName tagName(String value) {
        return new TagName(value);
    }

    public static TargetScreenName targetScreenName(String value) {
        return new TargetScreenName(value);
    }

    public static TargetUid targetUid(long value) {
        return new TargetUid(value);
    }

    public static TargetUid targetUid(String value) {
        return new TargetUid(value);
    }

    public static TemplateId templateId(long value) {
        return new TemplateId(value);
    }

    public static Tid tid(long value) {
        return new Tid(value);
    }

    public static Title title(String value) {
        return new Title(value);
    }

    public static TrendId trendId(long value) {
        return new TrendId(value);
    }

    public static TrendName trendName(String value) {
        return new TrendName(value);
    }

    public static Uid uid(long value) {
        return new Uid(value);
    }

    public static Uid uid(String value) {
        return new Uid(value);
    }

    public static UrlLong urlLong(String value) {
        return new UrlLong(value);
    }

    public static UrlShort urlShort(String value) {
        return new UrlShort(value);
    }

    public static CenterCoordinate centerCoordinate(String value) {
        return new CenterCoordinate(value);
    }

    public static CenterCoordinate centerCoordinate(Coordinate coordinate) {
        return new CenterCoordinate(coordinate);
    }

    public static CenterCoordinate centerCoordinate(Longitude longitude, Latitude latitude) {
        return new CenterCoordinate(longitude, latitude);
    }

    public static Coordinates coordinates(String value) {
        return new Coordinates(value);
    }

    public static Coordinates coordinates(Coordinate... coordinatesParam) {
        return new Coordinates(coordinatesParam);
    }

    public static Font font(String value) {
        return new Font(value);
    }

    public static Format format(String value) {
        return new Format(value);
    }

    public static Lines lines(String value) {
        return new Lines(value);
    }

    public static Names names(String... namesParam) {
        return new Names(namesParam);
    }

    public static OffsetX offsetX(String value) {
        return new OffsetX(value);
    }

    public static OffsetY offsetY(String value) {
        return new OffsetY(value);
    }

    public static Polygons polygons(String value) {
        return new Polygons(value);
    }

    public static Scale scale(boolean value) {
        return new Scale(value);
    }

    public static Traffic traffic(boolean value) {
        return new Traffic(value);
    }

    public static Zoom zoom(long value) {
        return new Zoom(value);
    }

    public static Ip ip(String value) {
        return new Ip(value);
    }

    public static PicId picId(String... picIdList) {
        return new PicId(picIdList);
    }

    public static Pic pic(String value) {
        return new Pic(value);
    }
}
