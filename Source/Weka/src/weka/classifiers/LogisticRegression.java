package weka.classifiers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

import weka.core.Instances;
import weka.classifiers.functions.Logistic;

public class LogisticRegression {

	/** for temp distance value */
	static int distance_temp = 0;

	/** The natural value 'e' */
	public final static double e = Math.E;

	/** Main */
	public static void main(String args[]) throws Exception {
		
		//LogisticRegression regression = new LogisticRegression();

		String petMomID = "M1";
		
		//ArrayList<BoardDTO> sortingList = regression.beforeSelect_price_night(petMomID);
		
		//ArrayList<BoardDTO> priority_sortingList = regression.beforeSelect_priority(petMomID);
		
		//regression.afterSelect(petMomID);
		
	}
	
	
	
	/** - afterSelect method -
	 *  Call after petMom select the petSitter 
	 *  1. call petMom's matching data from database (call getMatchingData method)
	 *  2. find the weight values to use the Logistic Regression
	 *  3. parse the Logistic report
	 *  4. insert the weight values to 'matchweight' table */
	public void afterSelect(String petMomID) throws Exception {

		CallDB call = new CallDB(petMomID);

		// PetMomID MatchingData call
		call.getMatchingData();

		// call temp.txt
		Instances data = new Instances(new BufferedReader(new FileReader("temp.txt")));

		data.setClassIndex(data.numAttributes() - 1);

		// Do Logistic Regression
		Logistic model = new Logistic();
		model.buildClassifier(data);

		// Parsing
		String result = model.toString();
		result = result.replaceAll(" ", "");
		String[] split = result.split("Intercept");
		String[] split2 = split[0].split("\n");

		// weight array => save weight values
		Double[] weight = new Double[10];
		int index = 0;
		for (int i = 5; i < split2.length; i++) {
			weight[index] = Double.parseDouble(split2[i].split("=")[2]);
			index++;
		}

		// call DB and insert weight values
		call.insertWeight(weight);
	}

	/** - beforeSelect_priority method -
	 *  Call before petMom select the petSitter for show petSitter list in ordered of priority
	 *  1. call petMom's weightValue data from database (call getWeightValue method)
	 *     and make weight_map for calculate the priority easily (call makeWeightMap)
	 *  2. call all petSitter's data from database (call getPetSitterAllData)
	 *  3. calculate priority and if (priority >= 0.5) , insert petSitter's data to sortingList
	 *  4. return sortingList */
	public ArrayList<BoardDTO> beforeSelect_priority(String petMomID) {

		CallDB call = new CallDB(petMomID);

		// make weight_map
		HashMap<String, Double> weight_map = makeWeightMap(call.getWeightValue());

		// call all petsitter's data and make return list
		ArrayList<BoardDTO> petsitterList = call.getPetSitterAllData();
		ArrayList<BoardDTO> priority_sortingList = new ArrayList<BoardDTO>();
		
		// make temperature values and pattern value
		String price_value = null;
		String distance_value = null;
		double priority = 0;
		String pattern = "#.###########################";
		DecimalFormat dformat = new DecimalFormat(pattern);

		// calculate priority and insert to sortingList if priority >= 0.5
		for (int i = 0; i < petsitterList.size(); i++) {

			price_value = classifier_Price(petsitterList.get(i).getPrice_day());
			distance_value = classifier_Distance();
			priority = ((Math.pow(e, (weight_map.get(price_value) + weight_map.get(distance_value))))
					/ (1 + Math.pow(e, (weight_map.get(price_value) + weight_map.get(distance_value)))));
			if (priority >= 0.5) {
				priority_sortingList.add(petsitterList.get(i));
			}
		}

		return priority_sortingList;
	}
	
	/** - beforeSelect_price_day method -
	 *  Call before petMom select the petSitter for show petSitter list in ordered of day price */
	public ArrayList<BoardDTO> beforeSelect_price_day(String petMomID) {

		CallDB call = new CallDB(petMomID);

		// call all petsitter's data
		ArrayList<BoardDTO> petsitterList = call.getPetSitterAllData();
	
		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_day_AscCompare());
		
		return petsitterList;
	}
	
	/** - beforeSelect_price_night method -
	 *  Call before petMom select the petSitter for show petSitter list in ordered of night price */
	public ArrayList<BoardDTO> beforeSelect_price_night(String petMomID) {

		CallDB call = new CallDB(petMomID);

		// call all petsitter's data
		ArrayList<BoardDTO> petsitterList = call.getPetSitterAllData();
	
		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_night_AscCompare());
		
		return petsitterList;
	}
	
	/** - makeWeightMap method -
	 *  1. input value is weight list
	 *  2. make weight_map for calculate the priority easily */
	public static HashMap<String, Double> makeWeightMap(ArrayList<Double> weight) {

		HashMap<String, Double> weight_map = new HashMap<String, Double>();

		weight_map.put("vh", weight.get(0));
		weight_map.put("h", weight.get(1));
		weight_map.put("pm", weight.get(2));
		weight_map.put("l", weight.get(3));
		weight_map.put("vl", weight.get(4));

		weight_map.put("vf", weight.get(5));
		weight_map.put("f", weight.get(6));
		weight_map.put("dm", weight.get(7));
		weight_map.put("n", weight.get(8));
		weight_map.put("vn", weight.get(9));

		return weight_map;
	}

	public static String classifier_Price(int price) {

		String value = null;

		if (price < 10000) {
			value = "vl";
		} else if (price >= 10000 && price < 20000) {
			value = "l";
		} else if (price >= 20000 && price < 30000) {
			value = "pm";
		} else if (price >= 30000 && price < 40000) {
			value = "h";
		} else {
			value = "vh";
		}

		return value;
	}

	public static String classifier_Distance() {

		String value = null;

		if (distance_temp % 5 == 0) {
			value = "vn";
		} else if (distance_temp % 5 == 0) {
			value = "n";
		} else if (distance_temp % 5 == 0) {
			value = "dm";
		} else if (distance_temp % 5 == 0) {
			value = "f";
		} else {
			value = "vf";
		}

		distance_temp++;

		return value;
	}
	
	static class Price_day_AscCompare implements Comparator<BoardDTO>{

		@Override
		public int compare(BoardDTO arg0, BoardDTO arg1) {
			return arg0.getPrice_day() < arg1.getPrice_day() 
					? -1 : arg0.getPrice_day() > arg1.getPrice_day() ? 1:0;
		}
		
	}
	
	static class Price_night_AscCompare implements Comparator<BoardDTO>{

		@Override
		public int compare(BoardDTO arg0, BoardDTO arg1) {
			return arg0.getPrice_night() < arg1.getPrice_night() 
					? -1 : arg0.getPrice_night() > arg1.getPrice_night() ? 1:0;
		}
		
	}
}