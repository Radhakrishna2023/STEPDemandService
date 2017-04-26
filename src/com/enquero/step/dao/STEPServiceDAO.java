package com.enquero.step.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.enquero.step.bo.DemandForm;
import com.enquero.step.bo.ItForm;
import com.enquero.step.exception.STEPServiceException;
import com.enquero.step.logger.STEPLogger;
import com.enquero.step.utils.CommonUtils;

import static com.enquero.step.constants.STEPConstants.*;

public class STEPServiceDAO implements ISTEPServiceDAO{
	
	private static STEPServiceDAO dao = new STEPServiceDAO();
	
	private STEPServiceDAO(){
		
	}
	
	public static STEPServiceDAO getInstance(){
		return dao;
	}
	
	/*public Integer insertDemandData(DemandForm request) throws STEPServiceException{
		int response = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = CommonUtils.getDBConnection();
			String query = "INSERT INTO demand_master "
					+ "(subject, requestowner, requestdate, primarybusinesscontact, regionsinscope, priority, "
					+ "targetduedate, uattesters, businessobjective, expectedresult, areaofimpact, subarea, "
					+ "communication, valueadd, businessroi, risk, qualityofwork, demandid) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			STEPLogger.debugLog(STEPServiceDAO.class, "insertDemandData", 
					 "query in insertDemandData...", query);
			ps = conn.prepareStatement(query);
			ps.setString(1, request.getSubject());
			ps.setString(2, request.getRequestOwner());
			ps.setString(3, request.getRequestDate());
			ps.setString(4, request.getPrimaryBusinessContact());
			ps.setString(5, request.getRegionsInScope());
			ps.setString(6, request.getPriority());
			ps.setString(7, request.getTargetDueDate());
			ps.setString(8, request.getUatTesters());
			ps.setString(9, request.getBusinessObjective());
			ps.setString(10, request.getExpectedResult());
			ps.setString(11, request.getAreaofImpact());
			ps.setString(12, request.getSubArea());
			ps.setString(13, request.getCommunication());
			ps.setString(14, request.getValueAdd());
			ps.setString(15, request.getBusinessRoi());
			ps.setString(16, request.getRisk());
			ps.setString(17, request.getQualityofWork());
			ps.setInt(18, Integer.parseInt(request.getDemandId()));
			response = ps.executeUpdate();
			STEPLogger.debugLog(STEPServiceDAO.class, "insertDemandData", 
					 "response in insertDemandData...", response);
			
			
		}catch(Exception e){
			STEPLogger.errorLog(STEPServiceDAO.class, "insertDemandData", 
					 "Exception in insertDemandData...", e.getMessage());
			throw new STEPServiceException(e);
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (SQLException e) {
				STEPLogger.errorLog(STEPServiceDAO.class, "insertDemandData", 
						 "Exception in insertDemandData...", e);
			}
		}
		return response;
	}*/
	
