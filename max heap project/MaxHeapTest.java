import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class MaxHeapTest{


    private static boolean isValidHeap(MaxHeap heap)
    {
        for (int i=2; i<=heap.getSize(); i++)
        {
            Comparable parent = heap.get(heap.parentIndex(i));
            Comparable child = heap.get(i);

            if (parent.compareTo(child) < 0) return false;
        }
        return true;
    }

    private static boolean verifyHeap(MaxHeap heap)
    {
        System.out.println("Result: " + heap);
        boolean isValidHeap = isValidHeap(heap);
        System.out.println("Is valid heap: " + isValidHeap);
        return isValidHeap;
    }

    private static boolean testInserts(Integer... numsToInsert)
    {
        System.out.println("Inserting integers: " + Arrays.toString(numsToInsert));
        MaxHeap heap = new MaxHeap(1);
        for (Integer num : numsToInsert) heap.insert(num);
        return verifyHeap(heap);
    }

    private static boolean testHeapify(int a, int b){

        System.out.println("Testing Heapify: " + a);
        MaxHeap heap = new MaxHeap(2);
        heap.set(1,a);
        heap.set(2,b);
        heap.heapsize = 2;
        heap.heapify(1);
        return verifyHeap(heap);
    }

    private static boolean testHeapifyUp(int a, int b){

        System.out.println("Testing HeapifyUp: " + a);
        MaxHeap heap = new MaxHeap(2);
        heap.set(1,a);
        heap.set(2,b);
        heap.heapsize = 2;
        heap.heapifyUp(2);
        return verifyHeap(heap);
    }
    private static boolean testArrayConstructor(Integer... array)
    {
        System.out.println("Heapifying array: " + Arrays.toString(array));
        return verifyHeap(new MaxHeap(array));
    }

    private static boolean testMaxExtract(Integer[] array, Integer... extractExpected)
    {
        System.out.println("Extracting " + extractExpected.length + " numbers from " + Arrays.toString(array));
        MaxHeap heap = new MaxHeap(32);
        for (Integer num : array) heap.insert(num);

        boolean passed = true;
        for (Integer expect : extractExpected)
        {
            Integer extracted = (Integer)heap.extractMax();
            System.out.println("Extracted " + extracted + ", expected " + expect);

            if (!Objects.equals(expect, extracted)) passed = false;
        }

        return passed;
    }

    private static boolean runTest(BooleanSupplier test)
    {
        System.out.println("Beginning test...");
        try
        {
            boolean passed = test.getAsBoolean();
            System.out.println(passed ? "Test passed." : "Test failed.");
            System.out.println();
            return passed;
        }
        catch (RuntimeException e)
        {
            System.out.println("Test failed due to exception:");
            e.printStackTrace();
            System.out.println();
            return false;
        }
    }

    public static void main(String[] args)
    {
        boolean passedAll = true;
        int testsPerformed = 5;
        int testsPassed = 0;

        if (runTest(() -> testInserts(7, 5, -30, 3, 1, 100))) testsPassed++;
        if (runTest(() -> testArrayConstructor(7, 5, -30, 3, 1, 100))) testsPassed++;
        if (runTest(() -> testMaxExtract(new Integer[]{7, 5, -30, 3, 1, 100}, 100, 7, 5))) testsPassed++;
        if (runTest(() -> testHeapify(5,9))) testsPassed++;
        if (runTest(() -> testHeapifyUp(5, 9))) testsPassed++;

        System.out.println("Passed " + testsPassed + " tests out of " + testsPerformed);

        float percentPassed = (100.0f*testsPassed)/testsPerformed;
        System.out.println("Percent passed: " + percentPassed + "%");
    }
}