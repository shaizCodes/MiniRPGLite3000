package com.isep.utils;

import java.util.Scanner;

public class InputParser {
    public InputParser() {
        this.scanner = new Scanner(System.in);
    }

    private final Scanner scanner;
    public final int CONSOLE_WIDTH = 60;
    public final int PAUSE_TIME = 750;

    public String readInput(){
        return scanner.nextLine();
    }

    //Reads only positive integers
    public int readInt(){
        int input = 0;
        while(input <= 0){
            try{
                input = Integer.parseInt(scanner.next());
            }
            catch(Exception error){
                System.out.println("Please enter an integer!");
                printSeparator(CONSOLE_WIDTH);
            }
        }
        return input;
    }

    //Reads only positive integers in range 1 to <inputUpperBound> inclusively
    public int readInt(String message, int inputUpperBound){
        int userInput;
        do{
            System.out.print(message+"\n >>> ");
            try{
                userInput = Integer.parseInt(scanner.next());
                if(userInput > inputUpperBound){
                    throw new Exception("USER INPUT IS OUT OF ACCEPTABLE VALUES RANGE");
                }
            }
            catch(Exception error){
                userInput = 0;
                System.out.println("Please enter an integer less than or equal to "+inputUpperBound+"!");
                printSeparator(CONSOLE_WIDTH);
            }
        }while(userInput > inputUpperBound || userInput < 1);
        return userInput;
    }

    //Prints separator to help design/format the output to the console
    public void printSeparator(int nTimes){
        for(int n=0; n<nTimes; n++){
            System.out.print("-");
        }
        System.out.println();
    }

    //Prints message to the console followed by a line
    public void printMessage(String message){
        System.out.println(message);
    }

    //Prints <nLines> times spaces each on a line
    public void clearConsole(){
        System.out.println("\033[H\033[2J");
    }

    public void pause(){
        System.out.println("Press Enter key to continue...");
        scanner.nextLine();
    }

    public void flushConsole(){
        scanner.nextLine();
    }
}