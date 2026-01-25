public class minimumPrefixLength (int[] nums){
    int n = nums.length;

        if(n==1){
        return 0;
    }
    int ans =0;

        for(int i=n-2 ; i>=0;i--){
        if(nums[i] >= nums[i+1]){
            ans = i+1;
            break;
        }
    }
        return ans;
}
