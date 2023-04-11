package com.digdes.school;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {
            System.out.println(starter.execute(
                    "INSERT VALUES 'lastName' = 'Ivanov' , 'id' = '3' , 'active' = 'true'"));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}