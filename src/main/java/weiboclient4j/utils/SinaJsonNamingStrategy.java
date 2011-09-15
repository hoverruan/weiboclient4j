package weiboclient4j.utils;

import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.introspect.AnnotatedField;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;

/**
* @author Hover Ruan
*/
public class SinaJsonNamingStrategy extends PropertyNamingStrategy {
    @Override
    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
        return convertName(defaultName);
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convertName(defaultName);
    }

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return convertName(defaultName);
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
