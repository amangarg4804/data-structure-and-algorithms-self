package algorithms.interviews.bp;

import java.util.ArrayList;
import java.util.List;

//You are given a notice board with length n.
//You need to write 2 funtions.
//1. add_notice(left, right): in this function, you need to add a notice to the notice board in the co-ordinates given as input.
//                            New notice can partially or completely overlap previous notice on the board.
//                            The user will call this method multiple times and add different notices on the board at various places.
//2. get_visible_notices(): After all the notices are applied, using the above method, the user will call this method to get all the notices which will be visible at the end.
//                          Here, you need to return fully exposed as well as partially exposed notices.
//
//eg. if the user gives us notices like this: (5,15), (16, 18), (12, 20) -> then, the second notice(16,18) will be overlapped by the third notice (12,20) and thus, it will not be visible.
//and first notice will be partially overlapped. Thus, we should return 2 as an answer
public class VisibleNotices {
    private List<Notice> notices = new ArrayList<>();
    private void addNotice(int left, int right) {
//        if(visible.isEmpty()) {
//            visible.add(new Notice(left, right));
//            return;
//        }
        // which notices can overlap?
        // If notice1's left is less than notice2's left, notice2 can't hide notice1
        int insertIndex =0;
        for(; insertIndex < notices.size(); insertIndex++) {
            Notice currentNotice = notices.get(insertIndex);
            if(currentNotice.left >=left) {
                break;
            }
        }
        notices.add(insertIndex, new Notice(left, right));

        // remove hidden notices
        int checkIndex = insertIndex + 1;
        while (checkIndex < notices.size()) {
            if(notices.get(checkIndex).right <= right) {
//                visible.remove(checkIndex); // (5,15),  (12, 20) (16, 18),  // remove is O(n), so time complexity becomes O(n^2). It is also possible that we want to extend this class in future to support remove
                notices.get(checkIndex).visible = false;
            } else {
                break;
            }
            checkIndex++;
        }
    }
    private int getVisibleCount() {
        return (int) notices.stream().filter(notice -> notice.visible).count();
    }

    public static void main(String[] args) {
        VisibleNotices visibleNotices = new VisibleNotices();
        visibleNotices.addNotice(5, 15);
        visibleNotices.addNotice(16, 18);
        visibleNotices.addNotice(12, 20);
        System.out.println(visibleNotices.getVisibleCount());
    }

    private static class Notice {
        int left;
        int right;
        boolean visible;

        public Notice(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Notice{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
