import java.util.*;
import java.io.*;
class Tester
{
    public static void main(String arg[])
    {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter file name:");
	String f=sc.next();	
	ReadDumpFile rdf=new ReadDumpFile();		
	rdf.setFileName(f);
	ArrayList<URLData> al=(ArrayList<URLData>)rdf.getData();
	System.out.println("Sending to Exact Duplicates" + al.size());
	ArrayList<URLData> al2=new ArrayList<URLData>();
	for(URLData ud: al) {
	    System.out.println("URL: "+ud.getURL());
	    try {
		boolean val=ExactDuplicates.checkExactDuplicates(ud.getData());
		System.out.println("Is it an exact duplicate: "+ val);
		System.out.println("------------------------------------------------------------------");
		//rdf.WriteToFile(ud.getURL(),val);
		if(val)
		{
			rdf.WriteToSpecificFile("ExactDuplicates.txt",ud.getURL());
		}
		if(val==false) {

		    al2.add(ud);
			}
	    }
	    catch(Exception e) {
		e.printStackTrace();
		System.out.println("Exception in checkExactDuplicates menthod");
	    }	    
	}
	//calling near duplicates code
	NearDeduplicator ndd=new NearDeduplicator();
	ArrayList<String> nddlist=(ArrayList<String>)ndd.detectNearDuplicates(al2);
	int counter=0;
	System.out.println("Size of near duplicates: "+nddlist.size());	
	for(String s: nddlist)
	{
	    System.out.println("Near duplicates: "+s);
	    counter++;
	    rdf.WriteToSpecificFile("NearDuplicates.txt",s);
	}
	System.out.println("Counter: "+counter);	    
    }
}