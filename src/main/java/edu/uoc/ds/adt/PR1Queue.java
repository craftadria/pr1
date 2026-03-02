package edu.uoc.ds.adt;


import edu.uoc.ds.adt.sequential.Queue;
import edu.uoc.ds.adt.sequential.QueueArrayImpl;

public class PR1Queue {

    public final int CAPACITY = 10;

    private Queue<Character> queue;

    public PR1Queue() {
        newQueue();
    }
    public void newQueue() {
        queue = new QueueArrayImpl<>(CAPACITY);
    }

    public Queue<Character> getQueue() {
        return this.queue;
    }

    public void add(Character c) {
        this.queue.add(c);
    }

    public Character poll() {
        return this.queue.poll();
    }
}
