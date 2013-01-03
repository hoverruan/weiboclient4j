package weiboclient4j.model;

import java.io.*;

/**
 * @author Hover Ruan
 */
public abstract class AbstractParseJsonTest {
    protected String readResource(String path) throws Exception {
        BufferedReader reader = null;
        StringWriter result;
        try {
            InputStream inputStream = getClass().getResourceAsStream(path);
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            result = new StringWriter();
            PrintWriter out = new PrintWriter(result);

            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return result.toString();
    }
}
