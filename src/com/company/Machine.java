package com.company;

import java.util.Scanner;

public class Machine {
    final static int DEFAULT_MONEY = 550;
    final static int DEFAULT_WATER = 400;
    final static int DEFAULT_MILK = 540;
    final static int DEFAULT_COFFEE = 120;
    final static int DEFAULT_CUPS = 9;
    public int water, milk, coffee, cups, money;

    public static void getStarted(Machine coffeeMachine) {
        coffeeMachine.water = DEFAULT_WATER;
        coffeeMachine.milk = DEFAULT_MILK;
        coffeeMachine.coffee = DEFAULT_COFFEE;
        coffeeMachine.cups = DEFAULT_CUPS;
        coffeeMachine.money = DEFAULT_MONEY;

    }

    public static void showStatus(Machine coffeeMachine) {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """, coffeeMachine.water, coffeeMachine.milk, coffeeMachine.coffee, coffeeMachine.cups, coffeeMachine.money);
    }

    public static String getAction(Scanner scanner) {

        System.out.println("\nWrite action (buy, fill, take, remaining, exit):  ");
        return scanner.next();
    }


    public static int buyCheck(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        return scanner.nextInt();
    }

    public static boolean checkEnough(Machine coffeeMachine, Coffee coffee) {
        boolean resultWater = coffeeMachine.water - coffee.water >= 0;
        boolean resultMilk = coffeeMachine.milk - coffee.milk >= 0;
        boolean resultCoffee = coffeeMachine.coffee - coffee.coffee >= 0;
        boolean resultCups = coffeeMachine.cups >= 1;
        return resultWater && resultMilk && resultCoffee && resultCups;
    }

    public static String checkWhatNotEnough(Machine coffeeMachine, Coffee coffee) {
        boolean resultWater = coffeeMachine.water - coffee.water < 0;
        boolean resultMilk = coffeeMachine.milk - coffee.milk < 0;
        boolean resultCoffee = coffeeMachine.coffee - coffee.coffee < 0;
        boolean resultCups = coffeeMachine.cups < 1;
        if (resultWater) {
            return "water";
        } else if (resultMilk) {
            return "milk";
        } else if (resultCoffee) {
            return "coffee beans";
        } else if (resultCups) {
            return "disposable cups";
        }
        return "ok";
    }

    public static void buy(Machine coffeeMachine, Coffee coffee) {
        if (Machine.checkEnough(coffeeMachine, coffee)) {
            System.out.println("I have enough resources, making you a coffee!");
            coffeeMachine.water -= coffee.water;
            coffeeMachine.milk -= coffee.milk;
            coffeeMachine.coffee -= coffee.coffee;
            coffeeMachine.cups--;
            coffeeMachine.money += coffee.price;
        } else {
            String resource = Machine.checkWhatNotEnough(coffeeMachine, coffee);
            System.out.printf("Sorry, not enough %s!\n", resource);
        }
    }

    public static void fill(Machine coffeeMachine, Scanner scanner) {
        System.out.println("Write how many ml of water you want to add: ");
        coffeeMachine.water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        coffeeMachine.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        coffeeMachine.coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        coffeeMachine.cups += scanner.nextInt();

    }

    public static int take(Machine coffeeMachine) {
        int cash = coffeeMachine.money;
        coffeeMachine.money = 0;
        return cash;
    }

}
