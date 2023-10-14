package hw4;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(4);
        tree.add(2);
        tree.add(4);
        tree.add(7);
        tree.add(9);
        tree.printTree();

        tree.remove(4);
        tree.remove(8);

        tree.printTree();

        tree.add(4);
        tree.add(8);

        tree.printTree();
    }
}
