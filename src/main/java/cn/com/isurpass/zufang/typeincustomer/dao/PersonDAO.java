package cn.com.isurpass.zufang.typeincustomer.dao;

import cn.com.isurpass.zufang.typeincustomer.po.PersonPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO  extends CrudRepository<PersonPO,Long> {

    PersonPO findByPersonCodeAndPersonPasswordAndStatusIn(String personCode, String personPassword, int[] ints);
}
