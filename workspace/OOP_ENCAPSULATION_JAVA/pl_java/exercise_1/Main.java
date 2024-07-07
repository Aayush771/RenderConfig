package pl_java.exercise_1;

public class Main {
    
  
    
 
   
    public static void main(String args[]) {
        CustomTime cTime = new CustomTime();
        printTime(cTime.getTime());
    }
 
 




    public static void printTime(int[] time){
        int hr = time[0];
		int min = time[1];
		int sec = time[2];
        System.out.println(
				"The time is set to: "
						+ hr + ":" + min + ":" + sec
				);
    }
}
