import java.util.PriorityQueue;

// Node class for Huffman Tree
class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    // Compare nodes by frequency (for greedy selection)
    public int compareTo(Node n) {
        return this.freq - n.freq;
    }
}

public class HuffmanEncodingSimple {

    // Function to print Huffman Codes
    static void printCodes(Node root, String code) {
        if (root == null) return;

        // If leaf node, print its code
        if (root.left == null && root.right == null)
            System.out.println(root.ch + " : " + code);

        // Traverse left and right subtrees
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        // Step 1: Characters and their frequencies
        char[] chars = {'A', 'B', 'C', 'D', 'E'};
        int[] freq = {5, 9, 12, 13, 16};

        // Step 2: Create a min-heap (PriorityQueue)
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Step 3: Add all characters to queue
        for (int i = 0; i < chars.length; i++)
            pq.add(new Node(chars[i], freq[i]));

        // Step 4: Build Huffman Tree using greedy method
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();

            // Create new internal node with combined frequency
            Node newNode = new Node('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;

            pq.add(newNode);
        }

        // Step 5: Print Huffman Codes
        System.out.println("Huffman Codes are:");
        printCodes(pq.peek(), "");
    }
}
