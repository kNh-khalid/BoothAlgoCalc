package com.example.boothalgocalc;

public class BoothAlgoCalc {

    public static String getBinary(int n) {
        String str1 = "";
        if (n >= 0) {
            str1 = Integer.toBinaryString(n);
            str1 = "0" + str1;
        } else {
            str1 = Integer.toBinaryString(-1 * n);
            str1 = get2sComplement(str1);
            str1 = "11" + str1;
        }
        if (str1.length() > 4) {
            str1 = str1.substring(1);
        }
        return str1;
    }

    public static String get2sComplement(String s1) {
        String ones = "";
        for (int i = 0; i < s1.length(); i++) {
            ones += (s1.charAt(i) == '0') ? '1' : '0';
        }
        String twos = "";
        String carry = "1";
        for (int i = ones.length() - 1; i >= 0; i--) {
            if (ones.charAt(i) == '1' && carry.equals("1")) {
                twos = '0' + twos;
                carry = "1";
            } else if (ones.charAt(i) == '0' && carry.equals("1")) {
                twos = '1' + twos;
                carry = "0";
            } else {
                twos = ones.charAt(i) + twos;
            }
        }
        return twos;
    }

    public static String shiftRight(String str) {
        return str.charAt(0) + str.substring(0, str.length() - 1);
    }

    public static String binaryAdd(String s1, String s2) {
        StringBuilder res = new StringBuilder();
        char carry = '0';
        for (int i = s1.length() - 1; i >= 0; i--) {
            char bit1 = s1.charAt(i);
            char bit2 = s2.charAt(i);
            int sum = (bit1 - '0') + (bit2 - '0') + (carry - '0');
            res.insert(0, sum % 2);
            carry = (sum > 1) ? '1' : '0';
        }
        return res.toString();
    }
    public static long binarytodec(String s) {
        Long n = Long.parseLong(s);
        Long rem = (long) 0;
        Long ans = (long) 0;
        Long val = (long) 1;
        while (n != 0) {
            rem = n % 10;
            ans = (ans + rem * val);
            n = n / 10;
            val = val * 2;
        }
        return ans/2;

    }

    public static String boothMultiply(int m1, int m2) {
        String M = getBinary(m1);
        String Q = getBinary(m2);
        int m = M.length();
        int q = Q.length();
        String A = M;
        for (int i = 0; i <= q; i++) {
            A = A + "0"; //to add 0 after the M
        }
        String S = get2sComplement(M.substring(1));
        if (m1 <= 0) {
            S = "0" + S;
        }
        else {
            S = "1" + S;
        }
        for (int i = 0; i <= q; i++) {
            S = S + "0";
        }
        String P = "";
        for (int i = 0; i < m; i++) {
            P += "0";
        }
        P = P + Q + "0";
        int half = (P.length()-1)/2;

        StringBuilder result = new StringBuilder("n\t\tA\t\tQ\t\tQ-1\n");
        for (int i = q - 1; i >= 0; i--) {
            if (P.endsWith("01")) {
                P = binaryAdd(P, A);
            } else if (P.endsWith("10")) {
                P = binaryAdd(P, S);
            }
            P = shiftRight(P);
            result.append(i).append("\t\t")
                    .append(P.substring(0,half)).append("\t\t")
                    .append(P.substring(half,P.length()-1)).append("\t\t")
                    .append(P.substring(P.length()-1)).append("\n");
        }
        String sentence = "";
        long dec = 0;
        if (P.charAt(0) == '0') {
            sentence = "It's Decimal Equivalent is ";
            int i;
            for (i = 0; i < P.length(); i++) {
                if (P.charAt(i) == '0') {      // leftmost zeroes are removed to get decimal eqv to avoid redundancy
                    continue;
                }
                break;
            }

            dec = binarytodec(P.substring(i));  // after removing leading zeroes
        }

        if (P.charAt(0) == '1') {
            sentence = "MSB = 1 indicates that the given\n number is negative.\n It's magnitude is given by the magnitude of\n it's 2's complement, that is " ;
            dec = binarytodec(get2sComplement(P));
        }
        if (m2<0)
            dec += 1;

        result.append("\n" + sentence + dec);
        return result.toString();
    }
}