package algorithms;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmsPart10_String {
    public static void main(String[] args) {
        String s = "abcd";

        // Substring [startIndex, endIndex)
        System.out.println(s.substring(1, 3));

        // indexOf - search substring inside string
        System.out.println(s.indexOf("cd"));

        // Create freq map
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (Character ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }


    }
}
