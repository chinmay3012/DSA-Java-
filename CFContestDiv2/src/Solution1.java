//divisible permutation

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int t = sc.nextInt();
            while (t-- > 0) {
                int n = sc.nextInt();
                solve(n);
            }
        }
        sc.close();
    }

    private static void solve(int n) {
        int[] p = new int[n];
        int current = n / 2 + 1;
        p[0] = current;

        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                current -= i;
            } else {
                current += i;
            }
            p[i] = current;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(p[i]);
            if (i < n - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
