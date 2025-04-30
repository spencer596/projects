/**
 * Represents a single task with name, priority, energy taken/received, money gained, and varying unfortunate event
 * probabilities.
 *
 * @author Spencer Pattillo
 */
public class Task implements TaskInterface, Comparable<Task>{

    private int priority;
    private TaskType type;
    private int hourCreated;
    private String taskDescription;
    private int waitingTime;


    /**
     * Creates a new task.
     *
     * @param hourCreated The hour this task was created.
     * @param type A TaskType enum.
     * @param taskDescription A string description of this task.
     */
    public Task(int hourCreated, TaskType type, String taskDescription){
        this.hourCreated = hourCreated;
        this.type = type;
        this.taskDescription = taskDescription;
    }

    /**
     * Returns the current priority of this task.
     *
     * @return the current priority of this task.
     */
    @Override
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the string description of this task.
     *
     * @return the string description of this task.
     */
    public String getTaskDescription(){
        return taskDescription;
    }

    /**
     * Sets the current priority of this task to the given value.
     *
     * @param priority The value to set priority to.
     */
    @Override
    public void setPriority(int priority) {
        this.priority = priority;

    }

    /**
     * Returns this task's type.
     *
     * @return this task's type.
     */
    @Override
    public TaskType getTaskType() {
        return type;
    }

    /**
     * Increases this task's waiting time by 1.
     */
    @Override
    public void incrementWaitingTime() {

        waitingTime++;
    }

    /**
     * Resets this task's waiting time to the default, 0.
     */
    @Override
    public void resetWaitingTime() {
        waitingTime = 0;

    }

    /**
     * Returns this task's waiting time.
     *
     * @return this task's waiting time.
     */
    @Override
    public int getWaitingTime() {
        return waitingTime;
    }

    /**
     * Increments this task's priority if its waiting time exceeds the given time and its priority is lower than the
     * given max. Also resets the waiting time if required.
     *
     * @param timeToIncrementPriority The time after which to increment priority.
     * @param maxPriority The maximum priority for this task.
     * @return True if the priority was incremented, false otherwise.
     */
    public boolean updatePriority(int timeToIncrementPriority, int maxPriority)
    {
        if (waitingTime >= timeToIncrementPriority)
        {
            resetWaitingTime();
            if (priority < maxPriority)
            {
                priority++;
                return true;
            }
        }
        return false;
    }

    /**
     * Compares this task to another, provided task. Satisfies the contract of Comparable.compareTo().
     *
     * @param task the object to be compared.
     * @return Returns -1, 0, or +1 as this object is less than, equal to, or greater than the specified task.
     */
    @Override
    public int compareTo(Task task) {
        if (Integer.compare(priority, task.getPriority()) == 0){
            return Integer.compare(task.hourCreated, hourCreated);
        }
        return Integer.compare(priority, task.getPriority());
    }

    /**
     * Returns a string representation of this task. Satisfies the contract of Object.toString();
     *
     * @return A string representation of the task.
     */
    public String toString(){
        return type.toString() + " " + taskDescription.toString() + " at Hour: " + hourCreated +":00";
    }
}
