package utils;

public class ArrayUtil {

	public static int sum(byte[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum = sum + arr[i];
		return sum;
	}

}
