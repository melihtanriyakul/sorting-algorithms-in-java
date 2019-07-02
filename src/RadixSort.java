import java.util.Arrays;
public class RadixSort {
	public static int maxVal(int arr[]) {
		int max = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		return max;
	}

	public static void countSort(int arr[], int exp) {
		int arrLen = arr.length;
		int outArr[] = new int[arrLen];
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);

		for (i = 0; i < arrLen; i++) {
			count[(arr[i] / exp) % 10]++;
		}

		for (i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}

		for (i = arrLen - 1; i >= 0; i--) {
			outArr[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}

		for (i = 0; i < arrLen; i++) {
			arr[i] = outArr[i];
		}
	}

	public static void sort(int arr[]) {
		int max = maxVal(arr);

		for (int exp = 1; max / exp > 0; exp *= 10) {
			countSort(arr, exp);
		}
	}
}
