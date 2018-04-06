package com.healthrecord.mobile.services;

import java.io.Serializable;
import java.util.List;

import com.healthrecord.mobile.model.data.results.CloudantResultSetM;

public interface IJSONDataService<M extends Serializable> {
//	public CloudantResultSetM<M> get() throws Exception;
	public List<M> getAll() throws Exception;
	public void push(M m) throws Exception;
}
