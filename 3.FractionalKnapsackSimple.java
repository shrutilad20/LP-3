import java.util.*;

class Item {
    int weight;
    int value;

    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsackSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter value and weight of item " + (i + 1) + ": ");
            int value = sc.nextInt();
            int weight = sc.nextInt();
            items[i] = new Item(weight, value);
        }

        // Step 2: Input Knapsack capacity
        System.out.print("Enter Knapsack capacity: ");
        int capacity = sc.nextInt();

        // Step 3: Sort items by value/weight ratio (greedy choice)
        Arrays.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        // Step 4: Take items into knapsack
        double totalValue = 0.0;
        int remaining = capacity;

        for (Item item : items) {
            if (item.weight <= remaining) {
                totalValue += item.value;
                remaining -= item.weight;
            } else {
                // Take fraction of the remaining weight
                totalValue += item.value * ((double) remaining / item.weight);
                break;
            }
        }

        // Step 5: Print result
        System.out.println("\nMaximum value in Knapsack = " + totalValue);
    }
}
