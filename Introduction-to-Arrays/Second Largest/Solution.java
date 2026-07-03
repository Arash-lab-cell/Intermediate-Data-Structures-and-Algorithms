public class Solution {
    public int solve(int[] A) {
        int max = Integer.MIN_VALUE;
        int sec_max = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            if(A[i] > max){
                sec_max = max;
                max = A[i];
            }else if(A[i] > sec_max && A[i] < max){
                sec_max = A[i];
            }
        }
        if(sec_max == Integer.MIN_VALUE){
            return -1;
        }else {
            return sec_max;
        }
