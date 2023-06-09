package com.digdes.school;

public class Main {
    public static void main(String[] args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();

        try {
           System.out.println(starter.execute(
                    "INSERT VALUES 'id' = 3, 'name' = 'fedor', 'counter' = false, 'surname' = 'Ivanov'"));
            System.out.println(starter.execute(
                    "INSERT VALUES 'id' = 3, 'name' = 'Redor', 'counter' = true, 'surname' = 'Sidorov'"));

            System.out.println(starter.execute(
                    "UPDATE VALUES 'name' = 'Ilya' WHERE 'id' = 3"));

            System.out.println(starter.execute("SELECT"));

           System.out.println(starter.execute("DELETE WHERE 'id' = 3"));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}