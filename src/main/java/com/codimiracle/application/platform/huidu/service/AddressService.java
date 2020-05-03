package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;
import com.codimiracle.web.basic.contract.Filter;
import com.codimiracle.web.basic.contract.Page;
import com.codimiracle.web.basic.contract.PageSlice;
import com.codimiracle.web.basic.contract.Sorter;
import com.codimiracle.web.mybatis.contract.support.vo.Service;


/**
 * @author Codimiracle
 */
public interface AddressService extends Service<String, Address, AddressVO> {

    AddressVO findByIdIntegrally(String id);

    PageSlice<AddressVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void defaultAddress(String userId, String addressId);

    AddressVO findDefaultByUserIdIntegrally(String userId);
}
