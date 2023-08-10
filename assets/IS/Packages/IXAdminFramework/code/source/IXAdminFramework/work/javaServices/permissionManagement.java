package IXAdminFramework.work.javaServices;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
// --- <<IS-END-IMPORTS>> ---

public final class permissionManagement

{
	// ---( internal utility methods )---

	final static permissionManagement _instance = new permissionManagement();

	static permissionManagement _newInstance() { return new permissionManagement(); }

	static permissionManagement _cast(Object o) { return (permissionManagement)o; }

	// ---( server methods )---




	public static final void deletePermissionJava (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deletePermissionJava)>> ---
		// @sigtype java 3.5
		// [i] record:1:required deletePermissionInputJava
		// [i] - field:0:required FEATURE_ID
		// [i] - field:0:required FEATURE_NAME
		// [i] - field:0:required MANAGED_BY
		// [i] - field:0:required LAST_UPDATED_BY
		// [i] - object:0:required LAST_UPDATED_DATE
		// [i] - field:0:required IS_SECTION
		// [i] - field:0:required PARENT_ID
		// [i] - field:0:required ROUTE_URL
		// [i] - field:0:required SVG
		// [i] - field:0:required ICON
		// [i] - field:0:required MODULE_ORDER
		// [i] - field:0:required MENU_ID
		// [i] - field:0:required MODULE_ID
		// [i] - field:0:required FEATURE_TYPE
		// [i] - field:0:required MENU_NAME
		// [i] - field:0:required MODULE_NAME
		// [i] - field:0:required PERMISSION_ID
		// [i] - field:0:required ROLE_ID
		// [i] field:0:required INPUT_FEATURE_ID
		// [i] field:0:required INPUT_MODULE_ID
		// [i] field:0:required IS_ENABLED
		// [i] field:0:required INPUT_PERMISSION_ID
		// [i] field:0:required READ_FEATURE_ID
		// [i] field:0:required INPUT_ROLE_ID
		// [o] record:1:required deletePermissionOutput
		// [o] - field:0:required permissionId
		// [o] - field:0:required roleId
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			IData[] inputList = IDataUtil.getIDataArray( pipelineCursor, "deletePermissionInputJava" );
			String inputFeatureId = IDataUtil.getString(pipelineCursor, "INPUT_FEATURE_ID");
			String inputModuleId = IDataUtil.getString(pipelineCursor, "INPUT_MODULE_ID");
			String inputPermissionId = IDataUtil.getString(pipelineCursor, "INPUT_PERMISSION_ID");
			String readFeatureId = IDataUtil.getString(pipelineCursor, "READ_FEATURE_ID");
			String inputRoleId = IDataUtil.getString(pipelineCursor, "INPUT_ROLE_ID");
			ArrayList<IData> finalValues = new ArrayList<>(); 
			for(IData el: inputList) {
				HashMap<String, String> inputValues = getInputValues(el);
				if(inputValues.get("permissionId").equals(inputPermissionId)) {
					// ** Check and add Read permission (If more than one feature is enabled, then read should not be deleted) ** //
					if(!inputValues.get("featureId").equals(readFeatureId)) {
						String readPermissionId = getPermissionIdByModuleAndFeature(inputValues.get("moduleId"), readFeatureId, inputList);
						if(readPermissionId != null && checkIfReadToBeDeleted(inputValues.get("moduleId"), readFeatureId, inputValues.get("permissionId"), inputRoleId, inputList)) 
							addRolePermission(readPermissionId, inputRoleId, finalValues);
					}
					addRolePermission(inputValues.get("permissionId"), inputRoleId, finalValues);
					checkAndDeleteChildModules(inputList, inputValues, inputFeatureId, readFeatureId, inputRoleId, finalValues);
					// ** Checking Parent Modules only if the current module's parentId != 0 ** //
					if(!inputValues.get("parentId").equals("0") || inputValues.get("parentId").equals(null)) {
						checkAndDeleteParentModules(inputList, inputValues, inputFeatureId, readFeatureId, inputRoleId, finalValues);
					}
				}
			}
			IDataUtil.put(pipelineCursor, "deletePermissionOutput", convertToIDataArray(finalValues));
			pipelineCursor.destroy();
		} catch(Exception e) {
			pipelineCursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void insertPermissionJava (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(insertPermissionJava)>> ---
		// @sigtype java 3.5
		// [i] record:1:required preparePermissionInput
		// [i] - field:0:required FEATURE_ID
		// [i] - field:0:required FEATURE_NAME
		// [i] - field:0:required MANAGED_BY
		// [i] - field:0:required LAST_UPDATED_BY
		// [i] - object:0:required LAST_UPDATED_DATE
		// [i] - field:0:required IS_SECTION
		// [i] - field:0:required PARENT_ID
		// [i] - field:0:required ROUTE_URL
		// [i] - field:0:required SVG
		// [i] - field:0:required ICON
		// [i] - field:0:required MODULE_ORDER
		// [i] - field:0:required MENU_ID
		// [i] - field:0:required MODULE_ID
		// [i] - field:0:required FEATURE_TYPE
		// [i] - field:0:required MENU_NAME
		// [i] - field:0:required MODULE_NAME
		// [i] - field:0:required PERMISSION_ID
		// [i] field:0:required INPUT_FEATURE_ID
		// [i] field:0:required INPUT_MODULE_ID
		// [i] field:0:required IS_ENABLED
		// [i] field:0:required INPUT_PERMISSION_ID
		// [i] field:0:required READ_FEATURE_ID
		// [i] field:0:required ROLE_ID
		// [o] record:1:required preparePermissionOutput
		// [o] - field:0:required permissionId
		// [o] - field:0:required roleId
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			IData[] inputList = IDataUtil.getIDataArray( pipelineCursor, "preparePermissionInput" );
			String inputFeatureId = IDataUtil.getString(pipelineCursor, "INPUT_FEATURE_ID");
			String inputModuleId = IDataUtil.getString(pipelineCursor, "INPUT_MODULE_ID");
			String inputPermissionId = IDataUtil.getString(pipelineCursor, "INPUT_PERMISSION_ID");
			String readFeatureId = IDataUtil.getString(pipelineCursor, "READ_FEATURE_ID");
			String roleId = IDataUtil.getString(pipelineCursor, "ROLE_ID");
			ArrayList<IData> finalValues = new ArrayList<>(); 
			for(IData el: inputList) {
				HashMap<String, String> inputValues = getInputValues(el);
				if(inputValues.get("permissionId").equals(inputPermissionId)) {
					// ** Adding Read Permission for input module by default ** //
					if(!inputValues.get("featureId").equals(readFeatureId)) {
						String readPermissionId = getPermissionIdByModuleAndFeature(inputValues.get("moduleId"), readFeatureId, inputList);
						addRolePermission(readPermissionId, roleId, finalValues);
					}
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
					getChildModules(inputList, inputValues, inputPermissionId, inputFeatureId, inputModuleId,readFeatureId,roleId, finalValues);
					// ** Checking Parent Modules only if the current module's parentId != 0 ** //
					if(!inputValues.get("parentId").equals("0") || inputValues.get("parentId").equals(null)) {
						getParentModules(inputList, inputValues, inputPermissionId, inputFeatureId, inputModuleId,readFeatureId,roleId, finalValues);
					}
				}
			}
			IDataUtil.put(pipelineCursor, "preparePermissionOutput", convertToIDataArray(finalValues));
			pipelineCursor.destroy();
		} catch(Exception e) {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void prepareMenuJava (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prepareMenuJava)>> ---
		// @sigtype java 3.5
		// [i] record:1:required menuInput
		// [i] - field:0:required FEATURE_ID
		// [i] - field:0:required FEATURE_NAME
		// [i] - field:0:required MANAGED_BY
		// [i] - field:0:required LAST_UPDATED_BY
		// [i] - object:0:required LAST_UPDATED_DATE
		// [i] - field:0:required IS_SECTION
		// [i] - field:0:required PARENT_ID
		// [i] - field:0:required ROUTE_URL
		// [i] - field:0:required SVG
		// [i] - field:0:required ICON
		// [i] - field:0:required MODULE_ORDER
		// [i] - field:0:required MENU_ID
		// [i] - field:0:required MODULE_ID
		// [i] - field:0:required FEATURE_TYPE
		// [i] - field:0:required MENU_NAME
		// [i] - field:0:required ONLY_CHECKED
		// [i] - field:0:required INPUT_ROLES
		// [i] - field:0:required ROLE_ID
		// [i] - field:0:required MODULE_NAME
		// [i] - field:0:required PERMISSION_ID
		// [i] - field:0:required ROLE_PERMISSION_ID
		// [i] - field:0:required isProfileInformation
		// [o] object:1:required output
		IDataCursor pipelineCursor = pipeline.getCursor();
		try {
			IData[] inputList = IDataUtil.getIDataArray( pipelineCursor, "menuInput" );
			
			ArrayList<IData> finalModules = new ArrayList<>();
			
			//** Adding Parent Modules **//
			for(IData el: inputList) {
				HashMap<String, String> inputValues = getInputValues(el);
				String parentId = inputValues.get("parentId");
				if(parentId.equals(null) || parentId.equals("") || parentId.equals("0")) {
					if(!checkIsExist(inputValues.get("moduleId"), finalModules)) {
						IData moduleIData = addModule(inputValues);
						finalModules.add(moduleIData);
						addFeaturesToModule(inputList, moduleIData);
						if(inputValues.get("isProfileInformation") != null && inputValues.get("isProfileInformation").equals("true")) {
							checkAndDeleteModule(finalModules, moduleIData);
						}
					}
				}
			}
			for(IData el: finalModules) 
				getChildModule(el, inputList);
			IDataUtil.put(pipelineCursor, "output", convertToIDataArray(finalModules));
		}
		finally {
			pipelineCursor.destroy();
		}
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	// ======== Delete Permission Java Service (UTILS) ========== //
	
	private static Boolean checkIfReadToBeDeleted(String moduleId, String readFeatureId, String currentPermissionId, String roleId, IData[] inputList) {
		Boolean isDelete = true;
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("moduleId").equals(moduleId) && inputValues.get("roleId").equals(roleId)
					&& !inputValues.get("featureId").equals(readFeatureId) && !inputValues.get("permissionId").equals(currentPermissionId)) {
				isDelete = false;
				return isDelete;
			}
		}
		return isDelete;
	}
	
	// **** Checking Parent Module and removing same feature and Read Feature aswell (If exists) **** //
	private static void checkAndDeleteParentModules(IData[] inputList,HashMap<String, String> currentInputValues,
			String featureId, String readFeatureId, String roleId, ArrayList<IData> finalValues) {
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("moduleId").equals(currentInputValues.get("parentId"))) {
				if(inputValues.get("featureId").equals(featureId)) {
					if(!checkIfChildrenHasPermission(inputValues.get("moduleId"), inputValues.get("featureId"), roleId, inputValues.get("permissionId"), inputList)){
						addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
					} else if (inputValues.get("featureId").equals(readFeatureId)) {
						if(!checkIfChildrenHasPermission(inputValues.get("moduleId"), inputValues.get("featureId"), roleId, inputValues.get("permissionId"), inputList)){
							addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
						}
					}
				}
				checkAndDeleteParentModules(inputList, inputValues, featureId, readFeatureId, roleId, finalValues);
			}
		}
	}
	
	// **** Check if atleast one children has a given permission, so that the permission should not be deleted in parent **** //
	private static Boolean checkIfChildrenHasPermission(String moduleId, String featureId, String roleId, String currentChildPermissionId, IData[] inputList ) {
		Boolean hasPermission = false;
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("parentId").equals(moduleId) && inputValues.get("featureId").equals(featureId) && 
				 inputValues.get("roleId").equals(roleId) && !inputValues.get("permissionId").equals(currentChildPermissionId)) {
				hasPermission = true;
				return hasPermission;
			}
		}
		return hasPermission;
	}
	
	// **** Checking Child Module and removing same feature and Read Feature aswell (If exists) **** //
	private static void checkAndDeleteChildModules(IData[] inputList,HashMap<String, String> currentInputValues,
			String featureId, String readFeatureId, String roleId, ArrayList<IData> finalValues) {
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("parentId").equals(currentInputValues.get("moduleId"))) {
				if(!currentInputValues.get("featureId").equals(readFeatureId)) {
					if(checkIfReadToBeDeleted(inputValues.get("moduleId"), readFeatureId, inputValues.get("permissionId"), roleId, inputList)) {
						addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
					}
				} else if(inputValues.get("featureId").equals(featureId)) {
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
				}
				checkAndDeleteParentModules(inputList, inputValues, featureId, readFeatureId, roleId, finalValues);
			}
		}
	}
	
	// ======== Insert Permission Java Service (UTILS) ========== //
	
	// **** Fetching Parents of specific module and adding Read feature **** //
	private static void getParentModules(IData[] inputList,HashMap<String, String> currentInputValues, String inputPermissionId, 
			String featureId, String moduleId, String readFeatureId, String roleId, ArrayList<IData> finalValues) {
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("moduleId").equals(currentInputValues.get("parentId"))) {
				if(inputValues.get("featureId").equals(readFeatureId)) {
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
				} else  if(inputValues.get("featureId").equals(featureId)) {
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
				}
				getParentModules(inputList, inputValues, inputPermissionId, featureId, moduleId, readFeatureId, roleId, finalValues);
			}
		}
	}
	
	// **** Fetching Childs of specific module and adding Read feature & current Module feature(If Exist) **** //
	private static void getChildModules(IData[] inputList,HashMap<String, String> currentInputValues, String inputPermissionId, 
			String featureId, String moduleId, String readFeatureId, String roleId, ArrayList<IData> finalValues) {
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("parentId").equals(currentInputValues.get("moduleId"))) {
				if(inputValues.get("featureId").equals(readFeatureId)) {
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
				} else if (inputValues.get("featureId").equals(featureId)) {
					addRolePermission(inputValues.get("permissionId"), roleId, finalValues);
				}
				getChildModules(inputList, inputValues, inputPermissionId, featureId, moduleId, readFeatureId, roleId, finalValues);
			}
		}
	}
	
	// **** Adding PermissionId and roleId to IData **** //
	private static void addRolePermission(String permissionId, String roleId, ArrayList<IData> finalValues) {
		IData permission = IDataFactory.create();
		IDataCursor permissionCursor = permission.getCursor();
		IDataUtil.put(permissionCursor, "permissionId", permissionId);
		IDataUtil.put(permissionCursor, "roleId", roleId);
		permissionCursor.destroy();
		finalValues.add(permission);
	}
	
	private static String getPermissionIdByModuleAndFeature(String moduleId, String featureId, IData[] inputList) {
		String permissionId = null;
		for(IData el : inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			if(inputValues.get("moduleId").equals(moduleId) && inputValues.get("featureId").equals(featureId)) {
				permissionId = inputValues.get("permissionId");
			}
		}
		return permissionId;
	}
	
	// ======== Prepare Menu Java Service (UTILS) ========== //
	
	private static void getChildModule(IData currentModule, IData[] inputList) {
		IDataCursor currentModuleCursor = currentModule.getCursor();
		ArrayList<IData> subModules = new ArrayList<>();
		for(IData el: inputList) {
			HashMap<String, String> inputValues = getInputValues(el);
			String currentModuleId = IDataUtil.getString(currentModuleCursor, "id");
			if(inputValues.get("parentId").equals(currentModuleId)) {
				if(!checkIsExist(inputValues.get("moduleId"), subModules)) {
					IData moduleIData = addModule(inputValues);
					subModules.add(moduleIData);
					getChildModule(moduleIData, inputList);
					addFeaturesToModule(inputList, moduleIData);
					if(inputValues.get("isProfileInformation") != null && inputValues.get("isProfileInformation").equals("true")) {
						checkAndDeleteModule(subModules, moduleIData);
					}
				}
			} 
		}
		IDataUtil.put(currentModuleCursor, "subModules", convertToIDataArray(subModules));
		currentModuleCursor.destroy();
	}
		
		// **** (UTIL) Adding Features to module **** //
		private static void addFeaturesToModule(IData[] inputList, IData module) {
			IDataCursor moduleCursor = module.getCursor();
			ArrayList<IData> features = new ArrayList<>();
			for(IData el: inputList) {
				HashMap<String, String> inputValues = getInputValues(el);
				String moduleId = IDataUtil.getString(moduleCursor, "id");
				if(inputValues.get("moduleId").equals(moduleId)) {
					// *** Adding Features for input User roles (For Permission) *** //
					if((inputValues.get("onlyChecked") != null) && inputValues.get("onlyChecked").equals("true")) {
						Boolean isRoleMatched = checkFeatureForInputRole(inputValues.get("inputRoles"), inputValues.get("moduleId"), inputValues.get("featureId"), inputList);
						if(isRoleMatched) features.add(addFeature(inputValues, null));
					}
					// *** Adding Features for the module *** //
					else if(!checkIsExist(inputValues.get("featureId"), features)) {
						if(inputValues.get("inputRoles") != null) {
							Boolean isRoleMatched = checkFeatureForInputRole(inputValues.get("inputRoles"), inputValues.get("moduleId"), inputValues.get("featureId"), inputList);
							if(isRoleMatched) features.add(addFeature(inputValues, "true"));
							else features.add(addFeature(inputValues, "false"));
						} else {
							features.add(addFeature(inputValues, null));
						}
					}
				} 
			}
			IDataUtil.put(moduleCursor, "features", convertToIDataArray(features));
			moduleCursor.destroy();
		}
		
		// **** (UTIL) Converting input values from IData to Map **** //
		private static HashMap<String, String> getInputValues(IData input) {
			HashMap<String, String> inputValue = new HashMap<>();
			IDataCursor elementCursor = input.getCursor(); 
			inputValue.put("moduleId", IDataUtil.getString(elementCursor, "MODULE_ID"));
			inputValue.put("moduleName", IDataUtil.getString(elementCursor, "MODULE_NAME"));
			inputValue.put("isSection", IDataUtil.getString(elementCursor, "IS_SECTION"));
			inputValue.put("routeUrl", IDataUtil.getString(elementCursor, "ROUTE_URL"));
			inputValue.put("svg", IDataUtil.getString(elementCursor, "SVG"));
			inputValue.put("icon", IDataUtil.getString(elementCursor, "ICON"));
			inputValue.put("moduleOrder", IDataUtil.getString(elementCursor, "MODULE_ORDER"));
			inputValue.put("parentId", IDataUtil.getString(elementCursor, "PARENT_ID"));
			inputValue.put("moduleOrder", IDataUtil.getString(elementCursor, "MODULE_ORDER"));
			inputValue.put("permissionId", IDataUtil.getString(elementCursor, "PERMISSION_ID"));
			inputValue.put("featureName", IDataUtil.getString(elementCursor, "FEATURE_NAME"));
			inputValue.put("featureId", IDataUtil.getString(elementCursor, "FEATURE_ID"));
			inputValue.put("featureType", IDataUtil.getString(elementCursor, "FEATURE_TYPE"));
			inputValue.put("rolePermissionId", IDataUtil.getString(elementCursor, "ROLE_PERMISSION_ID"));
			inputValue.put("onlyChecked", IDataUtil.getString(elementCursor, "ONLY_CHECKED"));
			inputValue.put("inputRoles", IDataUtil.getString(elementCursor, "INPUT_ROLES"));
			inputValue.put("roleId", IDataUtil.getString(elementCursor, "ROLE_ID"));
			inputValue.put("isProfileInformation", IDataUtil.getString(elementCursor, "isProfileInformation"));
			return inputValue;
		}
		
		// **** (UTIL) Adding Module into IData **** //
		private static IData addModule(HashMap<String, String> inputValues) {
			IData moduleIData = IDataFactory.create();
			IDataCursor moduleCursor = moduleIData.getCursor();
			IDataUtil.put(moduleCursor, "id", inputValues.get("moduleId"));
			IDataUtil.put(moduleCursor, "name", inputValues.get("moduleName"));
			IDataUtil.put(moduleCursor, "parentId", inputValues.get("parentId"));
			IDataUtil.put(moduleCursor, "isSection", inputValues.get("isSection"));
			IDataUtil.put(moduleCursor, "routeUrl", inputValues.get("routeUrl"));
			IDataUtil.put(moduleCursor, "svg", inputValues.get("svg"));
			IDataUtil.put(moduleCursor, "icon", inputValues.get("icon"));
			IDataUtil.put(moduleCursor, "order", inputValues.get("moduleOrder"));
			moduleCursor.destroy();
			return moduleIData;
		}
		
		// **** (UTIL) Adding Feature into IData **** //
		private static IData addFeature(HashMap<String, String> inputValues, String isEnabled) {
			IData featureIData = IDataFactory.create();
			IDataCursor featureCursor = featureIData.getCursor();
			IDataUtil.put(featureCursor, "id", inputValues.get("featureId"));
			IDataUtil.put(featureCursor, "name", inputValues.get("featureName"));
			IDataUtil.put(featureCursor, "type", inputValues.get("featureType"));
			IDataUtil.put(featureCursor, "permissionId", inputValues.get("permissionId"));
			if(isEnabled != null) {
				IDataUtil.put(featureCursor, "isEnabled", isEnabled);
			}
			featureCursor.destroy();
			return featureIData;
		}
		
		// **** (UTIL) Converting ArrayList to IData [] **** //
		private static IData[] convertToIDataArray(ArrayList<IData> list) {
			IData[] output = new IData[list.size()];
			for (int i = 0; i < list.size(); i++)
				output[i] = IDataUtil.clone(list.get(i));
			return output;
		}
		
		// **** (UTIL) Checking the duplication of object in a given list **** //
		private static Boolean checkIsExist(String id, ArrayList<IData> list) {
			List<IData> result = list.stream().filter(dup -> IDataUtil.getString(dup.getCursor(), "id").equals(id)).collect(Collectors.toList());
			if(result.isEmpty()) {
				return false;
			}
			return true;
		}
		
		private static Boolean checkFeatureForInputRole(String inputRoles, String moduleId, String featureId, IData[] inputList) {
			String[] rolesList = inputRoles.split(",");
			Boolean isFound = false;
			for(String role : rolesList) {
				for(IData el : inputList) {
					HashMap<String, String> inputValues = getInputValues(el);
					if(inputValues.get("roleId") != null && inputValues.get("moduleId") != null && 
							inputValues.get("featureId") != null && inputValues.get("roleId").equals(role) && 
							inputValues.get("moduleId").equals(moduleId) && inputValues.get("featureId").equals(featureId)) {
						isFound = true;
						return isFound;
					}
				}
			}
			return isFound;
		}
		
		private static void checkAndDeleteModule(ArrayList<IData> list, IData obj) {
			IDataCursor elementCursor = obj.getCursor(); 
			IData[] features = IDataUtil.getIDataArray(elementCursor, "features");
			if(features.length == 0) {
				list.remove(obj);
			}
		}
	// --- <<IS-END-SHARED>> ---
}

