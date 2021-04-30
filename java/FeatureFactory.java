import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FeatureFactory {

    /** Add any necessary initialization steps for your features here.
     *  Using this constructor is optional. Depending on your
     *  features, you may not need to intialize anything.
     */
	public static HashMap<String, Boolean> names;
	public static HashMap<String, Boolean> trainedNames;

    public FeatureFactory() {
        names = new HashMap<String, Boolean>();
        parseFile(names, "../data/names");   
        
        trainedNames = new HashMap<String, Boolean>();
        parseFile(names, "../data/trainedNames");     


    }

    public void parseFile(HashMap<String, Boolean> hm, String fileName) {
        try {
          BufferedReader in = new BufferedReader(new FileReader(fileName));

          for (String line = in.readLine(); line != null; line = in.readLine()) {
            hm.put(line.toLowerCase(), true);
          }
        }  catch (Exception e) {
          System.out.println("fuck ...");  // Shouldn't happen anyway

        }
      }

    private List<String> computeFeatures(List<String> words,
					 String previousLabel, int position) {

    

	List<String> features = new ArrayList<String>();

	String currentWord = words.get(position);
///**
//############################################################# ORGANIZATIONS #######################################################
	// Baseline Features
	features.add("word=" + currentWord);
	//features.add("prevLabel=" + previousLabel);
	//features.add("word=" + currentWord + ", prevLabel=" + previousLabel);

	// if (currentWord.equals("شركة") ) {
	//     	features.add("shareket");
	// }
	if(previousLabel=="PERSON" && currentWord.startsWith("و")){
		features.add("org + wa");
	}
	
		if(previousLabel=="PERSON"){
		features.add("prevlabel");
	}
	if (currentWord.equals("\"\"\"\"") ) {
	    	features.add("quotes");
	}
	if (position > 1) {
	    if ((words.get(position -1).endsWith("شركة")) && currentWord.startsWith("ال")) {
	    	features.add("after sherket");
	    }
    }
	if (position > 1) {
	    if ((words.get(position -1).endsWith("البنك")) && currentWord.startsWith("ال")) {
	    	features.add("al bank al____");
	    }
    }
	if (position <= words.size()-2) {
	    if (words.get(position).endsWith("شركة") &&  words.get(position+1).startsWith("ال") ) {
	    	features.add("sharekat al");
	    }
    }
	if (position > 1) {
	    if ((words.get(position -1).endsWith("منظمة")) && currentWord.startsWith("ال")) {
	    	features.add("after sherket");
	    }
    }

	if (position <= words.size()-2) {
	    if (words.get(position).endsWith("منظمة") &&  words.get(position+1).startsWith("ال") ) {
	    	features.add("sharekat al");
	    }
    }
	if (position > 1) {
	    if (words.get(position -1).equals("وفد") && words.get(position ).equals("ال")) {
	    	features.add("wafed sth");
	    }
    }

	if (position > 1) {
	    if (words.get(position -1).equals("\"\"\"\"") ) {
	    	features.add("three quotes");
	    }
    }

	if (position <= words.size()-2) {
	    if (words.get(position + 1).equals("\"\"\"\"") ) {
	    	features.add("three quotes2");
	    }
    }
	
	if (position <= words.size()-2) {
	    if (words.get(position + 1).startsWith("ال") && words.get(position ).equals("الاتحاد") ) {
	    	features.add("al etehad al ____");
	    }
    }

//**/


/**
//##################################################################### LOCATIONS #####################################################
    	// Baseline Features
	features.add("word=" + currentWord);
	features.add("prevLabel=" + previousLabel);
	features.add("word=" + currentWord + ", prevLabel=" + previousLabel);

	if (position > 1) {
	    if ( words.get(position -1).equals("في")) {
	    	features.add("in ____");
	    }
    }

    if (position > 1) {
	    if ( words.get(position -1).equals("على")) {
	    	features.add("ala ____");

	    }
    }

    // if (position > 1) {
	//     if ( words.get(position -1).equals("إلى")) {
	//     	features.add("ela ____");
	//     }
    // }

    // if (position > 1) {
	//     if ( words.get(position -1).equals("من")) {
	//     	features.add("min ____");
	//     }
    // }

    if (position > 1) {
	    if ( words.get(position -1).equals("مدينة") || words.get(position -1).equals("دولة") || words.get(position -1).equals("بلدة") || words.get(position -1).equals("عاصمة") || words.get(position -1).equals("ولاية")|| words.get(position -1).equals("منطقة")|| words.get(position -1).contains("مقاطعة")|| words.get(position -1).equals("قرب")) {
	    	features.add("in ____");
	    }
    }

	if (position > 1) {
	    if (words.get(position -1).contains("شمال")) {
	    	features.add("shamal");
	    }
    }

	if (currentWord.equals("الولايات") ) {
	    	features.add("welayat");
	}
	if ( currentWord.contains("مملكة")) {
		features.add("mamlakeh");
	}

	if ( currentWord.contains("البحر")|| currentWord.contains("المحيط")) {
		features.add("ba7er");
	}
	if (position > 1) {
	    if (words.get(position -1).equals("البحر") || words.get(position -1).equals("المحيط") ) {
	    	features.add("ba7er2");
	    }
    }
	if (position > 1) {
	    if (words.get(position -1).equals("الولايات") ) {
	    	features.add("welayat");
	    }
    }
	if (position > 1) {
	    if ( words.get(position -1).contains("مملكة")) {
	    	features.add("mamlakeh");
	    }
    }
//**/


/**
//############################################# PERSONS ####################################################
		// Baseline Features
	features.add("word=" + currentWord);
	//features.add("prevLabel=" + previousLabel);
	//features.add("word=" + currentWord + ", prevLabel=" + previousLabel);

	if(previousLabel=="PERSON" && currentWord.startsWith("و")){
		features.add("name + wa");
		currentWord = currentWord.substring(1);
	}

	if (currentWord.endsWith("أبو")){
		features.add("abo itself");
	}

	if (position > 1) {
	    if (words.get(position -1).endsWith("أبو") ) {
	    	features.add("abo _______");
	    }
    }
	if (position <= words.size()-2) {
	    if (words.get(position + 1).equals("بن") ) {
	    	features.add("____ bin sth");
	    }
    }

	if (position <= words.size()-2) {
	    if (words.get(position).length() == 3  && words.get(position+1).equals("الله")) {
	    	features.add("____ bin sth");
	    }
    }

	if (position <= words.size()-2) {
	    if (words.get(position + 1).equals("عبد") ) {
	    	features.add("abd ___");
	    }
    }
	if(!(position <= words.size()-2 && words.get(position + 1).equals("بن")) && !(position > 1 && words.get(position -1).contains("بن"))){
		if(currentWord.length()>4){
			features.add("long word");

		}
	}
    if (position > 1) {
	    if ( words.get(position -1).equals("قال") || words.get(position -1).equals("اخبر") || words.get(position -1).contains("قول")) {
	    	features.add("qal ____");
	    }
    }
    if (position > 1) {
	    if (words.get(position -1).startsWith("ال") ) {
	    	features.add("a word with al alta3ref befor it");
	    }
    }
    
    if (position > 1) {
	    if (words.get(position -1).contains("بن") && words.get(position -1).length()<5 ) {
	    	features.add("sth bin _______");
	    }
    }
	if (position > 1) {
	    if (words.get(position -1).equals("الأمير") || words.get(position -1).equals("الملك") ||  words.get(position -1).equals("الرئيس")) {
	    	features.add("after title");
	    }
    }

    if (position > 1) {
	    if (words.get(position -1).startsWith("ال") && words.get(position -1).endsWith("ي") && words.get(position -1).length()>4) {
	    	features.add("probably an adjective before it");
	    }
    }

	//features.add("Word length=" + currentWord.length());
	
    
    if (this.names.containsKey(currentWord)) {
    features.add("dataset of names");
    }

	// if (position > 1) {
	//     if ( previousLabel=="PEOPLE" && currentWord.startsWith("و") ) {
	//     	features.add("location wa ____");
	//     }
    // }

    // if ( ( currentWord.startsWith("و") ) && previousLabel.equals("PERSON")){
    // 	features.add("word starts with waw and has a name before it");
    // }

	// if ( ( currentWord.startsWith("و") ) ){
    // 	features.add("word starts with waw");
    // }

	//    if ( !( currentWord.startsWith("ال") ) ){
	//    	features.add("doen't start with al alta3reef");
	//    }
    
	// if (position <= words.size()-2) {
	//     if (words.get(position + 1).equals("و")  ) {
	//     	features.add("word before waw");
	//     }
    // }

    // if (position > 1) {
	//     if (words.get(position -1).startsWith("ال") && words.get(position -1).endsWith("ي")) {
	//     	features.add("probably an adjective before it");
	//     }
    // }
    

    // if (position > 1) {
	//     if (words.get(position -1).contains("أب") && words.get(position -1).length()<5 ) {
	//     	features.add("someone's child2");
	//     }
    // }

	//     if (position > 1) {
	//     if (words.get(position -1).equals("هو") || words.get(position -1).equals("هي")) {
	//     	features.add("howa or heya");
	//     }
    // }

    // if (position > 1) {
	//     if (words.get(position -1).startsWith("ال") && words.get(position -1).endsWith("ة") && words.get(position -1).length()>4) {
	//     	features.add("mawsoof");
	//     }
    // }

    // if (position > 1) {
	//     if ( words.get(position -1).startsWith("ي") && words.get(position -1).length()==5 && words.get(position -1).substring(2).startsWith("ا") ) {
	//     	features.add("yofa3el before word");
	//     }
    // }

//**/

	return features;
    }



    /** Do not modify this method **/
    public List<Datum> readData(String filename) throws IOException {

	List<Datum> data = new ArrayList<Datum>();
	BufferedReader in = new BufferedReader(new FileReader(filename));

	for (String line = in.readLine(); line != null; line = in.readLine()) {
	    if (line.trim().length() == 0) {
		continue;
	    }
	    String[] bits = line.split("\\s+");
	    String word = bits[0];
	    String label = bits[1];

	    Datum datum = new Datum(word, label);
	    data.add(datum);
	}

	return data;
    }

    /** Do not modify this method **/
    public List<Datum> readTestData(String ch_aux) throws IOException {

	List<Datum> data = new ArrayList<Datum>();

	for (String line : ch_aux.split("\n")) {
	    if (line.trim().length() == 0) {
		continue;
	    }
	    String[] bits = line.split("\\s+");
	    String word = bits[0];
	    String label = bits[1];

	    Datum datum = new Datum(word, label);
	    data.add(datum);
	}

	return data;
    }

    /** Do not modify this method **/
    public List<Datum> setFeaturesTrain(List<Datum> data) {
	// this is so that the feature factory code doesn't accidentally use the
	// true label info
	List<Datum> newData = new ArrayList<Datum>();
	List<String> words = new ArrayList<String>();

	for (Datum datum : data) {
	    words.add(datum.word);
	}

	String previousLabel = "O";
	for (int i = 0; i < data.size(); i++) {
	    Datum datum = data.get(i);

	    Datum newDatum = new Datum(datum.word, datum.label);
	    newDatum.features = computeFeatures(words, previousLabel, i);
	    newDatum.previousLabel = previousLabel;
	    newData.add(newDatum);

	    previousLabel = datum.label;
	}

	return newData;
    }

    /** Do not modify this method **/
    public List<Datum> setFeaturesTest(List<Datum> data) {
	// this is so that the feature factory code doesn't accidentally use the
	// true label info
	List<Datum> newData = new ArrayList<Datum>();
	List<String> words = new ArrayList<String>();
	List<String> labels = new ArrayList<String>();
	Map<String, Integer> labelIndex = new HashMap<String, Integer>();

	for (Datum datum : data) {
	    words.add(datum.word);
	    if (labelIndex.containsKey(datum.label) == false) {
		labelIndex.put(datum.label, labels.size());
		labels.add(datum.label);
	    }
	}

	// compute features for all possible previous labels in advance for
	// Viterbi algorithm
	for (int i = 0; i < data.size(); i++) {
	    Datum datum = data.get(i);

	    if (i == 0) {
		String previousLabel = "O";
		datum.features = computeFeatures(words, previousLabel, i);

		Datum newDatum = new Datum(datum.word, datum.label);
		newDatum.features = computeFeatures(words, previousLabel, i);
		newDatum.previousLabel = previousLabel;
		newData.add(newDatum);

	    } else {
		for (String previousLabel : labels) {
		    datum.features = computeFeatures(words, previousLabel, i);

		    Datum newDatum = new Datum(datum.word, datum.label);
		    newDatum.features = computeFeatures(words, previousLabel, i);
		    newDatum.previousLabel = previousLabel;
		    newData.add(newDatum);
		}
	    }

	}

	return newData;
    }

    /** Do not modify this method **/
    public void writeData(List<Datum> data, String filename)
	throws IOException {


	FileWriter file = new FileWriter(filename + ".json", false);

	       
	for (int i = 0; i < data.size(); i++) {
	    try {
		JSONObject obj = new JSONObject();
		Datum datum = data.get(i);
		obj.put("_label", datum.label);
		obj.put("_word", base64encode(datum.word));
		obj.put("_prevLabel", datum.previousLabel);

		JSONObject featureObj = new JSONObject();

		List<String> features = datum.features;
		for (int j = 0; j < features.size(); j++) {
		    String feature = features.get(j).toString();
		    featureObj.put("_" + feature, feature);
		}
		obj.put("_features", featureObj);
		obj.write(file);
		file.append("\n");
	    } catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	file.close();
    }

    /** Do not modify this method **/
    private String base64encode(String str) {
	Base64 base = new Base64();
	byte[] strBytes = str.getBytes();
	byte[] encBytes = base.encode(strBytes);
	String encoded = new String(encBytes);
	return encoded;
    }

}
