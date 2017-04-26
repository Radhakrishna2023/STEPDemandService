/**
 * 
 */
package com.enquero.step.dao;

import java.util.List;

import com.enquero.step.bo.DemandForm;
import com.enquero.step.bo.ItForm;
import com.enquero.step.exception.STEPServiceException;

/**
 * @author dell
 *
 */
public interface ISTEPServiceDAO {
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws STEPServiceException
	 */
	//public Integer insertDemandData(DemandForm request) throws STEPServiceException;
	
	/**
	 * 
	 * @param demandId
	 * @return
	 * @throws STEPServiceException
	 */
	public DemandForm getDemandData(String demandId) throws STEPServiceException;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws STEPServiceException
	 */
	public Integer insertDemandData(DemandForm request) throws STEPServiceException;
	
	/**
	 * 
	 * @return
	 * @throws STEPServiceException
	 */
	public List<DemandForm> getDemands() throws STEPServiceException;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws STEPServiceException
	 */
	public Integer insertITData(ItForm request) throws STEPServiceException;

}
