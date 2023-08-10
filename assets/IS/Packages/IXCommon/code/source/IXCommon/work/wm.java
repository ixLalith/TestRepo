package IXCommon.work;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ACLManager;
import java.io.PrintWriter;
import java.io.StringWriter;
// --- <<IS-END-IMPORTS>> ---

public final class wm

{
	// ---( internal utility methods )---

	final static wm _instance = new wm();

	static wm _newInstance() { return new wm(); }

	static wm _cast(Object o) { return (wm)o; }

	// ---( server methods )---




	public static final void setACL (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setACL)>> ---
		// @sigtype java 3.5
		// [i] record:1:required config
		// [i] - field:0:required resourceName
		// [i] - field:0:required aclName
		// [o] record:0:required result
		// [o] - field:0:required message
		// [o] - field:0:required isSuccess
		String message = "Failed";
		String isSuccess = "false";
		
		try{
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		
			// config
			IData[]	config = IDataUtil.getIDataArray( pipelineCursor, "config" );
			if ( config != null)
			{
				for ( int i = 0; i < config.length; i++ )
				{
					IDataCursor configCursor = config[i].getCursor();
						String	resourceName = IDataUtil.getString( configCursor, "resourceName" );
						String	aclName = IDataUtil.getString( configCursor, "aclName" );
						ACLManager.setAclGroup(resourceName, aclName);
					configCursor.destroy();
				}
				
			}
		pipelineCursor.destroy();
		
		message = "Success";
		isSuccess = "true";
		}
		catch (Exception e) {
			// TODO: handle exception
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			message = sw.toString();
			isSuccess = "false";
		}
		
		
		
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		
		// result
		IData	result = IDataFactory.create();
		IDataCursor resultCursor = result.getCursor();
		IDataUtil.put( resultCursor, "message", message );
		IDataUtil.put( resultCursor, "isSuccess", isSuccess );
		resultCursor.destroy();
		IDataUtil.put( pipelineCursor_1, "result", result );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}
}

