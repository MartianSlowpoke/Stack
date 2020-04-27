package collection;

import java.io.Serializable;

public class LinkedList<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Link<T> first;
	private Link<T> last;

	public LinkedList() {
		super();
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(T data) {
		Link<T> link = new Link<T>(data);
		if (isEmpty())
			last = link;
		link.setNext(first);
		first = link;
	}

	public void insertLast(T data) {
		Link<T> link = new Link<T>(data);
		if (isEmpty())
			first = link;
		else
			last.setNext(link);
		last = link;
	}

	public T deleteFirst() {
		if (!isEmpty()) {
			T temp = first.getData();
			if (first.getNext() == null)
				last = null;
			first = first.getNext();
			return temp;
		}
		return null;
	}

	public int size() {
		int count = 0;
		Iterator<T> i = this.getIterator();
		while (!i.isNull()) {
			count = count + 1;
			i.getCurrent();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public T min() {
		Iterator<T> iterator = this.getIterator();
		Comparable<T> min = (Comparable<T>) first.getData();
		while (!iterator.isNull()) {
			T current = iterator.getCurrent();
			int result = min.compareTo(current);
			if (result > 0)
				min = (Comparable<T>) current;
		}
		return (T) min;
	}

	@SuppressWarnings("unchecked")
	public T max() {
		Iterator<T> iterator = this.getIterator();
		Comparable<T> max = (Comparable<T>) first.getData();
		while (!iterator.isNull()) {
			T current = iterator.getCurrent();
			int result = max.compareTo(current);
			if (result < 0)
				max = (Comparable<T>) current;
		}
		return (T) max;
	}

	public Iterator<T> getIterator() {
		return new Iterator<T>(first);
	}

	public Link<T> getFirst() {
		return first;
	}

	public Link<T> getLast() {
		return last;
	}

}
