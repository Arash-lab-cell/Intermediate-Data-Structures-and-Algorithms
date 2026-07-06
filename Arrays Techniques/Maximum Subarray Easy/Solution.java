public class Solution {
    public int maxSubarray(int A, int B, int[] C) {
        int n = C.length;
        int ans = 0;
        for(int s = 0; s < n; s++){
            int sum = 0;
            for(int e = s; e < n; e++){
                sum = sum + C[e];
                if(sum <= B){
                    ans = Math.max(ans, sum);
                }
            }
        }
        return ans;
    }
}
