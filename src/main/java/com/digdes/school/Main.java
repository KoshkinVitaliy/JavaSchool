package com.digdes.school;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {
            starter.execute("");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}