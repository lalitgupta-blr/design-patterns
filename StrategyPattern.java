package strategy;


public class StrategyPattern {

	public static void main(String[] args) { 
	    MultiplicationStrategy strategy1 = new  BitWiseMultiplyStrategy();
	    SimpleInterestCalculator calc1 = new SimpleInterestCalculator(strategy1);
	    //principal=200, rateOfInterest=5% ,duration = 2 years
	    int result1 = calc1.interestAmount(200, 5, 2);
	    System.out.println("\n For BitWiseMultiplyStrategy ");
	    System.out.println("\n Interest amount: " + result1);
	    
	    MultiplicationStrategy strategy2 = new  MultiplyOperatorStrategy();
	    SimpleInterestCalculator calc2 = new SimpleInterestCalculator(strategy2);
	   //principal=200, rateOfInterest=5% ,duration = 2 years
	    int result2 = calc2.interestAmount(200, 5, 2);
	    System.out.println("\n For MultiplyOperatorStrategy ");
	    System.out.println("\n Interest amount: " + result2);
	    
	  }
}

interface  MultiplicationStrategy
{
	int calculateMultiplication(int a, int b);
}

class BitWiseMultiplyStrategy implements MultiplicationStrategy
{
	@Override
	public int calculateMultiplication(int x, int y){
	        boolean neg = false;
	        if(x < 0 && y >= 0){
	            x = -x;
	            neg = true;
	        }
	        else if(y < 0 && x >= 0){
	            y = -y;
	            neg = true;
	        }else if( x < 0 && y < 0){
	            x = -x;
	            y = -y;
	        }

	        int res = 0;
	        while(y!=0){
	            if((y & 1) == 1) res += x;
	            x <<= 1;
	            y >>= 1;
	        }
	        return neg ? (-res) : res;
	}
}

class MultiplyOperatorStrategy implements MultiplicationStrategy
{
	@Override
	public int calculateMultiplication(int x,int y){
	       int res = 0, count = 0; 
	       while (y > 0) 
	        { 
	            // check for set bit and left  
	            // shift n, count times 
	            if (y % 2 == 1)              
	                res += x << count; 
	      
	            // increment of place  
	            // value (count) 
	            count++; 
	            y /= 2; 
	        } 
	          
	        return res;
	}
}


class SimpleInterestCalculator
{
  private MultiplicationStrategy strategy;

  public SimpleInterestCalculator(MultiplicationStrategy strategy)
  {
    this.strategy = strategy;
  }

  public int interestAmount(int principal, int rateOfInterest, int duration)
  {
    return  strategy.calculateMultiplication(strategy.calculateMultiplication(principal, rateOfInterest),duration)/100;
  }
}