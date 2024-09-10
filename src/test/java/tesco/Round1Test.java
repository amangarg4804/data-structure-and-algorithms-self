package tesco;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Round1Test {

    @Test
    void getShiftTimingsOverlapping() {
        Schedule schedule1 = new Schedule(8, 10);
        Schedule schedule2 = new Schedule(9, 11);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        Round1 round1 = new Round1();
        List<Schedule> result = round1.getShiftTimings(schedules);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(8, result.get(0).start);
        Assertions.assertEquals(11, result.get(0).end);
    }

    @Test
    void getShiftTimingsUnsorted() {
        Schedule schedule1 = new Schedule(9, 11);
        Schedule schedule2 = new Schedule(8, 10);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        Round1 round1 = new Round1();
        List<Schedule> result = round1.getShiftTimings(schedules);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(8, result.get(0).start);
        Assertions.assertEquals(11, result.get(0).end);
    }

    @Test
    void getShiftTimingsNonOverlapping() {
        Schedule schedule1 = new Schedule(1, 3);
        Schedule schedule2 = new Schedule(8, 10);
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule1);
        schedules.add(schedule2);
        Round1 round1 = new Round1();
        List<Schedule> result = round1.getShiftTimings(schedules);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(1, result.get(0).start);
        Assertions.assertEquals(3, result.get(0).end);
        Assertions.assertEquals(8, result.get(1).start);
        Assertions.assertEquals(10, result.get(1).end);
    }
}