##### Binary Search

A simple Java implementation of binary search. It searches for a number in an array sorted in ascending order and returns its index.



##### How it works

The algorithm divides the array in half at each step and compares the middle element with the target number:

&#x20;- if they match, it returns the index;

&#x20;- if the middle element is less than the target, it searches the right half;

&#x20;- if it is greater, it searches the left half.

Algorithm complexity: O(log n).



#### Requirements

&#x20;- The array must be sorted in ascending order.

&#x20;- JDK 8+.



#### Usage

```java
int\[] arr = {1, 3, 5, 7, 9, 11};
int result = binarySearch(arr, 7); // return 3
```



#### Return value

&#x20;- The index of the found number if it exists in the array.

&#x20;- It will return "-1" if the number is not found.

