package com.grgbanking.core.controller.workorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grgbanking.common.utils.LoginUser;
import com.grgbanking.core.entity.user.WxUser;
import com.grgbanking.core.service.equipment.CustomerService;
import com.grgbanking.css.bean.equipment.EquipmentContact;
import com.grgbanking.css.bean.equipment.EquipmentContactModel;
import com.grgbanking.css.bean.equipment.EquipmentInfo;
import com.grgbanking.css.dao.EquipmentDAO;
import com.grgbanking.css.service.EquipmentContactService;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：qywx_ms
 * 
 * 类描述：接待人控制器 类名称：com.grgbanking.core.controller.workorder.ReceiverController
 * 创建人：TXH 创建时间：2015-8-13 下午2:28:34 修改人： 修改时间：2015-8-13 下午2:28:34 修改备注：
 * 
 * @version V1.0
 */
@Controller
public class ReceiverController {
    
    @Autowired
    CustomerService customerService;

    @Resource(name = "equipmentContactService")
    private EquipmentContactService equipmentContactService;

    @Autowired
    private EquipmentDAO<EquipmentInfo, Integer> equipmentDAO;

    /**
     * 
     * @Title: receiverList
     * @Description: TODO(联系人列表)
     * @return
     */
    @RequestMapping(value = "/cp/receiver/list")
    public String receiverList(Model model, Integer equipmentId) {
        try{
            List<EquipmentContact> listReceiver = this.equipmentContactService.listById(equipmentId);
            List<EquipmentContactModel> list = new ArrayList<EquipmentContactModel>();
            EquipmentContactModel ecModel = null;
            for(EquipmentContact bean: listReceiver){
                ecModel = new EquipmentContactModel();
                BeanUtils.copyProperties(bean, ecModel);
                if(bean.getIsManager() != null){
                    ecModel.setIsManagerStr(bean.getIsManager().equals("Y") ? "是":"否");
                }
                if(bean.getIsRefuseSms() != null){
                    ecModel.setIsRefuseSmsStr(bean.getIsRefuseSms().equals("Y") ? "是":"否");
                }
                if(bean.getSex() != null){
                    ecModel.setSexStr(bean.getSex().equals("1") ? "男":"女");
                }
                
                list.add(ecModel);
            }
            model.addAttribute("equipmentId", equipmentId);
            model.addAttribute("listReceiver", list);
        } catch (Exception e) {
            model.addAttribute("status", "0");
            model.addAttribute("errMsg", "查询出现异常:"+e.toString());
            e.printStackTrace();
        }
        return "/receiver/receiver_list";
    }

    /**
     * 
     * @Title: receiverInfo
     * @Description: TODO(联系人详情)
     * @param model
     * @return
     */
    @RequestMapping(value = "/cp/receiver/info")
    public String receiverInfo(Model model, HttpServletRequest request) {
        String equipmentId = request.getParameter("equipmentId");
        String equipmentContactId = request.getParameter("equipmentContactId");
        String type = request.getParameter("type");
        if (StringUtils.isNotBlank(equipmentContactId)) {
            EquipmentContact contact = equipmentContactService
                    .getEquipmentContactById(Integer
                            .valueOf(equipmentContactId));
            if (null != contact) {
                equipmentId = String.valueOf(contact.getEquipment()
                        .getEquipmentId());
            }
            request.setAttribute("contact", contact);
        }
        request.setAttribute("equipmentId", equipmentId);
        request.setAttribute("type", type);
        return "/receiver/receiver_info";
    }
    
    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/cp/receiver/delete")
    public String deleteCustomer(HttpServletRequest request) {
        WxUser wxUser = LoginUser.getSessionUser();
        String equipmentContactId = request.getParameter("equipmentContactId");
        String result = customerService.doDeleteCustomer(wxUser.getLoginName(),
                equipmentContactId);
        return result;
    }
    
    /**
     * 添加或修改客户联系人
     * 
     */
    @ResponseBody
    @RequestMapping(value = "/cp/receiver/saveOrUpdate")
    public String createOrupdateCustomer(HttpServletRequest request) {
        String result = null;// 返回结果
        JSONObject param = new JSONObject();
        WxUser wxUser = LoginUser.getSessionUser();
        String equipmentContactId = request.getParameter("equipmentContactId");
        String equipmentId = request.getParameter("equipmentId");// 设备ID
        String contactName = request.getParameter("contactName");// 联系人名称
        String mobliePhone = request.getParameter("mobliePhone");// 移动电话
        String telephone = request.getParameter("telephone");// 固定电话号码
        String positionVal = request.getParameter("positionVal");// 岗位职级
        String sex = request.getParameter("sex");
        String isManager = request.getParameter("isManager");
        String isRefuseSms = request.getParameter("isRefuseSms");// 是否拒收短信
        String remark = request.getParameter("remark");// 备注
        String type = request.getParameter("type");

        if ("add".equals(type)) {// 1为添加新客户联系人
            try {
                EquipmentContact equipmentContact = new EquipmentContact();
                EquipmentInfo equipment = equipmentDAO.get(Integer
                        .valueOf(equipmentId));
                equipmentContact.setEquipment(equipment);
                equipmentContact.setContactName(contactName);
                equipmentContact.setMobliePhone(mobliePhone);
                equipmentContact.setTelephone(telephone);
                equipmentContact.setPositionVal(positionVal);
                equipmentContact.setSex(sex);
                equipmentContact.setIsManager(isManager);
                equipmentContact.setIsRefuseSms(isRefuseSms);
                equipmentContact.setRemark(remark);
                equipmentContact.setCreateUserId(Integer.valueOf(wxUser.getId()
                        .toString()));
                equipmentContact.setCreateDate(new Date());
                equipmentContact.setDeleted("N");
                result = customerService.doCreateCustomer(
                        wxUser.getLoginName(), equipmentContact);
            } catch (Exception e) {
                param.element("status", "0");
                param.element("errMsg", "添加客户联系人失败");
                result = param.toString();
            }
        } else if ("update".equals(type)) {// 2修改客户联系人
            try {
                EquipmentContact equipmentContact = equipmentContactService
                        .getEquipmentContactById(Integer
                                .valueOf(equipmentContactId));
                if (null != equipmentContact) {
                    equipmentContact.setContactName(contactName);
                    equipmentContact.setMobliePhone(mobliePhone);
                    equipmentContact.setTelephone(telephone);
                    equipmentContact.setPositionVal(positionVal);
                    equipmentContact.setSex(sex);
                    equipmentContact.setIsManager(isManager);
                    equipmentContact.setIsRefuseSms(isRefuseSms);
                    equipmentContact.setRemark(remark);
                    /*equipmentContact.setCreateUserId(Integer.valueOf(wxUser
                            .getId().toString()));
                    equipmentContact.setCreateDate(new Date());*/
                }
                result = customerService.updateByPrimaryKey(
                        wxUser.getLoginName(), equipmentContact);
            } catch (Exception e) {
                param.element("status", "0");
                param.element("errMsg", "修改客户联系人失败");
                result = param.toString();
            }
        } else {
            param.element("status", "0");
            param.element("errMsg", "网络异常，请关闭重新打开！");
            result = param.toString();
        }
        return result;
    }
}
