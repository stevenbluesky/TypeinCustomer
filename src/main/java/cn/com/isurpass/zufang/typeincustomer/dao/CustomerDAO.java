package cn.com.isurpass.zufang.typeincustomer.dao;

import cn.com.isurpass.zufang.typeincustomer.po.CustomerPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends CrudRepository<CustomerPO,Integer> {

    Page<CustomerPO> findAll(Pageable pageable);

    CustomerPO findByCustomerid(Integer customerid);

    void deleteByCustomerid(int i);

    Page<CustomerPO> findByNameContainingAndPhonenumberContainingAndLabelContainingAndSexInAndTitleInAndPersonid(Pageable pageable, String name, String phonenumber, String label, List<Integer> sexlist, List<Integer> titlelist, Long id);

    Long countByNameContainingAndPhonenumberContainingAndLabelContainingAndSexInAndTitleInAndPersonid(String name, String phonenumber, String label, List<Integer> sexlist, List<Integer> titlelist, Long id);
}
