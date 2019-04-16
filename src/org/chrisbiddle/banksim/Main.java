package org.chrisbiddle.banksim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        System.out.print("Your name: ");
        String name = userInput.nextLine();

        System.out.print("Teller's name: ");
        Teller teller = new Teller(name, userInput.nextLine());

        System.out.print("Bank account's balance: ");
        Bank bank = new Bank(userInput.nextDouble());

        Interest interest = new Interest();

        System.out.println("Investment type:\n" +
                "- Continuous (c)\n" +
                "- Yearly (y)\n" +
                "- Monthly (m)\n" +
                "- Daily (d)");
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

        // Interest Rate
        System.out.println("Bank's interest rate: ");
        interest.setRate(userInput.nextDouble() / 100);
        userInput.nextLine();

        System.out.println();
        teller.welcome(name);

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
                case EXIT:
                    teller.farewell();
                    System.exit(0);
                    break;
            }
        }

    }
}
