package com.bericotech.clavin;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

import com.bericotech.clavin.resolver.ResolvedLocation;
import com.bericotech.clavin.util.TextUtils;

public class GetLocations {

    public static void main(String[] args) throws Exception {
        
        // Instantiate the CLAVIN GeoParser
        GeoParser parser = GeoParserFactory.getDefault("./IndexDirectory");
        
        // Unstructured text file about Somalia to be geoparsed
        //File inputFile = new File("src/test/resources/sample-docs/Somalia-doc.txt");
        
        File folder = new File("/Users/atma/Documents/572/hw2/AdeP2");
        File [] fileList = folder.listFiles();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/atma/Documents/572/hw2/geodocs.txt"));
        
        int counter = 0;
        try {
            for (File file : fileList) {
                // Grab the contents of the text file as a String
                String inputString = TextUtils.fileToString(file);
        
                // Parse location names in the text into geographic entities
                List<ResolvedLocation> resolvedLocations = parser.parse(inputString);
            
                List<LatLon> locations = new ArrayList<LatLon>();

                // Display the ResolvedLocations found for the location names
                for (ResolvedLocation resolvedLocation : resolvedLocations) {           
                    locations.add(new LatLon(resolvedLocation.getGeoname().getLatitude(), resolvedLocation.getGeoname().getLongitude()));
                }

                for (LatLon location : locations) {
                    location.setLat(Math.round(location.getLat() * 10) / 10);
                    location.setLon(Math.round(location.getLon() * 10) / 10);                                        
                }

                TreeSet<LatLon> dedup = new TreeSet<LatLon>(locations);

                // !!! Change locations to dedup if uncommenting above
                counter++;
                System.out.println(counter + ", " + dedup.size());
                if (dedup.size() > 0) {
                    bw.write(file.getName() + ";");
                    for (LatLon location : dedup) {
                        bw.write(location.toString() + ";");
                    }
                    bw.write("\n");
                    bw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.close();
        }
    }
}
