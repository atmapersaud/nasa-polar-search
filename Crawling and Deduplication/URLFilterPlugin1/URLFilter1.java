package org.apache.nutch.urlfilter.duplicates;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.nutch.net.URLFilter;
import org.apache.log4j.Logger;
import java.io.*;

class URLFilter1 implements URLFilter
{
	public void setConf(Configuration conf)
	{}
	public Configuration getConf()
	{
		return new Configuration();
	}
	public String filter(String urlString)
	{
		String filename="ExactDuplicates.txt";
		String cr;
		
		try
		{
			BufferedReader br=new BufferedReader(new FileReader(filename));
			while((cr=br.readLine())!=null)
			{
				if(urlString.equalsIgnoreCase(cr.trim()))
					return null;
			}
			return urlString;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}
}
