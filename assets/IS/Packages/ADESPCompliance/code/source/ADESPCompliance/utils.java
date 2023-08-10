package ADESPCompliance;

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




	public static final void countForSubmissions (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(countForSubmissions)>> ---
		// @sigtype java 3.5
		// [i] object:0:required totalApproved
		// [i] object:0:required totalSubmitted
		// [i] object:0:required totalReviewed
		// [i] object:0:required totalPending
		// [i] field:0:required status
		// [o] object:0:required totalApproved
		// [o] object:0:required totalSubmitted
		// [o] object:0:required totalReviewed
		// [o] object:0:required totalPending
		// pipeline
		  
		// pipeline
		
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		int	totalApproved = (int) IDataUtil.get( pipelineCursor, "totalApproved" );
		int	totalSubmitted = (int) IDataUtil.get( pipelineCursor, "totalSubmitted" );
		int	totalReviewed = (int) IDataUtil.get( pipelineCursor, "totalReviewed" );
		int	totalPending = (int) IDataUtil.get( pipelineCursor, "totalPending" );
		String	status = IDataUtil.getString( pipelineCursor, "status" );
		pipelineCursor.destroy();
		
		 
		if(status.equals("PENDING")){
			totalPending = totalPending + 1;
		}
		if(!(status.equals("PENDING"))){
			totalSubmitted=totalSubmitted+1;
			
		}      
		if(status.equals("APPROVED")){
			totalApproved = totalApproved+1;
		}
		
		if(status.equals("REVIEWED")  || status.equals("APPROVED")){
			totalReviewed = totalReviewed+1;
		}
		  
		
		  
		
		// pipeline
		IDataCursor pipelineCursor1 = pipeline.getCursor();
		
		IDataUtil.put( pipelineCursor, "totalApproved", totalApproved );
		
		IDataUtil.put( pipelineCursor, "totalSubmitted", totalSubmitted );
		
		IDataUtil.put( pipelineCursor, "totalReviewed", totalReviewed );
		
		IDataUtil.put( pipelineCursor, "totalPending", totalPending );
		pipelineCursor1.destroy();
		
			
		// --- <<IS-END>> ---

                
	}
}

