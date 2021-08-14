package merge_sort;

import abstract_class.AbstractSortArrayClass;

class MergeSortTest extends AbstractSortArrayClass {

  @Override
  public void sortArray(int[] array) {
    MergeSort.mergeSort(array);
  }
}
