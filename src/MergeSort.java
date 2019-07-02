public class MergeSort {
	public static void merge(int arr[], int l, int m, int r) {
		int leftSize = m - l + 1;
		int rightSize = r - m;

		int leftArray[] = new int[leftSize];
		int rightArray[] = new int[rightSize];

		for (int i = 0; i < leftSize; ++i) {
			leftArray[i] = arr[l + i];
		}
		for (int j = 0; j < rightSize; ++j) {
			rightArray[j] = arr[m + 1 + j];
		}

		int firstIndex = 0, secondIndex = 0;

		int k = l;

		while (firstIndex < leftSize && secondIndex < rightSize) {
			if (leftArray[firstIndex] <= rightArray[secondIndex]) {
				arr[k] = leftArray[firstIndex];
				firstIndex++;
			} else {
				arr[k] = rightArray[secondIndex];
				secondIndex++;
			}

			k++;
		}

		while (firstIndex < leftSize) {
			arr[k] = leftArray[firstIndex];
			firstIndex++;
			k++;
		}

		while (secondIndex < rightSize) {
			arr[k] = rightArray[secondIndex];
			secondIndex++;
			k++;
		}
	}

	public static void sort(int arr[], int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;

			sort(arr, l, m);
			sort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}
}
