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

    /**
     * Calculates the position to insert a task based on its priority.
     * @param task the task to be added
     * @return position task should be inserted
//     * @throws DuplicateElementException if the task is already present in the queue
     */
    private int calcPosition(Task task){
        for (int i = 0; i < queue.size(); i++){
            Task t = queue.get(i);
            if (task.equals(t)) {
//                not recognising exception
//               throw new DuplicateElementException("Task already exists in the queue");
            }if (task.compareTo(t) < 0){
                return i;//task has lower priority to task t i.e deadline not as close as task t deadline.
            }
        }
        return queue.size(); // task has highest priority in queue i.e shortest deadline
    }

    /**
     * Adds task to queue at specific position based on its priority and
     * only if queue is not full and task is not already in it
     * @param task the task to be added
     * @throws IllegalStateException if the queue is full
    //     * @throws DuplicateElementException if the task is already present in the queue
     */
    public void add(Task task){
        if(isFull()){
            throw new IllegalStateException("Queue is full");
        }if(queue.contains(task)){
//            throw new DuplicateElementException("Task already in queue");
        }
        int pos = calcPosition(task);
        queue.add(pos, task);
    }

    /**
     * checks to see if task can be added to queue at specific position based on its priority and
     * only if queue is not full and task is not already in it
     * @param task the task to be checked
     * @return true if possible for task to be added, false otherwise.
     */
    public boolean offer(Task task){
        if(isFull() || queue.contains(task)){
            System.out.println("task cannot be added");
            return false;
        }
        int pos = calcPosition(task);
        queue.add(pos, task);
        System.out.println("Task can be added");
        return true;
    }
}
