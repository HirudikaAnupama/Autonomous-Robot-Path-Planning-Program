package PathFindingProgram;

public class CustomQueue {

    private int front;
    private int rear;
    private int numberOfItems;
    private final int maxSize;
    private final int[][] queueArray; // Change to int[][] to store arrays of integers

    public CustomQueue(int maxSize) {
        this.maxSize = maxSize;
        queueArray = new int[maxSize][1];
        front = 0;
        rear = -1;
        numberOfItems = 0;
    }

    public boolean isEmpty() {
        return (numberOfItems == 0);
    }

    public boolean isFull() {
        return (numberOfItems == maxSize);
    }



    public void enqueue(int[] item) {
        if (!isFull()) {

            queueArray[++rear] = item;
            numberOfItems++;
        } else {
            System.out.println("Queue is full. Cannot enqueue.");
        }
    }



    public int[] dequeue() {
        if (!isEmpty()) {

            numberOfItems--;
            int[] item = queueArray[front++];
            return item;

        } else {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }
    }



}
