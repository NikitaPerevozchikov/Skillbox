package quick_sort;

public class QuickSort
{
    public static void sort(int[] array)
    {
        if(array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to)
    {
        if(from < to)
        {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot, to);
        }
    }

    public static int partition(int[] array, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = array[from];
        while (leftIndex <= rightIndex) {
            while (array[leftIndex] < pivot) {
                leftIndex++;
            }

            while (array[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                int tmp  = array[rightIndex];
                array[rightIndex] = array[leftIndex];
                array[leftIndex] = tmp;
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }
}
