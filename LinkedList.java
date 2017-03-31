public class LinkedList<T> {

    // ---------------------------------------------- ATTRIBUTES
    private Node head;
    private int size;
    // ---------------------------------------------- CONSTRUCTOR

    public LinkedList() {
        this.setHead(new Node<T>());
        this.setSize(0);
    }

    // ---------------------------------------------- METHODS
    public void printList() {
        Node<T> currentNodeToPrint;

        if (this.isEmpty()) {
            System.out.println("The list is empty");
        } else {
            currentNodeToPrint = this.getHead();
            while (currentNodeToPrint.getNextNode() != null) {
                System.out.print(currentNodeToPrint + " ");
                currentNodeToPrint = currentNodeToPrint.getNextNode();
            }
            System.out.println();
        }
    }

    public void makeEmpty() {
        this.getHead().setNextNode(null);
        this.setSize(0);
    }

    public int findPosition(T askedValue) // (-1) -> Value not here // :)
    {
        Node<T> currentNode;
        int positionToReturn;

        positionToReturn = -1;
        currentNode = this.getHead();
        for (int index = 0; index < this.getSize(); index = index + 1) {
            if (currentNode.getValue().equals(askedValue)) {
                positionToReturn = index;
                break;
            }

            currentNode = currentNode.getNextNode();
        }

        return positionToReturn;
    }

    public T findValue(int position) // :) IF getNode is fixed
    {
        return this.getNode(position).getValue();
    }

    public Node<T> getNode(int position) // :) IF position <= ( this.getSize() - 1) && (position > 0)
    {
        Node<T> nodeToReturn;

        nodeToReturn = this.getHead();
        for (int currentPosition = 0; currentPosition < position; currentPosition = currentPosition + 1) {
            nodeToReturn = nodeToReturn.getNextNode();
        }

        return nodeToReturn;
    }

    public boolean insertValue(T newValue, int insertPosition) // :)
    {
        boolean successfullyDone;
        Node<T> nodeToSave;

        if ((insertPosition > this.getSize()) || (insertPosition < 0)) {
            successfullyDone = false;
        } else if (insertPosition == 0) {
            nodeToSave = this.getHead();
            this.setHead(new Node<T>());
            this.getHead().setValue(newValue);
            this.getHead().setNextNode(nodeToSave);

            this.setSize(this.getSize() + 1);
            successfullyDone = true;
        } else {
            nodeToSave = this.getNode(insertPosition);
            this.getNode(insertPosition - 1).setNextNode(new Node<T>());
            this.getNode(insertPosition).setValue(newValue);
            this.getNode(insertPosition).setNextNode(nodeToSave);

            this.setSize(this.getSize() + 1);
            successfullyDone = true;
        }

        return successfullyDone;
    }

    public boolean eliminateValue(int eliminationPosition) // :)
    {
        Node<T> nodeToSave;
        boolean successfullyDone;

        if ((eliminationPosition > (this.getSize() - 1)) || (eliminationPosition < 0)) {
            successfullyDone = false;
        } else {
            if (eliminationPosition == 0) {
                this.setHead(this.getHead().getNextNode());
            } else {
                nodeToSave = this.getNode(eliminationPosition + 1);
                this.getNode(eliminationPosition - 1).setNextNode(nodeToSave);
            }

            this.setSize(this.getSize() - 1);
            successfullyDone = true;
        }

        return successfullyDone;
    }

    public boolean isEmpty() {
        boolean returnValue;

        returnValue = (this.getSize() == 0) ? (true) : (false);

        return returnValue;
    }

    // ---------------- GETTERS & SETTERS
    public Node getHead() {
        return this.head;
    }

    public int getSize() {
        return this.size;
    }

    public void setHead(Node newHead) {
        this.head = newHead;
    }

    public void setSize(int newSize) {
        this.size = newSize;
    }
}

class Node<T> {

    // ---------------------------------------------- ATTRIBUTES
    private T value;
    private Node nextNode;

    // ---------------------------------------------- CONSTRUCTORS
    public Node() {
        // void
    }

    // ---------------------------------------------- METHODS
    public String toString() {
        return "" + this.getValue();
    }

    // ---------------- GETTERS & SETTERS
    public Node getNextNode() {
        return this.nextNode;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }

    public void setNextNode(Node newNextNode) {
        this.nextNode = newNextNode;
    }
}