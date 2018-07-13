package cn.com.isurpass.zufang.typeincustomer.dao;

import cn.com.isurpass.zufang.typeincustomer.po.SystemParameterPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterDAO extends CrudRepository<SystemParameterPO,String> {

    SystemParameterPO findByStrkey(String dbkey);
}
