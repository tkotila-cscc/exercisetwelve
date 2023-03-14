package cscc.tkotila;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(3);

        try {
            queue.enqueue(5);
            queue.enqueue(345);
            queue.enqueue(76);
            queue.printElements();
            System.out.println(queue.dequeue());
            queue.printElements();
        } catch (QueueFullException | QueueEmptyException e) {
            e.printStackTrace();
        }
    }
}

class QueueFullException extends Exception {}
class QueueEmptyException extends Exception {}

class Queue<E> {
    private E[] elements;
    private int front = -1, rear = -1;
    private final int size;

    public Queue(int size) {
        elements = (E[]) new Object[size];
        this.size = size;
    }

    void enqueue(E element) throws QueueFullException {
        if (front == 0 && rear == size - 1) {
            throw new QueueFullException();
        }

        if (front == -1) {
            front = 0;
        }

        rear++;
        elements[rear] = element;
    }

    E dequeue() throws QueueEmptyException {
        if (front == -1) {
            throw new QueueEmptyException();
        }

        E element = elements[front];

        if (front >= rear) {
            front = -1;
            rear = -1;
        } else {
            front++;
        }

        return element;
    }

    void printElements() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(elements, front, rear + 1)));
    }
}
