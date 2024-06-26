import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task>
    {
        private String owner;
        private String description;
        private LocalDate deadline;

        // Constructor
    public Task(String owner, String description, LocalDate deadline) {
        if (deadline.compareTo(LocalDate.now()) <= 0) {
            throw new IllegalArgumentException("Deadline must be in the future");
        }
        this.owner = owner;
        this.description = description;
        this.deadline = deadline;
    }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getDeadline() {
            return deadline;
        }


        public void setDeadline(LocalDate deadline) {
            if (deadline.compareTo(LocalDate.now()) <= 0) {
                throw new IllegalArgumentException("Deadline must be in the future");
            }
            this.deadline = deadline;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Task task = (Task) o;
            return Objects.equals(owner, task.owner) && Objects.equals(description, task.description) && Objects.equals(deadline, task.deadline);
        }

        @Override
        public int hashCode() {
            return Objects.hash(owner, description, deadline);
        }

        @Override
        public String toString() {
            return "Task{" +
                    "owner='" + owner + '\'' +
                    ", description='" + description + '\'' +
                    ", deadline=" + deadline +
                    '}';
        }

        /**
         * Compares this task with the specified task for natural order.
         * Returns a negative integer, zero, or a positive integer if the task's deadline is before,
         * equal to, or after the specified task's deadline.
         *
         * @param otherTask the task to be compared
         * @return a negative integer, zero, or a positive integer if the task's deadline
         *         is before, equal to, or after the specified task's deadline
         */
        @Override
        public int compareTo(Task otherTask) {
            return this.deadline.compareTo(otherTask.deadline);
        }
    }
