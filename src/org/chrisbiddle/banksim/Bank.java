package org.chrisbiddle.banksim;

import java.util.Scanner;

enum Facility {
    init, DEPOSIT, WITHDRAW, MORTGAGE, INTEREST, LEAVE
}

public class Bank {
    double balance;

    public Bank(double balance) {
        this.balance = balance;
    }

    public Facility getFacility() {
        System.out.println("- Deposit money (d)");
        System.out.println("- Withdraw money (w)");
        System.out.println("- Setup a mortgage (m)");
        System.out.println("- Leave money in the bank for a time (t)");
        System.out.println("- Leave the bank (l)");

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
            case "m":
                option = Facility.MORTGAGE;
                break;
            case "t":
                option = Facility.INTEREST;
                break;
            case "l":
                option = Facility.LEAVE;
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
