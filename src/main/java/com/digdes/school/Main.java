package com.digdes.school;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {
           System.out.println(starter.execute(
                    "INSERT VALUES 'id' = 3, 'name' = 'fedor', 'counter' = false, 'surname' = 'Ivanov'"));
            System.out.println(starter.execute("DELETE WHERE 'id' = 3"));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}