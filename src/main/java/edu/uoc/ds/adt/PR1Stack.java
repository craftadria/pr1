package edu.uoc.ds.adt;


import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;

public class PR1Stack {
    public final int CAPACITY = 10;

    private Stack<Character> stack;

    public PR1Stack() {
        newStack();
    }

    public void newStack() {
        stack = new StackArrayImpl<Character>(CAPACITY);
    }

    public Stack<Character> getStack() {
        return this.stack;
    }

    public void push(Character c) {
        this.stack.push(c);
    }

    public Character pop() {
        return this.stack.pop();
    }
}
