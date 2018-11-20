package com.uiAutomatiom.helper.assertions;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiAutomatiom.helper.logger.LogHelper;


public class AssertionHelper {
	
	      private static Logger log = LogHelper.getLogger(AssertionHelper.class);
	      
	      public static void verifyText (String s1, String s2){
	    	  log.info(" verifying the text " + s1 + " with " +  s2);
	    	  Assert.assertEquals(s1, s2);
	      }	 
	      
	      public static void  makeTrue(){
	    	  log.info(" making the script pass.." );
	    	  Assert.assertTrue(true);
	      }
	
	      public static void makeTrue(String message){
	    	  log.info(" making the script pass with message " + message);
	    	  Assert.assertTrue(true, message);
	      }
	      
	     public static void makeFalse(){
	    	 log.info(" making the script fail.." );
	    	  Assert.assertTrue(false);
	     }
	     
	     public static void makeFalse(String message){
	    	  Assert.assertTrue(false, message);
	      }
         
	     public static void verifyTrue(boolean status) {
	    	 Assert.assertTrue(status);
	     }
	     
	     public static void verifyFalse(boolean status){
	    	 Assert.assertFalse(status);
	     }
	     
	     public static void verifyNull(String s1){
	    	 log.info(" verify object is null." );
	    	 Assert.assertNull(s1);
	     }
	     
	     public static void verifyNotNull(String s1){
	    	 log.info(" verify object is not null.." );
	    	 Assert.assertNotNull(s1);
	     }
	     
	     public static void fail(){
	    	 Assert.assertTrue(false);
	     }
	     public static void pass() {
	    	 Assert.assertTrue(true);
	     }
	     public static void updateTestStatus(boolean  status){
	    	 if (status){
	    		 pass();
	    	 }
	    	 else {
	    		 fail();
	    	 }
	     }
}
