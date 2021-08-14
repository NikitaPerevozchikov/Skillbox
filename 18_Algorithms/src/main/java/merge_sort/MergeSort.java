package merge_sort;

public class MergeSort {
  public static void mergeSort(int[] array) {

    int n = array.length;
    if (n < 2) {
      return;
    }
    int middle = n / 2;
    int[] leftArray = new int[middle];
    int[] rightArray = new int[n - middle];

    for (int i = 0; i < middle; i++) {
      leftArray[i] = array[i];
    }
    for (int i = middle; i < n; i++) {
      rightArray[i - middle] = array[i];
    }
    mergeSort(leftArray);
    mergeSort(rightArray);
    merge(array, leftArray, rightArray);
  }

  private static void merge(int[] array, int[] left, int[] right) {
    int indexLeft = 0;
    int indexRight = 0;
    for (int i = 0; i < array.length; i++) {
      if (indexLeft == left.length) {
        array[i] = right[indexRight];
        indexRight++;
      } else if (indexRight == right.length) {
        array[i] = left[indexLeft];
        indexLeft++;
      } else if (left[indexLeft] < right[indexRight]) {
        array[i] = left[indexLeft];
        indexLeft++;
      } else {
        array[i] = right[indexRight];
        indexRight++;
      }
    }
  }
}
