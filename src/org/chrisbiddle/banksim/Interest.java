package org.chrisbiddle.banksim;

// TODO: Integreate "implements"

/**
 * A constant list of interval types available
 */
enum Interval {
    CONTINUOUS, DAILY, MONTHLY, YEARLY
}
enum Type {
    INTEREST, LOAN
}

/**
 * Calculates how much you owe
 */
public class Interest {
    private Interval interval;
    private double principle;
    private double interestRate;
    private double loanRate;
    private double time;

    /**
     * Sets the principle money that is taken out
     */
    public void setPrinciple(double principle) {
        this.principle = principle;
    }

    /**
     * Sets the type of loan
     */
    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    /**
     * Sets the percentage rate of interest
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    /**
     * Sets the amount of time for the loan
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Returns how much money you owe after the time period
     */
    public double evaluate(Type type) {
        double rate = 0;
        switch(type) {
            case INTEREST:
                rate = this.interestRate;
                break;
            case LOAN:
                rate = this.loanRate;
                break;
        }

        double output = 0;
        switch(this.interval) {
            case CONTINUOUS:
                output = this.principle * Math.exp(rate * this.time);
                break;
            case YEARLY:
                output = this.principle * Math.pow(1 + rate, this.time);
                break;
            case MONTHLY:
                output = this.principle * Math.pow(1 + rate / 12, 12 * this.time);
                break;
            case DAILY:
                output = this.principle * Math.pow(1 + rate / 365, 365 * this.time);
                break;
        }
        return output - this.principle;
    }
}
