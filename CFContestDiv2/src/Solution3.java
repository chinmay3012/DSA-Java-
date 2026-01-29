//shrotest statement ever

import java.util.*;
import java.io.*;

public class Solution3 {
    static final long INF = Long.MAX_VALUE / 2;

    public static void main(String[] args) {
        FastReader sc = new FastReader(System.in);
        String tStr = sc.next();
        if (tStr == null)
            return;
        int t = Integer.parseInt(tStr);
        while (t-- > 0) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            solve(x, y);
        }
    }

    private static void solve(long x, long y) {
        // dp[bit][sx][sy] stores min Manhattan distance sum for bits [30, bit]
        // State 0: tight, 1: greater, 2: less
        long[][][] dp = new long[32][3][3];
        int[][][] bestP = new int[32][3][3], bestQ = new int[32][3][3];
        int[][][] prevSx = new int[32][3][3], prevSy = new int[32][3][3];

        for (int i = 0; i < 32; i++)
            for (int sx = 0; sx < 3; sx++)
                Arrays.fill(dp[i][sx], INF);

        dp[31][0][0] = 0;

        for (int i = 30; i >= 0; i--) {
            int xi = (int) ((x >> i) & 1), yi = (int) ((y >> i) & 1);
            long pow2 = (1L << i);
            for (int psx = 0; psx < 3; psx++) {
                for (int psy = 0; psy < 3; psy++) {
                    if (dp[i + 1][psx][psy] == INF)
                        continue;
                    for (int pi = 0; pi <= 1; pi++) {
                        for (int qi = 0; qi <= 1; qi++) {
                            if (pi == 1 && qi == 1)
                                continue; // p & q = 0 constraint

                            int nsx = psx;
                            long costX = 0;
                            if (psx == 0) {
                                if (pi > xi) {
                                    nsx = 1;
                                    costX = pow2;
                                } else if (pi < xi) {
                                    nsx = 2;
                                    costX = pow2;
                                }
                            } else {
                                costX = (long) (psx == 1 ? pi - xi : xi - pi) * pow2;
                            }

                            int nsy = psy;
                            long costY = 0;
                            if (psy == 0) {
                                if (qi > yi) {
                                    nsy = 1;
                                    costY = pow2;
                                } else if (qi < yi) {
                                    nsy = 2;
                                    costY = pow2;
                                }
                            } else {
                                costY = (long) (psy == 1 ? qi - yi : yi - qi) * pow2;
                            }

                            long total = dp[i + 1][psx][psy] + costX + costY;
                            if (total < dp[i][nsx][nsy]) {
                                dp[i][nsx][nsy] = total;
                                bestP[i][nsx][nsy] = pi;
                                bestQ[i][nsx][nsy] = qi;
                                prevSx[i][nsx][nsy] = psx;
                                prevSy[i][nsx][nsy] = psy;
                            }
                        }
                    }
                }
            }
        }

        int csx = 0, csy = 0;
        long minC = INF;
        for (int sx = 0; sx < 3; sx++)
            for (int sy = 0; sy < 3; sy++)
                if (dp[0][sx][sy] < minC) {
                    minC = dp[0][sx][sy];
                    csx = sx;
                    csy = sy;
                }

        long p = 0, q = 0;
        for (int i = 0; i <= 30; i++) {
            int pi = bestP[i][csx][csy], qi = bestQ[i][csx][csy];
            if (pi == 1)
                p |= (1L << i);
            if (qi == 1)
                q |= (1L << i);
            int psx = prevSx[i][csx][csy], psy = prevSy[i][csx][csy];
            csx = psx;
            csy = psy;
        }
        System.out.println(p + " " + q);
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
                    return null;
                }
            }
            return st.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
