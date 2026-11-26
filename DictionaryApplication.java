import java.util.Scanner;

// Class representing a node in the Binary Search Tree
class DictionaryNode {
    private String word;
    private String definition;
    private DictionaryNode left;
    private DictionaryNode right;

    public DictionaryNode(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public DictionaryNode getLeft() {
        return left;
    }

    public void setLeft(DictionaryNode left) {
        this.left = left;
    }

    public DictionaryNode getRight() {
        return right;
    }

    public void setRight(DictionaryNode right) {
        this.right = right;
    }
}

// Binary Search Tree Dictionary Class
class DictionaryBST {
    private DictionaryNode root;

    // Insert word and definition
    public void insert(String word, String definition) {
        root = insertRecursive(root, word, definition);
    }

    private DictionaryNode insertRecursive(
            DictionaryNode current,
            String word,
            String definition) {

        if (current == null) {
            return new DictionaryNode(word, definition);
        }

        int comparison = word.compareToIgnoreCase(current.getWord());

        if (comparison < 0) {
            current.setLeft(
                    insertRecursive(current.getLeft(), word, definition));
        } else if (comparison > 0) {
            current.setRight(
                    insertRecursive(current.getRight(), word, definition));
        } else {
            // Word already exists, update definition
            current.setDefinition(definition);
        }

        return current;
    }

    // Search for a word
    public String search(String word) {
        DictionaryNode result = searchRecursive(root, word);

        if (result != null) {
            return result.getDefinition();
        }

        return null;
    }

    private DictionaryNode searchRecursive(DictionaryNode current,
                                           String word) {

        if (current == null) {
            return null;
        }

        int comparison = word.compareToIgnoreCase(current.getWord());

        if (comparison == 0) {
            return current;
        }

        if (comparison < 0) {
            return searchRecursive(current.getLeft(), word);
        }

        return searchRecursive(current.getRight(), word);
    }

    // Display all words and definitions
    public void displayAllWords() {
        if (root == null) {
            System.out.println("Dictionary is empty.");
            return;
        }

        System.out.println("\n=== ALL SAVED WORDS ===");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(DictionaryNode node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());

            System.out.println("Word       : " + node.getWord());
            System.out.println("Definition : " + node.getDefinition());
            System.out.println("----------------------------------");

            inOrderTraversal(node.getRight());
        }
    }
}

// Main Application
public class DictionaryApplication {

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
            System.out.print("Enter choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter word: ");
                    String word = scanner.nextLine();

                    System.out.print("Enter definition: ");
                    String definition = scanner.nextLine();

                    dictionary.insert(word, definition);
                    System.out.println("Word saved successfully!");
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
                    dictionary.displayAllWords();
                    break;

                case 4:
                    System.out.println("Thank you for using the dictionary.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        scanner.close();
    }
}