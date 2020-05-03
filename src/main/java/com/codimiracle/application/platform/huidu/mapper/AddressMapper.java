package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AddressMapper extends Mapper<Address, AddressVO> {
    AddressVO selectByIdIntegrally(String id);

    List<AddressVO> selectAllIntegrally(@Param("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @RequestParam("page") Page page);

    void deleteByIdLogically(String id);

    AddressVO selectDefaultByUserIdIntegrally(String userId);

    Address selectDefaultByUserId(String userId);
}