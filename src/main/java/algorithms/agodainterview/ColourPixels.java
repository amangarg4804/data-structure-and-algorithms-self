package algorithms.agodainterview;

// import java.io.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;
//
///**
//Color function:
//--- The user selects a color, then clicks on 1 pixel, and the closed shape around that pixel is colored
//**/
//
//class Solution {
//
//    // 0 is white
//    // 1 is yellow
//    // 2 is black
//    int[][] canvas = new int[1000][1000];
//
//    /**
//
//    0 0 0 0 0 0 0 0
//    0 0 0 0 0 0 0 0
//    0 2 2 2 2 0 0 0
//    0 2 0 0 2 0 0 0
//    0 2 2 2 0 0 0 0
//    0 0 0 0 0 0 0 0
//
//    **/
//
//    public void color(int x, int y, int newColor, int firstClickedColor) {
//        if(x< 0 || y< 0  || x > canvas.length || y > canvas[0].length) {
//            return;
//        }
//
//        if(canvas[x][y] == firstClickedColor)
//            return;
//
//        canvas[x][y] = newColor;
//
//
//        async( color(x+1,y, newColor, firstClickedColor); )
//        async( color(x,y+1, newColor, firstClickedColor); )
//        async( color(x-1,y, newColor, firstClickedColor); )
//        async( color(x-1,y-1, newColor, firstClickedColor); )
//
//    }
//
//
//
//}
public class ColourPixels {

}
