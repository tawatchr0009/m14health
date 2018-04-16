package com.healthrecord.mobile.logic;

import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonLogic {
	public boolean applay(JsonNode role,JsonNode data) {
		Boolean isApply = null;
		Iterator<String> keyIter = role.fieldNames();
		while(keyIter.hasNext()) {
			String operator = keyIter.next();
			System.out.println("oper_path:"+operator);
			JsonNode condNode = role.path(operator);
			Iterator<JsonNode> elementIter = condNode.elements();
			JsonNode e1 = elementIter.next();
			while(elementIter.hasNext()) {
				JsonNode e2 = elementIter.next();
				boolean isUnitApply =  apply(operator,e1,e2,data);
				if (isApply==null) {
					isApply = isUnitApply;
				}else {
					isApply = isApply && isUnitApply;
				}
				e1 = e2;
			}
			
		}
		
		
		if (isApply==null) {
			isApply = false;
		}
		return isApply;
	}

	private boolean apply(String operator, JsonNode e1, JsonNode e2, JsonNode data) {
		boolean isApply = false;
		if (operator.equals("<")) {
			if (isLessThan(e1,e2,data)) {
				isApply = true;
			};
		}else if (operator.equals("<=")) {
			if (isLessThanEqual(e1,e2,data)) {
				isApply = true;
			};
		} else if (operator.equals(">")) {
			if (isMoreThan(e1,e2,data)) {
				isApply = true;
			};
		} else if (operator.equals(">=")) {
			if (isMoreThanEqual(e1,e2,data)) {
				isApply = true;
			};
		}
		return isApply;
	}

	public Double getDouble(JsonNode node,JsonNode data) {
		
		if (node.isNumber()) {
			System.out.println(" getDouble.isNumber: "+node.asDouble());
			return node.asDouble();
		} else if (node.isObject()) {
			JsonNode varNode = node.path("var");
			String varName = varNode.asText();
			System.out.println(" getDouble.isObject: varName->"+varName);
			JsonNode valueNode = data.path(varName);
			System.out.println(" getDouble.isObject: double->"+valueNode.asDouble());
			return valueNode.asDouble();
		} else {
			return null;
		}
	}
	
	private boolean isLessThan(JsonNode e1, JsonNode e2,JsonNode data) {
		boolean isLessThan = false;
		if (getDouble(e1,data) < getDouble(e2,data)) {
			isLessThan = true;
		}
		return isLessThan;
	}
	
	
	private boolean isLessThanEqual(JsonNode e1, JsonNode e2,JsonNode data) {
		boolean isLessThan = false;
		if (getDouble(e1,data) <= getDouble(e2,data)) {
			isLessThan = true;
		}
		return isLessThan;
	}
	private boolean isMoreThan(JsonNode e1, JsonNode e2,JsonNode data) {
		boolean isLessThan = false;
		if (getDouble(e1,data) > getDouble(e2,data)) {
			isLessThan = true;
		}
		return isLessThan;
	}
	private boolean isMoreThanEqual(JsonNode e1, JsonNode e2,JsonNode data) {
		boolean isLessThan = false;
		if (getDouble(e1,data) >= getDouble(e2,data)) {
			isLessThan = true;
		}
		return isLessThan;
	}
}
