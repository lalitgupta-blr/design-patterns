public class TemplatePatternTogetherWithStrategyPattern {

    public static void main(String[] args) {
        MoneyInvestment moneyInvestment1 = new InvestmentInBondForSimpleInterest(200,2);
        double gains1 = moneyInvestment1.increaseMoney();
        System.out.println(" Invested Money after investment in bond for simple interest : " + gains1);

        MoneyInvestment moneyInvestment2 = new InvestmentInBondForCompundInterest(200,2);
        double gains2 = moneyInvestment2.increaseMoney();
        System.out.println(" Invested Money after investment in bond for compund interest : " + gains2);
    }

}

abstract class MoneyInvestment
{
    public double principal, duration;

    public MoneyInvestment(double principal, double duration){
        this.principal=principal;
        this.duration=duration;
    }

    public double increaseMoney(){
        //Following is the template for incresing money
        //Calculate gains
        double gains = calculateGains();
        //Calculate new principal after adding gains
        this.principal = this.principal + gains;

        return this.principal;
    }

    protected abstract double calculateGains();
}

class InvestmentInBondForSimpleInterest extends MoneyInvestment
{
    static double RATE_OF_INTEREST_FOR_SIMPLE_BONDS=5;

    public InvestmentInBondForSimpleInterest (double principal, double duration){
        super( principal,  duration);
    }

    @Override
    protected double calculateGains() {
        SimpleIntrestOnBondsStrategy strategy1 = new  SimpleIntrestOnBondsStrategy();
        MoneyIncreaseUsingBonds calc1 = new MoneyIncreaseUsingBonds(strategy1);
        double interestAmount = calc1.interestAmount(this.principal, RATE_OF_INTEREST_FOR_SIMPLE_BONDS, this.duration);
        return interestAmount;
    }
}

class InvestmentInBondForCompundInterest extends MoneyInvestment
{
    static double RATE_OF_INTEREST_FOR_COMPUND_BONDS=5;

    public InvestmentInBondForCompundInterest (double principal, double duration){
        super( principal,  duration);
    }

    @Override
    protected double calculateGains() {
        CompoundIntrestOnBondsStrategy strategy2 = new  CompoundIntrestOnBondsStrategy();
        MoneyIncreaseUsingBonds calc2 = new MoneyIncreaseUsingBonds(strategy2);
        double interestAmount = calc2.interestAmount(this.principal, RATE_OF_INTEREST_FOR_COMPUND_BONDS, this.duration);
        return interestAmount;
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
