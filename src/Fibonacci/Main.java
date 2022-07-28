package Fibonacci;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter the number of sequences ☺ : ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer> fibonacciArray = new ArrayList<>();

        for (int i = 0; i < n; i++) fibonacciArray.add(getFibonacciSequence(i));

        System.out.println(fibonacciArray);
    }

    public static int getFibonacciSequence(int i) {
        if (i <= 1) return i;
        else return getFibonacciSequence(i-1) + getFibonacciSequence(i-2);
    }
}
