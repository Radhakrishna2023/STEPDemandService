package com.enquero.step.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.enquero.step.bo.DemandForm;
import com.enquero.step.bo.ItForm;
import com.enquero.step.dao.STEPServiceDAO;
import com.enquero.step.exception.STEPServiceException;
import com.enquero.step.logger.STEPLogger;

@Path("/stepservice")
public class STEPService{
	
	STEPServiceDAO dao = STEPServiceDAO.getInstance();

	@POST
	@Path("/insertdemanddata")
	@Produces("application/json")
	public Integer insertDemandData(DemandForm request, 
			@Context HttpHeaders headers) throws STEPServiceException{
		int response = 0;
		try{
			response = dao.insertDemandData(request);			
		}catch(Exception e){
			STEPLogger.errorLog(STEPService.class, "insertDemandData", 
					 "Exception in insertDemandData...", e.getMessage());
			throw new STEPServiceException(e);
		}
		
		return response;
	}
	
	@POST
	@Path("/insertitdata")
	@Produces("application/json")
	public Integer insertITData(ItForm request, 
			@Context HttpHeaders headers) throws STEPServiceException{
		int response = 0;
		try{
			response = dao.insertITData(request);			
		}catch(Exception e){
			STEPLogger.errorLog(STEPService.class, "insertITData", 
					 "Exception in insertITData...", e.getMessage());
			throw new STEPServiceException(e);
		}
		
		return response;
	}
	
	@GET
	@Path("/getdemanddata/{demandIntakeId}")
	@Produces("application/json")
	public DemandForm getDemandData(@PathParam("demandIntakeId") String demandIntakeId,
			@Context HttpHeaders headers) throws STEPServiceException{
		DemandForm response = new DemandForm();
		try{
			response = dao.getDemandData(demandIntakeId);
		}catch(Exception e){
			STEPLogger.errorLog(STEPService.class, "getDemandData", 
					 "Exception in getDemandData...", e.getMessage());
			throw new STEPServiceException(e);
		}
		return response;
		
	}
	
	
	@GET
	@Path("/getdemands")
	@Produces("application/json")
	public List<DemandForm> getDemands(@Context HttpHeaders headers) 
			throws STEPServiceException{
		List<DemandForm> response = new ArrayList<>();
		try{
			response = dao.getDemands();
		}catch(Exception e){
			STEPLogger.errorLog(STEPService.class, "getDemands", 
					 "Exception in getDemands...", e.getMessage());
			throw new STEPServiceException(e);
		}
		return response;
		
	}

}
