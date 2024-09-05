package concurrency.part7;

import sun.java2d.pipe.SpanShapeRenderer;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo2 {
    /*
        Print birthdate of users in multithreaded way
     */
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Date> userIdToBirthDate = new HashMap<>();
        userIdToBirthDate.put(1, new Date(2000, 01, 01));
        userIdToBirthDate.put(2, new Date(2000, 02, 01));
        userIdToBirthDate.put(3, new Date(2000, 03, 01));
        userIdToBirthDate.put(4, new Date(2000, 04, 01));
        userIdToBirthDate.put(5, new Date(2000, 05, 01));

        // We can create separate thread each time
        System.out.println("Using multiple threads");
        for (int userId=1; userId<=5; userId++) {
            int finalUserId = userId;
            new Thread (() -> {
                String birthDateString = getBirthDateString(userIdToBirthDate.get(finalUserId));
                System.out.println("birthDateString = " + birthDateString);
            }).start();
        }

        /*
            Here the problem is if we have 1000 or more users it will consume lot of memory and also creating multiple objects of SimpleDateFormat
            So one solution is to save memory and object creation is to use ThreadPoolExecutor and single global instance of SimpleDateFormat
            We will use two threads for thread pool
         */

        System.out.println("Using thread pool eecutor");
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i=1; i<=5; i++) {
            int userId = i;
            threadPool.submit(() -> {
                String birthDateString = sdf.format(userIdToBirthDate.get(userId));
                System.out.println("birthDateString = " + birthDateString);
            });
        }

        /*
            The problem now is that SimpleDateFormat is not thread safe
            We can introduce synchronization in this through synchronized keyword but that will affect performance

            Therefore, to summarize
                - One object per task: If we create sdf objects per thread that will be too many objects and will consume memory
                - Global object: If we create global object then
                    No locks = not thread safe
                    With locks = slow performance

            What we want is middle ground - memory optimization as well as performance.
                - One object per thread: If we can create one sdf object per thread (in case of executor)
                - With this we can achieve memory efficiency and thread safety


         */

        for (int i=1; i<=5; i++) {
            int userId = i;
            threadPool.submit(() -> {
                SimpleDateFormat sdf2 = ThreadSafeFormatter.dateFormatter.get();
                String birthDateString = sdf2.format(userIdToBirthDate.get(userId));
                System.out.println("ThreadSafeFormatter birthDateString = " + birthDateString);
            });
        }

    }

    public static String getBirthDateString(Date birthDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(birthDate);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatter =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
}