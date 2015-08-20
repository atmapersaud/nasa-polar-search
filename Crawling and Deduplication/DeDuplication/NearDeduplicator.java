import java.util.*;

public class NearDeduplicator {
    public static final int NUM_HASHES = 200;
    public static final int SHINGLE_SIZE = 4;
    public static final double JACCARD_THRESHOLD = 0.9;

    private class SetSizeComparator implements Comparator {
        public int compare(Object a, Object b) {
            int aSize = ((Set) a).size();
            int bSize = ((Set) b).size();
            if (aSize < bSize) {
                return -1;
            } else {
                return aSize > bSize ? 1 : 0;                
            }
        }
    }

    public Set<Integer> getMinHash(URLData doc) {
	List<Integer> shingleHash = getShingling(doc.getData());
	/*if (shingleHash.size() == 0) {
	  continue;
	  }*/
	Collections.sort(shingleHash);
	if (shingleHash.size() >= NUM_HASHES) {
	    Set<Integer> minHash = new LinkedHashSet<Integer>(shingleHash.subList(0,NUM_HASHES));
	    //minHashes.add(minHash);
	    return minHash;
	} else {
	    Set<Integer> minHash = new LinkedHashSet<Integer>(shingleHash);
	    //minHashes.add(minHash);
	    return minHash;
	}
    }

    public List<String> detectNearDuplicates(List<String> urls, List< Set<Integer> > minHashes) {
        int numMinHashes = minHashes.size();
        Collections.sort(minHashes, new SetSizeComparator());
        List<String> dupeList = new ArrayList<String>();
        for (int i = 0; i < numMinHashes; i++) {
            for (int j = i+1; j < numMinHashes; j++) {
                if (areNearDuplicates(minHashes.get(i), minHashes.get(j))) {
                    dupeList.add(urls.get(i));
		    break;
                }
            }
            System.out.println("Computed Doc " + (i+1) + " near duplicates");
        }
        return dupeList;
    }

    public List<String> detectExactDuplicates(List<String> urls, List< Set<Integer> > minHashes) {
        int numMinHashes = minHashes.size();
        Collections.sort(minHashes, new SetSizeComparator());
        List<String> dupeList = new ArrayList<String>();
        for (int i = 0; i < numMinHashes; i++) {
            for (int j = i+1; j < numMinHashes; j++) {
                if (areExactDuplicates(minHashes.get(i), minHashes.get(j))) {
                    dupeList.add(urls.get(i));
		    break;
                }
            }
            System.out.println("Computed Doc " + (i+1) + " exact duplicates");
        }
        return dupeList;
    }

    public List<Integer> getShingling(String doc) {
        List<Integer> result = new ArrayList<Integer>();
        String[] words = doc.split(" ");
        int numShingles = words.length - SHINGLE_SIZE + 1;

        for (int i = 0; i < numShingles; i++) {
            StringBuilder shingle = new StringBuilder(4096);
            for (int j = 0; j < SHINGLE_SIZE; j++) {
                shingle = shingle.append(words[i+j] + " ");
            }
            result.add(shingle.toString().hashCode());
        }

        return result;
    }

    public boolean areNearDuplicates(Set<Integer> docHash1, Set<Integer> docHash2) {
        if (docHash1.size() == 0) {
            return false;
        }
        Set<Integer> aUnionB = new TreeSet<Integer>(docHash1);
        aUnionB.addAll(docHash2);

        Iterator<Integer> iter = aUnionB.iterator();
        Set<Integer> x = new LinkedHashSet<Integer>();
        for (int i = 0; i < NUM_HASHES; i++) {
            if (iter.hasNext()) {
                x.add(iter.next());
            }
        }        

        Set<Integer> y = new LinkedHashSet<Integer>(docHash1);
        y.retainAll(docHash2);
        y.retainAll(x);

        double jaccardSimilarity = y.size() / (double) docHash1.size();

        return jaccardSimilarity > JACCARD_THRESHOLD;
    }

    public boolean areExactDuplicates(Set<Integer> docHash1, Set<Integer> docHash2) {
        if (docHash1.size() == 0) {
            return false;
        }
        Set<Integer> aUnionB = new TreeSet<Integer>(docHash1);
        aUnionB.addAll(docHash2);

        Iterator<Integer> iter = aUnionB.iterator();
        Set<Integer> x = new LinkedHashSet<Integer>();
        for (int i = 0; i < NUM_HASHES; i++) {
            if (iter.hasNext()) {
                x.add(iter.next());
            }
        }        

        Set<Integer> y = new LinkedHashSet<Integer>(docHash1);
        y.retainAll(docHash2);
        y.retainAll(x);

        double jaccardSimilarity = y.size() / (double) docHash1.size();

        return jaccardSimilarity == 1;
    }
}
