package task_management;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class User {
    String name;
    int age;
    String uid;
    String email;
}

enum TaskStatus {
    NEW, ACTIVE, COMPLETED, SUSPENDED;

    TaskStatus updateStatusToNext(TaskStatus taskStatus) {
        if(taskStatus == TaskStatus.NEW || taskStatus == TaskStatus.SUSPENDED) return TaskStatus.ACTIVE;
        return TaskStatus.COMPLETED;
    }

}

class Task {
    String title;
    String description;
    Date creationTime;
    Date dueDate;
    TaskStatus taskStatus;
    Task parentTask;
    List<Task> childTasks;

    Task(TaskBuilder taskBuilder) {
        this.title = taskBuilder.title;
        this.description = taskBuilder.description;
        this.creationTime = taskBuilder.creationTime;
        this.dueDate = taskBuilder.dueDate;
        this.taskStatus = TaskStatus.NEW;
        this.parentTask = taskBuilder.parentTask;
        this.childTasks = new ArrayList<Task>();
    }


    static class TaskBuilder {
        String title;
        String description;
        Date creationTime;
        Date dueDate;
        TaskStatus taskStatus;
        Task parentTask;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setCreationTime(Date creationTime) {
            this.creationTime = creationTime;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public void setParentTask(Task parentTask) {
            this.parentTask = parentTask;
        }

        public Task build() {
            return new Task(this);
        }
    }


}