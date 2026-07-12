public class Solution {
    public int colorful(int A) {
        ArrayList <Integer> al = new ArrayList<>();
        while(A > 0){
            al.add(A%10);
            A = A/10;
        }
        int l = 0;
        int r = al.size() - 1;
        while(l < r){
            int temp = al.get(l);
            al.set(l, al.get(r));
            al.set(r, temp);
            l++;
            r--;
        }
        ArrayList<Long> product = new ArrayList<>();
        for(int i = 0; i < al.size(); i++){
            Long prod = 1L;
            for(int j = i; j < al.size(); j++){
              prod = prod * al.get(j);
              if(product.contains(prod)){
                  return 0;
              }else{
                  product.add(prod);
              }

            }
        }
        return 1;
    }
}
