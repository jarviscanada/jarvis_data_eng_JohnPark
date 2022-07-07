package ca.jrvs.practice.sorting;

public class QuickSort {
  public void quickSort(int arr[], int begin, int end) {
    if (begin < end) {
      int partitionIndex = partition(arr, begin, end);

      quickSort(arr, begin, partitionIndex - 1);
      quickSort(arr, partitionIndex + 1, end);
    }
  }


  private int partition(int arr[], int begin, int end) {
    int pivot = arr[end]; // end is the pivot
    int i = (begin-1); // Item from left? why is it begin - 1? - this seems weird

    for (int j = begin; j < end; j++) {
      if (arr[j] <= pivot) {
        i++;

        int swapTemp = arr[i];
        arr[i] = arr[j];
        arr[j] = swapTemp; // equivalent to arr[i], arr[j] = arr[j], arr[i] more or less
      }
    }
    // i is the index where elements smaller or equal to pivot will be there.

    int swapTemp = arr[i+1];
    arr[i+1] = arr[end];
    arr[end] = swapTemp; // switching i+1th with pivot

    return i+1;


  }
}

