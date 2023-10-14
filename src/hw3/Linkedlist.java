package hw3;

public class Linkedlist {
    int size;
    Node head;
    Node tail;

    public void add(int _value) {
        Node newNode = new Node();
        newNode.value = _value;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void print() {
        if (head != null) {
            Node curNode = head;
            System.out.print("[");
            do {
                System.out.print(curNode.value + ", ");
                curNode = curNode.next;
            } while (curNode != null);
            System.out.println("\b\b], size=" + size);
        } else
            System.out.println("[], size=" + size);
    }

    public void remove(int _value) {
        Node curNode = head;
        while (curNode != null) {
            if (curNode.value == _value) {
                if (curNode.prev == null && curNode.next != null) {
                    head = curNode.next;
                    head.prev = null;
                    size--;
                    return;
                } else if (curNode.prev == null && curNode.next == null) {
                    head = null;
                    tail = null;
                    size--;
                    return;
                } else if (curNode.prev != null && curNode.next == null) {
                    tail = curNode.prev;
                    tail.next = null;
                    size--;
                    return;
                } else {
                    curNode.prev.next = curNode.next;
                    curNode.next.prev = curNode.prev;
                    size--;
                    return;
                }
            }
            curNode = curNode.next;
        }
    }

    public void revert() {
        Node curNode = head;
        while (curNode != null) {
            Node _next = curNode.next;
            Node _prev = curNode.prev;
            curNode.next = _prev;
            curNode.prev = _next;
            if (_prev == null) {
                tail = curNode;
            }

            if (_next == null) {
                head = curNode;
            }
            curNode = _next;
        }
    }
}
