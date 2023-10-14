package hw4;

class RedBlackTree<T extends Comparable<T>> {

    private Node root;

    // добавление элемента
    public void add(T data) {
        root = addNode(root, data);
        root.color = Color.BLACK; // корень всегда черный
    }

    // рекурсивное добавление элемента
    private Node addNode(Node node, T data) {
        if (node == null) {
            return new Node(data);
        }

        // поиск место для добавления узла
        if (data.compareTo(node.data) < 0) {
            node.left = addNode(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = addNode(node.right, data);
        }

        // балансировка
        return balance(node);
    }

    // удаление элемента
    public void remove(T data) {
        if (root == null) {
            return;
        }

        root = removeNode(root, data);
        if (root != null) {
            root.color = Color.BLACK; // корень всегда черный
        }
    }

    // рекурсивное удаление элемента
    private Node removeNode(Node node, T data) {
        if (data.compareTo(node.data) < 0) {
            if (node.left != null) {
                // удаляемый элемент находится в левом поддереве
                node.left = removeNode(node.left, data);
            }
        } else if (data.compareTo(node.data) > 0) {
            if (node.right != null) {
                // удаляемый элемент находится в правом поддереве
                node.right = removeNode(node.right, data);
            }
        } else {
            // нашли удаляемый элемент
            if (node.left == null && node.right == null) {
                // нет потомков
                return null;
            } else if (node.left == null) {
                // есть правый потомок
                return node.right;
            } else if (node.right == null) {
                // есть левый потомок
                return node.left;
            } else {
                // есть оба потомка
                Node successor = findMin(node.right);
                node.data = successor.data;
                node.right = removeNode(node.right, successor.data);
            }
        }

        // балансировка
        return balance(node);
    }

    // поиск минимального элемента в поддереве
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // метод балансировки
    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node); // правый малый поворот
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node); // левый малый поворот
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node); // смена цвета
        }
        return node;
    }

    // метод левого малого поворота
    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    // метод правого малого поворота
    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Color.RED;
        return temp;
    }

    // метод для выполнения смены цвета
    private void flipColors(Node node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    // метод для проверки цвета элемента
    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    // метод вывода на консоль
    public void printTree() {
        printTree(root, "", true);
    }

    // рекурсивный метод вывода на консоль
    private void printTree(Node node, String indent, boolean isLast) {
        if (node != null) {
            System.out.print(indent);
            if (isLast) {
                System.out.print("└─");
                indent += "  ";
            } else {
                System.out.print("├─");
                indent += "| ";
            }
            System.out.println(node.data + " (" + node.color + ")");
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }

    private class Node {

        private T data;
        private Node left;
        private Node right;
        private Color color;

        public Node(T data) {
            this.data = data;
            this.color = Color.RED; // Новые узлы всегда красные
        }
    }
}

enum Color {
    RED,
    BLACK
}
