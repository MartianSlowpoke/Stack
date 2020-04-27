package utils;

public class MeasureWorker {

	public static int measure(Object object) {
		return ((Measurable) object).measure();
	}

}
