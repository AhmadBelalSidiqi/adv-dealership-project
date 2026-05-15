package com.yearup.dealership;

import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);
    private UserInput(){}

    public static int getUserInteger(String prompt){
        do {
            System.out.println(prompt);
            String userInput = scanner.nextLine();
            try {
                return Integer.parseInt(userInput);
            } catch (NumberFormatException n){
                System.out.println(userInput+" is not valid Integer");
            }
        }while(true);
    }
    public static Double getUserDouble(String prompt){
        do {
            System.out.println(prompt);
            String userInput = scanner.nextLine();
            try {
                return Double.parseDouble(userInput);
            } catch (NumberFormatException n){
                System.out.println(userInput+" is not valid Double");
            }
        }while(true);
    }

    public static String getUserString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
