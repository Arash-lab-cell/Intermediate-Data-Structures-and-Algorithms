public class Solution {
    int[] reverse(int[] A, int i, int j){
        while(i < j){
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
        return A;
    }
    public int[] solve(int[] A, int B) {
        B = B % (A.length);
        reverse(A, 0, (A.length-1));
        reverse(A, 0, (B-1));
        reverse(A, B, (A.length-1));
        return A;
    }
}
