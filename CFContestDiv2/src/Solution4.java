//restricted sorting

import java.util.*;
import java.io.*;

public class Solution4 {
    public static void main(String[] args) {
        FastReader sc = new FastReader(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }

            long[] b = a.clone();
            Arrays.sort(b);

            boolean sorted = true;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                System.out.println("-1");
                continue;
            }

            long minVal = a[0];
            long maxVal = a[0];
            for (int i = 1; i < n; i++) {
                if (a[i] < minVal)
                    minVal = a[i];
                if (a[i] > maxVal)
                    maxVal = a[i];
            }

            long ans = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (a[i] != b[i]) {
                    long currentMaxSwap = Math.max(a[i] - minVal, maxVal - a[i]);
                    ans = Math.min(ans, currentMaxSwap);
                }
            }

            System.out.println(ans);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
