package com.healthrecord.mobile.services;
import com.healthrecord.mobile.model.NCDsM;

public interface INCDsDataService extends IJSONDataService<NCDsM>{
	public NCDsM getById(String id) throws Exception;
}
