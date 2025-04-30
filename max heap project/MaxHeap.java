
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * MaxHeap class. All methods assume indices start at 1.
 *
 * @param <T> The type of element in this MaxHeap.
 */
public class MaxHeap<T extends Comparable<T>>{


    protected int heapsize;
    protected Comparable[] a;

    /**
     * Makes a new MaxHeap with the given capacity.
     *
     * @param intialCapacity The initial capacity.
     */
    public MaxHeap(int intialCapacity){
        a = new Comparable[intialCapacity];

    }
    /**
     * Makes a new MaxHeap from the given array.
     * @param array An array of comparables.
     */
    public MaxHeap(T... array){
        a = new Comparable[array.length];
        for (T c : array) insert(c);
    }

    /**
     * Returns the element at the specified index.
     * @param i An index in this heap, where the first index is 1.
     * @return The element at th index.
     */
    public T get(int i)
    {
        if (i > heapsize) throw new ArrayIndexOutOfBoundsException();
        return (T)a[i-1];
    }

    /**
     * Returns the element at the specified index.
     * @param i An index in this heap, where the first index is 1.
     * @param T
     * @return The element at th index.
     */
    public void set(int i, T data) {a[i-1] = data;}

    /**
     * Returns the parent of the specified index.
     *
     * @param i An index in this heap, where the first index is 1.
     * @return The parent index.
     */
    public int parentIndex(int i){
        return (i)/2;
    }

    /**
     * Returns the left child of the specified index.
     *
     * @param i An index in this heap, where the first index is 1.
     * @return The left child index.
     */
    public int leftChildIndex(int i){
        return 2*i;
    }

    /**
     * Returns the right child of the specified index.
     *
     * @param i An index in this heap, where the first index is 1.
     * @return The right child index.
     */
    public int rightChildIndex(int i){
        return 2*i +1;
    }

    /**
     * Returns the number of elements in this heap.
     *
     * @return the number of elements in this heap.
     */
    public int getSize(){
        return heapsize;
    }

    /**
     * Swaps the elements at the two specified indices in the given array.
     *
     * @param a An array.
     * @param i An index in this heap, where the first index is 1.
     * @param j An index in this heap, where the first index is 1.
     */
    public static void swap (Comparable[] a, int i, int j){

        Comparable b = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = b;
    }

    /**
     * Sifts elements 'down' the heap to satisfy the heap condition, starting at the specified index.
     *
     * @param i An index in this heap, where the first index is 1.
     */
    public void heapify(int i){
        int largest = i;
        int l = leftChildIndex(i);
        int r = rightChildIndex(i);


        if (l <= heapsize &&a[l-1].compareTo(a[i-1]) >  0){
            largest = l;

        }

        if (r <= heapsize && a[r-1].compareTo(a[largest-1]) > 0){
            largest = r;
        }
        if (largest != i ){
            swap(a, i,largest);
            heapify(largest);
        }



    }

    /**
     * Sorts this heap in-place.
     */
    public void heapSort(){
        buildMaxHeap();

        for (int i = a.length; i >= 2 ; i--){
            swap(a, 1, i);
            heapsize--;
            heapify(1);

        }

    }

    /**
     * Sifts an element 'up' the heap to satisfy the heap condition, starting at the specified index.
     *
     * @param i An index in this heap, where the first index is 1.
     */
    public void heapifyUp(int i)  {

        if (i == 1){
            return;
        }
        Comparable j = a[parentIndex(i)-1];

        if (a[i-1].compareTo(j) > 0){
            swap(a,i,parentIndex(i));
            heapifyUp(parentIndex(i));

        }

    }

    /**
     * Rearranges every element in this heap's backing array so that they satisfy the heap condition.
     */
    private void buildMaxHeap(){


        heapsize = a.length-1;
        int i = (a.length)/(2);
        while (i >= 0){
            heapify(i);
            i--;
        }

    }

    /**
     * Inserts the given element into this heap.
     *
     * @param data The element to insert.
     */
    public void insert(Comparable data)  {

        if (data == null){
            throw new NullPointerException();
        }
        if (a.length == heapsize){
            expandCapacity();

        }
        a[heapsize] = data;
        heapsize++;
        heapifyUp(heapsize);


    }

    /**
     * Removes and returns the greatest (first) element of this heap.
     *
     * @return the greatest (first) element of this heap.
     */
    public Comparable extractMax() {
        if (heapsize == 0){
            throw new NoSuchElementException();
        }
        Comparable b = a[0];

        a[0] = a[heapsize-1];
        a[heapsize-1] = null;
        heapsize--;
        heapify(1);
        return b;


    }

    /**
     * Doubles the capacity of this heap.
     */
    private void expandCapacity() {
        Comparable[] b = new Comparable[a.length*2];
        System.arraycopy(a,0,b,0,heapsize);

        a = b;
    }

    /**
     * Returns a string representation of this heap.
     *
     * @return a string representation of this heap.
     */
    public String toString() {
        return Arrays.toString(a);
    }

}
