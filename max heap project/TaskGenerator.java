import java.util.Random;

/**
 * A factory for tasks. Controls the probability of task generation and the current energy available for tasks.
 */
public class TaskGenerator implements TaskGeneratorInterface{

    private int currentEnergyStorage;
    private int luckOfDay;
    private long seed;
    private double taskProbability;
    private Random rand;

    /**
     * Creates a new task generator with the given task probability and seed.
     *
     * @param taskProbability The probability between 0 and 1 that a task will be generated in any hour.
     * @param seed A seed for this generator's random number generator.
     */
    public TaskGenerator(double taskProbability, long seed){
        this.taskProbability = taskProbability;
        this.rand = new Random(seed);
        currentEnergyStorage = DEFAULT_ENERGY;
    }
    /**
     * Creates a new task generator with the given task probability, and a random seed.
     *
     * @param taskProbability The probability between 0 and 1 that a task will be generated in any hour.
     */
    public TaskGenerator(double taskProbability){
        this.taskProbability = taskProbability;
        this.rand = new Random();
        currentEnergyStorage = DEFAULT_ENERGY;
    }
    /**
     * Returns a new Task with the given parameters.
     *
     * @param hourCreated hour that the Task was created.
     * @param taskType type of the Task
     * @param taskDescription the Task's description
     * @return A new task.
     */
    @Override
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {

        Task task = new Task(hourCreated, taskType, taskDescription);
        task.setPriority(0);
        return task;
    }
    /**
     * Decreases the current energy storage by the given task type's energy per hour.
     *
     * @param taskType - the type of Task
     */
    @Override
    public void decrementEnergyStorage(Task.TaskType taskType) {

        currentEnergyStorage -= taskType.getEnergyPerHour();
    }
    /**
     * Resets the current energy storage to the default energy level.
     */
    @Override
    public void resetCurrentEnergyStorage() {

        currentEnergyStorage = DEFAULT_ENERGY;
    }
    /**
     * Returns the current energy storage.
     * @return the current energy storage.
     */
    @Override
    public int getCurrentEnergyStorage() {
        return currentEnergyStorage;
    }

    /**
     * Sets the current energy storage.
     *
     * @param newEnergyNum number to set the energy
     */
    @Override
    public void setCurrentEnergyStorage(int newEnergyNum) {

        this.currentEnergyStorage = newEnergyNum;
    }

    /**
     * Randomly returns true if a new task should be generated--with a probability equal to taskProbability.
     *
     * @return true if a new task should be generated.
     */
    @Override
    public boolean generateTask() {
        double randValue = rand.nextDouble();

        return randValue < taskProbability;
    }


    /**
     * Determines if the player should pass out or die performing the given task, based on the given random value.
     *
     * @param task Any task.
     * @param unluckyProbability A uniformly random value between 0 and 1.
     * @return 2 If the player should die, 1 if the player should pass out, and 0 otherwise.
     */
    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        if (unluckyProbability <= task.getTaskType().getPassingOutProbability()){
            if (unluckyProbability <= task.getTaskType().getDyingProbabilityProbability() && task.getTaskType() == TaskInterface.TaskType.MINING){
                currentEnergyStorage = (int)(currentEnergyStorage * 0.25);
                task.setPriority(0);
                return 2;
            } else {
                currentEnergyStorage = (currentEnergyStorage/2);
                return 1;
            }
        }
        return 0;
    }


    /**
     * Creates a string containing a task's information.
     *
     * @param task  the Task
     * @param taskType the Task's type
     * @return
     */
    public String toString(Task task, Task.TaskType taskType) {
        if(taskType == Task.TaskType.MINING) {
            return "     Mining " + task.getTaskDescription() + " at " +
                    currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        if(taskType == Task.TaskType.FISHING) {
            return "     Fishing " + task.getTaskDescription() + " at " +
                    currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
        }
        if(taskType == Task.TaskType.FARM_MAINTENANCE) {
            return "     Farm Maintenance " + task.getTaskDescription() + " at "
                    + currentEnergyStorage + " energy points (Priority:" + task.getPriority()
                    +")";
        }
        if(taskType == Task.TaskType.FORAGING) {
            return "     Foraging " + task.getTaskDescription() + " at " +
                    currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
        }
        if(taskType == Task.TaskType.FEEDING) {
            return "     Feeding " + task.getTaskDescription() + " at " +
                    currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        if(taskType == Task.TaskType.SOCIALIZING) {
            return "     Socializing " + task.getTaskDescription() + " at " +
                    currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
        }
        else { return "nothing to see here..."; }
    }
}


