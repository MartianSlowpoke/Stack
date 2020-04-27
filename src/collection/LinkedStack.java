package collection;

import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;

import utils.Measurable;
import utils.MeasureWorker;
import utils.UnMeasurableException;

public class LinkedStack<T extends Serializable> implements Stack<T>, Serializable {

	private static final long serialVersionUID = 1L;
	private LinkedList<T> list;

	public LinkedStack() {
		list = new LinkedList<T>();
	}

	public LinkedStack(T[] arr) {
		this();
		for (int i = 0; i < arr.length; i++) {
			@SuppressWarnings("unchecked")
			T copied = (T) SerializationUtils.clone(arr[i]);
			this.push(copied);
		}
	}

	public LinkedStack(Stack<T> stack) {
		list = new LinkedList<T>();
//		Iterator<T> i = stack.getIterator();
//		while (!i.isNull()) {
//			T current = i.getCurrent();
//			T copied = (T) SerializationUtils.clone(current);
//			list.insertLast(copied);
//		}
		copy(stack);
	}

	@SuppressWarnings("unchecked")
	private void copy(Stack<T> stack) {
		T e = null;
		if (!stack.isEmpty()) {
			e = stack.pop();
			copy(stack);
		}
		if (e != null) {
			stack.push(e);
			e = (T) SerializationUtils.clone(e);
			this.push(e);
		}
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void swap(Stack<T> stack) {
		Stack<T> temp = new LinkedStack<>(this);
		Stack<T> magic1 = new LinkedStack<>();
		Stack<T> magic2 = new LinkedStack<>();
		while (!this.isEmpty())
			this.pop();
		while (!stack.isEmpty())
			magic1.push(stack.pop());
		while (!magic1.isEmpty())
			this.push(magic1.pop());
		while (!temp.isEmpty())
			magic2.push(temp.pop());
		while (!magic2.isEmpty())
			stack.push(magic2.pop());
	}

	@Override
	public void push(T element) {
		list.insertFirst(element);
	}

	@Override
	public T pop() {
		return list.deleteFirst();
	}

	@Override
	public T top() {
		if (!isEmpty())
			return list.getFirst().getData();
		return null;
	}

	@Override
	public Iterator<T> getIterator() {
		return new Iterator<>(list.getFirst());
	}

	@Override
	public int compareTo(Stack<T> stack) throws UnMeasurableException {
		if (stack.top() instanceof Measurable && this.top() instanceof Measurable) {
			int this_count = compareToDelegate(this);
			int that_count = compareToDelegate(stack);
			int diff = this_count - that_count;
			return diff;
		}
		throw new UnMeasurableException("The class " + stack.top().getClass().getName() + "or + "
				+ this.top().getClass().getName() + " doesn'tenvelope support measure action!");
	}

	private int compareToDelegate(Stack<T> stack) {
		int size = 0;
		T e = null;
		if (!stack.isEmpty()) {
			e = stack.pop();
			size = compareToDelegate(stack);
		}
		if (e != null) {
			stack.push(e);
			size = size + MeasureWorker.measure(e);
			return size;
		}
		return size;
	}

	/*
	 * 3 2 1 => pop 1 => call (stack 3 2) => pop 2 => call (stack 3) => pop 3 =>
	 * empty - no call => push 3 => push 2 => push 1 (reverse order)
	 */
	@Override
	public boolean equals(Stack<T> stack) {
		if (this.isEmpty() != stack.isEmpty())
			return false;
		if (this.isEmpty() && stack.isEmpty())
			return true;
		T element_a = this.pop();
		T element_b = stack.pop();
		try {
			if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
				return false;
			return equals(stack);
		} finally { // restore elements
			this.push(element_a);
			stack.push(element_b);
		}
	}

}
