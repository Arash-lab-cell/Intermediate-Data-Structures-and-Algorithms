public class Solution {
    public int solve(int[] A, int B) {
        int n = A.length;
        int count = 0;
        for(int s = 0; s < n; s++){
            int sum = 0;
            for(int e = s; e < n; e++){
                sum = sum + A[e];
                if(sum < B){
                    count++;
                }
            }
        }
        return count;
    }
}
