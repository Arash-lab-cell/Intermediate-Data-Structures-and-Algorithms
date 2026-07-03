public class Solution {
    public int solve(int[] A) {
        int tot_sec = 0;
        Arrays.sort(A);
        for(int i = A.length-2; i >= 0; i--){
            tot_sec = tot_sec + (A[A.length-1] - A[i]);
        }
        return tot_sec;
    }
}
