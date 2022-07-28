package LongMultiplication;

import java.util.Scanner;

public class Main {
    public static int a, b, c, productToAdd;
    public static String A, B;
    public static Scanner sc;
    public static boolean done;
    public static void main(String[] args) {
        sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n");
            System.out.println("If a x b = c and a, b, and c are integers...");
            enterAEnterB();

            //to prevent a few bugs, we need to restrict the range of numbers that can be calculated
            if ((c > 100000000)) {
                while ((c > 100000000) | (c < 0)) {
                    System.out.println();
                    System.out.println("Numbers are too big (product is greater than one hundred million)");
                    enterAEnterB();
                }
            } else if (a < 0 | b < 0) {
                System.out.println();
                System.out.println("Number's shouldn't be negative for now");
                enterAEnterB();
            }

        /*To make the solution more readable,
        the max length of characters per line is set to be the number of digits contained in the largest number amongst a, b, and c
        For example, if we're to multiply 23 by 34, we'll get 782
        782 has the most digits of the three so the largest length will be 5 (3 + 2)
         */
            int largestLength = largestLength() + 2;

            System.out.println("\nSolution:\n");

            //print the multiplicand
            for (int i = 0; i < (largestLength - String.valueOf(a).length()); i++)
                System.out.print(" ");
            System.out.println(a);

            System.out.print("x");

            //print the multiplier
            for (int i = 0; i < (largestLength - String.valueOf(b).length() - 1); i++)
                System.out.print(" ");
            System.out.println(b);

            //draw a line
            for (int i = 0; i < largestLength; i++)
                System.out.print("-");
            System.out.println();

            //convert b to an array so that we can split it and make use of its digits later
            char[] bChar = String.valueOf(b).toCharArray();

        /* In a long multiplication method
        the first number is repeatedly multiplied by the digits of the second number starting from behind
        until every digit of the second number has multiplied the first one
        For example: 123 x 45
        We first do 123 x 5, and then 123 x 4,
        and then we find the sum after relatively shifting each product to the left by a tens value as shown below

           615
        + 492

        This is exactly what the block of code below does
         */
            for (int i = String.valueOf(b).length() - 1; i >= 0; i--) {
                int intFormOfbChar = Integer.parseInt(String.valueOf(bChar[i])), lengthOfbChar = String.valueOf(intFormOfbChar * a).length();

                if (bChar[i] != '0') {

                    //print a "+" sign before the last product
                    if ((i == 0) & (bChar.length != 1) & (String.valueOf(checkZeros(b)).length() != 1)) {
                        System.out.print("+");
                    }

                    //this specifies the number of spaces to be left before each product in such a way it's shifted one ten's place to the left
                    productToAdd = intFormOfbChar * a;
                    int lengthOfProductToAdd = String.valueOf(productToAdd).length();
                    int jj = largestLength - 1 + i - lengthOfProductToAdd;

                    for (int j = 0; j < largestLength; j++)   System.out.print(" ");
                    for (int j = 0; j < jj  ; j++) System.out.print("\b");
                    if (i == 0) System.out.print("\b");

                    System.out.print(productToAdd);

                /*If we're to multiply 234 by 200
                we're going to multiply by zero twice which gives zero
                and adding zero to a number gives the same number,
                so we don't need to multiply if the digit is zero
                rather, we print a ↓ sign to indicate that the zero is "brought down" instead

                So we'll have something like
                  234
                x 200
                -----
                468↓↓
                -----
                46800
                -----
                 */
                    if (((String.valueOf(productToAdd).length()) < (String.valueOf(c).length())) & (String.valueOf(checkZeros(b)).length() == 1)) {
                        for (int m = 0; m < ((String.valueOf(c).length()) - (String.valueOf(productToAdd).length())); m++)
                            System.out.print("↓");
                    }

                    System.out.println();
                } else {
                    for (int j = 0; j < largestLength - 1; j++)   System.out.print(" ");
                    System.out.println(c);
                }

            }

            //draw a line
            for (int i = 0; i < largestLength; i++)
                System.out.print("-");

        /*if and only if the second number contains more than one digit will we need to use the long multiplication method.
        And so a final answer is needed after adding two or more products
        But if the second number has just one digit, this block of code will not be executed
         */
            if (bChar.length > 1) {

                //print final result
                System.out.println("\n  " + c);

                //draw a line
                for (int i = 0; i < largestLength; i++)
                    System.out.print("-");
            }
        }

    }

    //finds the number with the highest number of digits among a, b, and c.
    public static int largestLength() {
        String a = String.valueOf(Main.a);
        String b = String.valueOf(Main.b);
        String c = String.valueOf(Main.c);

        int ll = 0;

        if (a.length() > ll) ll = a.length();
        if (b.length() > ll) ll = b.length();
        if (c.length() > ll) ll = c.length();

        return ll;
    }

    //eradicate 0's at the end of a number (100 changes to 1)
    public static int checkZeros (int num) {
         int reverse = reverseInt(num);
         return reverseInt(reverse);
    }

    public static int reverseInt (int num) {
        int reverse = 0;

        while (num != 0) {
            reverse *= 10;
            reverse += num % 10;
            num /= 10;
        }

        return reverse;
    }

    public static void enterAEnterB() {
        System.out.print("Enter a: ");
        A = sc.next();

        //ensure that a is an integer
        done = false;
        while ( (!done)) {
            try {
                a = Integer.parseInt(A);
                done = true;
            } catch (NumberFormatException e) {
                System.out.println("\na must be an integer");
                System.out.print("Enter a: ");
                A = sc.next();
            }
        }

        System.out.println();

        System.out.print("Enter b: ");
        B = sc.next();

        //ensure that b is an integer
        done = false;
        while ( (!done)) {
            try {
                b = Integer.parseInt(B);
                done = true;
            } catch (NumberFormatException e) {
                System.out.println("\nb must be an integer");
                System.out.print("Enter b: ");
                B = sc.next();
            }
        }

        //continuously ask the user for input if b > a
        while ((String.valueOf(b).length()) > (String.valueOf(a).length())) {
            System.out.println("\nb must be less than or equal to a");
            System.out.print("Enter a: ");
            a = sc.nextInt();
            System.out.print("Enter b: ");
            b = sc.nextInt();
        }

        c = a * b;
    }
}
