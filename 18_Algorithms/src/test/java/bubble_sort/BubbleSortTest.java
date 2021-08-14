package bubble_sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BubbleSortTest {
  @Test
  @DisplayName("Получить отсортированный массив")
  public void sortTest () {
    int [] array = new int[] {5,1,4,2,3};
    BubbleSort.sort(array);
    assertEquals(array[4], 5);
  }

}
