/**
 * Provides priority queue management via enqueue, dequeue, isEmpty and update methods.
 *
 * @author Spencer Pattillo
 */
public class MyPriorityQueue extends MaxHeap implements PriorityQueueInterface{



    /**
     * Creates a new priority queue with the provided initial capacity.
     *
     * @param capacity The initial capacity of this queue.
     */
    public MyPriorityQueue(int heapsize) {
        super(heapsize);
    }

    /**
     * Creates a new priority queue with an initial capacity of 32.
     */
    public MyPriorityQueue() {
        this(32);
    }

    /**
     * Inserts the given task into this priority queue.
     *
     * @param task - Task to enqueue.
     */
    @Override
    public void enqueue(Object task) {
        if (task == null){
            throw new NullPointerException();
        }
        insert((Comparable)task);

    }

    /**
     * Removes and returns the task with the highest priority in this queue.
     *
     * @return the task with the highest priority in this queue.
     */
    @Override
    public Task dequeue() {
        Task task = (Task)extractMax();
        return task;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return true if this queue is empty.
     */
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Increases the waiting times of all currently queued tasks. Each task that has passed timeToIncrementPriority
     * will have its priority increased, up to the provided maximum.
     *
     * @param timeToIncrementPriority How long each task waits for before its priority is incremented.
     * @param maxPriority The maximum priority any task can have.
     */
    @Override
    public void update(int timeToIncrementPriority, int maxPriority) {
        for (int i = 0; i < heapsize; i++) {
            Task task = (Task) a[i];
            task.incrementWaitingTime();
            if (task.updatePriority(timeToIncrementPriority, maxPriority))
                heapifyUp(i+1);
        }

    }
}
