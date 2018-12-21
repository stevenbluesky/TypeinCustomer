package cn.com.isurpass.zufang.typeincustomer.service;

import cn.com.isurpass.zufang.typeincustomer.dao.CustomerDAO;
import cn.com.isurpass.zufang.typeincustomer.dao.PersonDAO;
import cn.com.isurpass.zufang.typeincustomer.po.CustomerPO;
import cn.com.isurpass.zufang.typeincustomer.po.PersonPO;
import cn.com.isurpass.zufang.typeincustomer.util.Constants;
import cn.com.isurpass.zufang.typeincustomer.util.StringUtil;
import cn.com.isurpass.zufang.typeincustomer.vo.CustomerVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO cd;
    @Autowired
    private PersonDAO pd;
    private static Log log = LogFactory.getLog(CustomerService.class);
    @Transactional
    public Map<String, Object> listSearchCustomer(Pageable pageable, CustomerVO cvo, PersonPO person) {
        Map<String,Object> map = new HashMap<>();
        List<Integer> sexlist = new ArrayList<>();
        List<Integer> titlelist = new ArrayList<>();
        if(cvo.getSex()==2){
            sexlist.add(0);
            sexlist.add(1);
        }else{
            sexlist.add(cvo.getSex());
        }
        if(cvo.getTitle()==0){
            titlelist.add(1);
            titlelist.add(2);
        }else{
            titlelist.add(cvo.getTitle());
        }
        Page<CustomerPO> page = cd.findByNameContainingAndPhonenumberContainingAndLabelContainingAndSexInAndTitleInAndPersonid(pageable,cvo.getName(),cvo.getPhonenumber(),cvo.getLabel(),sexlist,titlelist,person.getId());
        Long count = cd.countByNameContainingAndPhonenumberContainingAndLabelContainingAndSexInAndTitleInAndPersonid(cvo.getName(),cvo.getPhonenumber(),cvo.getLabel(),sexlist,titlelist,person.getId());
        List<CustomerVO> listVO = new ArrayList<>();
        setProperties(page, listVO);
        map.put("rows",listVO);
        map.put("total",count);
     return map;
    }
    private void setProperties(Page<CustomerPO> listpage, List<CustomerVO> listVO) {
        listpage.forEach(zw -> {
            CustomerVO z = new CustomerVO();
            z.setCustomerid(zw.getCustomerid());
            z.setName(zw.getName());
            z.setSex(zw.getSex());
            z.setPhonenumber(zw.getPhonenumber());
            z.setTitle(zw.getTitle());
            z.setLabel(zw.getLabel());
            listVO.add(z);
        });
    }

    @Transactional
    public CustomerPO findCustomerByCustomerid(Integer customerid) {
        return cd.findByCustomerid(customerid);
    }

    @Transactional
    public void deleteCustomer(Integer[] ids) {
        for(int i = 0; i< ids.length;i++){
            cd.deleteByCustomerid(ids[i] );
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void save(CustomerPO cpo, PersonPO person) {
        cpo.setCountrycode(Constants.DEFAULT_COUNTRY_CODE);
        cpo.setIdentitytype(Constants.DEFAULT_IDENTITY_TYPE);
        cpo.setCreatetime(new Date());
        String customerinfo = cpo.getName()+cpo.getPhonenumber()+cpo.getIdentity()+cpo.getMail()+cpo.getAddress()+cpo.getLabel();
        cpo.setCustomerinfo(customerinfo);
        if(cpo.getCustomerid()==null) {
            if(person.getType()==1&&person.getSuperpersonid()!=null&&person.getSuperpersonid()!=0){
                cpo.setPersonid(person.getSuperpersonid().longValue());
                cpo.setTypeinpersonid(person.getId().intValue());
            }else{
                cpo.setPersonid(person.getId());
                cpo.setTypeinpersonid(person.getId().intValue());
            }
        }else{
            CustomerPO cus = cd.findByCustomerid(cpo.getCustomerid());
            cpo.setPersonid(cus.getPersonid());
            cpo.setTypeinpersonid(cus.getTypeinpersonid());
        }
        cd.save(cpo);
    }

    @Transactional
    public PersonPO checkLogin(PersonPO ppo) {
        ppo.setPersonPassword(StringUtil.md5(ppo.getPersonPassword()));
        PersonPO person = pd.findByPersonCodeAndPersonPasswordAndStatusIn(ppo.getPersonCode(),ppo.getPersonPassword(),new int[]{1,2});
        if(person!=null&&person.getType()==1&&person.getSuperpersonid()!=null&&person.getSuperpersonid()!=0){
            PersonPO superperson = pd.findById(person.getSuperpersonid().longValue()).orElse(null);
            if(superperson!=null&&superperson.getStatus()!=1){
                return null;
            }
        }
        return person;
    }
}
