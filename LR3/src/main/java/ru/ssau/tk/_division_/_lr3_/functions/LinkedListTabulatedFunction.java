package ru.ssau.tk._division_._lr3_.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removeable {

    private Node head;

    private static class Node {
        public Node next;
        public Node prev;
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString(){
            return "(" + this.x + "; " + this.y + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || this.getClass() != o.getClass()) return false;
            Node other = (Node) o;
            return (this.x == other.x) && (this.y == other.y);
        }

        @Override
        public int hashCode() {
            return (int)this.x ^ (int)this.y;
        }

        @Override
        public Object clone() {
            return new Node(this.x, this.y);
        }
    }

    private void addNode(double x, double y) {
        Node newNode = new Node(x, y);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node last = head.prev;
            last.next = newNode;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + step * i;
            double y = source.apply(x);
            addNode(x, y);
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    private Node getNode(int index) {
        Node currentNode = head;
        if (index < count / 2) {
            for (int i = 0; i < index; ++i) {
                currentNode = currentNode.next;
            }
        } else {
            for (int i = count; i > index; --i) {
                currentNode = currentNode.prev;
            }
        }
        return currentNode;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            if (currentNode.x == x) return i;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            if (currentNode.y == y) return i;
            currentNode = currentNode.next;
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (indexOfX(x) != -1) return indexOfX(x);
        if (x < head.x) return 0;
        if (x > head.prev.x) return count;
        Node currentNode = head;
        int index = 0;
        while (currentNode.next != head && currentNode.next.x <= x) {
            currentNode = currentNode.next;
            index++;
        }
        return index;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) return getY(0);
        return interpolate(x, 0);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) return getY(0);
        return interpolate(x, count - 2);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) return getY(0);
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }

    @Override
    public void insert(double x, double y) {
        if (head == null) {
            addNode(x, y);
            return;
        } else if (x < head.x) {
            Node newHead = new Node(x, y);
            newHead.next = head;
            newHead.prev = head.prev;
            head.prev.next = newHead;
            head.prev = newHead;
            head = newHead;
            ++count;
            return;
        } else if (x > head.prev.x) {
            Node newNode = new Node(x, y);
            newNode.next = head;
            newNode.prev = head.prev;
            head.prev.next = newNode;
            head.prev = newNode;
            ++count;
            return;
        }

        Node current = head;
        do {
            if (x == current.x) {
                current.y = y;
                return;
            } else if (x < current.x) {
                Node newNode = new Node(x, y);
                newNode.next = current;
                newNode.prev = current.prev;
                current.prev.next = newNode;
                current.prev = newNode;
                ++count;
                return;
            }
            current = current.next;
        } while (current != head);
    }

    @Override
    public void remove(int index) {
        Node deletedNode = getNode(index);
        if (deletedNode == head && getCount() > 1) {
            this.head = deletedNode.next;
        }
        else {
            deletedNode.prev.next = deletedNode.next;
            deletedNode.next.prev = deletedNode.prev;
        }
        --count;
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder result = new StringBuilder();
        do {
            result.append("(").append(current.x).append("; ")
                    .append(current.y).append(")\n");
            current = current.next;
        } while (current != head);
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListTabulatedFunction)) return false;

        LinkedListTabulatedFunction other = (LinkedListTabulatedFunction) o;
        Node currentOriginal = this.head;
        Node currentOther = other.head;

        if (this.count != other.count) return false;
        do {
            if (currentOriginal.x != currentOther.x ||
            currentOriginal.y != currentOther.y) return false;
            currentOriginal = currentOriginal.next;
            currentOther = currentOther.next;
        } while (currentOriginal != this.head);
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        Node current = head;
        do {
            hash *= (int) (current.x + current.y);
            current = current.next;
        } while (current != head);
        return hash;
    }

    @Override
    public Object clone() {
        double[] xArray = new double[count];
        double[] yArray = new double[count];
        Node current = head;
        int index = 0;
        do {
            xArray[index] = current.x;
            yArray[index] = current.y;
            current = current.next;
            ++index;
        } while (current != head);
        return new LinkedListTabulatedFunction(xArray, yArray);
    }
}