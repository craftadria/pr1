package edu.uoc.ds.adt;


import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;

public class PR1Queue<T> {

    public int CAPACITY = 10;
    private Queue<T> queue;

    public PR1Queue(int capacity) {
        this.CAPACITY = capacity;
        newQueue();
    }

    public void newQueue() {

        queue = new QueueArrayImpl<>(CAPACITY);
    }

    public Queue<T> getQueue() {
        return queue;
    }

    public void add(T element) {
        queue.add(element);
    }

    public T poll() {
        return queue.poll();
    }

    public T peek() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}