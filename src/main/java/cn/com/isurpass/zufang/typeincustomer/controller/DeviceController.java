package cn.com.isurpass.zufang.typeincustomer.controller;

import cn.com.isurpass.zufang.typeincustomer.po.DevicePO;
import cn.com.isurpass.zufang.typeincustomer.po.PersonPO;
import cn.com.isurpass.zufang.typeincustomer.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService ds;
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);
    @RequestMapping("findfingerdevice")
    @ResponseBody
    public List<DevicePO> findFingerDevice(HttpServletRequest request){
        PersonPO person = (PersonPO) request.getSession().getAttribute("person");
        return ds.findFingerDevice(person);
    }
    @RequestMapping("readfingerpring")
    @ResponseBody
    public String readFingerpring(@RequestParam("zwavedeviceid") String zwavedeviceid){
        try {
            int zwaveid = Integer.parseInt(zwavedeviceid);
            ds.readFingerpring(zwaveid);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("querystatusofreadfingerpring")
    @ResponseBody
    public Map<String,String> queryStatusOfReadFingerpring(@RequestParam("zwavedeviceid") String zwavedeviceid,Model model,HttpServletRequest request){
        Map<String,String> resultmap = new HashMap<String,String>();
        try {
            int zwaveid = Integer.parseInt(zwavedeviceid);
            String fingerprint = ds.queryStatusOfReadFingerpring(zwaveid);
            //String fingerprint = "23ewdfvrdt95swerfdsara46";
            if(!StringUtils.isEmpty(fingerprint)){
                resultmap.put("success",fingerprint);
            }
            resultmap.put("hi","hi");
            return resultmap;
        }catch (Exception e){
            resultmap.put("failed",e.getMessage());
            logger.info(e.getMessage());
            return resultmap;
        }
    }
}
