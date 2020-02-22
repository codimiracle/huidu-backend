package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.contract.AbstractService;
import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.mapper.AddressMapper;
import com.codimiracle.application.platform.huidu.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class AddressServiceImpl extends AbstractService<String, Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;

}
