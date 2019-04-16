package org.chrisbiddle.banksim;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Teller {
    private String name;
    private String tellerName;

    public Teller(String name, String tellerName) {
        this.name = name;
        this.tellerName = tellerName;
    }

    public void welcome(String name) {
        System.out.printf("Welcome to the bank, %s!\n", name);
        System.out.println("What can I help you with?");
    }

    public void farewell() {
        System.out.printf("\nGoodbye, %s!", name);
    }

    public void tellBalance(Bank bank) {
        BigDecimal formatted = new BigDecimal(bank.getBalance()).setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("\nYou now have $" + formatted + " in your account.");
    }

    public void depositMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("\nHow much money would you like to deposit?");
        bank.addMoney(userInput.nextDouble());

        this.tellBalance(bank);
    }

    public void withdrawMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("\nHow much money would you like to withdraw?");
        bank.withdrawMoney(userInput.nextDouble());

        this.tellBalance(bank);
    }

    public void setupInterest(Bank bank, Interest interest) {
        Scanner userInput = new Scanner(System.in);

        // Time Period
        System.out.println("\nHow many years would like to invest for?");
        interest.setTime(userInput.nextDouble());
        userInput.nextLine();

        interest.setPrinciple(bank.getBalance());
        double money = interest.evaluate();
        bank.addMoney(money);

        this.tellBalance(bank);
    }

    public void setupLoan(Bank bank, Interest interest) {
        Scanner userInput = new Scanner(System.in);

        System.out.println("\nHow many years would you like to take out the loan for?");
        interest.setTime(userInput.nextDouble());
        userInput.nextLine();

        interest.setPrinciple(bank.getBalance());
        double money = interest.evaluate();
        bank.withdrawMoney(money);

        this.tellBalance(bank);
    }

}
