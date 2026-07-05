public class Solution {
    public int solve(int[] A) {
        int n = A.length;
        int[] ps_even = new int[n];
        ps_even[0] = A[0];
        for(int i = 1; i < n; i++){
            if(i%2 == 0){
                ps_even[i] = ps_even[i-1] + A[i];
            }else{
                ps_even[i] = ps_even[i-1];
            }
        }
        int[] ps_odd = new int[n];
        ps_odd[0] = 0;
        for(int i = 1; i < n; i++){
            if(i%2 != 0){
                ps_odd[i] = ps_odd[i-1] + A[i];
            }else{
                ps_odd[i] = ps_odd[i-1];
            }
        }
        int sum_even = 0;
        int sum_odd = 0;
        int special_index = 0;
        for(int i = 0; i < n; i++){
           if(i == 0){
               sum_even = ps_odd[n-1] - ps_odd[i];
               sum_odd = ps_even[n-1] - ps_even[i];
           }else{
               sum_even = ps_even[i-1] + (ps_odd[n-1] - ps_odd[i]);
               sum_odd = ps_odd[i-1] + (ps_even[n-1] - ps_even[i]);
           }
           if(sum_even == sum_odd){
               special_index++;
           }
        }
        return special_index;
    }
}
