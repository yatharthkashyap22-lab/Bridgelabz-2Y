public class Main {
    public static void main(String[] args) {
        Book[] books = {
            new Book(101, "Data Structures",   "Mark",   400.0),
            new Book(101, "Data Structures",   "Mark",   400.0), // duplicate
            new Book(102, "Java Basics",       "James",  300.0),
            new Book(103, "Python Guide",      "Guido",  600.0),
            new Book(104, "Database Systems",  "Raghu",  500.0),
            new Book(105, "Computer Networks", "Andrew", 700.0)
        };
        int n = books.length;

        // ---- Task 1 ----
        System.out.println("=== Task 1: Remove Duplicates ===");
        int count = LibraryManagement.removeDuplicates(books, n);
        System.out.println("Unique Books Count: " + count);
        System.out.println("Book List:");
        for (int i = 0; i < count; i++) System.out.println(books[i]);

        // ---- Task 2 ----
        System.out.println("\n=== Task 2: Search by Title ===");
        LibraryManagement.searchByTitle(books, count, "data");

        // ---- Task 3 ----
        System.out.println("\n=== Task 3: Sort by Price ===");
        LibraryManagement.sortByPrice(books, count);

        // ---- Task 4 ----
        System.out.println("\n=== Task 4: Search by Price ===");
        double target = 500.0;
        System.out.println("Searching for Price Rs. " + target + "...");
        int idx = LibraryManagement.searchByPrice(books, count, target);
        if (idx != -1) {
            System.out.println("Result: Book found at index " + idx + ": ["
                    + books[idx].bookId + "] " + books[idx].title + " (Rs. " + books[idx].price + ")");
        } else {
            System.out.println("Result: No book found with that price.");
        }

        // ---- Task 5 ----
        System.out.println("\n=== Task 5: Sliding Window ===");
        double S = 1000.0;
        System.out.println("Finding minimum consecutive books whose total price >= Rs. " + S + "...");
        int minLen = LibraryManagement.minBooksForTargetCost(books, count, S);
        System.out.println("Minimum Consecutive Books Needed: " + minLen);

        // Optional: show every window of that minimum length (for demo output)
        if (minLen > 0) {
            for (int start = 0; start + minLen <= count; start++) {
                double sum = 0;
                for (int k = start; k < start + minLen; k++) sum += books[k].price;
                if (sum >= S) {
                    System.out.println("- Window (Index " + start + " to " + (start + minLen - 1)
                            + "): Total = Rs. " + sum + " -> Length: " + minLen + " books");
                }
            }
        }
    }
}
