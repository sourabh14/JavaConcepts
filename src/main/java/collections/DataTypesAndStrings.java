package collections;

import java.util.Arrays;

public class DataTypesAndStrings {
    public static void main(String[] args) {
        /*
             Java Data Types and Strings
         */

        /*
            Data Types
            ├── Primitive
            └── Non-Primitive (Reference)

            Primitive Data Types
                - Java has 8 primitive data types.
                - They store actual values, not references

                | Type    | Size          | Default  | Range              |
                | ------- | ------------- | -------- | ------------------ |
                | byte    | 1 byte        | 0        | -128 to 127        |
                | short   | 2 bytes       | 0        | -32,768 to 32,767  |
                | int     | 4 bytes       | 0        | ~ -2³¹ to 2³¹-1    |
                | long    | 8 bytes       | 0L       | very large         |
                | float   | 4 bytes       | 0.0f     | ~7 decimal digits  |
                | double  | 8 bytes       | 0.0      | ~15 decimal digits |
                | char    | 2 bytes       | '\u0000' | 0–65,535           |
                | boolean | JVM-dependent | false    | true / false       |


                Why char is 2 bytes?
                    - Java uses Unicode (UTF-16)
                    - Supports international characters

            Non-Primitive (Reference) Data Types
                - These store memory addresses, not actual data.
                - Examples : String, Array, Class, Interface, Enum

            | Feature       | Primitive | Reference      |
            | ------------- | --------- | -------------- |
            | Stores        | Value     | Memory address |
            | Null allowed  | ❌        | ✅             |
            | Default value | Fixed     | null           |
            | Memory        | Stack     | Heap           |
            | Methods       | ❌        | ✅             |

            Wrapper Classes
                - Each primitive has a wrapper class.
                - Why Wrapper Classes?
                    Collections need objects
                    Utility methods
                    Autoboxing

                | Primitive | Wrapper   |
                | --------- | --------- |
                | int       | Integer   |
                | byte      | Byte      |
                | short     | Short     |
                | long      | Long      |
                | float     | Float     |
                | double    | Double    |
                | char      | Character |
                | boolean   | Boolean   |


         */


        /*
            Strings
            - a String is an object that represents a sequence of characters
            - String is a class in java.lang
            - Strings are immutable
            - Stored as UTF-16 internally
            - Heavily optimized by JVM (String Pool)
            - String is final class

            String comparison (== vs equals())
            ==        -> compares references
            .equals() -> compares content
         */
        System.out.println("\nStrings");

        /*
            String pool
            - String Pool is a special memory area inside the JVM Heap that stores unique String literals.
            - If the same string literal appears multiple times, only one object is created, and all references point to it.
            - Purpose: save memory + improve performance
            - Java 7+ → String Pool is in Heap memory

            - With new String(), different objects are created
                String a = new String("java");
                String b = new String("java");

                System.out.println(a == b); // false
                System.out.println(a.equals(b)); // true

            intern() Method
            - moves string to pool
            - String s4 = new String("Java").intern();
            - Checks String Pool, If present → returns pooled reference, If absent → adds it to pool

                String a = new String("java").intern();
                String b = new String("java").intern();

                System.out.println(a == b); // true

            Why String pool?
            - Memory Optimization : Strings are the most commonly used objects. With pooling, all variables pointing
                to the literal "ID" refer to the same memory address. Only one object instead of multiple.
            - Performance (Speed) : Because Strings are pooled, Java can take advantage of caching.
                Faster Comparisons: When comparing two pooled strings using .equals(), if the references are the
                    same (s1 == s2), Java knows they are equal instantly without checking every character.
                Hashcode Caching: Strings are frequently used as keys in HashMap or HashSet. Since String literals a
                    re constant and pooled, their hashCode() can be calculated once and cached, making lookups very fast.
            - Security : String Pool + Immutability Connection
                - Java uses strings for almost all security-sensitive operations - Class Loading, network operations
                - Because Strings are immutable, JVM can safely:
                    Share references
                    Cache hash codes
                    Use them as HashMap keys
                - Pool works because Strings are immutable

         */

        // Creating a String
        // String Literal (String Pool) - Both refer to same object in String Pool
        String s1 = "Java";
        String s2 = "Java";

        // Using new keyword (Heap) - Creates a new object even if literal exists
        String s3 = new String("Java");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1.equals(s3)); // true

        /*
            Immutability of String (Very Important)
            - Once created, a String cannot be changed
            - String class is declared as final primarily to enforce immutability.

            Why Strings Are Immutable?
            - Security (classpath, DB URLs)
            - Thread safety
            - String Pool optimization
            - HashCode caching (used in HashMap)
         */
        String s = "Hello";
        s.concat(" World");
        System.out.println(s); // Hello
        // Correct way
        s = s.concat(" World");
        System.out.println(s);

        /*
            String methods
         */

        String str = "  Java Backend Developer  ";
        System.out.println("str = " + str);

