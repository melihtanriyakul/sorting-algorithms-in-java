public class SelectionSort {
	public static void sort(int arr[]) {
		int arrLen = arr.length;

		for (int i = 0; i < arrLen - 1; i++) {
			int minIndex = i;

			for (int j = i + 1; j < arrLen; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			int temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i] = temp;
		}
	}
}