	@Override
	public DemandForm getDemandData(String demandIntakeId) throws STEPServiceException{
		DemandForm response = new DemandForm();
		ResultSet rs = null;
		String query = "select subject,request_owner,request_date,primary_business_contact,"
				+ "regions_in_scope,priority,target_due_date,uat_testers,business_objective,"
				+ "expected_result,area_of_impact,sub_area,communication,value_add,business_roi,"
				+ "risk,quality_of_work from demand_business_case_tab b, demand_initiative_tab i, "
				+ "demand_measurement_tab me where b.demand_intake_id=i.demand_intake_id "
				+ "and b.demand_intake_id=me.demand_intake_id and b.demand_intake_id=?;";
		try(Connection conn = CommonUtils.getDBConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			
			ps.setInt(1, Integer.parseInt(demandIntakeId));
			rs = ps.executeQuery();
			while(rs.next()){
				response.setSubject(rs.getString("subject"));
				response.setRequestOwner(rs.getString("request_owner"));
				response.setRequestDate(rs.getString("request_date"));
				response.setPrimaryBusinessContact(rs.getString("primary_business_contact"));
				response.setRegionsInScope(rs.getString("regions_in_scope"));
				response.setPriority(rs.getString("priority"));
				response.setTargetDueDate(rs.getString("target_due_date"));
				response.setUatTesters(rs.getString("uat_testers"));
				response.setBusinessObjective(rs.getString("business_objective"));
				response.setExpectedResult(rs.getString("expected_result"));
				response.setAreaofImpact(rs.getString("area_of_impact"));
				response.setSubArea(rs.getString("sub_area"));
				response.setCommunication(rs.getString("communication"));
				response.setValueAdd(rs.getString("value_add"));
				response.setBusinessRoi(rs.getString("business_roi"));
				response.setRisk(rs.getString("risk"));
				response.setQualityofWork(rs.getString("quality_of_work"));
				response.setDemandIntakeId(demandIntakeId);
			}
		}catch(Exception e){
			STEPLogger.errorLog(STEPServiceDAO.class, "getDemandData", 
					 "Exception in getDemandData...", e);
			throw new STEPServiceException(e);
			
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				STEPLogger.errorLog(STEPServiceDAO.class, "getDemandData", 
						 "Exception in finally...", e);
			}
		}
		return response;
	}
	
	@Override
	public Integer insertDemandData(DemandForm request) throws STEPServiceException{
		
		int response = 0;
		String query = ADDINTAKEDATA.getValue();
		STEPLogger.debugLog(STEPServiceDAO.class, "insertDemandData", 
				 "query ...", query);
		try(Connection conn = CommonUtils.getDBConnection();
				CallableStatement cs = conn.prepareCall(query);){
			
			cs.setString(1, request.getSubject());
			cs.setString(2, request.getRequestOwner());
			cs.setDate(3, Date.valueOf(request.getRequestDate()));
			cs.setString(4, request.getPrimaryBusinessContact());
			cs.setString(5, request.getRegionsInScope());
			cs.setString(6, request.getPriority());
			cs.setString(7, request.getTargetDueDate());
			cs.setString(8, request.getUatTesters());
			cs.setString(9, request.getBusinessObjective());
			cs.setString(10, request.getExpectedResult());
			cs.setString(11, request.getAreaofImpact());
			cs.setString(12, request.getSubArea());
			cs.setString(13, request.getCommunication());
			cs.setString(14, request.getValueAdd());
			cs.setString(15, request.getBusinessRoi());
			cs.setString(16, request.getRisk());
			cs.setString(17, request.getQualityofWork());
			cs.setString(18, "STEPUser");
			cs.setDate(19, Date.valueOf(java.time.LocalDate.now()));
			cs.registerOutParameter(20,	Types.INTEGER);
			cs.execute();
			
			response = cs.getInt(20);
			STEPLogger.debugLog(STEPServiceDAO.class, "insertDemandData.", 
					 "response ...", response);
			
			
		}catch(Exception e){
			STEPLogger.errorLog(STEPServiceDAO.class, "insertDemandData..", 
					 "Exception ...", e.getMessage());
			throw new STEPServiceException(e);
		}
		return response;
	}
	
	@Override
	public List<DemandForm> getDemands() throws STEPServiceException{
		DemandForm response = null;
		List<DemandForm> responseList = new ArrayList<>();
		ResultSet rs = null;
		String query = "select subject,request_owner,request_date,primary_business_contact,"
				+ "regions_in_scope,priority,target_due_date,uat_testers,business_objective,"
				+ "expected_result,area_of_impact,sub_area,communication,value_add,business_roi,"
				+ "risk,quality_of_work,b.demand_intake_id from demand_business_case_tab b, demand_initiative_tab i, "
				+ "demand_measurement_tab me where b.demand_intake_id=i.demand_intake_id "
				+ "and b.demand_intake_id=me.demand_intake_id;";
		try(Connection conn = CommonUtils.getDBConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			
			rs = ps.executeQuery();
			while(rs.next()){
				response = new DemandForm();
				response.setSubject(rs.getString("subject"));
				response.setRequestOwner(rs.getString("request_owner"));
				response.setRequestDate(rs.getString("request_date"));
				response.setPrimaryBusinessContact(rs.getString("primary_business_contact"));
				response.setRegionsInScope(rs.getString("regions_in_scope"));
				response.setPriority(rs.getString("priority"));
				response.setTargetDueDate(rs.getString("target_due_date"));
				response.setUatTesters(rs.getString("uat_testers"));
				response.setBusinessObjective(rs.getString("business_objective"));
				response.setExpectedResult(rs.getString("expected_result"));
				response.setAreaofImpact(rs.getString("area_of_impact"));
				response.setSubArea(rs.getString("sub_area"));
				response.setCommunication(rs.getString("communication"));
				response.setValueAdd(rs.getString("value_add"));
				response.setBusinessRoi(rs.getString("business_roi"));
				response.setRisk(rs.getString("risk"));
				response.setQualityofWork(rs.getString("quality_of_work"));
				response.setDemandIntakeId(rs.getString("demand_intake_id"));
				responseList.add(response);
			}
		}catch(Exception e){
			STEPLogger.errorLog(STEPServiceDAO.class, "getDemands", 
					 "Exception in getDemands...", e);
			throw new STEPServiceException(e);
			
		}finally{
			try {
				
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				STEPLogger.errorLog(STEPServiceDAO.class, "getDemands", 
						 "Exception in getDemands finally...", e);
			}
		}
		return responseList;
	}
	
	@Override
	public Integer insertITData(ItForm request) throws STEPServiceException{
		
		int response = 0;
		String query = ADDGAPTECHDATA.getValue();
		STEPLogger.debugLog(STEPServiceDAO.class, "insertITData", 
				 "query ...", query);
		try(Connection conn = CommonUtils.getDBConnection();
				CallableStatement cs = conn.prepareCall(query);){
			
			cs.setInt(1, Integer.parseInt(request.getDemandIntakeId()));
			cs.setString(2, request.getTicketNumber());
			cs.setString(3, request.getRequestType());
			cs.setString(4, request.getEstimatedEffort());
			cs.setString(5, request.getTargetDate());
			cs.setString(6, request.getDeadLineDate());
			cs.setString(7, request.getFundsAvailable());
			cs.setString(8, request.getHeadCount());
			cs.setString(9, request.getHeadCountType());
			cs.setString(10, request.getImpactedTeams());
			cs.setString(11, request.getTicketLifeCycle());			
			cs.setDate(12, Date.valueOf(request.getRequestDate()));
			cs.setString(13, "STEPITUser");
			cs.setDate(14, Date.valueOf(java.time.LocalDate.now()));
			cs.registerOutParameter(15,	Types.INTEGER);
			cs.execute();
			
			response = cs.getInt(15);
			STEPLogger.debugLog(STEPServiceDAO.class, "insertITData.", 
					 "response ...", response);
			
			
		}catch(Exception e){
			STEPLogger.errorLog(STEPServiceDAO.class, "insertITData..", 
					 "Exception ...", e.getMessage());
			throw new STEPServiceException(e);
		}
		return response;
	}
}
