public class URLData
{
	private String url;
	private StringBuffer data;

	public URLData(String url,StringBuffer urldata)
	{
		this.url=url;
		data=urldata;
	} 
	public URLData()
	{
		url=null;
		data=new StringBuffer();
	}
	public boolean setURL(String url)
	{
		if(url==null||url.trim().length()==0)
			return false;
		this.url=url;
		return true;
	}
	public String getURL()
	{
		return url;
	}
	public boolean appendData(String data)
	{
		if(data!=null && data.trim().length()!=0)
		{
			this.data.append(data);
			return true;
		}
		return false;
	}
	public String getData()
	{
		if(data!=null)
			return data.toString();
		else
			return null;
	}
	/*
	This should return the data in 
	Url
	Data format
	one can skip the first line to get to the data automatically.
	*/
	public String flattenOut(URLData obj)
	{
		return obj.getURL()+"\n"+obj.getData().toString();
	}
}