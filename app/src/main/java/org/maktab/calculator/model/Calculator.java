package org.maktab.calculator.model;

public class Calculator {
    private double result ;

    public Calculator(){
        result = 0;
    }

    public double add(double a,double b){
        if(b>(Double.MAX_VALUE - a)){
            return Double.MAX_VALUE;
        }
        result = a+b;
        return result;
    }
    public double add(double c){
        result = this.add(result,c);
        return result;
    }
    public double sub(double a,double b){
        result = a-b;
        return result;
    }
    public double sub(double c){
        result = this.sub(result,c);
        return result;
    }

    public double mul(double a,double b){
        if(a!=0 && b>(Double.MAX_VALUE / a)){
            return Double.MAX_VALUE;
        }
        result = a*b;
        return result;
    }

    public double mul(double c){
        result=mul(result,c);
        return result;
    }
    public double div(double a,double b){
        if(b!=0){
            result = a/b;
        }else{
            result=Double.MAX_VALUE;
            System.out.println("Error :division by zero");
        }
        return result;
    }

    public double div(double c){
        result=div(result,c);
        return result;
    }

    public double getResult() {
        return result;
    }

    public void clearResult(){
        result=0;
    }

    public void calculate( double number, char op) {
        switch (op) {
            case '+':
                add(number);
                break;
            case '-':
                sub(number);
                break;
            case '*':
                mul(number);
                break;
            case '/':
                div(number);
                break;
        }
    }

}
