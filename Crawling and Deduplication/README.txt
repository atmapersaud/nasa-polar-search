README.txt

We have done a static analysis of the crawl data which we got from crawl 1. We then made a list of urls that we are not supposed to crawl.
This list is consulted by the URL filter plugin when it runs and checks if the current URL is in the list and discards it if it is.

How to run the code to detect duplicate urls.
The java files are present in the DeDuplication directory.
1)It takes the input as dump file from the crawl data. We have dumped the file so that it has the content data only.
2)Run the Deduplicator.java file and give the dump file as input.
3)This will produce 2 files that are "ExactDuplicates.txt" and "NearDuplicates.txt" which contain the respective Urls.

We tried an alternative algorithm to detect the near and the exact duplicates.
To run this algo, run the Tester.java file. This also takes as the input as dump file.

The difference between the 2 different approaches is that in one method we use the same algorithm for near duplicates and exact duplicates menthod by keeping the threshold as 100% to detect exact duplicates.(Deduplicator.java)
While in the second approach we use the different algorithms to detect the near duplicates and exact duplicates.(Tester.java)
  
