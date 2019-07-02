public class WorstCaseMerge {
	public static void merge(int[] arr, int[] left, int[] right) {
		int i, j;
		for (i = 0; i < left.length; i++)
			arr[i] = left[i];
		for (j = 0; j < right.length; j++, i++)
			arr[i] = right[j];
	}

	public static void seperate(int[] arr) {

		if (arr.length <= 1)
			return;

		if (arr.length == 2) {
			int swap = arr[0];
			arr[0] = arr[1];
			arr[1] = swap;
			return;
		}

		int i, j;
		int m = (arr.length + 1) / 2;
		int left[] = new int[m];
		int right[] = new int[arr.length - m];

		for (i = 0, j = 0; i < arr.length; i = i + 2, j++)
			left[j] = arr[i];

		for (i = 1, j = 0; i < arr.length; i = i + 2, j++)
			right[j] = arr[i];

		seperate(left);
		seperate(right);
		merge(arr, left, right);
	}
}
