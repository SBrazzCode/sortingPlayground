 <h2>Introduction</h2>
 This is my attempt to remove the bottlenecks in my dual-threaded MergeSort 
 
 *Spoiler* I went further and made it scale to the number of processors on the calling system.
 
 The sortingPlayground started as an assignment for CPRG-311. It is an eclipse project that runs on JRE 1.8+ We needed to write implementations of 6 sorting algorithms:
 
 1. Bubble Sort
 2. Insertion Sort
 3. Selection Sort
 4. MergeSort
 5. Quick Sort
 6. A sorting algorithm of our choice (I chose HeapSort)
 
 I went further an tried to run my mergesort with two threads. This attempt ran slower than my single-threaded implementation. To create a better solution I had to discover cache missing. Now, my MergeSort is cache friendly and creates a thread for each processor on the system. The MergeSort algorithm is the most documented algorithm, for it is the only algorithm that could be valuable to external parties.
 
 <h2>How To Use (Short)</h2>
 To use the sorting algorithms, download Sort.java :: found in sortingPlayground/sortCollection/src/utilities. Sort.java is a standalone file; the majority of the project demonstrates how you could use these sorting algorithms.
 

<h2>How To Use (Long)</h2>

Dependencies: [Eclipse](https://www.eclipse.org/downloads/), [commons-io-2.8.0.jar](https://commons.apache.org/proper/commons-io/download_io.cgi)
1. Clone the Repository
2. (Optional) Create a jar
3. Run with these arguments
`–fpolyfor1.txt –Ta –Sm`

if polyfor1.txt is not in the same directory as the .jar, or it is not directly under the sortCollection project directory, you must provide the absolute path after -f.


<h2>Arguments</h2>

`-f`
Provide the file with shapes to be sorted

`-t`
Provide the property of the shape to be used in comparisons: A for baseArea, H for height, V for Volume

`-s`
Provide the sorting implementation to be used: M for Merge, I for insertion, S for Selection, B for Bubble, Q for Quick and Z for Heap
