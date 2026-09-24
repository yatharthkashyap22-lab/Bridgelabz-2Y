public class LibraryManagement {

    // ---------------------------------------------------------------
    // Task 1: Remove duplicates in-place (array is sorted by bookId)
    // Two-pointer technique: O(N) time, O(1) extra space.
    // ---------------------------------------------------------------
    public static int removeDuplicates(Book[] books, int n) {
        if (books == null || n == 0) return 0;

        int unique = 1; // books[0..unique-1] holds the unique books
        for (int i = 1; i < n; i++) {
            if (books[i].bookId != books[unique - 1].bookId) {
                books[unique] = books[i];
                unique++;
            }
        }
        // Clear leftover slots so stale duplicates are not accessible
        for (int i = unique; i < n; i++) {
            books[i] = null;
        }
        return unique;
    }

    // ---------------------------------------------------------------
    // Task 2: Case-insensitive partial title search
    // ---------------------------------------------------------------
    public static void searchByTitle(Book[] books, int count, String query) {
        System.out.println("Search Results for '" + query + "':");
        String q = query.toLowerCase();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (books[i].title.toLowerCase().contains(q)) {
                System.out.println("- Found: [" + books[i].bookId + "] "
                        + books[i].title + " (Rs. " + books[i].price + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("- No matching books found.");
        }
    }

    // ---------------------------------------------------------------
    // Task 3: Selection sort by price (ascending), counting swaps
    // ---------------------------------------------------------------
    public static void sortByPrice(Book[] books, int count) {
        int swaps = 0;

        for (int i = 0; i < count - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < count; j++) {
                if (books[j].price < books[minIdx].price) {
                    minIdx = j;
                }
            }
            if (minIdx != i) { // only count real swaps
                Book temp = books[i];
                books[i] = books[minIdx];
                books[minIdx] = temp;
                swaps++;
            }
        }

        System.out.println("Books Sorted by Price:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
        System.out.println("Total Swaps: " + swaps);
    }

    // ---------------------------------------------------------------
    // Task 4: Binary search by price, O(log N). Array must be sorted.
    // ---------------------------------------------------------------
    public static int searchByPrice(Book[] books, int count, double targetPrice) {
        final double EPS = 1e-9; // safe comparison for doubles
        int low = 0, high = count - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            double diff = books[mid].price - targetPrice;

            if (Math.abs(diff) < EPS) {
                return mid;
            } else if (diff < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ---------------------------------------------------------------
    // Task 5: Sliding window - minimum consecutive books with
    // total price >= targetCost. O(N) time, O(1) space.
    // Assumes prices are positive (true for book prices).
    // Returns 0 if no such window exists.
    // ---------------------------------------------------------------
    public static int minBooksForTargetCost(Book[] books, int count, double targetCost) {
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        double currentSum = 0;

        for (int right = 0; right < count; right++) {
            currentSum += books[right].price;

            while (currentSum >= targetCost && left <= right) {
                minLen = Math.min(minLen, right - left + 1);
                currentSum -= books[left].price;
                left++;
            }
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }
}
