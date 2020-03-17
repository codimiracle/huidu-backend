package com.codimiracle.application.platform.huidu.mapper;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AddressMapper extends Mapper<Address> {
    AddressVO selectByIdIntegrally(String id);

    List<AddressVO> selectAllIntegrally(@Param("filter") Filter filter, @RequestParam("sorter") Sorter sorter, @RequestParam("page") Page page);

    void deleteByIdLogically(String id);

    AddressVO selectDefaultByUserIdIntegrally(String userId);

    Address selectDefaultByUserId(String userId);
}