import java.util.Scanner;

// Node class for Binary Search Tree
class DictionaryNode {
    String word;
    String definition;
    DictionaryNode left, right;

    public DictionaryNode(String word, String definition) {
        this.word = word;
        this.definition = definition;
        left = right = null;
    }
}

// Dictionary BST class
class DictionaryBST {
    private DictionaryNode root;

    // Insert a word and definition
    public void insert(String word, String definition) {
        root = insertRec(root, word, definition);
    }

    private DictionaryNode insertRec(DictionaryNode root, String word, String definition) {
        if (root == null) {
            return new DictionaryNode(word, definition);
        }

        int compare = word.compareToIgnoreCase(root.word);

        if (compare < 0) {
            root.left = insertRec(root.left, word, definition);
        } else if (compare > 0) {
            root.right = insertRec(root.right, word, definition);
        } else {
            // Update existing definition
            root.definition = definition;
        }

        return root;
    }

    // Search for a word
    public String search(String word) {
        DictionaryNode result = searchRec(root, word);

        if (result != null) {
            return result.definition;
        }

        return null;
    }

    private DictionaryNode searchRec(DictionaryNode root, String word) {
        if (root == null) {
            return null;
        }

        int compare = word.compareToIgnoreCase(root.word);

        if (compare == 0) {
            return root;
        }

        if (compare < 0) {
            return searchRec(root.left, word);
        }

        return searchRec(root.right, word);
    }

    // Display all words and definitions
    public void displayAll() {
        if (root == null) {
            System.out.println("Dictionary is empty.");
            return;
        }

        System.out.println("\n===== DICTIONARY CONTENTS =====");
        inorder(root);
    }

    private void inorder(DictionaryNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println("Word: " + root.word);
            System.out.println("Definition: " + root.definition);
            System.out.println("--------------------------------");
            inorder(root.right);
        }
    }
}

// Main class
public class DictionaryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DictionaryBST dictionary = new DictionaryBST();

        int choice;

        do {
            System.out.println("\n===== DICTIONARY MENU =====");
            System.out.println("1. Add Word");
            System.out.println("2. Search Word");
            System.out.println("3. View All Words");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = scanner.nextLine();

                    System.out.print("Enter definition: ");
                    String definition = scanner.nextLine();

                    dictionary.insert(word, definition);
                    System.out.println("Word added successfully!");
                    break;

                case 2:
                    System.out.print("Enter word to search: ");
                    String searchWord = scanner.nextLine();

                    String result = dictionary.search(searchWord);

                    if (result != null) {
                        System.out.println("Definition: " + result);
                    } else {
                        System.out.println("Word not found.");
                    }
                    break;

                case 3:
                    dictionary.displayAll();
                    break;

                case 4:
                    System.out.println("Exiting Dictionary...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        scanner.close();
    }
}
