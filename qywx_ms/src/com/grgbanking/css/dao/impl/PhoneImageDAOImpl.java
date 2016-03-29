package com.grgbanking.css.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grgbanking.css.common.CssBaseDAOImpl;
import com.grgbanking.css.common.PhoneImage;
import com.grgbanking.css.dao.PhoneImageDAO;
import com.grgbanking.css.util.HqlHelper;
/**
 *
 * Product:Grgbanking Service Of Customer System.
 * Version:2.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 * Author: jeff steve . lin
 * Date: 2012-6-14 上午11:40:07
 */
@Repository("phoneImageDAO")
public class PhoneImageDAOImpl extends CssBaseDAOImpl<PhoneImage, Integer> implements PhoneImageDAO<PhoneImage, Integer>  {

	/* (non-Javadoc)
	 * @see com.grgbanking.soc.dao.equipment.PhoneImageDAO#getEquipmentImage()
	 */
	public List<PhoneImage> getPhoneImages(Integer departId,Integer equipId,Integer workformId,Integer custId,Integer type) {
		HqlHelper hqlHelper=new HqlHelper(PhoneImage.class);
		if(departId != null){
			hqlHelper.addEqual("departmentId", departId);
		}
		if(equipId != null){
			hqlHelper.addEqual("equipmentId", equipId);
		}
		if(workformId != null){
			hqlHelper.addEqual("workformId", workformId);
		}
		if(custId !=null){
			hqlHelper.addEqual("customerId",custId);
		}
		if(type !=null){
			hqlHelper.addEqual("imagesType", type);
		}
		return this.find(hqlHelper);
	}

//	@Override
//	public List<Map> queryWorkFormList(Page queryPage,
//			WorkFormBean workFormBean) {
//		List<String> sqlList = new ArrayList<String>();
//		String sql="select t.id,t.equipment_id,t.images_content,t.longitude,t.latitude,t.images_address,t.department_id, " 
//				  +" t.customer_id,t.workform_id,t.create_user_id,t.images_url,t.create_date,t.images_type " 
//				  +" from t_services_images t "
//			      +" left join t_services_workform w on t.workform_id=w.workform_id "
//			      +" where t.workform_id is not null and t.images_url not like '/upload/ams%' and w.deleted='N' ";
//		if(StringUtils.isNotEmpty(workFormBean.getBranchName())){
//			sql+=" and t.images_url like '%"+workFormBean.getBranchName()+"%' ";
//		}
//		if(null!=workFormBean.getDepartmentId()){
//			sql+=" and w.department_id in (select d.department_id from t_base_department d "
//				+" where d.deleted = 'N' start with d.department_id = "+workFormBean.getDepartmentId()
//				+" connect by prior d.department_id = d.parent_id) ";
//		}
//		if(workFormBean.getPoNumber()!=null){
//				String[] poNumbers = workFormBean.getPoNumber().split(",");
//				int i=0;
//				for(;i<poNumbers.length/1000;i++){
//					String poNumber = "";
//					for(int j=i*1000;j<1000*(i+1);j++){
//						poNumber +=poNumbers[j]+"','";
//					}
//					String sql0=sql+" and w.po_number in ('"+poNumber.substring(0,poNumber.length()-2)+") ";
//					sqlList.add(sql0);
//				}
//				if(poNumbers.length%1000!=0){
//					String poNumber = "";
//					for(int j=i*1000;j<poNumbers.length;j++){
//						poNumber +=poNumbers[j]+"','";
//					}
//					String sql0=sql+" and w.po_number in ('"+poNumber.substring(0,poNumber.length()-2)+") ";
//					sqlList.add(sql0);
//				}
//			if(sqlList.size()>0){
//				sql="";
//				for(i=0;i<sqlList.size();i++){
//					String sql0 = sqlList.get(i);
//					if(i!=sqlList.size()-1){
//						sql+=sql0+" union ";
//					}else{
//						sql+=sql0;
//					}
//				}
//			}
//		}
//		sql="select t.* from ("+sql+") t order by t.create_date desc";
//		return this.findMapResultBySql(sql, queryPage);
//	}
//
//	@Override
//	public void deleteImagesByWorkformId(Integer workformId) {
//		if (workformId != null) {
//			HqlHelper hqlHelper = new HqlHelper(PhoneImage.class);
//			hqlHelper.addEqual("workformId", workformId);
//			List<PhoneImage> phoneImages = this.find(hqlHelper);
//			this.delete(phoneImages);
//		}
//	}
//	
//	@Override
//	public InputStream executeBlobInputStream(Integer phoneImageId) {
//		InputStream inputStream = null;
//		Connection connection = null;
//		PreparedStatement stmt = null;
//		ResultSet rst = null;
//		try{
//			connection = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
//			stmt = connection.prepareStatement("select t.images_blob from t_services_images t where t.id = "+phoneImageId);
//			rst = stmt.executeQuery();
//			if(rst.next()){
//				BLOB b =(oracle.sql.BLOB)rst.getBlob("IMAGES_BLOB");
//				inputStream = b.getBinaryStream();
//            }
//		}catch (Exception e) {
//			log.error(ExceptionUtils.getFullStackTrace(e));
//		}finally {
//			try{
//				rst.close();
//				if(stmt != null) {
//		            stmt.close();
//		        }
//		        if(connection != null && connection.getAutoCommit()) {
//		        	connection.close();
//		        }
//			}catch (SQLException e) {
//				log.error(ExceptionUtils.getFullStackTrace(e));
//			}
//		}
//		return inputStream;
//	}
}
