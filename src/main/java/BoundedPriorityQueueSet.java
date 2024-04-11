import java.util.LinkedList;

public class BoundedPriorityQueueSet {
    private final LinkedList<Task> queue;
    private final int maxSize;

    /**
     * Creates a BoundedPriorityQueueSet with a default maximum size of 10.
     */
    public BoundedPriorityQueueSet() {
        this.queue = new LinkedList<>();
        this.maxSize = 10;
    }

    /**
     * Creates a BoundedPriorityQueueSet with the specified maximum size.
     * @param maxSize the maximum size of the queue
     */
    public BoundedPriorityQueueSet(int maxSize) {
        this.queue = new LinkedList<>();
        this.maxSize = maxSize;
    }

    /**
     * Returns the number of tasks currently stored in the queue.
     * @return the number of tasks in the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Check if queue is empty
     * @return true if queue is empty, false otherwise.
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * Check if queue is full
     * @return true if queue is full, false otherwise.
     */
    public boolean isFull(){
        return queue.size() == maxSize;
    }

}
