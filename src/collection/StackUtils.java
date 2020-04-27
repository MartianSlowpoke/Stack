package collection;

public class StackUtils {

	static <T> boolean compareStacks(Stack<T> a, Stack<T> b) {
		if (a.isEmpty() != b.isEmpty())
			return false;
		if (a.isEmpty() && b.isEmpty())
			return true;
		T element_a = a.pop();
		T element_b = b.pop();
		try {
			if (((element_a == null) && (element_b != null)) || (!element_a.equals(element_b)))
				return false;
			return compareStacks(a, b);
		} finally { // restore elements
			a.push(element_a);
			b.push(element_b);
		}
	}

}