        // 1. length()
        System.out.println("Length: " + str.length());

        // 2. trim()
        String trimmed = str.trim();
        System.out.println("Trimmed: '" + trimmed + "'");

        // 3. toUpperCase() / toLowerCase()
        System.out.println("Uppercase: " + trimmed.toUpperCase());
        System.out.println("Lowercase: " + trimmed.toLowerCase());

        // 4. charAt()
        System.out.println("Character at index 2: " + trimmed.charAt(2));

        // 5. substring()
        System.out.println("Substring (0,4): " + trimmed.substring(0, 4));

        // 6. contains()
        System.out.println("Contains 'Backend': " + trimmed.contains("Backend"));

        // 7. startsWith() / endsWith()
        System.out.println("Starts with 'Java': " + trimmed.startsWith("Java"));
        System.out.println("Ends with 'Developer': " + trimmed.endsWith("Developer"));

        // 8. equals() / equalsIgnoreCase()
        String s11 = "java";
        String s22 = "Java";
        System.out.println("Equals: " + s11.equals(s22));
        System.out.println("Equals Ignore Case: " + s11.equalsIgnoreCase(s22));

        // 9. replace()
        String replaced = trimmed.replace("Developer", "Engineer");
        System.out.println("Replaced: " + replaced);

        // 10. split()
        String[] words = trimmed.split(" ");
        System.out.println("Split words: " + Arrays.toString(words));


        // 11. indexOf() / lastIndexOf()
        System.out.println("Index of 'a': " + trimmed.indexOf('a'));
        System.out.println("Last index of 'a': " + trimmed.lastIndexOf('a'));

        // 12. isEmpty() / isBlank()
        String empty = "";
        String blank = "   ";
        System.out.println("empty.isEmpty(): " + empty.isEmpty());

        // 13. concat()
        String concatStr = "Java".concat(" ").concat("String");
        System.out.println("Concatenated: " + concatStr);

        // 14. valueOf()
        int num = 100;
        String numStr = String.valueOf(num);
        System.out.println("String valueOf int: " + numStr);

        // 15. toCharArray()
        char[] chars = "Hello".toCharArray();
        System.out.println("Characters:");
        for (char c : chars) {
            System.out.print(c + " ");
        }

        // 16. matches() (regex)
        String email = "test@example.com";
        String rev = new StringBuilder(email).reverse().toString();
        System.out.println("\nValid email: " +
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));

        /*
            String vs StringBuilder vs StringBuffer

            String
            - Immutable, Any modification creates a new object
            - Thread-safe
            - Stored in String Pool (if literal)

                When to use String
                - Constants
                - Config values
                - Keys (HashMap)
                - Small, fixed strings

            StringBuilder
            - Mutable
            - Not thread-safe
            - Fastest for modifications - Same object modified, No new object creation
            - Loops, String concatenation
            - preferred to StringBuffer as it will be faster under most implementations.


                When to use StringBuilder
                - Loops
                - String concatenation
                - Single-threaded applications
                - Heavy string manipulation

            StringBuffer
            - Mutable
            - Thread-safe
            - Older (legacy-friendly)
            - Slower than StringBuilder

                When to use StringBuffer
                - Multi-threaded environment
                - Legacy code
                - When thread safety is required
         */

        System.out.println("\nStringBuilder");
        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Backend");
        System.out.println("sb: " + sb);

        // use in loops
        StringBuilder sb2 = new StringBuilder();
        for (int i=0; i<10; i++) {
            sb2.append(i);
        }
        System.out.println("sb2: " + sb2);
        System.out.println("sb2 reverse: " + sb2.reverse());

        StringBuffer sbuf = new StringBuffer("Java");
        sbuf.append(" Backend");

        /*
            String concatenation and performance
            - '+' internal working
            - compile time vs runtime concatenation
            - '+' vs sb.append() in loops
         */

        // '+' internal working

        /*
            Case 1: Simple concatenation (compile-time)
            ✔ Happens at compile time
            ✔ Stored directly in String Pool
            ✔ No StringBuilder/StringBuffer involved
         */
        String sbs = "Java" + " Backend";
        /*
            Case 2: Runtime concatenation (important)
            ✔ Happens at run time
            ✔ StringBuilder is used internally
         */
        String sbsb = "Java";
        sbsb = sbsb + " Backend";
        /*
            Decompiled bytecode (conceptually):
            s = new StringBuilder()
                    .append(s)
                    .append(" Backend")
                    .toString();
         */

        /*
            - When we use + in a loop,
                New StringBuilder object every iteration
                New String object every iteration
                Time Complexity: O(n²)
                Massive object creation + GC pressure
         */

        String t = "";
        for (int i = 0; i < 1000; i++) {
            t = t + i;
        }

        /*
            Using StringBuilder directly (correct way)

         */
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb1.append(i);
        }
        String finalstring = sb.toString();









    }
}
