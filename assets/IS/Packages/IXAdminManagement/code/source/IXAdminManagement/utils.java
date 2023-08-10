package IXAdminManagement;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void stringContain (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringContain)>> ---
		// @sigtype java 3.5
		// [i] field:0:required inputString
		// [i] field:0:required containString
		// [o] object:0:required outputString
		boolean out=false;
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	inputString = IDataUtil.getString( pipelineCursor, "inputString" );
			String	containString = IDataUtil.getString( pipelineCursor, "containString" );
		pipelineCursor.destroy();
		out=inputString.contains(containString); 
		
		// pipeline
		if(inputString ==null){
			inputString=" ";
		}
		IDataCursor pipelineCursor1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor1, "outputString", out );
		pipelineCursor1.destroy();
			
		// --- <<IS-END>> ---

                
	}
}

