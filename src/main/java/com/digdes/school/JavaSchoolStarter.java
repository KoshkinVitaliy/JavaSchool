package com.digdes.school;

import java.util.*;

public class JavaSchoolStarter {
    public List<Map<String, Object>> list = new ArrayList<>();


    public JavaSchoolStarter() {

    }
    public List<Map<String, Object>> execute(String request) {

        switch (readRequest(request)) {
            case "INSERT" -> insertListElement(request);
            case "UPDATE" -> updateListElement(request);
            case "DELETE" -> deleteListElement(request);
            case "SELECT" -> selectListElement(request);
            default -> System.out.println(new Exception());

        }
        return list;
    }

    private void insertListElement(String request) {
        //переписать метод добавления в коллекцию
        String key = "";
        String value = "";
        char[] requestArray = request.replaceAll(" ", "").toCharArray();
        Map<String, Object> row = new HashMap<>();
        boolean keyFlag = false;
        boolean valueFlag = false;

        System.out.println(request.replaceAll(" ", ""));
        for (int i=0; i < request.length(); i++) {
            if(i >= requestArray.length) {
                break;
            }
            if (String.valueOf(requestArray[i]).equals("=")) {
                i++;
                valueFlag = true;
            }
            else if(String.valueOf(requestArray[i]).equals("'")) {
                i++;
                keyFlag = true;
            }

            if(!keyFlag) {
                if(!valueFlag) {
                    if (i+1 == requestArray.length) {
                        break;
                    }
                }
                else {
                    if (String.valueOf(requestArray[i]).equals("'")) {
                        i++;
                    }
                    value = value + requestArray[i];
                    if (String.valueOf(requestArray[i+1]).equals("'")) {
                        i++;
                        valueFlag = false;
                        System.out.println("Value - " + value);

                        row.put(key, value);
                        System.out.println("i - " + i);
                        value = "";
                        key = "";
                    }
                    else if(String.valueOf(requestArray[i+1]).equals(",")) {
                        valueFlag = false;
                        System.out.println("Value - " + value);

                        row.put(key, value);
                        value = "";
                        key = "";
                    }
                }
            }
            else {
                key = key + requestArray[i];
                if(i == requestArray.length - 1) {
                    break;
                }
                else if (String.valueOf(requestArray[i+1]).equals("'")) {
                    i++;
                    keyFlag = false;
                    System.out.println("Key - " + key);
                }
            }

        }

        list.add(row);
    }

    private void updateListElement(String request) {
        String key = "";
        String value = "";
        String keyUpdate = "";
        String valueUpdate = "";
        char[] requestArray = request.replaceAll(" ", "").toCharArray();
        boolean keyFlag = false;
        boolean valueFlag = false;
        String whereCheck = "";
        Map<String, Object> updateRow = new HashMap<>();

        for (int i=0; i < request.length(); i++) {
            if(i >= requestArray.length) {
                break;
            }

            whereCheck = whereCheck + requestArray[i];

            if (whereCheck.equals("WHERE")) {
                if(String.valueOf(requestArray[i]).equals("'")) {
                    i++;
                    keyFlag = true;
                }
                if(keyFlag) {
                    keyUpdate = keyUpdate + requestArray[i];
                    System.out.println("KeyUpdate - " + key);

                    if(String.valueOf(requestArray[i+1]).equals("'")) {
                        keyFlag = false;
                        i++;
                    }
                }

                if(String.valueOf(requestArray[i]).equals("=")) {
                    i++;
                    if (String.valueOf(requestArray[i]).equals("'")) {
                        i++;
                    }
                    if (i >= requestArray.length) {
                        break;
                    }
                    else {
                        if(!String.valueOf(requestArray[i]).equals("'")) {
                            valueUpdate = valueUpdate + requestArray[i];
                        }
                    }
                }
            }
            else {
                if (String.valueOf(requestArray[i]).equals("=")) {
                    i++;
                    valueFlag = true;
                }
                else if(String.valueOf(requestArray[i]).equals("'")) {
                    i++;
                    keyFlag = true;
                }

                if(!keyFlag) {
                    if(!valueFlag) {
                        if (i+1 == requestArray.length) {
                            break;
                        }
                    }
                    else {
                        if (String.valueOf(requestArray[i]).equals("'")) {
                            i++;
                        }

                        value = value + requestArray[i];
                        if (i+1 >= requestArray.length) {
                            break;
                        }
                        if (String.valueOf(requestArray[i+1]).equals("'")) {
                            i++;
                            valueFlag = false;
                            System.out.println("Value - " + value);

                            updateRow.put(key, value);
                            System.out.println("i - " + i);
                            value = "";
                            key = "";
                        }
                        else if(String.valueOf(requestArray[i+1]).equals(",")) {
                            valueFlag = false;
                            whereCheck = "";
                            System.out.println("Value - " + value);

                            updateRow.put(key, value);
                            value = "";
                            key = "";
                        }
                    }
                }
                else {
                    key = key + requestArray[i];
                    if(i == requestArray.length - 1) {
                        break;
                    }
                    else if (String.valueOf(requestArray[i+1]).equals("'")) {
                        i++;
                        keyFlag = false;
                        whereCheck = "";
                        System.out.println("Key - " + key);
                    }
                }
            }
        }
        for (int g=0; g < list.size(); g++) {
            if (list.get(g).get(keyUpdate) == valueUpdate) {
                for (String keyMap: list.get(g).keySet()) {
                    for (String updateKeyMap: updateRow.keySet()) {
                        if (keyMap.equals(updateKeyMap)) {
                            list.get(g).replace(keyMap, updateRow.get(updateKeyMap));
                        }
                    }
                }
            }
        }
    }

    private void deleteListElement(String request) {
        String key = "";
        String value = "";
        if(request.equals("DELETE")) {
            list.clear();
        }
        else {
            char[] requestArray = request.replaceAll(" ", "").toCharArray();
            boolean keyFlag = false;
            System.out.println(request.replaceAll(" ", ""));
            for(int s=0; s < requestArray.length; s++) {
                if(String.valueOf(requestArray[s]).equals("'")) {
                    s++;
                    keyFlag = true;
                }
                if(keyFlag) {
                    key = key + requestArray[s];
                    System.out.println("Key - " + key);

                    if(String.valueOf(requestArray[s+1]).equals("'")) {
                        keyFlag = false;
                        s++;
                    }
                }

                if(String.valueOf(requestArray[s]).equals("=")) {
                    s++;
                    if (String.valueOf(requestArray[s]).equals("'")) {
                        s++;
                    }
                    if (s >= requestArray.length) {
                        break;
                    }
                    else {
                        if(!String.valueOf(requestArray[s]).equals("'")) {
                            value = value + requestArray[s];
                        }
                    }
                }
            }
        }

        System.out.println("Key - " + key + ", value - " + value);
        for (int t=0; t < list.size(); t++) {
            if (list.get(t).get(key).equals(value)) {
                list.remove(t);
            }
        }
    }

    private void selectListElement(String request) {
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
