/**
 * 912. Sort an Array
 * Heap sort: O(n log n) time, O(1) extra space, no built-in sort.
 */
public class Solution {

    public int[] sortArray(int[] nums) {
        int n = nums.length;

        // Phase 1: turn the array into a max-heap (biggest value at index 0).
        // Start from the last parent and go back to the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(nums, i, n);
        }

        // Phase 2: repeatedly move the biggest value to the end of the array,
        // shrink the heap by one, and repair the heap.
        for (int end = n - 1; end > 0; end--) {
            swap(nums, 0, end);
            siftDown(nums, 0, end); // heap size is now "end"
        }
        return nums;
    }

    // Push the value at index i down until both children are not bigger than it.
    private void siftDown(int[] a, int i, int size) {
        while (true) {
            int left = 2 * i + 1;
            if (left >= size) {
                break; // no children -> done
            }
            int bigger = left;
            int right = left + 1;
            if (right < size && a[right] > a[left]) {
                bigger = right;
            }
            if (a[bigger] <= a[i]) {
                break; // heap rule is already fine
            }
            swap(a, i, bigger);
            i = bigger;
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
