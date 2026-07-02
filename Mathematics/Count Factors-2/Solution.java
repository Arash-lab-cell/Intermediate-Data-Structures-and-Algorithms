public class Solution {
    public int solve(int A) {
        int fact_count = 0;
        for(int i = 1; i*i <= A; i++){
    if (A % i == 0){
        if(i == A/i){
            fact_count++;
        }else{
                fact_count += 2;
            }
        }
        }
        return fact_count;
    }
}
