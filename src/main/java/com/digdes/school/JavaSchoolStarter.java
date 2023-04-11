package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    private List<Map<String, Object>> list = new ArrayList<>();

    public JavaSchoolStarter() {

    }
    public List<Map<String, Object>> execute(String request) {

        switch (readRequest(request)) {
            case "INSERT" -> insertListElement(request);
            case "UPDATE" -> updateListElement();
            case "DELETE" -> deleteListElement();
            case "SELECT" -> selectListElement();
            default -> System.out.println(new Exception());

        }
        return list;
    }

    private void insertListElement(String request) {
        String key = "";
        String value = "";
        char[] requestArray = request.replaceAll(" ", "").toCharArray();
        Map<String, Object> row = new HashMap<>();

        System.out.println(request.replaceAll(" ", ""));
        for (int i=0; i < request.length(); i++) {
            if(String.valueOf(requestArray[i]).equals("'")) {
                for (int k = i+1; k < requestArray.length; k++) {
                    if(!String.valueOf(requestArray[k]).equals("'")) {
                        key = key + requestArray[k];
                    }
                    else {
                        for (int m = k + 3; m < requestArray.length; m++) {
                            if(!String.valueOf(requestArray[m]).equals("'")) {
                                value = value + requestArray[m];
                            }
                            else {
                                System.out.println("Key - " + key + ", value - '" + value + "'");
                                System.out.println("i = " + i + ", k =  " + k + " m = " + m);
                                row.put(key, value);
                                i = m + 2 + value.length();
                                k = i-1;
                                key = "";
                                value = "";
                                break;
                            }
                        }
                    }
                }
            }
            if (i >= requestArray.length) {
                break;
            }
        }

        list.add(row);
    }

    private void updateListElement() {
        System.out.println("Updating value");
    }

    private void deleteListElement() {
        System.out.println("Deleting value");
    }

    private void selectListElement() {
        System.out.println("Selecting value");
    }

    private String readRequest(String request) {
        if ((request.startsWith("UPDATE")) || (request.startsWith("INSERT"))
        || (request.startsWith("DELETE")) || (request.startsWith("SELECT"))) {
            return request.substring(0, 6);
        }
        else {
            throw new RuntimeException("Incorrect request.");
        }
    }
}
