package com.codimiracle.application.platform.huidu.service;

import com.codimiracle.application.platform.huidu.contract.*;
import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;


/**
 * @author Codimiracle
 */
public interface AddressService extends Service<String, Address> {

    AddressVO findByIdIntegrally(String id);

    PageSlice<AddressVO> findAllIntegrally(Filter filter, Sorter sorter, Page page);

    void deleteByIdLogically(String id);

    void defaultAddress(String userId, String addressId);

    AddressVO findDefaultByUserIdIntegrally(String userId);
}
