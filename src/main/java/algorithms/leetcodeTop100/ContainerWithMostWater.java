package algorithms.leetcodeTop100;

public class ContainerWithMostWater {

  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length-1;
    int maxArea = 0;
    while (left<right) {
      int currentArea = calculateArea(height, left, right);
      maxArea = Math.max(currentArea, maxArea);
      if(height[left]< height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return maxArea;
  }

  private int calculateArea(int[] height, int left, int right) {
    return Math.min(height[left], height[right]) * (right-left);
  }
}
