package priends.work;

import java.io.BufferedReader;
import java.io.FileReader;
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
	public String afterSelect(String petMomID) throws Exception {

		PetMomDAO call = new PetMomDAO();

		// PetMomID MatchingData call
		call.getMatchingData(petMomID);

		// call temp.txt
		Instances data = new Instances(
				new BufferedReader(new FileReader("/home/hosting_users/dldbrms79/www/temp.txt")));

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
		Double[] weight = new Double[15];
		int index = 0;
		for (int i = 5; i < split2.length; i++) {
			weight[index] = Double.parseDouble(split2[i].split("=")[2]);
			index++;
		}

		// call DB and insert weight values
		String s = call.insertWeight(weight, petMomID);
		
		return s;
	}

	/**
	 * - beforeSelect_priority method - Call before petMom select the petSitter
	 * for show petSitter list in ordered of priority 1. call petMom's
	 * weightValue data from database (call getWeightValue method) and make
	 * weight_map for calculate the priority easily (call makeWeightMap) 2. call
	 * all petSitter's data from database (call getPetSitterAllData) 3.
	 * calculate priority and if (priority >= 0.5) , insert petSitter's data to
	 * sortingList 4. return sortingList
	 * 
	 * @throws ClassNotFoundException
	 */
	public ArrayList<PetSitterDTO> beforeSelect_priority(String petMomID, String bitstring, String Start_Date, String End_Date)
			throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();
		ForCalculate cal = new ForCalculate();

		boolean existWeight = false;
		int [] date = cal.start_end_Date(Start_Date, End_Date);
		
		// call all petsitter's data and make return list
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData(bitstring, date[0], date[1]);
		ArrayList<PetSitterDTO> priority_sortingList = new ArrayList<PetSitterDTO>();

		ArrayList<Double> weight = call.getWeightValue(petMomID);

		for (int i = 0; i < 15; i++) {
			if (weight.get(i) != 0.0) {
				existWeight = true;
			}
		}

		if (existWeight == true) {

			// make weight_map
			HashMap<String, Double> weight_map = makeWeightMap(weight);

			Double[] petMom_mapPoint = call.getMappointData(petMomID);

			// make temperature values and pattern value
			String price_value = null;
			String distance_value = null;
			String recommend_value = null;
			double priority = 0;
			int priority_i = 0;

			// calculate priority and insert to sortingList if priority >= 0.5
			for (int i = 0; i < petsitterList.size(); i++) {

				price_value = cal.classifier_Price(petsitterList.get(i).getPrice_day());
				int distance = cal.distance(petMom_mapPoint[0], petMom_mapPoint[1],
						petsitterList.get(i).getPoint_x(), petsitterList.get(i).getPoint_y());
				distance_value = cal.classifier_Distance(distance);
				recommend_value = cal.classifier_Recommend(petsitterList.get(i).getRecommend());

				priority = ((Math.pow(e,
						(weight_map.get(price_value) + weight_map.get(distance_value)
								+ weight_map.get(recommend_value))))
						/ (1 + Math.pow(e, (weight_map.get(price_value) + weight_map.get(distance_value)
								+ weight_map.get(recommend_value)))));

				priority_i = (int) (priority * 1000000000);
				petsitterList.get(i).setPriority(priority_i);
				petsitterList.get(i).setDistance(distance);

				if (priority >= 0.5) {
					priority_sortingList.add(petsitterList.get(i));
				}
			}
		}

		// Collections.sort(petsitterList, new Priority_AscCompare());

		return priority_sortingList;
	}

	/**
	 * - beforeSelect_price_day method - Call before petMom select the petSitter
	 * for show petSitter list in ordered of day price
	 * 
	 * @throws ClassNotFoundException
	 */
	public ArrayList<PetSitterDTO> beforeSelect_price_day(String petMomID, String bitstring, String Start_Date, String End_Date)
			throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();
		ForCalculate cal = new ForCalculate();
		int [] date = cal.start_end_Date(Start_Date, End_Date);

		// call all petsitter's data
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData(bitstring, date[0], date[1]);
		
		Double[] petMom_mapPoint = call.getMappointData(petMomID);
		for (int i = 0; i < petsitterList.size(); i++) {
			petsitterList.get(i).setDistance(cal.distance(petMom_mapPoint[0], petMom_mapPoint[1],
					petsitterList.get(i).getPoint_x(), petsitterList.get(i).getPoint_y()));
		}

		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_day_AscCompare());

		return petsitterList;
	}

	/**
	 * - beforeSelect_price_night method - Call before petMom select the
	 * petSitter for show petSitter list in ordered of night price
	 * 
	 * @throws ClassNotFoundException
	 */
	public ArrayList<PetSitterDTO> beforeSelect_price_night(String petMomID, String bitstring, String Start_Date, String End_Date)
			throws ClassNotFoundException {

		PetMomDAO call = new PetMomDAO();
		
		ForCalculate cal = new ForCalculate();
		int [] date = cal.start_end_Date(Start_Date, End_Date);
		
		// call all petsitter's data
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData(bitstring, date[0], date[1]);

		Double[] petMom_mapPoint = call.getMappointData(petMomID);
		for (int i = 0; i < petsitterList.size(); i++) {
			petsitterList.get(i).setDistance(cal.distance(petMom_mapPoint[0], petMom_mapPoint[1],
					petsitterList.get(i).getPoint_x(), petsitterList.get(i).getPoint_y()));
		}
		
		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Price_night_AscCompare());

		return petsitterList;
	}

	/**
	 * - beforeSelect_price_night method - Call before petMom select the
	 * petSitter for show petSitter list in ordered of night price
	 * 
	 * @throws ClassNotFoundException
	 */
	public ArrayList<PetSitterDTO> beforeSelect_price_distance(String petMomID, String bitstring, String Start_Date, String End_Date)
			throws ClassNotFoundException {

		ForCalculate cal = new ForCalculate();
		int [] date = cal.start_end_Date(Start_Date, End_Date);

		PetMomDAO call = new PetMomDAO();
		Double[] petMom_mapPoint = call.getMappointData(petMomID);
		// call all petsitter's data
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData(bitstring, date[0], date[1]);

		for (int i = 0; i < petsitterList.size(); i++) {
			petsitterList.get(i).setDistance(cal.distance(petMom_mapPoint[0], petMom_mapPoint[1],
					petsitterList.get(i).getPoint_x(), petsitterList.get(i).getPoint_y()));
		}

		// use collections and PriceAscCompare() method for sort
		Collections.sort(petsitterList, new Distance_AscCompare());

		return petsitterList;

	}
	
	public String[][] getDataToArray(String petMomID, String bitstring, String Start_Date, String End_Date) throws ClassNotFoundException{
		
		ForCalculate cal = new ForCalculate();
		int [] date = cal.start_end_Date(Start_Date, End_Date);
		PetMomDAO call = new PetMomDAO();
		ArrayList<PetSitterDTO> petsitterList = call.getPetSitterAllData(bitstring, date[0], date[1]);
		
		int size = petsitterList.size();
		
		String[][] array = new String[size][3];
		
		for(int i = 0; i < petsitterList.size(); i++){
			array[i][0] = petsitterList.get(i).getName();
			array[i][1] = Double.toString(petsitterList.get(i).getPoint_x());
			array[i][2] = Double.toString(petsitterList.get(i).getPoint_y());
		}
		
		return array;
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

		weight_map.put("vg", weight.get(10));
		weight_map.put("g", weight.get(11));
		weight_map.put("rm", weight.get(12));
		weight_map.put("l", weight.get(13));
		weight_map.put("vl", weight.get(14));

		return weight_map;
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

	static class Distance_AscCompare implements Comparator<PetSitterDTO> {

		@Override
		public int compare(PetSitterDTO arg0, PetSitterDTO arg1) {
			return arg0.getDistance() < arg1.getDistance() ? -1 : arg0.getDistance() > arg1.getDistance() ? 1 : 0;
		}
	}

	static class Priority_AscCompare implements Comparator<PetSitterDTO> {

		@Override
		public int compare(PetSitterDTO arg0, PetSitterDTO arg1) {
			return arg0.getPriority() < arg1.getPriority() ? -1 : arg0.getPriority() > arg1.getPriority() ? 1 : 0;
		}
	}
}