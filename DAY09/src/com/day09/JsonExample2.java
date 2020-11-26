package com.day09;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONValue;
public class JsonExample2{
    public static void main(String args[]){
        Map obj=new HashMap();
        obj.put("name","sonoo");
        obj.put("age", 27);
        obj.put("salary", 600000d);
        String jsonText = JSONValue.toJSONString(obj);
        System.out.print(jsonText);
    }
}


