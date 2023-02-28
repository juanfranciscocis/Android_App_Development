## Multitasking
### Multitasking refers to the ability of a computer or program to perform multiple tasks simultaneously. In Java, there are two main types of multitasking: concurrency and parallelism.

#### `Concurrency` refers to the ability of a program to perform multiple tasks in overlapping time periods. This means that different parts of the program can execute simultaneously, but not necessarily at the same time. For example, a program with multiple threads might switch between threads to allow them all to execute at different points in time.

#### `Parallelism`, on the other hand, refers to the ability of a program to perform multiple tasks at the same time using multiple processors or cores. This means that different parts of the program can execute simultaneously and independently, without needing to switch between threads.

** When working with concurrent programs in Java, there are several potential issues that can arise, including: **

- `Memory sharing`: When multiple threads access the same memory locations, it can lead to race conditions and other synchronization issues.
- `Overhead`: Managing multiple threads and synchronizing their access to shared resources can incur significant overhead, which can impact performance.
- `Trashing`: When threads compete for limited resources, it can lead to excessive context switching and other performance issues.
- `Starvation`: When a thread is unable to acquire a required resource, it can enter a state of starvation and be unable to make progress.
- `Deadlock`: When two or more threads are blocked waiting for resources held by each other, it can lead to a deadlock, where none of the threads can make progress.

## Thread Class
** Implements the Runnable Class in A Class **
```
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running.");
    }
}

```

```
public class Main {
    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
```

## ExecutorService
** We can tell the executor service the number of fixed threads that the commands will execute on **
** example 1 **

```
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(() -> {
    System.out.println("Task 1 executed.");
});
executor.submit(() -> {
    System.out.println("Task 2 executed.");
});
executor.shutdown();
```

** example 2 **

```
ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
executor.scheduleAtFixedRate(() -> {
    System.out.println("Task executed.");
}, 0, 1, TimeUnit.SECONDS);
Thread.sleep(5000);
executor.shutdown();
```

```
This code creates a new ScheduledExecutorService instance using the Executors.newScheduledThreadPool method, which creates a thread pool that can schedule tasks to run at a specific time or on a fixed interval. The scheduleAtFixedRate method is then used to schedule a task to run every second, starting immediately. Finally, the shutdown method is called to shut down the executor service after five seconds of execution.

```

## JavaFX Task

### In JavaFX, the Task class is a subclass of the javafx.concurrent.Service class, which provides a convenient way to perform background tasks that may take a long time to complete without blocking the user interface thread.

## The Task class has two main methods: call() and updateProgress(). The call() method contains the code to execute the task in a background thread, while the updateProgress() method can be used to update the progress of the task and notify the user interface thread of any changes.

**Here is an example of how to use the Task class in JavaFX:**

```
Task<Integer> task = new Task<Integer>() {
    @Override
    protected Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            sum += i;
            updateProgress(i, 10);
        }
        return sum;
    }
};

ProgressBar progressBar = new ProgressBar();
progressBar.progressProperty().bind(task.progressProperty());

task.setOnSucceeded(event -> {
    int sum = task.getValue();
    System.out.println("Sum is: " + sum);
});

Thread thread = new Thread(task);
thread.start();
```


** The Task class in JavaFX provides several other methods besides updateProgress() that you can use to communicate with the user interface thread while the task is running. Here are some examples:**

- `updateMessage()`: This method can be used to update a message that is displayed to the user to provide feedback on the progress of the task. You can call this method inside the call() method to update the message as the task progresses. Here is an example:
```
Task<Integer> task = new Task<Integer>() {
    @Override
    protected Integer call() throws Exception {
        updateMessage("Starting task...");
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            sum += i;
            updateMessage("Calculating sum (" + i + "/10)...");
            updateProgress(i, 10);
        }
        updateMessage("Task completed.");
        return sum;
    }
};

Label messageLabel = new Label();
messageLabel.textProperty().bind(task.messageProperty());

Thread thread = new Thread(task);
thread.start();
```

```
In this example, the updateMessage() method is called several times inside the call() method to update the message displayed in the Label instance bound to the task's message property. This provides feedback to the user on the progress of the task.
```

- `updateValue()`: This method can be used to update a value that is associated with the task. You can call this method inside the call() method to update the value as the task progresses. Here is an example:

```
Task<Integer> task = new Task<Integer>() {
    @Override
    protected Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            sum += i;
            updateValue(sum);
            updateProgress(i, 10);
        }
        return sum;
    }
};

Label valueLabel = new Label();
valueLabel.textProperty().bind(task.valueProperty().asString());

Thread thread = new Thread(task);
thread.start();
```

```
In this example, the updateValue() method is called inside the call() method to update the value associated with the task. This value is then displayed in the Label instance bound to the task's value property.
```



