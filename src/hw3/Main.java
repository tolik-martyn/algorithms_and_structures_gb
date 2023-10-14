package hw3;

public class Main {
    public static void main(String[] args) {
        Linkedlist list = new Linkedlist();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.print();
        list.revert();
        list.print();

        System.out.println(list.head.value);
        System.out.println(list.head.next.value);
        System.out.println(list.tail.prev.value);
        System.out.println(list.tail.value);
    }
}
