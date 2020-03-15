import java.lang.Math;

public class StrategyPattern {

    public static void main(String[] args) {
        SimpleIntrestOnBondsStrategy strategy1 = new  SimpleIntrestOnBondsStrategy();
        MoneyIncreaseUsingBonds calc1 = new MoneyIncreaseUsingBonds(strategy1);
        //principal=200, rateOfInterest=5% ,duration = 2 years
        double result1 = calc1.interestAmount(200, 5, 2);
        System.out.println("\n For SimpleIntrestOnBondsStrategy ");
        System.out.println("\n Interest amount: " + result1);

        CompoundIntrestOnBondsStrategy strategy2 = new  CompoundIntrestOnBondsStrategy();
        MoneyIncreaseUsingBonds calc2 = new MoneyIncreaseUsingBonds(strategy2);
        //principal=200, rateOfInterest=5% ,duration = 2 years
        double result2 = calc2.interestAmount(200, 5, 2);
        System.out.println("\n For CompoundIntrestOnBondsStrategy ");
        System.out.println("\n Interest amount: " + result2);
    }
}

interface  BondsInterestStrategy
{
    double calculateInterest(double principal, double rateOfInterest, double duration);
}


class SimpleIntrestOnBondsStrategy implements BondsInterestStrategy
{
    @Override
    public double calculateInterest(double principal, double rateOfInterest, double duration){
        return principal * rateOfInterest * duration/100;
    }
}

class CompoundIntrestOnBondsStrategy implements BondsInterestStrategy
{
    @Override
    public double calculateInterest(double principal, double rateOfInterest, double duration){
        return principal * Math.pow (( 1+ rateOfInterest/100),duration) - principal;
    }
}

class MoneyIncreaseUsingBonds
{
    private BondsInterestStrategy strategy;

    public MoneyIncreaseUsingBonds(BondsInterestStrategy strategy)
    {
        this.strategy = strategy;
    }

    public double interestAmount(double principal, double rateOfInterest, double duration)
    {
        return  strategy.calculateInterest(principal, rateOfInterest,duration);
    }
}


