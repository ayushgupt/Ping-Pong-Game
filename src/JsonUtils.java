import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Arrays;

/**
 * Created by quantumcoder on 4/27/2016.
 */
public class JsonUtils {

    public static Integer[] stringToIntarr(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        Integer result[] = new Integer[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public static String[] stringToStrarr(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        String result[] = new String[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = strings[i];
        }
        return result;
    }

    public static String arrToStr(Object[] intarr){
        return Arrays.toString(intarr);
    }

    public static String jsonToString(JSONObject json_obj){
        return json_obj.toString();
    }

    public static JSONObject stringToJson(String json_str) throws ParseException {
        return (JSONObject) new JSONParser().parse(json_str);
    }


}
