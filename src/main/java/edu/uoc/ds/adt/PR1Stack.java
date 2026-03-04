package edu.uoc.ds.adt;


import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;

public class PR1Stack<T> {
    private int CAPACITY = 10;

    private Stack<T> stack;

    public PR1Stack(int capacity) {
        this.CAPACITY = capacity;
        newStack();
    }

    public void newStack() {
        stack = new StackArrayImpl<>(CAPACITY);
    }

    public Stack<T> getStack() {
        return stack;
    }

    public void push(T element) {
        stack.push(element);
    }

    public T pop() {
        return stack.pop();
    }

    public T peek() {
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}