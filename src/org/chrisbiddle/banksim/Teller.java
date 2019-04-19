package org.chrisbiddle.banksim;

import java.util.Scanner;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Does all of the functions that a teller would do
 */
public class Teller {
    private String customer;
    private String tellerName;

    /**
     * @param tellerName Takes the teller's name
     * @param customer Takes the customer's name
     */
    public Teller(String tellerName, String customer) {
        this.tellerName = tellerName;
        this.customer = customer;
    }

    /**
     * Welcomes the customer by name
     */
    public void welcome() {
        System.out.printf("%s: Welcome to the bank, %s!\n", this.tellerName, this.customer);
        System.out.printf("%s: What can I help you with?\n", this.tellerName);
    }

    /**
     * Wishes the customer goodbye by name
     */
    public void farewell() {
        System.out.printf("%s: Goodbye, %s!\n", this.tellerName, this.customer);
    }

    /**
     * Tells the user his balance with correct rounding and currency
     * @param bank The bank object
     */
    public void tellBalance(Bank bank) {
        String formattedBalance = NumberFormat.getCurrencyInstance().format(new BigDecimal(bank.getBalance()));
        System.out.printf("%s: You now have %s in your account.\n", this.tellerName, formattedBalance);
    }

    /**
     * Deposits money into the bank account
     * @param bank The bank object
     */
    public void depositMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s: How much money would you like to deposit?\n", this.tellerName);
        bank.addMoney(userInput.nextDouble());
        System.out.println();

        this.tellBalance(bank);
    }

    /**
     * Withdraws money from the bank account
     * @param bank The bank object
     */
    public void withdrawMoney(Bank bank) {
        Scanner userInput = new Scanner(System.in);

        System.out.printf("%s: How much money would you like to withdraw?\n", this.tellerName);
        bank.withdrawMoney(userInput.nextDouble());
        System.out.println();

        this.tellBalance(bank);
    }

    /**
     * Lets the customer wait for his account's balance to grow
     * @param bank The bank object
     * @param interest The interest object for the interest
     */
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

    /**
     * Lets the customer take out money for a time
     * @param bank The bank object
     * @param interest The interest object for the loan
     */
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
