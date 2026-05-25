package collections;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ReviseDataTypesStrings {
    public static void main(String[] args) {
        /*
            DataTypes
            - List primitive and non-primitive data types and their size

            Strings
            - Definition and parent class
            - String-literal vs new keyword, String comparison == vs equals()
            - String pool, intern(), reasons for string pooling
            - Immutability, reason for immutability
            - Methods :
                length(), charAt(), toCharArray(), substring(),
                trim(), toUpperCase(), toLowerCase(), contains(), indexOf(), lastIndexOf()
                startsWith(), endsWith(), equalsIgnoreCase(), replace()
                split(), isEmpty(), isBlank(), concat()
            - String vs StringBuilder vs StringBuffer
            - String concatenation, runtime vs compile-time
            - Methods :
                append(), reverse(), toString()

            String practice que
            - Reverse a string, check palindrome
            - Count characters - Given a string create frequency map of characters
            - Remove duplicate characters, preserve order
         */

        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str3));
        System.out.println("Length: " + str1.length());

        String pal1 = "test";
        String rev = new StringBuilder(pal1).reverse().toString();
        System.out.println(pal1 + " is palindrome: " + pal1.equals(rev));

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : pal1.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        System.out.println("freq: " + freq);

        String str = "programming";

        Set<Character> unique = new LinkedHashSet<>();
        for (char ch : str.toCharArray()) {
            unique.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : unique) sb.append(ch);
        System.out.println("unique string: " + sb);
    }
}
