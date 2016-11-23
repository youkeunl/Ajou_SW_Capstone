package priends.work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

import weka.core.Instances;
import weka.classifiers.functions.Logistic;

public class Main {

	/** The natural value 'e' */
	public final static double e = Math.E;

	/** Main for local Test */
	public static void main(String args[]) throws Exception {

	}

	/**
	 * - afterSelect method - Call after petMom select the petSitter 1. call
	 * petMom's matching data from database (call getMatchingData method) 2.
	 * find the weight values to use the Logistic Regression 3. parse the
	 * Logistic report 4. insert the weight values to 'match weight' table
	 */
	public void afterSelect(String petMomID) throws Exception {

		PetMomDAO call = new PetMomDAO();

		// PetMomID MatchingData call
		call.getMatchingData(petMomID);

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
		call.insertWeight(weight, petMomID);
	}

	/**
	 * - beforeSelect_priority method - Call before petMom select the petSitter
	 * for show petSitter list in ordered of priority 1. call petMom's
	 * weightValue data from database (call getWeightValue method) and make
	 * weight_map for calculate the priority easily (call makeWeightMap) 2. call
	 * all petSitter's data from database (call getPetSitterAllData) 3.
	 * calculate priority and if (priority >= 0.5) , insert petSitter's data to
	 * sortingList 4. return sortingList
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<PetSitterDTO> beforeSelect_priority(String petMomID) throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();

		// make weight_map
		HashMap<String, Double> weight_map = makeWeightMap(call.getWeightValue(petMomID));

		// call all petsitter's data and make return list
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData();
		ArrayList<PetSitterDTO> priority_sortingList = new ArrayList<PetSitterDTO>();

		Double[] petMom_mapPoint = call.getMappointData(petMomID);
		
		// make temperature values and pattern value
		String price_value = null;
		String distance_value = null;
		double priority = 0;
		String pattern = "#.###########################";
		DecimalFormat dformat = new DecimalFormat(pattern);

		// calculate priority and insert to sortingList if priority >= 0.5
		for (int i = 0; i < petsitterList.size(); i++) {

			price_value = classifier_Price(petsitterList.get(i).getPrice_day());
			distance_value = classifier_Distance(distance(petMom_mapPoint[0], petMom_mapPoint[1], petsitterList.get(i).getPoint_x(),petsitterList.get(i).getPoint_y()));
			
			priority = ((Math.pow(e, (weight_map.get(price_value) + weight_map.get(distance_value))))
					/ (1 + Math.pow(e, (weight_map.get(price_value) + weight_map.get(distance_value)))));
			if (priority >= 0.5) {
				priority_sortingList.add(petsitterList.get(i));
			}
		}

		return priority_sortingList;
	}

	/**
	 * - beforeSelect_price_day method - Call before petMom select the petSitter
	 * for show petSitter list in ordered of day price
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<PetSitterDTO> beforeSelect_price_day(String petMomID) throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();

		// call all petsitter's data
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData();

		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_day_AscCompare());

		return petsitterList;
	}

	/**
	 * - beforeSelect_price_night method - Call before petMom select the
	 * petSitter for show petSitter list in ordered of night price
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<PetSitterDTO> beforeSelect_price_night(String petMomID) throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();

		// call all petsitter's data
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData();

		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_night_AscCompare());

		return petsitterList;
	}

	/**
	 * - makeWeightMap method - 1. input value is weight list 2. make weight_map
	 * for calculate the priority easily
	 */
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

	public static String classifier_Distance(double distance) {

		String value = null;

		if (distance < 1.0) {
			value = "vn";
		} else if (distance >= 1.0 && distance < 1.5) {
			value = "n";
		} else if (distance >= 1.5 && distance < 2.0) {
			value = "dm";
		} else if (distance >= 2.0 && distance < 2.5) {
			value = "f";
		} else {
			value = "vf";
		}

		return value;
	}

	/**Calculate distance (One PetMom from One PetSitter)
	 * 
	 * @param lat1 : petmom's Mappoint_X
	 * @param lon1 : petmom's Mappoint_Y
	 * @param lat2 : petsitter's Mappoint_X
	 * @param lon2 : petsitter's Mappoint_Y
	 * @return dist : Distance (Unit = Kilometer)
	 */
	private static double distance(double lat1, double lon1, double lat2, double lon2) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1.609344;
		
		return (dist);
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	static class Price_day_AscCompare implements Comparator<PetSitterDTO> {

		@Override
		public int compare(PetSitterDTO arg0, PetSitterDTO arg1) {
			return arg0.getPrice_day() < arg1.getPrice_day() ? -1 : arg0.getPrice_day() > arg1.getPrice_day() ? 1 : 0;
		}

	}

	static class Price_night_AscCompare implements Comparator<PetSitterDTO> {

		@Override
		public int compare(PetSitterDTO arg0, PetSitterDTO arg1) {
			return arg0.getPrice_night() < arg1.getPrice_night() ? -1
					: arg0.getPrice_night() > arg1.getPrice_night() ? 1 : 0;
		}

	}
}