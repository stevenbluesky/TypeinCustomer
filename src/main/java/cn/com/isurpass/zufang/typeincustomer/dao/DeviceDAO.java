package cn.com.isurpass.zufang.typeincustomer.dao;

import cn.com.isurpass.zufang.typeincustomer.po.DevicePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDAO extends CrudRepository<DevicePO ,Integer> {
    List<DevicePO> findByPersonIdAndDeviceType(Long id, Integer type);
}
