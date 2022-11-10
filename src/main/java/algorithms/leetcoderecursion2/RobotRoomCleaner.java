package algorithms.leetcoderecursion2;


interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
     boolean move();
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
     void turnLeft();
     void turnRight();
     void clean();
}

//0 means cell is blocked
//1 means cell is accessible. Visit each cell containing 1
//Initial direction of robot is facing up

public class RobotRoomCleaner {
    public void cleanRoom(Robot robot, int row, int col) {

    }
}
