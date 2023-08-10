package IXAdminFramework.utils;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class emailCofiguration

{
	// ---( internal utility methods )---

	final static emailCofiguration _instance = new emailCofiguration();

	static emailCofiguration _newInstance() { return new emailCofiguration(); }

	static emailCofiguration _cast(Object o) { return (emailCofiguration)o; }

	// ---( server methods )---




	public static final void decodeImageBase64 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decodeImageBase64)>> ---
		// @sigtype java 3.5
		// [i] field:0:required image
		// [o] object:0:required bytes
		IDataMap map = new IDataMap(pipeline); 
		String image = map.getAsString("image");
		byte[] temp =  Base64.getDecoder().decode(image);
		
			
		map.put("bytes", temp);
			
		// --- <<IS-END>> ---

                
	}



	public static final void getUniqueNameFromString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getUniqueNameFromString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required emails
		// [o] field:0:required uniqueEmails
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			String	emails = IDataUtil.getString( pipelineCursor, "emails" );
		
				
			String[] names = emails.split(",");
			//pipelineCursor.destroy();
		
			Set<String> uniqueNames = new HashSet<>();
			
			 for (String name : names) {
		            uniqueNames.add(name);
		        }
		
			 StringBuilder stringBuilder = new StringBuilder();
		     for (String item : uniqueNames) {
		            stringBuilder.append(item);
		            stringBuilder.append(", ");
		        }
		     
		     int length = stringBuilder.length();
		        if (length > 2 || length >= 1) {
		            // Remove the last ", " from the StringBuilder
		            stringBuilder.delete(length - 2, length);
		        }
			
		       String result = stringBuilder.toString();
		    
			
		        
		     // pipeline
		     IDataCursor pipelineCursor1 = pipeline.getCursor();
		     IDataUtil.put( pipelineCursor1, "uniqueEmails",result  );
		     pipelineCursor1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getUniqueNameFromTwoString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getUniqueNameFromTwoString)>> ---
		// @sigtype java 3.5
		// [i] field:0:required toEmail
		// [i] field:0:required ccEmail
		// [o] field:0:required toEmailOut
		// [o] field:0:required ccEmailOut
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	toEmail = IDataUtil.getString( pipelineCursor, "toEmail" );
			String	ccEmail = IDataUtil.getString( pipelineCursor, "ccEmail" );
		//	pipelineCursor.destroy();
			
			String[] to = toEmail.split(",");
			String[] cc = ccEmail.split(",");
			
			  StringBuilder updatedCC = new StringBuilder();
		
		        for (String name : cc) {
		            if (!containsName(to, name)) {
		            	updatedCC.append(name);
		            	updatedCC.append(", ");
		            }
		        }
			
		     // Remove the trailing ", " from the StringBuilder
		        if (updatedCC.length() > 2 || updatedCC.length() >= 1) {
		        	updatedCC.setLength(updatedCC.length() - 2);
		        }
		
		        String ccout = updatedCC.toString();
		       
		
		     // pipeline
		     IDataCursor pipelineCursor2 = pipeline.getCursor();
		     IDataUtil.put( pipelineCursor2, "toEmailOut", toEmail );
		     IDataUtil.put( pipelineCursor2, "ccEmailOut", ccout );
		     pipelineCursor2.destroy();
		
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static boolean containsName(String[] names, String name) {
	       for (String n : names) {
	           if (n.trim().equalsIgnoreCase(name.trim())) {
	               return true;
	           }
	       }
	       return false;
	}
		
	// --- <<IS-END-SHARED>> ---
}

