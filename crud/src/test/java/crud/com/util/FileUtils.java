package crud.com.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String readFileContent(String arquivo) throws IOException {
        InputStream is = new FileInputStream("src/test/resources/" + arquivo);
        return IOUtils.toString(is, "UTF-8");
    }

}
