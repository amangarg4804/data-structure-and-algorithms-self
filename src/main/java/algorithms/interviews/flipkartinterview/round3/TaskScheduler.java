package algorithms.interviews.flipkartinterview.round3;


import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class TaskScheduler {
    Queue<ScheduledTask> q = new PriorityQueue<>(Comparator.comparing(ScheduledTask::getNextExecutionTime));

    List<Worker> workers = new ArrayList<>();
    private Thread schedulerThread;
    private Object objLock = new Object();
    private final ReentrantLock lock = new ReentrantLock();
    private boolean isShutDown;

    public TaskScheduler(int workers) {
        for (int i = 0; i < workers; i++) {
            Worker worker = new Worker();
            this.workers.add(worker);
        }
        schedulerThread = new Thread(() -> {
            work();
        });
        schedulerThread.start();
    }

    private void work() {
        while (true) {
            synchronized (objLock) {
                try {
                    while (q.isEmpty()) {
                        objLock.wait();
                    }
                    ScheduledTask scheduledTask = q.peek();
                    Instant current = Instant.now();
                    if (scheduledTask.getNextExecutionTime().isAfter(current)) {
                        Thread.sleep(Duration.between(current, scheduledTask.getNextExecutionTime()).toMillis());
                    } else {
                        ScheduledTask currentTask = q.poll();
                        currentTask.getTask().run();
                        scheduledTask.updateNextExecutionTime();
                        if (currentTask.getSchedule() instanceof RecurringSchedule && scheduledTask.hasNextExecution()) {
                            q.add(scheduledTask);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public void shutdown() {
        lock.lock();
        try {
            this.isShutDown = true;
        } finally {
            lock.unlock();
        }
    }

    public void startExecution() {
        lock.lock();
        try {
            while (!q.isEmpty()) {
                ScheduledTask scheduledTask = q.poll();
                Instant nextExecutionTime = scheduledTask.getNextExecutionTime();
                if (nextExecutionTime.isAfter(Instant.now())) {
                    sleepUntil(nextExecutionTime);
                }
                executeTask(scheduledTask.getTask());
                scheduledTask.updateNextExecutionTime();
                if (scheduledTask.getSchedule() instanceof RecurringSchedule && scheduledTask.hasNextExecution()) {
                    q.add(scheduledTask);
                }

            }
        } finally {
            lock.unlock();
        }
    }

    private void executeTask(Task task) {
        lock.lock();
        try {
            Worker worker = getAvailableWorker();
            if (worker == null) {
                throw new IllegalStateException("No worker available to execute task");
            }
            worker.executeTask(task);
        } finally {
            lock.unlock();
        }
    }

    private void sleepUntil(Instant instant) {
        try {
            Thread.sleep(Duration.between(Instant.now(), instant).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Worker getAvailableWorker() {
        Worker available = null;
        while (available == null) {
            for (Worker worker : workers) {
                if (!worker.isBusy()) {
                    available = worker;
                    break;
                }
            }
            if (available == null) {
                sleepFor(100);
            }
        }
        return available;
    }

    private static void sleepFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void addTask(Task task, Schedule schedule) {
        if(isShutDown) {
            throw new IllegalStateException("can't submit tasks after shutdown");
        }
        synchronized (objLock) {
            q.add(new ScheduledTask(task, schedule));
            objLock.notify();
        }

    }
}



