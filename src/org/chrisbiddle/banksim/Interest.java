package org.chrisbiddle.banksim;

// TODO: Integreate "implements"

/**
 * A constant list of interval types available
 */
enum Interval {
    CONTINUOUS, DAILY, MONTHLY, YEARLY
}

/**
 * A constant list of uses of interest
 */
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
     * @param principle The principle to start out with
     */
    public void setPrinciple(double principle) {
        this.principle = principle;
    }

    /**
     * Sets the interval
     * @param interval How often the load is calculated
     */
    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    /**
     * Sets the percentage rate of interest
     * @param interestRate The interest's rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Sets the loan rate
     * @param loanRate The loan's rate
     */
    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    /**
     * Sets the amount of time the interest lasts
     * @param time The duration of the interest
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Returns how much money you owe after the time period
     * @param type The type of interest
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
            case YEARLY:
                output = this.principle * Math.pow(1 + rate, this.time);
                break;
            case MONTHLY:
                output = this.principle * Math.pow(1 + rate / 12, 12 * this.time);
                break;
            case DAILY:
                output = this.principle * Math.pow(1 + rate / 365, 365 * this.time);
                break;
            case CONTINUOUS:
                output = this.principle * Math.exp(rate * this.time);
                break;
        }
        return output - this.principle;
    }
}
