package algorithms.flipkartinterview.round3;

import java.time.Duration;
import java.time.Instant;

public class TaskSchedulerTest {
    public static void main(String[] args) throws Exception{
        TaskScheduler taskScheduler = new TaskScheduler(3);
        Task task1 = () -> System.out.println("Hello1");
//        taskScheduler.addTask(oneTimeTask, new OneTimeScheduleOld(LocalDateTime.now().plusSeconds(10)));
        taskScheduler.addTask(task1, new OneTimeSchedule(Instant.now().plusSeconds(10)));
        Task task2 = () -> System.out.println("Hello2");
        taskScheduler.addTask(task2, new RecurringSchedule(Instant.now().plusSeconds(15), Duration.ofSeconds(5), 5));
//        taskScheduler.startExecution();
    }
}
