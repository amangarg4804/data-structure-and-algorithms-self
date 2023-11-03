package algorithms.interviews.flipkartinterview.round3;

import java.time.Duration;
import java.time.Instant;

public class RecurringSchedule implements Schedule{

    private final Instant startAt;
    private final Duration interval;
    private int occurrences;

    public RecurringSchedule(Instant startAt, Duration interval, int occurrences) {
        this.startAt = startAt;
        this.interval = interval;
        this.occurrences = occurrences;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public Duration getInterval() {
        return interval;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
}
