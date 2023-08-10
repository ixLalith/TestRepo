package IXCommon;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.lang.Object;
import com.softwareag.util.IDataMap;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataFactory;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void new_javaService (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(new_javaService)>> ---
		// @sigtype java 3.5
		// [i] record:1:required listOfKeys
		// [i] - field:0:required key
		// [i] - field:0:required value
		// [i] - field:0:required operator {"=","<",">","<=",">=","IN","NOT IN","LIKE"}
		// [o] field:0:required query
		/** 
		 * The primary method for the Java service
		 *
		 * @param pipeline
		 *            The IData pipeline
		 * @throws ServiceException
		 */
		
			// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String query = "";
			// listOfKeys
			IData[]	listOfKeys = IDataUtil.getIDataArray( pipelineCursor, "listOfKeys" );
			if ( listOfKeys != null)
			{
				for ( int i = 0; i < listOfKeys.length; i++ )
				{
					IDataCursor listOfKeysCursor = listOfKeys[i].getCursor();
						String	key = IDataUtil.getString( listOfKeysCursor, "key" );
						String	value = IDataUtil.getString( listOfKeysCursor, "value" );
						String	operator =	IDataUtil.getString( listOfKeysCursor, "operator" );
						
						if(value != null && !value.trim().equalsIgnoreCase("") && !value.isEmpty() && !value.equalsIgnoreCase("NULL") && !value.startsWith("%"))
							{
								if(operator.equalsIgnoreCase("IN")){
									query = ( query+ " " + key + " " + operator + " ('" + value +"') AND" );
								}
								else if(operator.equalsIgnoreCase("LIKE")){
									query = query+ " " + key + " " + operator + " '%" + value +"%' AND";
									
								}
								else{
								query = query+ " " + key + " " + operator + " '" + value +"' AND";
								}
							}
					listOfKeysCursor.destroy();
				}
			}
			if(query.length()>0){
				query = query.substring(0,query.length()-3);
				query = " WHERE " + query;
			}
		
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "query", query );
		pipelineCursor_1.destroy();
			
		
				
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void passwordEncryption (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(passwordEncryption)>> ---
		// @sigtype java 3.5
		// [i] field:0:required password
		// [o] field:0:required encryptedPassword
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	password = IDataUtil.getString( pipelineCursor, "password" );
			//String	encryptedPassword = IDataUtil.getString( pipelineCursor, "encryptedPassword" );
			String encryptedPassword1=null;
			
		       try   
		        {  
		            /* MessageDigest instance for MD5. */  
		            MessageDigest m = MessageDigest.getInstance("MD5");   
		              
		            /* Add plain-text password bytes to digest using MD5 update() method. */  
		            m.update(password.getBytes());  
		              
		            /* Convert the hash value into bytes */   
		            byte[] bytes = m.digest();  
		              
		            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
		            StringBuilder s = new StringBuilder();  
		            for(int i=0; i< bytes.length ;i++)  
		            {  
		                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
		            } 
		            
		              
		            
		            
		            /* Complete hashed password in hexadecimal format */  
		           encryptedPassword1 = s.toString();  
		        }   
		        catch (NoSuchAlgorithmException e)   
		        {  
		            e.printStackTrace();  
		        }  
			
		pipelineCursor.destroy();
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "encryptedPassword", encryptedPassword1 );
		pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}
}

