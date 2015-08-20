/*http://localhost:8983/solr/collection1/select?q=*%3ADo+rising+sea+levels+affect+animals%3F&sort=score+asc&fl=title%2Cscore%2Ccontent-type&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content
*/
/*
http://localhost:8983/solr/collection1/select?q=*%3Ado+we+have+natural+resources%3F&sort=pagerank+desc&rows=20&fl=title%2C+description%2C+content_type%2C+pagerank%2C+attr_content&wt=json&indent=true&defType=dismax
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class QueriesURLGenerator
{
	

	public static void main(String arg[])
	{

		try
		{

			//String baseurl="http://localhost:8983/solr/collection1/select?q="+URLEncoder.encode("*:");
			String PREURL="http://localhost:8983/solr/collection1/select?q="+URLEncoder.encode("*:");

			String content_based_post_string="&sort=score+desc&fl=*%2Cscore&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content";

			/*String content_based_post_string="&sort=score+desc&fl=title%2Cscore%2Ccontent-type%2C+attr_content&wt=json&indent=true&defType=dismax&qf=title^2+keywords^2+content";*/

			String link_based_post_string="&sort=pagerank+desc&rows=20&fl=*%2Cpagerank&wt=json&indent=true&defType=dismax";

			/*String link_based_post_string="&sort=pagerank+desc&rows=20&fl=title%2C+description%2C+content_type%2C+pagerank%2C+attr_content&wt=json&indent=true&defType=dismax";*/

			//Write the queries here.
			String queries[]=new String[2];
			queries[0]="Does rising sea level affect animals?";
			queries[1]="What kind of natural resources are there?";


			int counter=1;
			//make output files for URLS for content based.
			for(int i=0;i<queries.length;i++)
			{
				String preUrl=PREURL;
				preUrl+=URLEncoder.encode(queries[i]);
				preUrl+=content_based_post_string;
				System.out.println("URl made:"+ preUrl);

				String filename="Query"+counter+".txt";
				BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
				bw.write(preUrl);
				bw.flush();
				bw.close();
				counter++;

			}

			//make output files for URLS for link based.
			for(int i=0;i<queries.length;i++)
			{
				String preUrl=PREURL;
				preUrl+=URLEncoder.encode(queries[i]);
				preUrl+=link_based_post_string;
				System.out.println("URl made:"+ preUrl);
				String filename="Query"+counter+".txt";
				BufferedWriter bw=new BufferedWriter(new FileWriter(filename));
				bw.write(preUrl);
				bw.flush();
				bw.close();
				counter++;

			}

			/* Do not call solr via Java. That code has been shifted to bash file */
			/*
			URL url = new URL(preUrl);
	    	URLConnection conn = url.openConnection();
	    	conn.setDoOutput(true);
	    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    	wr.write(preUrl);
	    	wr.flush();

	    	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	String line;
	    	StringBuffer sb=new StringBuffer();
	    	while ((line = rd.readLine()) != null) {
	      		sb.append(line);
	    	}
	    	//call output display function
	    	System.out.println("\n******************** Final OUTPUT String ***************");
	    	System.out.println(sb.toString());
	    	wr.close();
	    	rd.close();*/

    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }


	}

}