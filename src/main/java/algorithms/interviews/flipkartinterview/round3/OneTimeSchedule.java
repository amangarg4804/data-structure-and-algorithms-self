package algorithms.interviews.flipkartinterview.round3;

import java.time.Instant;

public class OneTimeSchedule implements Schedule{
    private final Instant startAt;

    public OneTimeSchedule(Instant startAt) {
        if (startAt.isBefore(Instant.now())) {
            throw new IllegalArgumentException("now allowed to have a schedule before current time");
        }
        this.startAt = startAt;
    }

    public Instant getStartAt() {
        return startAt;
    }
}
