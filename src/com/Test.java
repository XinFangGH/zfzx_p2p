package com;

import java.math.BigDecimal;

public class Test {
     
    private int i = 10;
    private Object object = new Object();
     
    public static void main(String[] args){
      BigDecimal d = new BigDecimal("0.476334");
      System.out.println(d.setScale(2, BigDecimal.ROUND_HALF_UP));
    } 
     
     
}