package algorithms;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmsPart11_Sliding_Window {
    public static void main(String[] args) {
        /*
            Sliding Window
                The technique is used when we need to find subarrays or substrings according to a
                given set of conditions.

            Eg:
                finding subarrays with a specific sum
                finding the longest substring with unique characters

           Template:
                int n = s.length();
                int l=0, r=0, ans=0;

                for (; r<n; r++) {
                    // CODE: use A[j] to update state - which might make the window invalid
                    for (; invalid(); l++) {
                        // when invalid, keep shrinking the left edge until it's valid again
                        // CODE: update state using A[i]
                    }
                    // the window [l, r] is the maximum window we've found thus far
                    ans = max(ans, (r-l+1));
                }
                return ans;
         */

        // longest substring with unique characters

        String s = "abcabcaabb";
        int n = s.length();
        int l=0, r=0, ans=0;
        Map<Character, Integer> freq = new HashMap<>();
        for (; r<n; r++) {
            Character ch = s.charAt(r);
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            for (; freq.get(ch)>1; l++) {
                Character leftchar = s.charAt(l);
                freq.put(leftchar, freq.get(leftchar)-1);
            }
            ans = Math.max(ans, r-l+1);
        }
        // return ans;
    }
}
