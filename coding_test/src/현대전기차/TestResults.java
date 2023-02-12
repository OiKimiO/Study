package 현대전기차;

import java.util.stream.*;
import java.util.*;

public class TestResults{
	/* passName에 50 kWh 이상의 충전량을 나타내는 충전도시만 출력하도록 코드를 작성하세요.*/
	
	public static class ChargingStation {
        private String station;
        private int kwh;
        
        public ChargingStation(String station, int kwh) {
            this.station = station;
            this.kwh = kwh;
        }
        
        public int getKwh () {
            return kwh;
        }
        
        public String getName() {
            return station;
        }
    }
	
    public static List<String> chargingStationsThatPass(Stream<ChargingStation> chargingStations, int passingKwh) {
    	List<String> passName = new ArrayList<>();
    	
    	// 답안작성 
    	chargingStations.forEach(chargestation -> {
    		if(chargestation.getKwh() >= 50){
    			passName.add(chargestation.getName());
			}
    	});
    	
    	return passName;
    }
    
    public static void main(String[] args) {
        
        List<ChargingStation> chargingStations = new ArrayList<ChargingStation>();
        chargingStations.add(new ChargingStation("Seoul", 80));
        chargingStations.add(new ChargingStation("Incheon", 57));
        chargingStations.add(new ChargingStation("Gyeonggi", 21));
        
        /* 답안작성 */
        System.out.println(chargingStationsThatPass(chargingStations.stream(), 50));

    }
}