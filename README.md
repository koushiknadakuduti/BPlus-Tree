# BPlus-Tree
This work is a submission to "COP5536: Advanced Data Structures" class at University of Florida.

Problem description
Develop and test a class B+Tree to store pairs of the form (key, value). For this project you are to implement a memory resident B+tree (i.e., the entire tree resides in main memory). Your implementation must be able to store multiple pairs that have the same key (i.e., duplicates). The leaves should be linked into a doubly linked list to support efficient range retrieval. The supported operations are:
1. Initialize(m): create a new order m B+Tree
2. Insert (key, value)
3. Search (key) : returns all values associated with the key
4. Search (key1,key2): returns (all key value pairs) such that key1 <= key <= key2.
