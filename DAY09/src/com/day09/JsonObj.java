package com.day09;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.*;

import java.util.ArrayList;

public class JsonObj {
    public static void main(String args[]){
        JSONObject obj=new JSONObject();
        obj.put("name","sonoo");
        obj.put("age", 27);
        obj.put("salary", 600000d);

        JSONObject obj1=new JSONObject();
        obj1.put("name","Breach");
        obj1.put("age", 25);
        obj1.put("salary", 500000d);

        JSONObject obj2=new JSONObject();
        obj2.put("name","Viper");
        obj2.put("age", 20);
        obj2.put("salary", 4000000d);

        JSONArray arr = new JSONArray();
        arr.add(obj);
        arr.add(obj1);
        arr.add(obj2);

        JSONObject emp = new JSONObject();
        emp.put("employees", arr);

//        JSONObject objects = getArray.getJSONObject(i);

//        String string = (String) emp.toJSONString();
//        System.out.println(string);
//        JSONArray jsonArray = (JSONArray) emp.get("employees");
//        for (int i = 0, size = jsonArray.size(); i < size; i++)
//        {
//            JSONObject objTemp = (JSONObject) jsonArray.get(i);
//
//            String name = (String) objTemp.get("name");
//            Integer age = (Integer) objTemp.get("age");
//            Double salary = (Double) objTemp.get("salary");
//            System.out.println("Name : " + name);
//            System.out.println("Age : " + age);
//            System.out.println("Salary : " + salary);
//            System.out.println();
//        }
        JSONArray array = (JSONArray) emp.get("employees");
        for (Object isi:array) {
            JSONObject data = (JSONObject) isi;
            System.out.println("Name : " + data.get("name"));
            System.out.println("Age : " + data.get("age"));
            System.out.println("Salary : " + data.get("salary") + "\n");
        }
    }
}
