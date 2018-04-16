package com.healthrecord.mobile.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthrecord.mobile.logic.JsonLogic;

public class JsonLogicClient {

	public static void main(String[] args) {
		try {
			String role1 = "{\n" + 
					"        \"<=\": [\n" + 
					"          {\n" + 
					"            \"var\": \"value\"\n" + 
					"          },\n" + 
					"          18.4\n" + 
					"        ]\n" + 
					"      }";
			
			
			String role2 = "{\n" + 
					"        \"<=\": [\n" + 
					"          18.5,\n" + 
					"          {\n" + 
					"            \"var\": \"value\"\n" + 
					"          },\n" + 
					"          22.9\n" + 
					"        ]\n" + 
					"      }";
			
			String role3 = "{\n" + 
					"        \"<=\": [\n" + 
					"          23,\n" + 
					"          {\n" + 
					"            \"var\": \"value\"\n" + 
					"          },\n" + 
					"          24.9\n" + 
					"        ]\n" + 
					"      }";
			
			String role4 = "{\n" + 
					"        \">=\": [\n" + 
					"          {\n" + 
					"            \"var\": \"value\"\n" + 
					"          },\n" + 
					"          25\n" + 
					"        ]\n" + 
					"      }";
			
			String data = "{\"id\":\"cholesteral\",\"value\":12.0,\"date\":\"20180310\"}";
			
			JsonLogic logic = new JsonLogic();
			ObjectMapper obj1 = new ObjectMapper();
			JsonNode r1Node = obj1.readTree(role1);
			JsonNode r2Node = obj1.readTree(role2);
			JsonNode r3Node = obj1.readTree(role3);
			JsonNode r4Node = obj1.readTree(role4);
			
			ObjectMapper obj2 = new ObjectMapper();
			JsonNode dNode = obj2.readTree(data);
			
			boolean isApply1 = logic.applay(r1Node, dNode);
			
			
			boolean isApply2 = logic.applay(r2Node, dNode);
			
			
			boolean isApply3 = logic.applay(r3Node, dNode);
			
			
			boolean isApply4 = logic.applay(r4Node, dNode);
			
			System.out.println("isApply 1:"+isApply1);
			System.out.println("isApply 2:"+isApply2);
			System.out.println("isApply 3:"+isApply3);
			System.out.println("isApply 4:"+isApply4);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
