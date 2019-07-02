public class BinarySearch {
	public static int search(int arr[], int left, int right, int val) {
		if (right >= left) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == val) {
				return mid;
			}
			
			if (arr[mid] > val) {
				return search(arr, left, mid - 1, val);
			}
			
			return search(arr, mid + 1, right, val);
		}
		
		return -1;
	}
}
