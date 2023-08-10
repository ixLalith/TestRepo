package IXUserManagement.work;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.ACLManager;
import com.softwareag.util.IDataMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import com.wm.app.b2b.server.Session;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import com.innovatechs.jwt.Action;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import io.jsonwebtoken.JwtBuilder;
import com.innovatechs.authetication.TokenAuthenticator;
// --- <<IS-END-IMPORTS>> ---

public final class javaServices

{
	// ---( internal utility methods )---

	final static javaServices _instance = new javaServices();

	static javaServices _newInstance() { return new javaServices(); }

	static javaServices _cast(Object o) { return (javaServices)o; }

	// ---( server methods )---




	public static final void getJWTToken (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getJWTToken)>> ---
		// @sigtype java 3.5
		// [i] field:0:required secretKey
		// [i] recref:1:required userData IXUserManagement.documents:UserData
		// [i] field:0:required expirationTime
		// [o] field:0:required jwtToken
		// pipeline 
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	secretKey = IDataUtil.getString( pipelineCursor, "secretKey" );
			String	expirationTime = IDataUtil.getString( pipelineCursor, "expirationTime" );
			long expTimeMillis = Long.valueOf(expirationTime);
		    Map<String, Object> claims = new HashMap<String, Object>();
			// userData 
			IData[]	userData = IDataUtil.getIDataArray( pipelineCursor, "userData" );
			if ( userData != null) 
			{
				for ( int i = 0; i < userData.length; i++ )
				{
					IDataCursor userDataCursor = userData[i].getCursor();
						String	key = IDataUtil.getString( userDataCursor, "key" );
						String	value = IDataUtil.getString( userDataCursor, "value" );
						value = value.startsWith("%") && value.endsWith("%") ? "" : value;
						if(key != null && value != null){
							claims.put(key, value);
						}
					userDataCursor.destroy();
				} 
			}
		pipelineCursor.destroy();
		
