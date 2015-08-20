import java.security.MessageDigest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;

class ExactDuplicates {
	public static void main(String[] args) throws Exception{
        String str = "dvhbvldfvbdfhvb;daifgvdb;fvdfa;iuvbifa;bvazvf fdb";
		System.out.println(str); // Display the string.

        boolean flag = checkExactDuplicates(str); //

        System.out.println(flag);
	}

	public static boolean checkExactDuplicates(String doc) throws Exception {
        System.out.println(doc);
        int L = doc.length(); //L stores length of document
        int N = 100; //N is the number of intervals
        int k = 100; //k is the number of words at each interval

        StringBuffer substr = new StringBuffer();

        if(L > N*k){
            for(int i=0; i<10; i++){
                int x = i*L/N;
                if(L > (x+k)){
                    substr.append(doc.substring(x, x+k));
                } else{
                    substr.append(doc.substring(x));
                }
            }
        }else{
            substr.append(doc);
        }

        String hashString = hashString(substr.toString());

        String file = "hashStrings"; //the file storing unique hashStrings
        
        //check if the new hashString exists
        File f=new File(file);
        if(f.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals(hashString)){
                    br.close();
                    return true; //exact duplicate found
               }
            }
            br.close();
        }

        //add the new hashString to file
        FileWriter fw = new FileWriter(file, true); //the true will append the new data
        fw.write(hashString+"\n"); //appends the string to the file
        fw.close();

        return false; //exact duplicate not found
    }

    //this function is used to generate the hex hashString from the message
    private static String hashString(String message) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < hashedBytes.length; i++) {
            stringBuffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }
}
