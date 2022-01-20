package com.test.myfinance;

public class MoneyMove {


    private int sum;
    private String name;
    private boolean isRegular = false;
    private boolean isSpend = true;

   public MoneyMove (int sum, String name, boolean isRegular, boolean isSpend) {
       this.sum = sum;
       this.name = name;
       this.isRegular = isRegular;
       this.isSpend = isSpend;
   }

    public String getName() {
        return name;
    }
    public int getSum() {
        return sum;
    }
    public boolean getIsRegular(){
       return isRegular;
    }
    public boolean getIsSpend(){
       return isSpend;
    }

}
