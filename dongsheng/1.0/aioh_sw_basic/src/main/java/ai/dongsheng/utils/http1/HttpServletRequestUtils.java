package ai.dongsheng.utils.http1;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @program: aioh_sw_im
 * @description:
 * @author: MichelleJou
 * @create: 2019-10-19 18:43
 **/
public class HttpServletRequestUtils {

    public static String getBodyData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine()))
                data.append(line);
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }
}
