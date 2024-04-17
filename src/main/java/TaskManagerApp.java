import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class TaskManagerApp {
    public static void main(String[] args) {
        Random random = new Random();
        boolean useRiskyApproach = random.nextBoolean(); // Randomly choose risky or safe approach

        Scanner scanner = new Scanner(System.in);

        // Ask the user for the size of the queue
        System.out.print("Enter the size of the queue: ");
        int maxSize = scanner.nextInt();
        BoundedPriorityQueueSet taskQueue = new BoundedPriorityQueueSet(maxSize, useRiskyApproach);

        int choice;
        do {
            // Display menu
            System.out.println("\nMenu:");
            System.out.println("1. Add a new Task");
            System.out.println("2. View the next Task");
            System.out.println("3. Mark the next Task as done");
            System.out.println("4. View the number of Tasks remaining");
            System.out.println("5. View the space remaining in the queue");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a new Task
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter owner: ");
                    String owner = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter deadline (YYYY-MM-DD): ");
                    String deadlineStr = scanner.next();
                    LocalDate deadline = LocalDate.parse(deadlineStr);
                    Task newTask = new Task(owner, description, deadline);
                    taskQueue.add(newTask);
                    break;
                case 2:
                    // View the next Task
                    Task nextTask = taskQueue.peek();
                    if (nextTask != null) {
                        System.out.println("Next Task: " + nextTask);
                    } else {
                        System.out.println("No Task available.");
                    }
                    break;
                case 3:
                    // Mark the next Task as done
                    Task doneTask = taskQueue.poll();
                    if (doneTask != null) {
                        System.out.println("Task marked as done: " + doneTask);
                        if (taskQueue.isEmpty()) {
                            System.out.println("Congratulations! All tasks completed!");
                        }
                    } else {
                        System.out.println("No Task available.");
                    }
                    break;
                case 4:
                    // View the number of Tasks remaining
                    System.out.println("Number of Tasks remaining: " + taskQueue.size());
                    break;
                case 5:
                    // View the space remaining in the queue
                    System.out.println("Space remaining in the queue: " + (maxSize - taskQueue.size()));
                    break;
                case 6:
                    // Exit the program
                    try {
                        writeTasksToF(taskQueue);
                    } catch (IOException e) {
                        System.out.println("Error writing tasks to file: " + e.getMessage());
                    }

                    // Ask user to guess the approach
                    System.out.print("Guess the approach used (risky or safe): ");
                    String guess = scanner.next().toLowerCase();
                    if (guess.equals("risky") && useRiskyApproach || guess.equals("safe") && !useRiskyApproach) {
                        System.out.println(" You guessed correctly!");
                    } else {
                        System.out.println("Sorry, your guess was wrong.");
                    }

                    System.out.println("Exiting the program swiftly");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void writeTasksToF(BoundedPriorityQueueSet taskQueue) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"))) {
            Task task;
            while ((task = taskQueue.poll()) != null) {
                writer.println(task.toString());
            }
        }
    }
}
