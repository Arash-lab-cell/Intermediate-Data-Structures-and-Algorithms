public class Solution {
    public int repeatedNumber(int[] A) {
       int n = A.length;
       if(n == 1){
           return A[0];
       }
       int num1 = 0;
       int count1 = 0;
       int num2 = 0;
       int count2 = 0;
       int i = 0;
       while(i < n){
           if(A[i] == num1){
               count1++;
           }else if(A[i] == num2){
               count2++;
           }else if(count1 == 0){
               num1 = A[i];
               count1 = 1;
           }else if(count2 == 0){
               num2 = A[i];
               count2 = 1;
           }else if(A[i] != num1 && A[i] != num2){
               count1--;
               count2--;
           }
           i++;
       }
       int freqNum1 = 0;
       int freqNum2 = 0;
       for(int j = 0; j < n; j++){
           if(A[j] == num1){
               freqNum1++;
           }else if(A[j] == num2){
               freqNum2++;
           }
       }
       if(freqNum1 > n/3){
           return num1;
       }else if(freqNum2 > n/3){
           return num2;
       }else{
           return -1;
       }
    }
}
