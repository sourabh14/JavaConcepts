package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Leetcode {

    public int solve(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        int n = A.size();
        int capacity = C;
        List<Integer> values = A;
        List<Integer> weights = B;

        int[][] dp = new int[n+1][capacity+1];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=capacity; j++) {
                if (weights.get(i-1) <= j) {
                    // we can either choose i-1 item or we can go with without it
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights.get(i-1)] + values.get(i-1));
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][capacity];
    }


    public static void main(String[] args) {
        longestPalindrome("babad");
    }
}
