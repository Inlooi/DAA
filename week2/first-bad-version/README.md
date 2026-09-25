#### First Bad Version



Find the first bad version in a sequence where every version after a bad one is also bad, using the fewest possible calls to isBadVersion(version).



#### Problem



Given n versions \[1, 2, ..., n], once a version is bad, all following versions are bad too. Find the first bad version.



#### Approach: Binary Search



Since the versions follow a good, good, ..., bad, bad pattern, we can binary search instead of checking one by one.



1. Start with the full range lo = 1, hi = n.
2. Check the middle version:
3. If it's bad, the first bad version is at mid or earlier → move hi = mid.
4. If it's good, the first bad version is after mid → move lo = mid + 1.
5. Repeat until lo == hi. That's the answer.



This runs in O(log n) calls instead of O(n).

