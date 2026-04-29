package Week1.day5;

import java.util.*;
import java.util.stream.Collectors;

public class ParallelStreams {

    static class Parallel {
        public static void demonstrate() {
            System.out.println(" PARALLEL STREAMS \n");

            System.out.println("1. Large dataset processing:");
            List<Integer> largeList = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                largeList.add(i);
            }

            long startSeq = System.currentTimeMillis();
            long sumSeq = largeList.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .count();
            long timeSeq = System.currentTimeMillis() - startSeq;

            long startPar = System.currentTimeMillis();
            long sumPar = largeList.parallelStream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .count();
            long timePar = System.currentTimeMillis() - startPar;

            System.out.printf("Sequential time: %d ms%n", timeSeq);
            System.out.printf("Parallel time:   %d ms%n", timePar);
            System.out.println("Parallel benefit: " + (timeSeq > timePar ? "YES" : "NO"));

            System.out.println("\n2. CPU-intensive operations:");
            List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

            long startCpuSeq = System.nanoTime();
            long resultSeq = numbers.stream()
                .map(n -> expensiveComputation(n))
                .reduce(0, Integer::sum);
            long timeCpuSeq = System.nanoTime() - startCpuSeq;

            long startCpuPar = System.nanoTime();
            long resultPar = numbers.parallelStream()
                .map(n -> expensiveComputation(n))
                .reduce(0, Integer::sum);
            long timeCpuPar = System.nanoTime() - startCpuPar;

            System.out.printf("Sequential: %d ns%n", timeCpuSeq);
            System.out.printf("Parallel:   %d ns%n", timeCpuPar);

          
        }

        private static Integer expensiveComputation(Integer n) {
            int sum = 0;
            for (int i = 0; i < 1_000_000; i++) {
                sum += Math.sqrt(i * n);
            }
            return sum % 10000;
        }
    }

    static class NParallel {
        public static void demonstrate() {
            System.out.println("\nnpraleel\n");

            System.out.println("1. Small dataset (overhead > benefit):");
            List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

            long startSeq = System.nanoTime();
            long countSeq = smallList.stream()
                .filter(n -> n % 2 == 0)
                .count();
            long timeSeq = System.nanoTime() - startSeq;

            long startPar = System.nanoTime();
            long countPar = smallList.parallelStream()
                .filter(n -> n % 2 == 0)
                .count();
            long timePar = System.nanoTime() - startPar;

            System.out.printf("Sequential: %d ns%n", timeSeq);
            System.out.printf("Parallel:   %d ns%n", timePar);
    

        }
    }

    static class ParallelExamples {
        public static void demonstrate() {
            System.out.println("\nPARALLEL STREAM \n");

            List<String> words = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                words.add("word" + i);
            }

            System.out.println("1. Create parallel stream:");
            long count = words.parallelStream()
                .filter(w -> w.length() > 5)
                .count();
            System.out.println("Words > 5 chars: " + count);

            System.out.println("\n2. Convert to parallel:");
            long countParallel = words.stream()
                .parallel()
                .filter(w -> w.contains("1"))
                .count();
            System.out.println("Words containing '1': " + countParallel);

            System.out.println("\n3. Mix parallel and sequential:");
            List<String> result = words.parallelStream()
                .filter(w -> w.length() > 4)
                .sequential()
                .collect(Collectors.toList());
            System.out.println("First 5 words: " + result.stream().limit(5).collect(Collectors.toList()));

            System.out.println("\n4. Unordered parallel operations:");
            long distinctCount = words.parallelStream()
                .unordered()
                .distinct()
                .count();
            System.out.println("Distinct words: " + distinctCount);

            System.out.println("\n5. GroupingBy with parallel:");
            Map<Integer, Long> byLength = words.parallelStream()
                .collect(Collectors.groupingByConcurrent(
                    String::length,
                    Collectors.counting()
                ));
            System.out.println("Length distribution: " + byLength);
        }
    }

    static class PerformanceComparison {
        public static void demonstrate() {
            System.out.println("\nPERFORMANCE COMPARISON \n");

            int[] sizes = {100, 1000, 10000, 100000};

            System.out.println("Size\t\tSequential\tParallel\tBenefit");
            System.out.println("-".repeat(60));

            for (int size : sizes) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    list.add(i);
                }

                long startSeq = System.nanoTime();
                long resultSeq = list.stream()
                    .filter(n -> n % 2 == 0)
                    .map(n -> n * n)
                    .count();
                long timeSeq = System.nanoTime() - startSeq;

                long startPar = System.nanoTime();
                long resultPar = list.parallelStream()
                    .filter(n -> n % 2 == 0)
                    .map(n -> n * n)
                    .count();
                long timePar = System.nanoTime() - startPar;

                System.out.printf("%d\t\t%d\t%d\t%s%n",
                    size, timeSeq / 1000, timePar / 1000,
                    timeSeq > timePar ? "YES" : "NO");
            }
        }
    }

 

    public static void main(String[] args) {
        System.out.println("\n");

        Parallel.demonstrate();
        NParallel.demonstrate();
        ParallelExamples.demonstrate();
        PerformanceComparison.demonstrate();
       

    }
}
