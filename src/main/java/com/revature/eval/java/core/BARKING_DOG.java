package com.revature.eval.java.core;

public class BARKING_DOG {
	boolean bd;
	int hod;
	
	public static boolean shouldWakeUp(boolean isBarking, int hourOfDay) {
		boolean bd= isBarking;
		int hod = hourOfDay;
			
			if (bd == false) {
			System.out.println("False");
			return false;
			
			} else if(  bd==true && (hod  >=8 && 22<hod) || bd ==true && (hod < 0 && 23<hod )) {
				
				System.out.println("False");
				return false;
				
			}
			
			 else {
				 System.out.println("True");
				return true;
			}
	
	}


	public static void main(String[] args) {
		shouldWakeUp(true,1);
	}

}

