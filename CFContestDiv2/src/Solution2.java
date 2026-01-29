//Seats

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int t = sc.nextInt();
            while (t-- > 0) {
                int n = sc.nextInt();
                String s = sc.next();
                System.out.println(solve(n, s));
            }
        }
        sc.close();
    }

    private static long solve(int n, String s) {
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        if (ones.isEmpty()) {
            // Minimum students to cover n seats is ceil(n/3.0)
            return (long) (n + 2) / 3;
        }

        long total = ones.size();

        // Start segment: k zeros before the first student
        // The student at ones.get(0) covers the zero at ones.get(0)-1
        // We need to cover zeros from 0 to ones.get(0)-2 (length k-1)
        int kStart = ones.get(0);
        if (kStart >= 2) {
            total += (long) (kStart + 1) / 3;
        } else if (kStart == 1) {
            // kStart=1: "0 1". Zero at 0 is covered by 1 at 1. No addition.
        }

        // Middle segments: k zeros between students at P and Q
        // Students at P and Q cover P+1 and Q-1
        // We need to cover zeros from P+2 to Q-2 (length k-2)
        for (int i = 0; i < ones.size() - 1; i++) {
            int kMiddle = ones.get(i + 1) - ones.get(i) - 1;
            if (kMiddle >= 3) {
                total += (long) kMiddle / 3;
            }
        }

        // End segment: k zeros after the last student
        // The student at ones.get(last) covers the zero at ones.get(last)+1
        // We need to cover zeros from ones.get(last)+2 to n-1 (length k-1)
        int kEnd = n - 1 - ones.get(ones.size() - 1);
        if (kEnd >= 2) {
            total += (long) (kEnd + 1) / 3;
        }

        return total;
    }
}
