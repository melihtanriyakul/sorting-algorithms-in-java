public class InsertionSort {
	public static void sort(int arr[]) {
		int arrLen = arr.length;

		for (int i = 1; i < arrLen; ++i) {
			int key = arr[i];
			int j = i - 1;
			
			while(j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j -= 1;
			}
			arr[j + 1] = key;
		}
	}
}
