package algorithms;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmsPart7_String {
    public static void main(String[] args) {
        String s = "abcd";

        // Create freq map
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (Character ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }


    }
}
