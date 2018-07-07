package cn.com.isurpass.zufang.typeincustomer.service;

import cn.com.isurpass.zufang.typeincustomer.dao.DeviceDAO;
import cn.com.isurpass.zufang.typeincustomer.po.DevicePO;
import cn.com.isurpass.zufang.typeincustomer.po.PersonPO;
import cn.com.isurpass.zufang.typeincustomer.util.HttpsUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.isurpass.zufang.typeincustomer.util.Constants;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO dd;

    @Transactional
    public List<DevicePO> findFingerDevice(PersonPO person) {
        return dd.findByPersonIdAndDeviceType(person.getId(),Constants.DEVICE_TYPE_FINGER);
    }
    @Transactional
    public void readFingerpring(int zwaveid) {
        String s = HttpsUtils.readFingerpring(zwaveid);
        JSONObject result = JSONObject.parseObject(s);
        if(StringUtils.isEmpty(result)){
            throw new RuntimeException("错误");
        }
        if (result.getInteger("resultCode") == 0) {

        } else if (result.getInteger("resultCode") == 10023 ||result.getInteger("resultCode") == 30311) {
            throw new RuntimeException("找不到指定的设备");
        }else if (result.getInteger("resultCode") == 10006) {//设备超时
            throw new RuntimeException("设备超时");
        } else if (result.getInteger("resultCode") == 30021) {//不支持的设备类型
            throw new RuntimeException("不支持的设备类型");
        } else if (result.getInteger("resultCode") == 10022) {
            throw new RuntimeException("没有权限");
        }else if (result.getInteger("resultCode") == 10024) {
            throw new RuntimeException("设备没有响应");
        } else {
            throw new RuntimeException("错误");
        }
    }
    @Transactional
    public String queryStatusOfReadFingerpring(int zwaveid) {
        String s = HttpsUtils.queryStatusOfReadFingerpring(zwaveid);
        JSONObject result = JSONObject.parseObject(s);
        if(StringUtils.isEmpty(result)){
            throw new RuntimeException("错误");
        }
        if (result.getInteger("resultCode") == 10023 ||result.getInteger("resultCode") == 30311) {
            throw new RuntimeException("找不到指定的设备");
        }else if(result.getInteger("resultCode") == 0){

        }else{
            throw new RuntimeException("错误");
        }
        if(result.getInteger("status") == 0){
            throw new RuntimeException("设备没有进入指纹采集状态");
        }else if(result.getInteger("status") == 1){
            throw new RuntimeException("正在采集指纹");
        }else if(result.getInteger("status") == 2){
            throw new RuntimeException("再次输入指纹");
        }else if(result.getInteger("status") == 10){
            throw new RuntimeException("采集超时，请重试");
        }else if(result.getInteger("status") == 11){
            throw new RuntimeException("指纹不匹配，采集失败");
        }else if(result.getInteger("status") == 3){
            String fingerprint = result.getString("fingerprint");
            return fingerprint;
        }else{
            throw new RuntimeException("失败");
        }
    }
}
