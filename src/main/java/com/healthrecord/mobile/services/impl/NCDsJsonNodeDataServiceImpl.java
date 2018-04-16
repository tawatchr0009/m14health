package com.healthrecord.mobile.services.impl;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.model.NCDsM;
import com.healthrecord.mobile.model.UserIndicatorM;
import com.healthrecord.mobile.services.INCDsDataService;

public class NCDsJsonNodeDataServiceImpl extends JsonNodeDataServiceImpl  implements INCDsDataService{

	public NCDsJsonNodeDataServiceImpl(String endpoint) {
		super(endpoint);
		
	}

	public List<NCDsM> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void push(NCDsM m) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public NCDsM getById(String id) throws Exception {
		JsonNode dNode = getDataJsonNode(id);
		ObjectMapper mapper = new ObjectMapper();
		NCDsM iObj = mapper.treeToValue(dNode, NCDsM.class);
		return iObj;

	}

}
