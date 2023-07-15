package algorithms.flipkartinterview.round3;

import java.time.Instant;

public class ScheduledTask {
    private final Task task;
    private final Schedule schedule;

    private Instant nextExecutionTime = Instant.now();

    public ScheduledTask(Task task, Schedule schedule) {
        this.task = task;
        this.schedule = schedule;
        setNextExecutionTime();
    }
    public void setNextExecutionTime() {
        if (schedule instanceof OneTimeSchedule) {
            OneTimeSchedule oneTimeSchedule = (OneTimeSchedule) schedule;
            nextExecutionTime = oneTimeSchedule.getStartAt();
        }else if (schedule instanceof RecurringSchedule) {
            RecurringSchedule recurringSchedule = (RecurringSchedule) schedule;
            nextExecutionTime = recurringSchedule.getStartAt();
        }
    }
    public void updateNextExecutionTime() {
        if (schedule instanceof OneTimeSchedule) {
            OneTimeSchedule oneTimeSchedule = (OneTimeSchedule) schedule;
            nextExecutionTime = oneTimeSchedule.getStartAt();
        }else if (schedule instanceof RecurringSchedule) {
            RecurringSchedule recurringSchedule = (RecurringSchedule) schedule;
            if (recurringSchedule.getOccurrences() == -1) {//infinite
                nextExecutionTime = nextExecutionTime.plus(recurringSchedule.getInterval());
            } else {
                int occurrences = recurringSchedule.getOccurrences();
                if (occurrences > 1) {
                    nextExecutionTime = nextExecutionTime.plus(recurringSchedule.getInterval());
                    recurringSchedule.setOccurrences(occurrences - 1);
                } else {
                    nextExecutionTime = Instant.MAX; // we don't have to execute this task anymore
                }
            }
        }
    }


    public boolean hasNextExecution() {
        return !nextExecutionTime.equals(Instant.MAX);
    }

    public Task getTask() {
        return task;
    }

    public Instant getNextExecutionTime() {
        return nextExecutionTime;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
