package Quere;

public class QuereMain {

    public static void main(String[] args) {
        Queue q = new Queue();
        System.out.println("is empty " + q.isEmpty());
        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println("is empty " + q.isEmpty());
        q.print();

//        while (!q.isEmpty()) {
//            System.out.println("peek " + q.peek());
//            System.out.println("dequeue " + q.dequeue());
//            System.out.println("size " + q.sizr());
//            System.out.println("----------------");
//        }
        while (q.hasData()) {
            System.out.println("peek " + q.peek());
            System.out.println("dequeue " + q.dequeue());
            System.out.println("size " + q.sizr());
            System.out.println("----------------");
        }
    }

}

class Queue {

    private LinkedList dataList;

    public Queue() {
        dataList = new LinkedList(false);
    }

    void enqueue(int _data) {
        this.dataList.insertLast(_data);
    }

    int dequeue() {
        int data = this.dataList.head.data;
        this.dataList.deleteHead();
        return data;
    }

    int peek() {
        if (this.dataList.head == null) {
            return 0;
        }
        return this.dataList.head.data;
    }

    boolean isEmpty() {

        if (this.dataList.length <= 0) {
            return true;
        }
        return false;
    }
    boolean hasData() {

        if (this.dataList.length > 0) {
            return true;
        }
        return false;
    }
    int sizr() {
        return this.dataList.length;
    }

    void print() {
        this.dataList.printList();
    }

}

class Node {

    public int data;
    public Node next;

    Node(int data) {
        this.data = data;
    }
}

class LinkedList {

    public Node head = null;
    public Node tail = null;
    public boolean unique;

    LinkedList(boolean unique) {
        this.length = 0;
        this.unique = unique == true ? unique : false;
    }
    public int length;

    linkedListIterator begin() {
        linkedListIterator itr = new linkedListIterator(this.head);
        return itr;
    }

    boolean canInsert(int data) {

        if (this.unique
                && this.isExist(data)) {
            System.out.println("this data => " + data + " is already exist");
            return false;
        } else {
            return true;
        }
    }

    void insertLast(int data) {
        if (this.canInsert(data) == false) {
            return;
        }

        Node newNode = new Node(data);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.length++;
    }

    void printList() {
        for (linkedListIterator itr = this.begin(); itr.current() != null; itr.next()) {
            System.out.println(itr.data() + " ");
        }

    }

    boolean isExist(int data) {
        if (this.find(data) != null) {
            return true;
        } else {
            return false;
        }
    }

    Node find(int _data) {
        for (linkedListIterator itr = this.begin(); itr.current() != null; itr.next()) {
            if (itr.data() == _data) {
                return itr.current();
            }
        }
        return null;
    }

    Node findParent(Node node) {
        for (linkedListIterator itr = this.begin(); itr.current() != null; itr.next()) {
            if (itr.current().next == node) {
                return itr.current();
            }
        }
        return null;
    }

    void insertAfter(Node oldNode, int data) {
        if (this.canInsert(data) == false) {
            return;
        }
        Node newNode = new Node(data);
        newNode.next = oldNode.next;
        oldNode.next = newNode;
        if (this.tail == null || newNode.next == null) {
            this.tail = newNode;
        }

        this.length++;
    }

    void insertAfter2(int oldData, int data) {
        if (this.canInsert(data) == false) {
            return;
        }
        Node newNode = new Node(data);

        for (linkedListIterator itr = this.begin(); itr.current() != null; itr.next()) {
            if (itr.data() == oldData) {

                newNode.next = itr.current().next;
                itr.current().next = newNode;
            }
        }

        if (this.tail == null || newNode.next == null) {
            this.tail = newNode;
        }

        this.length++;
    }

    void insertBefore(Node oldNode, int data) {
        if (this.canInsert(data) == false) {
            return;
        }
        Node newNode = new Node(data);
        newNode.next = oldNode;
        Node parent = this.findParent(oldNode);
        if (parent == null) {
            this.head = newNode;
        } else {
            parent.next = newNode;
        }
        this.length++;
    }

    void deleteNode(Node node) {
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else if (this.head == node) {
            this.head = node.next;

        } else {
            Node parent = this.findParent(node);
            if (this.tail == node) {
                this.tail = parent;

            } else {
                parent.next = node.next;
            }
        }
        this.length--;
    }

    void insertFirst(int data) {
        if (this.canInsert(data) == false) {
            return;
        }
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
        this.length++;

    }

    void deleteHead() {
        if (this.head == null) {
            return;
        }
        this.head = this.head.next;
        this.length--;
    }
}

class linkedListIterator {

    private Node current;

    public linkedListIterator() {
        this.current = null;
    }

    public linkedListIterator(Node node) {
        this.current = node;
    }

    int data() {
        return this.current.data;
    }

    linkedListIterator next() {
        this.current = this.current.next;
        return this;
    }

    Node current() {
        return this.current;
    }

}
