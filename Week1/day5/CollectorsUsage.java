package Week1.day5;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsUsage {

    static class Book {
        private String title;
        private String author;
        private String genre;
        private double price;
        private int pages;

        public Book(String title, String author, String genre, double price, int pages) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.price = price;
            this.pages = pages;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getGenre() { return genre; }
        public double getPrice() { return price; }
        public int getPages() { return pages; }

        @Override
        public String toString() {
            return String.format("%s by %s ($%.2f)", title, author, price);
        }
    }

    static class ToListExamples {
        public static void demonstrate() {
            System.out.println(" toList\n");

            List<Book> books = Arrays.asList(
                new Book("Java Basics", "Oracle", "Technology", 29.99, 300),
                new Book("Clean Code", "Robert Martin", "Technology", 39.99, 464),
                new Book("The Hobbit", "Tolkien", "Fantasy", 15.99, 310),
                new Book("1984", "Orwell", "Fiction", 18.99, 328),
                new Book("Python Guide", "Guido", "Technology", 34.99, 400)
            );

            System.out.println("1. Collect all books to List:");
            List<Book> allBooks = books.stream()
                .collect(Collectors.toList());
            System.out.println("Total books: " + allBooks.size());

            System.out.println("\n2. Collect Technology books to List:");
            List<Book> techBooks = books.stream()
                .filter(b -> b.getGenre().equals("Technology"))
                .collect(Collectors.toList());
            techBooks.forEach(System.out::println);

            System.out.println("\n3. Collect only titles:");
            List<String> titles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
            titles.forEach(t -> System.out.println("  " + t));

            System.out.println("\n4. Collect titles of books < $30:");
            List<String> cheapTitles = books.stream()
                .filter(b -> b.getPrice() < 30)
                .map(Book::getTitle)
                .collect(Collectors.toList());
            cheapTitles.forEach(t -> System.out.println("  " + t));

            System.out.println("\n5. Collect to LinkedList:");
            LinkedList<String> authorList = books.stream()
                .map(Book::getAuthor)
                .collect(Collectors.toCollection(LinkedList::new));
            System.out.println("Authors: " + authorList);
        }
    }

    static class GroupingByExamples {
        public static void demonstrate() {
            System.out.println("\ngroupby\n");

            List<Book> books = Arrays.asList(
                new Book("Java Basics", "Oracle", "Technology", 29.99, 300),
                new Book("Clean Code", "Robert Martin", "Technology", 39.99, 464),
                new Book("The Hobbit", "Tolkien", "Fantasy", 15.99, 310),
                new Book("1984", "Orwell", "Fiction", 18.99, 328),
                new Book("Python Guide", "Guido", "Technology", 34.99, 400),
                new Book("The Lord of the Rings", "Tolkien", "Fantasy", 25.99, 1178)
            );

            System.out.println("1. Books grouped by genre:");
            Map<String, List<Book>> byGenre = books.stream()
                .collect(Collectors.groupingBy(Book::getGenre));
            byGenre.forEach((genre, bookList) -> {
                System.out.println(genre + ":");
                bookList.forEach(b -> System.out.println("  - " + b));
            });

            System.out.println("\n2. Books grouped by author:");
            Map<String, List<Book>> byAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
            byAuthor.forEach((author, bookList) ->
                System.out.println(author + ": " + bookList.size() + " book(s)")
            );

            System.out.println("\n3. Count books per genre:");
            Map<String, Long> countByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.counting()
                ));
            countByGenre.forEach((genre, count) ->
                System.out.println(genre + ": " + count + " books")
            );

            System.out.println("\n4. Average price by genre:");
            Map<String, Double> avgPriceByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.averagingDouble(Book::getPrice)
                ));
            avgPriceByGenre.forEach((genre, avgPrice) ->
                System.out.printf("%s: $%.2f%n", genre, avgPrice)
            );

            System.out.println("\n5. Titles grouped by genre:");
            Map<String, List<String>> titlesByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.mapping(Book::getTitle, Collectors.toList())
                ));
            titlesByGenre.forEach((genre, titles) -> {
                System.out.println(genre + ":");
                titles.forEach(t -> System.out.println("  - " + t));
            });
        }
    }

    static class JoiningExamples {
        public static void demonstrate() {
            System.out.println("\njoining()\n");

            List<Book> books = Arrays.asList(
                new Book("Java Basics", "Oracle", "Technology", 29.99, 300),
                new Book("The Hobbit", "Tolkien", "Fantasy", 15.99, 310),
                new Book("1984", "Orwell", "Fiction", 18.99, 328)
            );

            System.out.println("1. Join titles with comma:");
            String titles = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
            System.out.println(titles);

            System.out.println("\n2. Join titles with brackets:");
            String bracketed = books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", ", "[", "]"));
            System.out.println(bracketed);

            System.out.println("\n3. Authors list:");
            String authors = books.stream()
                .map(Book::getAuthor)
                .collect(Collectors.joining(" | "));
            System.out.println(authors);

            System.out.println("\n4. Technology book titles:");
            String techTitles = books.stream()
                .filter(b -> b.getGenre().equals("Technology"))
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
            System.out.println(techTitles);

            System.out.println("\n5. Formatted book list:");
            String formatted = books.stream()
                .map(b -> b.getTitle() + " by " + b.getAuthor())
                .collect(Collectors.joining("\n  - ","Books:\n  - ", ""));
            System.out.println(formatted);
        }
    }

    static class CountingExamples {
        public static void demonstrate() {
            System.out.println("\ncounting\n");

            List<Book> books = Arrays.asList(
                new Book("Java Basics", "Oracle", "Technology", 29.99, 300),
                new Book("Clean Code", "Robert Martin", "Technology", 39.99, 464),
                new Book("The Hobbit", "Tolkien", "Fantasy", 15.99, 310),
                new Book("1984", "Orwell", "Fiction", 18.99, 328),
                new Book("Python Guide", "Guido", "Technology", 34.99, 400)
            );

            System.out.println("1. Total books:");
            long total = books.stream()
                .collect(Collectors.counting());
            System.out.println("Count: " + total);

            System.out.println("\n2. Count by genre:");
            Map<String, Long> countByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.counting()
                ));
            countByGenre.forEach((genre, count) ->
                System.out.println(genre + ": " + count)
            );

            System.out.println("\n3. Technology books:");
            long techCount = books.stream()
                .filter(b -> b.getGenre().equals("Technology"))
                .collect(Collectors.counting());
            System.out.println("Count: " + techCount);

            System.out.println("\n4. Books > $30:");
            long expensiveCount = books.stream()
                .filter(b -> b.getPrice() > 30)
                .collect(Collectors.counting());
            System.out.println("Count: " + expensiveCount);

            System.out.println("\n5. Average pages by genre:");
            Map<String, Double> avgPagesByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.averagingInt(Book::getPages)
                ));
            avgPagesByGenre.forEach((genre, avgPages) ->
                System.out.printf("%s: %.0f pages%n", genre, avgPages)
            );
        }
    }

    static class AdvancedExamples {
        public static void demonstrate() {
            System.out.println("\n ADVANCED: Complex Collectors \n");

            List<Book> books = Arrays.asList(
                new Book("Java Basics", "Oracle", "Technology", 29.99, 300),
                new Book("Clean Code", "Robert Martin", "Technology", 39.99, 464),
                new Book("The Hobbit", "Tolkien", "Fantasy", 15.99, 310),
                new Book("1984", "Orwell", "Fiction", 18.99, 328),
                new Book("Python Guide", "Guido", "Technology", 34.99, 400),
                new Book("The Lord of the Rings", "Tolkien", "Fantasy", 25.99, 1178)
            );

            System.out.println("1. Most expensive book per genre:");
            Map<String, Optional<Book>> maxByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.maxBy(Comparator.comparingDouble(Book::getPrice))
                ));
            maxByGenre.forEach((genre, book) ->
                book.ifPresent(b -> System.out.println(genre + ": " + b))
            );

            System.out.println("\n2. Price summary by genre:");
            Map<String, DoubleSummaryStatistics> statsByGenre = books.stream()
                .collect(Collectors.groupingBy(
                    Book::getGenre,
                    Collectors.summarizingDouble(Book::getPrice)
                ));
            statsByGenre.forEach((genre, stats) ->
                System.out.printf("%s: Avg=$%.2f, Min=$%.2f, Max=$%.2f%n",
                    genre, stats.getAverage(), stats.getMin(), stats.getMax())
            );

            System.out.println("\n3. Partition by price (< $30 vs >= $30):");
            Map<Boolean, List<Book>> byPrice = books.stream()
                .collect(Collectors.partitioningBy(b -> b.getPrice() < 30));
            System.out.println("Affordable (< $30): " + byPrice.get(true).size());
            System.out.println("Premium (>= $30): " + byPrice.get(false).size());
        }
    }

    public static void main(String[] args) {
        System.out.println("COLLECTORS USAGE \n");

        ToListExamples.demonstrate();
        GroupingByExamples.demonstrate();
        JoiningExamples.demonstrate();
        CountingExamples.demonstrate();
        AdvancedExamples.demonstrate();

    }
}
