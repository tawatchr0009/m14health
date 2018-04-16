package com.healthrecord.mobile.vm;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.util.media.AMedia;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.logic.JsonLogic;
import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.model.UserProfileM;
import com.healthrecord.mobile.services.INCDsDataService;
import com.healthrecord.mobile.services.USERPROFILE;
import com.healthrecord.mobile.services.impl.IndicatorRolesDataServiceImpl;
import com.healthrecord.mobile.services.impl.NCDsDataServiceImpl;

public class NCDsVM extends BaseVM implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<NCDsM> ncds = new ArrayList<NCDsM>();
	private UserProfileM myProfile = null;
	private JsonNode profileNode;
	private JsonNode indicatorNode;
	
	@Init
	public void init() {
		try {
			ncds = getNCDs();
//			myProfile = callService(USERPROFILE.U0001, UserProfileM.class);
			 profileNode = callJsonNodeService(USERPROFILE.U0001);
			
		}catch(Exception ex) {
			ncds = new ArrayList<NCDsM>();
		}
	}
	public <T> T findElementById(T[] items,String name,String value) {
		if (items!=null) {
			for(T t: items) {
				String v_t = "";
				if (v_t!=null && value!=null && v_t.equals(value)) {
					return t;
				}
			}
		};
		return null;
	}
	public UserIndicatorM getUserIndicatorM(JsonNode pNode,String indicator)throws Exception {
		JsonNode iNode = getUserIndicatorNode(pNode,indicator);
				if (iNode!=null) {
					System.out.println(" indicator -> "+indicator);

						ObjectMapper mapper = new ObjectMapper();
						UserIndicatorM iObj = mapper.treeToValue(iNode, UserIndicatorM.class);

						System.out.println("iObj.value:"+iObj.getValue());
						return iObj;

				};

		
		return null;
		
	}
	
	public JsonNode getUserIndicatorNode(JsonNode pNode,String indicator)throws Exception {
		if (pNode!=null) {
			JsonNode dNode = pNode.path("indicatorDoc");
			if (dNode!=null && dNode.has(indicator)) {
				System.out.println("indicatorDoc id: "+dNode.path("id").asText()+" with-indicator :"+indicator);
				JsonNode iNode = dNode.path(indicator);
				return iNode;
//				if (iNode!=null) {
//					System.out.println(" indicator -> "+indicator);
////					String iText = iNode.asText();
////					if (iText!=null) {
//						ObjectMapper mapper = new ObjectMapper();
//						UserIndicatorM iObj = mapper.treeToValue(iNode, UserIndicatorM.class);
////						UserIndicatorM iObj = mapper.readValue(iText, UserIndicatorM.class);
//						System.out.println("iObj.value:"+iObj.getValue());
//						return iObj;
////					};
//				};
			};
		}
//		String i_doc = profile.getIndicatorDoc();
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode jNode = mapper.readTree(i_doc);
		
		return null;
		
	}
	public String getIndicatorLevel(UserIndicatorM userIndicator) throws Exception {
		String level = "A0";
		if (userIndicator!=null) {
			Random random = new Random();
			int i = random.nextInt(3)+1;
			level = level+i;
		}else {
			level = "A00";
		}
//		profile.getIndicators().
		return level;
	}
	public String getIndicatorBackground(JsonNode uNode ,String indicator) throws Exception {
//		UserIndicatorM uIndicator =	findElementById(profile.getIndicators(),"id",indicator);
//		UserIndicatorM uIndicator =getUserIndicatorM(uNode,indicator);
//		String level = getIndicatorLevel(uIndicator);
		JsonNode iNode = getUserIndicatorNode(uNode,indicator);
		if (iNode!=null && iNode.has("indicator_role")) {
			String roleRef = iNode.path("indicator_role").asText();
			System.out.println("indicator_role:"+roleRef);
			String endpoint = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/indicator_roles";
			IndicatorRolesDataServiceImpl dService = new IndicatorRolesDataServiceImpl(endpoint);
			
			JsonNode roleRefNode = dService.getDataJsonNode(roleRef);
			System.out.println("roleRefNode:"+roleRefNode.path("_id").asText());
			Iterator<JsonNode> roleIter = roleRefNode.path("roles").elements();
			while(roleIter.hasNext()) {
				JsonNode roleNode = roleIter.next();
				JsonNode condNode = roleNode.path("condition");
				JsonLogic logic = new JsonLogic();
				boolean isApplyRole = logic.applay(condNode, iNode);
				if (isApplyRole) {
					return "bg_"+roleNode.path("result").asText()+".png";
				}
			}
			
		}
		
		return "bg_A00.png";
	}
	public AImage getIndicatorImage(UserProfileM profile,String indicator) throws Exception {
		
		InputStream is = NCDsVM.class.getClassLoader().getResourceAsStream("BMI.png");
		
		return new AImage("ima.png",is);
	}

	private List<NCDsM> getNCDs() throws Exception{
		 String ncds_endpoint   = "https://120f7ec5-945c-42e4-904b-e8cdf79f6b8e-bluemix.cloudant.com/ncds/_all_docs?include_docs=true";
			
		INCDsDataService ncdsService = new NCDsDataServiceImpl(ncds_endpoint);
		return ncdsService.getAll();
	}

	public List<NCDsM> getNcds() {
		return ncds;
	}

	public void setNcds(List<NCDsM> ncds) {
		this.ncds = ncds;
	}
	public UserProfileM getMyProfile() {
		return myProfile;
	}
	public void setMyProfile(UserProfileM myProfile) {
		this.myProfile = myProfile;
	}
	public JsonNode getProfileNode() {
		return profileNode;
	}
	public void setProfileNode(JsonNode profileNode) {
		this.profileNode = profileNode;
	}
	public JsonNode getIndicatorNode() {
		return indicatorNode;
	}
	public void setIndicatorNode(JsonNode indicatorNode) {
		this.indicatorNode = indicatorNode;
	}

}
