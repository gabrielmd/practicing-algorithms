import java.lang.*;

public class TrafficCongestionDivTwo{


	public static long theMinCars(int treeHeight){

		double cars = 0;
		for(double i = ((treeHeight + 1) % 2); i <= treeHeight - 1; i += 2){
			cars += Math.pow(2,i);
		}

		System.out.println(cars);
		System.out.println((cars%1000));


		if(treeHeight % 2 == 0){
			cars ++;
		}
		return (long)cars;
	}

	public static void main(String [] args){
		System.out.println(theMinCars(Integer.parseInt(args[0])));
	}

}