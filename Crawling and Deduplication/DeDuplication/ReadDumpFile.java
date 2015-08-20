import java.util.*;
import java.io.*;
public class ReadDumpFile
{
    private String dumpFileName;
    private String index="index.txt";
    public BufferedWriter fw;

    public static void main(String arg[]) {
	Scanner sc=new Scanner(System.in);
	if(arg.length==0) {
	    System.out.println("enter the file name as argument");
	}
	//System.out.println("Enter file name:");
	//String f=sc.next();
	String f=arg[0];		
	ReadDumpFile rdf=new ReadDumpFile();
	//rdf.dumpFileName=f;
	rdf.setFileName(f);
	// code for printing
	int counter=0;
	ArrayList<URLData> al=(ArrayList<URLData>)rdf.getData();
	for(URLData ud: al) {
	    System.out.println("URL: "+ud.getURL());
	    System.out.println("Data\n:"+ud.getData());
	    System.out.println("------------------------------------------------------------------");
	    if(++counter==2)
		break;
	}
	rdf.WriteToFile("url1",true);
	rdf.WriteToFile("url2",false);
    }

    /*
     * @param: dump file name as a string
     * @return: boolean value whether the filename is accepted or not
     */
    public boolean setFileName(String filename) {
	if(filename!=null && filename.length()!=0) {
	    dumpFileName=filename;
	    return true;
	}
	else
	    return false;
    }

    /*
     * This method return an ArrayList<URLData>.
     *@param: ideally it should be file name to retrieve the dump from.
     *@return:returns an ArrayList of <URLData>
     *@see: URLData class
     */
    public List<URLData> getData() {
	BufferedReader br=null;
	List<URLData> urldata=new ArrayList<URLData>();
	try {
	    br=new BufferedReader(new FileReader(dumpFileName));
	    String cr;//currentLine
	    String cURL;//current URL
	    URLData udata=null;
	    while((cr=br.readLine())!=null) {
		cr=cr.trim();
		if(cr.length()==0)
		    continue;
		if(cr.contains("URL::")) {
		    if(udata!=null)
			urldata.add(udata);
		    
		    udata=new URLData();
		    int index=cr.indexOf("URL::");
		    cr=cr.substring(index).trim();
		    cURL=cr;
		    udata.setURL(cURL);
		    continue;
		}
		if(cr.contains("Content:")||cr.contains("Recno:")||cr.contains("Version:")||cr.contains("url:")||cr.contains("base:")||cr.contains("contentType:")||cr.contains("metadata:")) {
		    continue;
		} else {
		    udata.appendData(cr);	
		}
	    }
	    return urldata;
	}
	catch(IOException e) {
	    e.printStackTrace();
	}
	finally {
	    try {
		if (br != null)
		    br.close();
	    } 
	    catch (IOException ex) {
		ex.printStackTrace();
	    }	    
	}
	return urldata;
    }
	
    public boolean WriteToFile(String url, boolean val) {
	try {
	    fw=new BufferedWriter(new FileWriter(index,true));
	    fw.write(url+"|||"+val+"\n");
	    fw.close();
	    return true;
	}
	catch(IOException e) {
	    e.printStackTrace();
	}
	return false;	
    }

    public boolean WriteToSpecificFile(String filename,String data) {
	try {
	    BufferedWriter bw=new BufferedWriter(new FileWriter(filename,true));
	    bw.write(data+"\n");
	    bw.close();
	    return true;
	}
	catch(IOException e) {
	    e.printStackTrace();
	}
	return false;	
    }

}