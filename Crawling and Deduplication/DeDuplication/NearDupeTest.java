import java.util.*;
import java.io.*;
class NearDupeTest
{
    public static void main(String arg[])
    {
	NearDeduplicator ndd=new NearDeduplicator();

	String doc1 = "My first name is Atma and my last name is Persaud";
	String doc2 = "His first name is Atma and his last name is Persaud";
	List<Integer> d1mh = ndd.getShingling(doc1);
	List<Integer> d2mh = ndd.getShingling(doc2);
	for (Integer i : d1mh) {
	    System.out.print(i + " ");
	}
	System.out.print("\n");

	for (Integer i : d2mh) {
	    System.out.print(i + " ");
	}
	System.out.print("\n");
    }
}