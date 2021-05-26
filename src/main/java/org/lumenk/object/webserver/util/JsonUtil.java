package org.lumenk.object.webserver.util;

import com.google.gson.Gson;

import java.util.ArrayList;

public class JsonUtil {

    private final Gson gson = new Gson();
    public static String[] splitToArray(String jsonArray){
        ArrayList<String> arrayList = new ArrayList<>();

        final int size = jsonArray.length();
        String current;
        StringBuilder builder = new StringBuilder();
        int depth = 0;
        for(int i = 0; i < size; i++){
            current = jsonArray.substring(i, i + 1);

            if(current.equals("{"))
                depth++;
            else if(current.equals("}"))
                depth--;

            if(depth == 0){
                switch (current) {
                    case "[":
                    case "]":
                    case ",":
                    case " ":
                        break;
                    default:
                        builder.append("}");
                        arrayList.add(builder.toString());
                        builder = new StringBuilder();
                        break;
                }
            }else
                builder.append(current);
        }

        return arrayList.toArray(new String[0]);
    }
}
