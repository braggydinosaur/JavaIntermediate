
/**
 * Write a description of class ArrayFun here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArrayFun
{
    public static void main(String[] args) {
       int[] array = { 1, 5, 10, 2};
       System.out.println(array[0]);
       
       int x = 0;
       while ( x < array.length ) {
            System.out.println(array[x]);
            x = x + 1;   
        }
        int[][] grid = {{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}};
        for (int y = 0 ; y < 3 ; y++) {
            for (int x = 0 ; x < 4 ; x++) {
                System.out.println(grid[x][y]);
            }
        
   