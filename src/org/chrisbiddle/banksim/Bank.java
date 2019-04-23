package org.chrisbiddle.banksim;

import java.util.Scanner;

/**
 * A constant list of things that you can do in the bank
 */
enum Facility {
    NOTHING, DEPOSIT, WITHDRAW, LOAN, INTEREST, EXIT
}

/**
 * Does the functions of a bank
 */
public class Bank {
    private double balance;

    /**
     * Creates new bank object
     * @param balance The initial balance of the bank account
     */
    public Bank(double balance) {
        this.balance = balance;
    }

    /**
     * Prompts the user for what he wants to do at the bank
     * @return The facility that the user chooses
     */
    public Facility getFacility() {
        System.out.println("- Deposit money (d)");
        System.out.println("- Withdraw money (w)");
        System.out.println("- Create a loan (l)");
        System.out.println("- Leave money in the bank for a time (t)");
        System.out.println("- Exit the bank (e)");

        Scanner userInput = new Scanner(System.in);
        String optionInput = userInput.nextLine();
        System.out.println();

        Facility option;
        switch (optionInput) {
            case "d":
                option = Facility.DEPOSIT;
                break;
            case "w":
                option = Facility.WITHDRAW;
                break;
            case "l":
                option = Facility.LOAN;
                break;
            case "t":
                option = Facility.INTEREST;
                break;
            case "e":
                option = Facility.EXIT;
                break;
            default:
                option = Facility.NOTHING;
                break;
        }
        return option;
    }

    /**
     * @return The balance of the account
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Adds money to the account
     * @param money Money to add
     */
    public void addMoney(double money) {
        this.balance += money;
    }

    /**
     * Withdraws money from the account
     * @param money Money to withdraw
     */
    public void withdrawMoney(double money) {
        this.balance -= money;
    }
}
