package org.chrisbiddle.banksim;

import java.util.Scanner;

enum Facility {
    init, DEPOSIT, WITHDRAW, LOAN, INTEREST, EXIT
}

public class Bank {
    private double balance;

    public Bank(double balance) {
        this.balance = balance;
    }

    public Facility getFacility() {
        System.out.println("- Deposit money (d)");
        System.out.println("- Withdraw money (w)");
        System.out.println("- Create a loan (l)");
        System.out.println("- Leave money in the bank for a time (t)");
        System.out.println("- Exit the bank (e)");

        Scanner userInput = new Scanner(System.in);
        String optionInput = userInput.nextLine();

        Facility option = Facility.init;
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
        }
        return option;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addMoney(double money) {
        this.balance += money;
    }

    public void withdrawMoney(double money) { this.balance -= money; }
}
