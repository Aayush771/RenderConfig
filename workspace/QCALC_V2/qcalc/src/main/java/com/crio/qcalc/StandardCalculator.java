package com.crio.qcalc;

public class StandardCalculator {
   protected double result;

    public static void getVersion(){
        System.out.println("Standard Calculator 1.0");
    }
    public final void add(double num1, double num2){

        result = num1+num2;
        if((result == Double.MAX_VALUE) || (result == Double.POSITIVE_INFINITY)){

            throw new ArithmeticException("Double overflow");
    
        }
    
    }


public final void  subtract(double num1, double num2){

    result = num1-num2;
    if((result == -Double.MAX_VALUE) || (result == Double.NEGATIVE_INFINITY)){

        throw new ArithmeticException("Double overflow");

    }
}


public final  void multiply(double num1, double num2){
    result = num1*num2;
    if((result <= -Double.MAX_VALUE) || (result == Double.NEGATIVE_INFINITY) 
    || (result >= Double.MAX_VALUE)){

        throw new ArithmeticException("Double overflow");

    }

}


public final void divide(double num1, double num2){
    
    if(num2 == 0.0){
        throw new ArithmeticException("Divide By Zero");

    }
    result = num1/num2;
}
 public static void main(String[] args) {
    System.out.println("Starting Qalc");
    StandardCalculator  calc = new StandardCalculator();
    calc.add(2,6);
    System.out.println(calc.getResult());
    calc.subtract(7, 5);
    System.out.println(calc.getResult());
    calc.multiply(5,7);
    System.out.println(calc.getResult());
    calc.divide(6,2);
    System.out.println(calc.getResult());
 }
public double getResult() {
    return this.result;
}
  public void setResult(int result) {
    if (result != 0) return;
     this.result = result;
  }


  public void printResult(){
    System.out.println("Standard Calculator Result:"+ result);
}


}
