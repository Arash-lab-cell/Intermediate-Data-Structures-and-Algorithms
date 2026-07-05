public class Solution {
    public int maxProfit(final int[] A) {
        if(A.length == 0){
            return 0;
        }
        int min_price = A[0];
        int max_profit = 0;
        int diff = 0;
        for(int i = 0; i < A.length; i++){
            diff = A[i] - min_price;
            if(A[i] < min_price){
                min_price = A[i];
            }
            if(diff > max_profit){
                max_profit = diff;
            }
        }
        return max_profit;
    }
}
