package collection;

public class Iterator<T> {

	private Link<T> first;

	public Iterator(Link<T> first) {
		this.first = first;
	}

	public boolean isNull() {
		return (first == null);
	}

	public T getCurrent() {
		T temp = first.getData();
		first = first.getNext();
		return temp;
	}

}
