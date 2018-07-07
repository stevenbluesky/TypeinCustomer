package cn.com.isurpass.zufang.typeincustomer.controller;

import cn.com.isurpass.zufang.typeincustomer.po.CustomerPO;
import cn.com.isurpass.zufang.typeincustomer.po.PersonPO;
import cn.com.isurpass.zufang.typeincustomer.service.CustomerService;
import cn.com.isurpass.zufang.typeincustomer.util.PageResult;
import cn.com.isurpass.zufang.typeincustomer.vo.CustomerVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService cs;
    private static Log log = LogFactory.getLog(CustomerController.class);
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("checklogin")
    public String checklogin(PersonPO ppo, HttpServletRequest request){
        try {
            PersonPO loginperson = cs.checkLogin(ppo);
            if(loginperson==null){
                return "unregistered";
            }else{
                request.getSession().setAttribute("person",loginperson);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("person");
        return "login";
    }

    @RequestMapping(value="/addcustomer")
    public String customerpage(){
        return "addcustomer";
    }

    @ResponseBody
    @RequestMapping("savecustomer")
    public String savecustomer(CustomerPO cpo,HttpServletRequest request){
        PersonPO person = (PersonPO) request.getSession().getAttribute("person");
        if(StringUtils.isEmpty(cpo.getName())||StringUtils.isEmpty(cpo.getPhonenumber())){
            return "-1";
        }
        cs.save(cpo,person);//TODO
        log.info("set1"+cpo);
        return "success";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("customerJsonList")
    @ResponseBody
    public Map<String, Object> customerJsonList(PageResult pr, CustomerVO cvo,HttpServletRequest request){
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "customerid");
        PersonPO person = (PersonPO) request.getSession().getAttribute("person");
        log.info("get2"+cs.listSearchCustomer(pageable,cvo,person));
        return cs.listSearchCustomer(pageable,cvo,person);
    }

    @RequestMapping("modifycustomer")
    public String modifypage(Model model,String customerid){
        if(StringUtils.isEmpty(customerid)){
            return "index";
        }
        int i = Integer.parseInt(customerid);
        CustomerPO cpo= cs.findCustomerByCustomerid(i);
        if(cpo==null){
            return "index";
        }
        model.addAttribute("customer",cpo);
        return "modifycustomer";
    }
    @RequestMapping("customerinfo")
    public String customerinfo(Model model,String customerid){
        if(StringUtils.isEmpty(customerid)){
            return "index";
        }
        int i = Integer.parseInt(customerid);
        CustomerPO cpo= cs.findCustomerByCustomerid(i);
        if(cpo==null){
            return "index";
        }
        model.addAttribute("customer",cpo);
        return "customerinfo";
    }

    @RequestMapping("deletecustomer")
    @ResponseBody
    public String deleteCustomer(@RequestBody Integer[] ids){
        try {
            cs.deleteCustomer(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
}
