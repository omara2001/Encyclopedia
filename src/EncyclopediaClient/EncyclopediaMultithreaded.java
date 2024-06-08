package EncyclopediaClient;

import encyclopedia.Encyclopedia;
import java.rmi.Naming;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EncyclopediaMultithreaded {
    public static void main(String[] args) {
        try {
            final int numberOfThreads = 5;
            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

            for (int i = 0; i < numberOfThreads; i++) {
                executor.submit(() -> {
                    try {
                        Encyclopedia encyclopedia = (Encyclopedia) Naming.lookup("rmi://localhost/Encyclopedia");

                        long startTime = System.currentTimeMillis();

                        int count = encyclopedia.count();
                        System.out.println("Number of letters: " + count);

                        String[] repeatedWords = encyclopedia.repeatedWords();
                        System.out.println("Repeated words: " + String.join(", ", repeatedWords));

                        String longest = encyclopedia.longest();
                        System.out.println("Longest word: " + longest);

                        String shortest = encyclopedia.shortest();
                        System.out.println("Shortest word: " + shortest);

                        Map<String, Integer> repeat = encyclopedia.repeat();
                        System.out.println("Repeat count: ");
                        repeat.forEach((key, value) -> System.out.println(key + " : " + value));

                        long endTime = System.currentTimeMillis();
                        long elapsedTime = endTime - startTime;
                        System.out.println("Elapsed time: " + elapsedTime + " ms");
                    } catch (Exception e) {
                        System.err.println("Client exception: " + e.toString());
                        e.printStackTrace();
                    }
                });
            }

            executor.shutdown();
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
