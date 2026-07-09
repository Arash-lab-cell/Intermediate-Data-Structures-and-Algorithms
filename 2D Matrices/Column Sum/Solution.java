public class Solution {
    public int[] solve(int[][] A) {
        int n = A[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum = sum + A[i][j];
            }
            ans[j] = sum;
        }
        return ans;
    }
}