		TokenAuthenticator token = new TokenAuthenticator();
		String jwtToken = token.getJWTUsingHS256(claims, secretKey, expTimeMillis);		
		// pipeline
		//
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "jwtToken", jwtToken );
		pipelineCursor_1.destroy();
			
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getLoggedInUser (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getLoggedInUser)>> ---
		// @sigtype java 3.5
		// [o] field:0:required userId
		// [o] field:0:required roles
		// pipeline
			Session session = Service.getSession()	;		
		//String
			String userId = session.getUser().getName();
			//String userId = (String) session.get("username");
			IDataCursor userCursor = session.getUser().getAsData().getCursor();
			IData members = IDataUtil.getIData(userCursor,"members");
			IDataCursor membersCursor = members.getCursor();
			String roles = "";
			while(membersCursor.hasMoreData()){
				membersCursor.next();
				roles = roles + ","+membersCursor.getKey();
			}
			membersCursor.destroy();
			userCursor.destroy();
		//session.setUser(session.getUser());
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		//IDataUtil.put( pipelineCursor, "userData",session.getUser().getAsData());
		//IDataUtil.put( pipelineCursor, "source",session.getUser().getSourceName());
		IDataUtil.put( pipelineCursor, "userId",userId);
		IDataUtil.put( pipelineCursor, "roles",roles);
		pipelineCursor.destroy();
			
			
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void getSignedJWTtoken (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getSignedJWTtoken)>> ---
		// @sigtype java 3.5
		// [i] field:0:required issuer
		// [i] field:0:required audiance
		// [i] object:0:required expiryHours
		// [i] field:0:required privateKey
		// [i] record:1:required claims
		// [i] - field:0:required key
		// [i] - field:0:required value
		// [i] field:0:required externalDirSer
		// [i] field:0:required internalDirSer
		// [i] field:0:required subject
		// [i] field:0:required roles
		// [i] field:0:required userType
		// [o] field:0:required token
		// [o] field:0:required isError
		//pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		String	issuer = IDataUtil.getString( pipelineCursor, "issuer" );
		String	roles = IDataUtil.getString( pipelineCursor, "roles" );
		String	audiance = IDataUtil.getString( pipelineCursor, "audiance" );
		//String	subject = IDataUtil.getString( pipelineCursor, "subject" );
		int	expiryHours = IDataUtil.getInt(pipelineCursor, "expiryHours", 0);
		String	privateKey = IDataUtil.getString( pipelineCursor, "privateKey" );
		String	externalDirSer = IDataUtil.getString( pipelineCursor, "externalDirSer" );
		String	internalDirSer = IDataUtil.getString( pipelineCursor, "internalDirSer" );
		Map<String, Object> claimsMap = new HashMap<String, Object>();
		// claims
		IData[]	claims = IDataUtil.getIDataArray( pipelineCursor, "claims" );
		Date issueDate = Date.from(Instant.now());
		Date expiryDate = Date.from(Instant.now().plusSeconds(3600*expiryHours));
		String token = "";
		String isError = "true";
		String	userType = IDataUtil.getString( pipelineCursor, "userType" );
		String subject = IDataUtil.getString( pipelineCursor, "subject" );
		
			
		
		try{
			PrivateKey privateKeyValue = getPrivateKey(privateKey);
			//token = 
			JwtBuilder jwts = 	Jwts.builder().setSubject(subject)
					.setExpiration(expiryDate)
					.setIssuer(issuer)
					.setAudience(audiance);
		
		
			isError = "false";
		
			if ( claims != null)
			{
				for ( int i = 0; i < claims.length; i++ )
				{
					IDataCursor claimsCursor = claims[i].getCursor();
					String	key = IDataUtil.getString( claimsCursor, "key" );
					String	value = IDataUtil.getString( claimsCursor, "value" );
					value = value.startsWith("%") && value.endsWith("%") ? "" : value;
					if(key != null && value != null){
						jwts.claim(key, value);
					}
					claimsCursor.destroy();
				}
			}
			
			if(userType==null || userType.isEmpty()){
			Session session = Service.getSession();
			
			if(session.getUser().getSourceName().split("/")[0].equalsIgnoreCase(internalDirSer))
			{
				jwts.claim("userType", "INTERNAL");
			}
				
			if(session.getUser().getSourceName().split("/")[0].equalsIgnoreCase(externalDirSer))
			{
				jwts.claim("userType", "EXTERNAL");
			}
			}
			else{
				jwts.claim("userType", "EXTERNAL");
			}
			jwts.claim("role", roles.substring(1));
			// RS256 with privateKey
			token = jwts.signWith(SignatureAlgorithm.RS256, privateKeyValue).compact();
			//token = session.getUser().getSourceName().split("/")[0];
		
		}
		
		catch (Exception e) {
			// TODO: handle exception
			//token = e.getLocalizedMessage();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			token = sw.toString();
			isError = "true";
		}
		
		pipelineCursor.destroy();
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "token", token );
		IDataUtil.put( pipelineCursor_1, "isError", isError );
		pipelineCursor_1.destroy();
			
			
			
		// --- <<IS-END>> ---

                
	}



	public static final void setLoggedUserDetails (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(setLoggedUserDetails)>> ---
		// @sigtype java 3.5
		// [i] record:0:required input
		// [i] - field:0:required fullName
		// [i] - field:0:required userId
		// [i] - field:0:required applicantId
		// [i] - record:1:required linkedTradeLicences
		// [i] -- field:0:required applicantId
		// [i] -- field:0:required applicantLogo
		// [i] -- field:0:required tradeLicenseNo
		// [i] - field:0:required emiratesId
		// [i] - field:0:required profilePicture
		// [i] - field:0:required userType
		// [i] - field:1:required listRoles
		// [o] record:0:required output
		// [o] - field:0:required fullName
		// [o] - field:0:required userId
		// [o] - field:0:required applicantId
		// [o] - record:1:required linkedTradeLicences
		// [o] -- field:0:required applicantId
		// [o] -- field:0:required applicantLogo
		// [o] -- field:0:required tradeLicenseNo
		// [o] - field:0:required emiratesId
		// [o] - field:0:required profilePicture
		// [o] - field:0:required userType
		// [o] - field:1:required listRoles
		//Get  the session object
		Session session = Service.getSession();
		 // Start a transaction  
		
		  // Get the IDataCursor to access input document variables
		
		 IDataCursor inputCursor = pipeline.getCursor();
		 IData	input = IDataUtil.getIData( inputCursor, "input" );	
		  
		  // Set session variables from the input document
		  String fullName = IDataUtil.getString(inputCursor, "fullName");
		  String userId = IDataUtil.getString(inputCursor, "userId");
		  String applicantId = IDataUtil.getString(inputCursor, "applicantId");
		  String emiratesId = IDataUtil.getString(inputCursor, "emiratesId");
		  String profilePicture = IDataUtil.getString(inputCursor, "profilePicture");
		  
		  // Set session variables for fullName, userId, applicantId, emiratesId, profilePicture
		  session.put("fullName", fullName);
		  session.put("userId", userId);
		  session.put("applicantId", applicantId);
		  session.put("emiratesId", emiratesId);
		  session.put("profilePicture", profilePicture);
				  // Set session variables for linkedTradeLicences array
		 // IDataArray linkedTradeLicencesArray = IDataUtil.getIDataArray(inputCursor, "linkedTradeLicences");
		  IData linkedTradeLicencesArray[] = IDataUtil.getIDataArray(inputCursor,"linkedTradeLicences");
		  if (linkedTradeLicencesArray != null) {
		    for (int i = 0; i < linkedTradeLicencesArray.length; i++) {
		      IData linkedTradeLicence = linkedTradeLicencesArray[i];
		      String tradeLicenseNo = IDataUtil.getString((IDataCursor) linkedTradeLicence, "tradeLicenseNo");
		      String applicantLogo = IDataUtil.getString((IDataCursor) linkedTradeLicence, "applicantLogo");
		      String linkedApplicantId = IDataUtil.getString((IDataCursor) linkedTradeLicence, "applicantId");
		      
		      //String tradeLicenseNo = IDataUtil.getString(linkedTradeLicence, "tradeLicenseNo");
		      //String applicantLogo = IDataUtil.getString(linkedTradeLicence, "applicantLogo");
		      //String linkedApplicantId = IDataUtil.getString(linkedTradeLicence, "applicantId");
		      
		      // Set the session variables for linkedTradeLicences
		      session.put("linkedTradeLicences[" + i + "].tradeLicenseNo", tradeLicenseNo);
		      session.put("linkedTradeLicences[" + i + "].applicantLogo", applicantLogo);
		      session.put("linkedTradeLicences[" + i + "].applicantId", linkedApplicantId);
		    }
		  }
		  String userType = IDataUtil.getString(inputCursor, "userType");
		  session.put("userType", userType);
		  String[] listRoles = IDataUtil.getStringArray(inputCursor, "listRoles");
		  if (listRoles != null) {
		        for (int i = 0; i < listRoles.length; i++) {
		            session.put("listRoles[" + i + "]", listRoles[i]);
		        }
		    }
		  
		  inputCursor.destroy();
		  
		  // Create the output IData document
		  IData	output = IDataFactory.create();	
		  IDataCursor pipelineCursor_1 = pipeline.getCursor();
		  		  
		  IDataUtil.copy(input, output);
		  IDataUtil.put( pipelineCursor_1, "output", output );
		  pipelineCursor_1.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void validateTokenAndGetData (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validateTokenAndGetData)>> ---
		// @sigtype java 3.5
		// [i] field:0:required secretKey
		// [i] field:0:required jwtToken
		// [o] recref:0:required output IXCommon.documents:JWTResponse
		//  pipeline
				IDataCursor pipelineCursor = pipeline.getCursor();
					String	secretKey = IDataUtil.getString( pipelineCursor, "secretKey" );
					String	jwtToken = IDataUtil.getString( pipelineCursor, "jwtToken" );
				pipelineCursor.destroy();
				String error ="";
				 Map<String, Object> claimsData=new HashMap<String, Object>();
			   try{
		//				PublicKey publicKey = getPublicKey(secretKey);
				   
		
				   claimsData = new HashMap<>(validateToken(jwtToken, secretKey));
				   
				   //data =validateToken(jwtToken, secretKey );
				//IData[]	userData = null;
				
				
			   }catch(Exception e) {
					// TODO: handle exception
					//token = e.getLocalizedMessage();
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					e.printStackTrace(pw);
					error = sw.toString();
			   }
			// pipeline
				IDataCursor pipelineCursor_1 = pipeline.getCursor();
				IData output = IDataFactory.create();
				IDataCursor outputCursor = output.getCursor();
				
				for(Entry<String, Object> entry : claimsData.entrySet()) {
			       // expectedMap.put(entry.getKey() , entry.getValue());
			        IDataUtil.put( outputCursor, entry.getKey(), entry.getValue() );
			    }
				outputCursor.destroy();
				//IDataUtil.put( pipelineCursor_1, "isValid", "" );
				IDataUtil.put( pipelineCursor_1, "output", output );
				IDataUtil.put( pipelineCursor_1, "error", error );
				pipelineCursor_1.destroy();
			
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static PrivateKey getPrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
	    
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    PrivateKey privKey = kf.generatePrivate(keySpec);
	    return privKey;
	}
					
	public static Claims validateToken(String token, String pubKey) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, InvalidKeySpecException, NoSuchAlgorithmException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec pubKeySpecX509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(pubKey));
		Jws parseClaimsJws = Jwts.parser().setSigningKey(kf.generatePublic(pubKeySpecX509EncodedKeySpec)).parseClaimsJws(token);
	    
	//		System.out.println("Header     : " + parseClaimsJws.getHeader());
	//
	//		System.out.println("Body       : " + parseClaimsJws.getBody());
	//
	//		System.out.println("Signature  : " + parseClaimsJws.getSignature());
		Claims claims = (Claims)parseClaimsJws.getBody();
		return claims;
	}
	// --- <<IS-END-SHARED>> ---
}

