import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Calculates the intersection of two lists.
 *
 * @author Andrei Muntean
 */
public class Intersection
{
    private static final int bucketCount = 666013;

    /**
     * Calculates the intersection of two lists.
     *
     * @param first The first list.
     * @param second The second list.
     *
     * @return The intersection of two lists.
     */
    public static List<Integer> getIntersection(List<Integer> first, List<Integer> second)
    {
        ArrayList<TreeSet<Integer>> buckets = new ArrayList<TreeSet<Integer>>(bucketCount);
        List<Integer> intersection = new ArrayList<Integer>();

        // Initializes the buckets.
        for (int index = 0; index < bucketCount; ++index)
        {
            buckets.add(new TreeSet<Integer>());
        }

        // Fills the buckets using the elements of the first list.
        for (int value : first)
        {
            buckets.get(value % bucketCount).add(value);
        }

        // Finds the common elements by checking whether the elements of the second list are found in the buckets.
        for (int value : second)
        {
            if (buckets.get(value % bucketCount).contains(value))
            {
                intersection.add(value);
            }
        }

        // Worst case: O(bucketCount + n * log((2^31 - 1) / bucketCount) + m * log((2^31 - 1) / bucketCount)).
        return intersection;
    }

    public static void main(String[] args)
    {
        List<Integer> first = new ArrayList<Integer>();
        List<Integer> second = new ArrayList<Integer>();

        try (Scanner scanner = new Scanner(System.in))
        {
            int firstCount = scanner.nextInt();
            int secondCount = scanner.nextInt();

            while (firstCount-- > 0)
            {
                first.add(scanner.nextInt());
            }

            while (secondCount-- > 0)
            {
                second.add(scanner.nextInt());
            }
        }

        for (int value : getIntersection(first, second))
        {
            System.out.printf("%d ", value);
        }

        System.out.println();
    }
}