package priends.work;

public class ForCalculate {

	public String classifier_Price(int price) {

		String value = null;

		if (price < 10000) {
			value = "vl";
		} else if (price >= 10000 && price < 15000) {
			value = "l";
		} else if (price >= 15000 && price < 20000) {
			value = "pm";
		} else if (price >= 25000 && price < 30000) {
			value = "h";
		} else {
			value = "vh";
		}

		return value;
	}

	public String classifier_Distance(int distance) {

		String value = null;

		if (distance < 2500) {
			value = "vn";
		} else if (distance >= 2500 && distance < 5000) {
			value = "n";
		} else if (distance >= 5000 && distance < 7500) {
			value = "dm";
		} else if (distance >= 7500 && distance < 10000) {
			value = "f";
		} else {
			value = "vf";
		}

		return value;
	}
	
	public String classifier_Recommend(Double recommend) {

		String value = null;

		if (recommend < 1.0) {
			value = "vl";
		} else if (recommend >= 1.0 && recommend < 1.5) {
			value = "l";
		} else if (recommend >= 1.5 && recommend < 2.0) {
			value = "rm";
		} else if (recommend >= 2.0 && recommend < 2.5) {
			value = "g";
		} else {
			value = "vg";
		}

		return value;
	}

	/**Calculate distance (One PetMom from One PetSitter)
	 * 
	 * @param lat1 : petmom's Mappoint_X
	 * @param lon1 : petmom's Mappoint_Y
	 * @param lat2 : petsitter's Mappoint_X
	 * @param lon2 : petsitter's Mappoint_Y
	 * @return dist : Distance (Unit = meter)
	 */
	public int distance(double lat1, double lon1, double lat2, double lon2) {

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		dist = dist * 1609.344;
		
		return (int)dist;
	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	public String[] symmetry_Value(String price, String distance, String recommend){
		
		String [] price_value = {"vl", "l", "pm", "h", "vh", "vh", "h", "pm", "l", "vl"};
		String [] distance_value = {"vn", "n", "dm", "f", "vf", "vf", "f", "dm", "n", "vn"};
		String [] recommend_value = {"vl", "l", "rm", "g", "vg", "vg", "g", "rm", "l", "vl"};
		
		int price_index = 0;
		int distance_index = 0;
		int recommend_index = 0;
		
		String [] return_value = new String[3];
		
		for(int i = 0; i < 5; i++){
			if(price_value[i].equals(price)){
				price_index = i+5;
			}
			if(distance_value[i].equals(distance)){
				distance_index = i+5;
			}
			if(recommend_value[i].equals(recommend)){
				recommend_index = i+5;
			}
		}
		
		return_value[0] = price_value[price_index];
		return_value[1] = distance_value[distance_index];
		return_value[2] = recommend_value[recommend_index];
		
		return return_value;
	}
	
	public int[] start_end_Date(String Start_Date, String End_Date){
		
		String [] split_start = Start_Date.split("-");
		String [] split_end = End_Date.split("-");
		
		int [] date = new int[2];
		
		date[0] = Integer.parseInt(split_start[2]);
		date[1] = Integer.parseInt(split_end[2]);
		
		return date;
	}
}
