public class Solution {
    public int solve(int[] A) {
        int max = A[0];
        for(int i = 0; i < A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        int freq = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == max){
                freq++;
            }
        }
        return A.length - freq;
    }
}
