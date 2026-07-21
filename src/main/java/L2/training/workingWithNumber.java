package L2.training;

import java.util.Scanner;

public class workingWithNumber {
    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 4;

        System.out.println(add(n1,n2));
    }

   static int add(int a,int b){
        return a+b;
    }

   static double divide(double a,double b){
        return a/b;
   }
   static int bigSum(int a,int b,int c){
        return a+b+c;

   }
   static int fakeAverage(int a, int b){
        return a+b/2;
   }
   static double realAverage(double a,double b){
        return a+b/2;
   }
   static boolean printState(boolean a){
        return a;
   }
   static boolean flippedState(boolean a){
       return !a;
   }



}
