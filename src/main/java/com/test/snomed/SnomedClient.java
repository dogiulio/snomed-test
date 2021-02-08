package com.test.snomed;

/**
 * An application entry point for testing the modes of accessing REST APIs.
 */
public class SnomedClient {

  private static SnomedRest client = new SnomedRest();

  public static void main(String[] args) {
	  

	  try {  
	  String result1 = client.getConceptsByString("heart attack");
      System.out.println(result1);

	  String result2 = client.getConceptById("109152007");
      System.out.println(result2);

	  String result3 = client.getDescriptionById("679406011");
      System.out.println(result3);  
      
	  String result4 = client.getDescriptionsByStringFromProcedure("heart", "procedure");
      System.out.println(result4);  
	  
	  }
      catch (Exception e) {
          e.printStackTrace();
          System.exit(1);
        }
        System.exit(0);
  }
}
