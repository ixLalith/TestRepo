package DOERegistration;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import com.webmethods.caf.common.CipherUtil;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void passwordEncryption (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(passwordEncryption)>> ---
		// @sigtype java 3.5
		// [i] field:0:required password
		// [o] field:0:required encryptedPassword
		try {
			IDataMap pipeData = new IDataMap(pipeline);
			String password = pipeData.getAsString("password");
			String encryptedPassword = CipherUtil.encrypt(password);
			encryptedPassword = encryptedPassword.substring(5, encryptedPassword.length());
			pipeData.put("encryptedPassword", encryptedPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		// --- <<IS-END>> ---

                
	}
}

