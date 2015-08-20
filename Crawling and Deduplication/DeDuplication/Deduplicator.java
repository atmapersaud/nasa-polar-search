import java.util.*;
import java.io.*;
class Deduplicator
{
    public static void main(String arg[])
    {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter file name:");
	String f=sc.next();
	//ReadDumpFile rdf=new ReadDumpFile();		
	DocHasher rdf = new DocHasher();
	rdf.setFileName(f);
	
	ArrayList< Set<Integer> > minHashes = (ArrayList< Set<Integer> >)rdf.readAndHashDocs();

	//calling near duplicates code
	NearDeduplicator ndd=new NearDeduplicator();
	ArrayList<String> eddlist = (ArrayList<String>)ndd.detectExactDuplicates(rdf.getDocUrls(), minHashes);
	ArrayList<String> nddlist = (ArrayList<String>)ndd.detectNearDuplicates(rdf.getDocUrls(), minHashes);
	int counter=0;
	System.out.println("Number of exact duplicates: " + eddlist.size());
	for(String s: eddlist) {
	    System.out.println("Exact duplicates: " + s);
	    counter++;
	    rdf.WriteToSpecificFile("ExactDuplicates.txt", s)
	}
	System.out.println("Number of near duplicates: " + (nddlist.size() - eddlist.size()) );	
	for(String s: nddlist) {
	    System.out.println("Near duplicates: " + s);
	    counter++;
	    rdf.WriteToSpecificFile("NearDuplicates.txt", s);
	}
	System.out.println("Counter: "+counter);	    
    }
}