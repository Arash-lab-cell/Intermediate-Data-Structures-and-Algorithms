public class Solution {
    public int solve(int[] A) {
        int min = A[0];
        int max = A[0];
        for(int i = 0; i < A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
            if(A[i] < min){
                min = A[i];
            }
        }
        int max_i = -1; 
        int min_i = -1;
        int sub_ar = A.length;
        for(int i = 0; i < A.length; i++){
            int diff = 0;
            if(A[i] == max){
                max_i = i;
            }
            if(A[i] == min){
                min_i = i;
            }
            if(max_i != -1 && min_i != -1){
                  if(max_i > min_i){
                diff = max_i - min_i + 1;
            }else {
                diff = min_i - max_i + 1;
            }
            if(diff < sub_ar){
                sub_ar = diff;
            }
            }
        }
        return sub_ar;
        }
    }
