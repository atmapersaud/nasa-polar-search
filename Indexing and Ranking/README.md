For this project, the crawled data was indexed using Apache Solr.  
`schema.xml` contains configuration for Solr.

Documents were ranked using PageRank, with a slight modification. The links in the graph of documents were based on coinciding geographical locations rather than in- and out-links on web pages. `GetLocations.java` is the main driver for obtaining latitude and longitude information for documents, using the CLAVIN library. `ranks2.txt ` shows example documents and their scores from our link-based ranking algorithm.