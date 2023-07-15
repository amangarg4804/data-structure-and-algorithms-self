package algorithms.flipkartinterview.round3;

public class Worker {
    private boolean isBusy;

    public boolean isBusy() {
        return isBusy;
    }
    public void executeTask(Task task) {
        isBusy = true;
        task.run();
        isBusy = false;
    }
}
