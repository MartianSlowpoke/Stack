package collection;

import java.io.Serializable;

public class Link<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private T data;
	private Link<T> next;

	public Link(T data) {
		super();
		this.data = data;
	}

	public void setNext(Link<T> next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public Link<T> getNext() {
		return next;
	}

}
