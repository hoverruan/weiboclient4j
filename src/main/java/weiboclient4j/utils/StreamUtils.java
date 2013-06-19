package weiboclient4j.utils;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Hover Ruan
 */
public final class StreamUtils {
    private StreamUtils() {
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    public static StreamWriter newStreamWriter(DataOutputStream stream) {
        return new StreamWriter(stream);
    }

    public static class StreamWriter {
        private static final String CRLF = "\r\n";

        private DataOutputStream stream;

        public StreamWriter(DataOutputStream stream) {
            this.stream = stream;
        }

        public StreamWriter writeLine(String content) throws IOException {
            stream.writeBytes(content + CRLF);

            return this;
        }

        public StreamWriter writeLine(byte[] content) throws IOException {
            if (content != null) {
                stream.write(content);
            }

            stream.writeBytes(CRLF);

            return this;
        }

        public StreamWriter writeLine() throws IOException {
            return writeLine("");
        }

        public StreamWriter writeFile(File file) throws IOException {
            BufferedInputStream in = null;
            FileInputStream fileInputStream = null;

            try {
                fileInputStream = new FileInputStream(file);
                in = new BufferedInputStream(fileInputStream);
                int buff;
                while ((buff = in.read()) != -1) {
                    stream.write(buff);
                }

                stream.writeBytes(CRLF);

            } finally {
                close(fileInputStream);
                close(in);
            }

            return this;
        }
    }
}
