Data from the following 3 seeds was crawled using Apache Nutch:

[NASA AMD] (http://gcmd.gsfc.nasa.gov/KeywordSearch/Home.do?Portal=amd&MetadataType=0)

[NSIDC ADE] (http://nsidc.org/acadis/search/)

[NSF ACADIS] (https://www.aoncadis.org/home.htm)

* The configuration files for this crawl as well as rules for URL filtering, can be found in the config and URLFilterPlugin directories.
* The code for finding exact and near duplicates can be found in the DeDuplication directory.
* The main driver is Deduplicator.java. It takes input in the form of a dump file from the crawl data.
* Run the Deduplicator.java file and give the dump file as input.
* This will produce 2 files that are "ExactDuplicates.txt" and "NearDuplicates.txt" which contain the respective URLs.  
