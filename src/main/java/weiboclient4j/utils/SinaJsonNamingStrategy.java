package weiboclient4j.utils;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/**
 * @author Hover Ruan
 */
public class SinaJsonNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {

    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return translateWithAnnotation(field.getAnnotation(JsonProperty.class), defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return translateWithAnnotation(method.getAnnotation(JsonProperty.class), defaultName);
    }

    public String translate(String propertyName) {
        return convertName(propertyName);
    }

    private String translateWithAnnotation(JsonProperty jsonPropertyAnn, String defaultName) {
        if (jsonPropertyAnn != null) {
            String propertyName = jsonPropertyAnn.value();
            if (StringUtils.isNotBlank(propertyName)) {
                return propertyName;
            }
        }

        return translate(defaultName);
    }

    String convertName(String defaultName) {
        StringBuilder buf = new StringBuilder();

        for (char ch : defaultName.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                buf.append('_').append(Character.toLowerCase(ch));
            } else {
                buf.append(ch);
            }
        }

        return buf.toString();
    }
}
