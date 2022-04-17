package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Machine coffeeMachine = new Machine();

        Machine.getStarted(coffeeMachine);

        boolean status = true;
        while (status) {
            String choice = Machine.getAction(scanner);
            switch (choice) {
                case "buy": {
                    int buyCheck = Machine.buyCheck(scanner);
                    switch (buyCheck) {
                        case 1:
                            Machine.buy(coffeeMachine, Coffee.ESPRESSO);
                            break;
                        case 2:
                            Machine.buy(coffeeMachine, Coffee.LATTE);
                            break;
                        case 3:
                            Machine.buy(coffeeMachine, Coffee.CAPPUCCINO);
                            break;
                    }
                    System.out.println();

                    break;
                }
                case "fill":
                    Machine.fill(coffeeMachine, scanner);
                    System.out.println();

                    break;

                case "take":
                    int cash = Machine.take(coffeeMachine);
                    System.out.printf("I gave you $%d \n", cash);
                    System.out.println();


                case "remaining":
                    Machine.showStatus(coffeeMachine);
                    break;

                case "exit":
                    status = false;
                    break;
            }
        }

    }


}

