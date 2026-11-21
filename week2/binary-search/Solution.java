class Solution {
    public static void main (String[] args){
        int[] nums = {-1,0,3,5,9,12};
        search(nums, 9);
    }
    static int search(int[] nums, int target) {
        int left = 0, right = nums.length -1;
        while(left <= right){
            int mid = left +(right - left) / 2;
            if(nums[mid] == target){
                System.out.println(target+" exists in nums and its index is "+ mid);
                return mid;
            }else if (nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid -1;
            }
        }
        System.out.println(target+" does not exist in nums so return -1");
        return -1;
    }
}