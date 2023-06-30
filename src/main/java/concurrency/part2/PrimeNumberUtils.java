package concurrency.part2;

public class PrimeNumberUtils {
    public Integer calculateNthPrime(int n) {
        long startTime = System.currentTimeMillis();

        int i;
        Integer result = 1;
        Integer numberOfPrimesFound = 0;

        while (numberOfPrimesFound < n) {
            result++;
            for (i=2; (i<=result); i++) {
                if (result % i == 0) break;
            }
            if (i == result) {
                numberOfPrimesFound++;
            }
        }
        System.out.println("[" + Thread.currentThread().getName() + "] " + n + "th Prime = " + result);
        System.out.println("[" + Thread.currentThread().getName() + "] Time taken for calculation of " + n + "th prime: " + ((System.currentTimeMillis() - startTime)/1000) + " sec");
        return result;
    }
}
