package weiboclient4j.utils;

import org.codehaus.jackson.map.PropertyNamingStrategy;

/**
 * @author Hover Ruan
 */
public class SinaJsonNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {
    public String translate(String propertyName) {
        return convertName(propertyName);
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
