package org.chrisbiddle.banksim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        /* Setup Bank */
        System.out.print("Your name: ");
        String name = userInput.nextLine();

        System.out.print("Teller's name: ");
        Teller teller = new Teller(userInput.nextLine(), name);

        System.out.print("Bank account's balance ($): ");
        Bank bank = new Bank(userInput.nextDouble());

        Interest interest = new Interest();

        System.out.println("Interest compounded:\n" +
                "- Yearly (y)\n" +
                "- Monthly (m)\n" +
                "- Daily (d)\n" +
                "- Continuous (c)");
        userInput.nextLine();
        switch (userInput.nextLine()) {
            case "c":
                interest.setInterval(Interval.CONTINUOUS);
                break;
            case "y":
                interest.setInterval(Interval.YEARLY);
                break;
            case "m":
                interest.setInterval(Interval.MONTHLY);
                break;
            case "d":
                interest.setInterval(Interval.DAILY);
                break;
        }

        System.out.println("Bank's interest rate (%): ");
        interest.setInterestRate(userInput.nextDouble() / 100);

        System.out.println("Bank's loan rate (%): ");
        interest.setLoanRate(userInput.nextDouble() / 100);
        System.out.println();

        System.out.println("-----------------\n");

        /* Simulates what happens when you walk into the bank */

        System.out.println("*You walk into the bank*");

        System.out.println();
        teller.welcome();

        // The loop that surrounds the majority of the program
        while(true) {
            Facility facility = bank.getFacility();

            switch (facility) {
                case DEPOSIT:
                    teller.depositMoney(bank);
                    break;
                case WITHDRAW:
                    teller.withdrawMoney(bank);
                    break;
                case INTEREST:
                    teller.setupInterest(bank, interest);
                    break;
                case LOAN:
                    teller.setupLoan(bank, interest);
                    break;
                case NOTHING:
                    teller.getMad();
                    break;
                case EXIT:
                    teller.farewell();
                    System.out.println();
                    System.out.println("*You leave the bank*");
                    System.exit(0);
                    break;
            }
        }

    }
}
