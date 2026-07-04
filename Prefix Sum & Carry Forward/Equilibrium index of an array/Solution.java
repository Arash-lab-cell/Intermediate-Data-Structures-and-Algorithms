public class Solution {
    public int solve(int[] A) {
        int sum = 0;
        for(int i = 0; i < A.length; i++){
            sum = sum + A[i];
        }
        int [] ps = new int [A.length];
        ps[0] = A[0];
        for(int i = 1; i < A.length; i++){
            ps[i] = ps[i-1] + A[i];
        }
        int left_sum = 0;
        int right_sum = 0;
        for(int i = 0; i < A.length; i++){
            if(i == 0){
                left_sum = 0;
                right_sum = sum - A[i];
            }else{
            left_sum = ps[i-1];
            right_sum = sum - (ps[i-1] + A[i]);
            }
            if(left_sum == right_sum){
                return i;
            }
        }
        return -1;
    }
}
