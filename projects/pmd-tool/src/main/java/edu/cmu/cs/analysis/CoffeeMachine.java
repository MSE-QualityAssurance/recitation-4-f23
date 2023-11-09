package edu.cmu.cs.analysis;

import java.util.Scanner;

/**
 * Coffee Machine Credits to: https://github.com/Pharele/simple-coffee-machine/
 */
public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    static int waterHas = 400;
    static int milkHas = 540;
    static int coffeeBeansHas = 120;
    static int disposableCups = 9;
    static int money = 550;

    public static void action() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        switch (scanner.nextLine()) {
            case "buy":
                buy();
                action();
                break;
            case "fill":
                fill();
                action();
                break;
            case "take":
                take();
                action();
                break;
            case "remaining":
                remaining();
                action();
                break;
            case "exit":
                break;
            default:
                System.out.println("Wrong input!");
                action();
                break;
        }
    }

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scanner.nextLine()) {
            case "1":
                buyEspresso();
                break;
            case "2":
                buyLatte();
                break;
            case "3":
                buyCappuccino();
                break;
            default:
                break;
        }
    }
    public static void buyEspresso() {
        if (disposableCups < 1 || waterHas < 250 || coffeeBeansHas < 16) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        disposableCups -= 1;
        waterHas -= 250;
        coffeeBeansHas -= 16;
        money += 4;
        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void buyLatte() {
        if (disposableCups < 1 || waterHas < 350 || coffeeBeansHas < 20) {
            System.out.println("Sorry, not enough water!");
            return;
        } else if (milkHas < 75) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        disposableCups -= 1;
        waterHas -= 350;
        milkHas -= 75;
        coffeeBeansHas -= 20;
        money += 7;
        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void buyCappuccino() {
        if (disposableCups < 1 || waterHas < 200 || coffeeBeansHas < 12) {
            System.out.println("Sorry, not enough water!");
            return;
        } else if (milkHas < 100) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        disposableCups -= 1;
        waterHas -= 200;
        milkHas -= 100;
        coffeeBeansHas -= 12;
        money += 6;
        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void fill() {
        System.out.println("Write how many ml of water you want to add:");
        waterHas += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkHas += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeansHas += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        disposableCups += scanner.nextInt();
    }

    public static void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }

    public static void remaining() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", waterHas);
        System.out.printf("%d ml of milk\n", milkHas);
        System.out.printf("%d g of coffee beans\n", coffeeBeansHas);
        System.out.printf("%d disposable cups\n", disposableCups);
        System.out.printf("$%d of money\n\n", money);
    }

    public static void main(String[] args) {
        action();
    }
}
