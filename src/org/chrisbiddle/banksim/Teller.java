package org.chrisbiddle.banksim;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class Teller {
    private String customer;
    private String tellerName;

    public Teller(String tellerName, String customer) {
        this.tellerName = tellerName;
        this.customer = customer;
    }

    public void welcome() {
        System.out.printf("%s: Welcome to the bank, %s!\n", this.tellerName, this.customer);
        System.out.printf("%s: What can I help you with?\n", this.tellerName);
    }

    public void farewell() {
        System.out.printf("%s: Goodbye, %s!\n", this.tellerName, this.customer);
    }

    public void tellBalance(Bank bank) {
        String formattedBalance = NumberFormat.getCurrencyInstance().format(new BigDecimal(bank.getBalance()));
        System.out.printf("%s: You now have %s in your account.\n", this.tellerName, formattedBalance);
    }

    public void depositMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s: How much money would you like to deposit?\n", this.tellerName);
        bank.addMoney(userInput.nextDouble());
        System.out.println();

        this.tellBalance(bank);
    }

    public void withdrawMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s: How much money would you like to withdraw?\n", this.tellerName);
        bank.withdrawMoney(userInput.nextDouble());
        System.out.println();

        this.tellBalance(bank);
    }

    public void setupInterest(Bank bank, Interest interest) {
        Scanner userInput = new Scanner(System.in);

        // Time Period
        System.out.printf("%s: How many years would like to invest for?\n", this.tellerName);
        interest.setTime(userInput.nextDouble());
        System.out.println();

        interest.setPrinciple(bank.getBalance());
        double money = interest.evaluate(Type.INTEREST);
        bank.addMoney(money);

        this.tellBalance(bank);
    }

    public void setupLoan(Bank bank, Interest interest) {
        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s: How many years would you like to take out the loan for?\n", this.tellerName);
        interest.setTime(userInput.nextDouble());
        System.out.println();

        System.out.printf("%s: How much money would you like loaned to you?\n", this.tellerName);
        interest.setPrinciple(userInput.nextDouble());
        System.out.println();

        double money = interest.evaluate(Type.LOAN);
        bank.withdrawMoney(money);

        this.tellBalance(bank);
    }

}
