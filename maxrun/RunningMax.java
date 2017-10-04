import java.util.*;

/**
 * This data structure class can initialize a list, add elements to the list,
 * and return the max element from the list.
 *
 * @author Andrew Hwang
 * @version 1.1 Oct. 21, 2015
 *
 */
public class RunningMax< T extends Comparable<T>> {

    /**
     * Set a global variable to the capacity of the list.
     */
    private int k;

    /**
     * Set a global variable as the list.
     */
    private ArrayList<T> myArrayList;

    /**
     * This routine initializes the max capacity of the list.
     * And the created data structure is a list.
	 */
    public void initialize(int k) {
        //If k is greater than 0;
        if (0 < k) {
            //Global variable k <- the inputted k.
            this.k = k;

            //Global list myArrayList <- new list with a capacity of k.
            this.myArrayList = new ArrayList<>(k);
        }

        //If k is less than or equal to 0
        else {
            //Show an error telling user to input a positive integer.
            throw new IllegalArgumentException("ERROR: Please input a positive integer!");
        }
    }

	/**
     * This routine adds a new element "x" to the list.
     * And removes the oldest element if there are already k elements in the list.
	 */
    public void offer(T x) {
        //If the parameter is undefined
        if (x == null) {
            //Show an error to input an appropriate value.
            throw new IllegalArgumentException("ERROR: Please enter a valid input!");
        }

        //If the size of the list is equal to k
        if (this.getNumOfElements() == k) {
            //Remove the oldest/first element of the list.
            myArrayList.remove(0);  //O(N)
        }

        //Add the input to the end of the list.
        myArrayList.add(x);   //O(1)
    }


	/**
     * This routine returns the max element of the last k or less elements.
	 */
    public T max() {
        //If the list is empty
        if (myArrayList.isEmpty()) {
            //Show an error telling the user to add elements to the list.
            throw new IndexOutOfBoundsException("ERROR: The list is EMPTY, please offer elements to the list!");
        }

        //Set the maxElement <- the first element of the list.
        T maxElement = this.getElement(0);

        //Set the index at 0.
        int i = 0;

        //Iterate through the list and compare each element to the current max element
        while (i < this.getNumOfElements()) {   //O(N)
            //If the max element is less than the current element of the list
            if (maxElement.compareTo(myArrayList.get(i)) < 0) {
                //Assign the current element as the new max value of the list.
                maxElement = myArrayList.get(i);
            }

            //Continue iterating through the list.
            i++;
        }

        // return the max element
        return maxElement;
    }

    /**
     * This routine returns the total number of elements within the list.
     */
    public int getNumOfElements() {
        //The total number of elements in list <- size of list.
        int totalNumOfElements = this.myArrayList.size();

        //Return the variable.
        return totalNumOfElements;
    }

    /**
     * This routine returns the element at the specified index.
     */
    public T getElement(int index) {
        //If the index is not 0 <= index < k
        if (index < 0 || k <= index) {
            //Show an error telling the user that index was out of bounds.
            throw new IndexOutOfBoundsException("ERROR: That index does not exist!");
        }

        //If the list is empty
        if (myArrayList.isEmpty()) {
            //Show an error telling the user to add elements to the list.
            throw new IndexOutOfBoundsException("ERROR: The list is EMPTY, please offer elements to the list!");
        }

        //Element <- element at the index.
        T element = this.myArrayList.get(index);

        //Return the element.
        return element;
    }
}
