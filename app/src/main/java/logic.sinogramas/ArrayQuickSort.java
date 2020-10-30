package logic;
/** 
 * Java program for implementation of QuickSort for arrays-type structures
 * @author Rajat Mishra
 * @author Cristian Davil Camilo Santos Gil
 * @author Diego Esteban Quintero Rey
 * @author Kevin Jair Gonzalez Sanchez
 * @author Stiven Leonardo Sánchez León 
 * @version 2.0
 */ 
public class ArrayQuickSort<T extends Comparable<T>>{ 

    /**
     * Constructor; no action to be performed
     */
    public ArrayQuickSort() {
        super();
    }

    /**
     * The main function that implements QuickSort(), it sorts from a given index to another one
     * @param arr: Array to be sorted
     * @param low Starting index
     * @param high Ending index
     */
    public void sort(T[] arrayToBeSorted, int lowIndex, int highIndex) { 
        if (lowIndex < highIndex) { 
            int partitionIndex = partition(arrayToBeSorted, lowIndex, highIndex);
            // Recursively sort elements before partition and after partition 
            sort(arrayToBeSorted, lowIndex, partitionIndex-1); 
            sort(arrayToBeSorted, partitionIndex+1, highIndex); 
        } 
    } 

    /**
     * The main function that implements QuickSort(), it sorts the whole array 
     * @param arr: Array to be sorted
     */
    public void sort(T[] arr) {
        sort(arr, 0, arr.length -1);
    }

    /**
     * This method takes the last element as pivot, then places it at its correct position in the array
     * and the smaller elements are places to the left and the greatest to the right of the pivot
     * @param arr: Array to be sorted
     * @param low: Starting index
     * @param high: Ending index
     * @return Partition index, so the it can continues
     */
    public int partition(T[] arrayToBeSorted, int lowIndex, int highIndex) { 
        T pivot = arrayToBeSorted[highIndex];  
        int i = (lowIndex-1); // index of smaller element 
        for (int j=lowIndex; j<highIndex; j++) {
            if (arrayToBeSorted[j].compareTo(pivot)<0) { 
                i++; 
                // swap arr[i] and arr[j] 
                T temp = arrayToBeSorted[i]; 
                arrayToBeSorted[i] = arrayToBeSorted[j]; 
                arrayToBeSorted[j] = temp; 
            } 
        } 
        // swap arr[i+1] and arr[high] (or pivot) 
        T temp = arrayToBeSorted[i+1]; 
        arrayToBeSorted[i+1] = arrayToBeSorted[highIndex]; 
        arrayToBeSorted[highIndex] = temp; 
        return i+1; 
    }
} 

/*This code is contributed by Rajat Mishra */