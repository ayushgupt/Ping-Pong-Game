<<<<<<< HEAD
/**
 * Created by quantumcoder on 4/27/2016.
 */

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class jsontest {

    private static Integer[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        Integer result[] = new Integer[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {
        JSONObject testobj = new JSONObject();
        testobj.put("user1","Aniket");
        testobj.put("user2","Manish");
        testobj.put("user3","Ayush");

        String[] intarray = new String[]{"1","2","3","4"} ;
        String arr = JsonUtils.arrToStr(intarray);

        testobj.put("numarr", arr);

        // System.out.println(testobj);

        String json_str = JsonUtils.jsonToString(testobj);


        JSONObject conv_obj = JsonUtils.stringToJson(json_str);


        String[] res_arr = JsonUtils.stringToStrarr((String) conv_obj.get("numarr"));
        for(String i: res_arr){
            System.out.println(i);
        }


    }

}
=======
/**
 * Created by quantumcoder on 4/27/2016.
 */

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class jsontest {

    private static Integer[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        Integer result[] = new Integer[strings.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    public static void main(String[] args) throws ParseException {
        JSONObject testobj = new JSONObject();
        testobj.put("user1","Aniket");
        testobj.put("user2","Manish");
        testobj.put("user3","Ayush");

        String[] intarray = new String[]{"1","2","3","4"} ;
        String arr = JsonUtils.arrToStr(intarray);

        testobj.put("numarr", arr);

        // System.out.println(testobj);

        String json_str = JsonUtils.jsonToString(testobj);


        JSONObject conv_obj = JsonUtils.stringToJson(json_str);


        String[] res_arr = JsonUtils.stringToStrarr((String) conv_obj.get("numarr"));
        for(String i: res_arr){
            System.out.println(i);
        }


    }

}
>>>>>>> 7a6958b254b8c209578964171b40c92a9aec4cf8
