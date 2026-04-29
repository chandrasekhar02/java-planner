package Week1.day2;

public class MaxMinArray {

    public static void main(String[] args) {
        int[] numbers = {7, 2, 9, 1, 5, 4, 8};
        findMaxMin(numbers);
    }

    
    private static void findMaxMin(int[] arr) {
        // if the arr is empty, it should be handled
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        // Initializing the  max and min with the first element of the array
        int max = arr[0];
        int min = arr[0];

        // Iterate through the array starting fromm the second element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; 
            } else if (arr[i] < min) {
                min = arr[i]; 
            }
        }

        System.out.println("The given array is: " + java.util.Arrays.toString(arr));
        System.out.println("Maximum element: " + max);
        System.out.println("Minimum element: " + min);
    }
}

