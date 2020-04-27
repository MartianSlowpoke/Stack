package collection;

import java.io.Serializable;

import utils.UnMeasurableException;

public interface Stack<T> extends Serializable {

	int size();

	boolean isEmpty();

	void swap(Stack<T> stack);

	void push(T element);

	T pop();

	T top();

	Iterator<T> getIterator();

	/*
	 * positive integer means this stack is greater than that
	 * 
	 * negative one means this stack is less than that
	 * 
	 * zero integer means either this stack is the same that stack OR count of
	 * records of this stack that are positive equals to ones of that stack that are
	 * negative
	 */
	int compareTo(Stack<T> stack) throws UnMeasurableException;

	boolean equals(Stack<T> stack);

}
