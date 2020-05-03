package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.Address;
import com.codimiracle.application.platform.huidu.entity.vo.AddressVO;
import com.codimiracle.application.platform.huidu.mapper.AddressMapper;
import com.codimiracle.application.platform.huidu.service.AddressService;
import com.codimiracle.web.mybatis.contract.ServiceException;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class AddressServiceImpl extends AbstractService<String, Address, AddressVO> implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public AddressVO findByIdIntegrally(String id) {
        return addressMapper.selectByIdIntegrally(id);
    }

    @Override
    public void defaultAddress(String userId, String addressId) {
        Address address = findDefaultByUserId(userId);
        if (Objects.isNull(address)) {
            address = findById(addressId);
            if (Objects.equals(address.getUserId(), userId)) {
                Address updatingAddress = new Address();
                updatingAddress.setDefaulted(true);
                updatingAddress.setId(addressId);
                update(updatingAddress);
                return;
            }
            throw new ServiceException("没有找到对应的地址");
        } else {
            if (!Objects.equals(address.getId(), addressId)) {
                Address updatingOriginal = new Address();
                updatingOriginal.setId(address.getId());
                updatingOriginal.setDefaulted(false);
                update(updatingOriginal);
                Address updating = new Address();
                updating.setId(addressId);
                updating.setDefaulted(true);
                update(updating);
            }
        }
    }

    private Address findDefaultByUserId(String userId) {
        return addressMapper.selectDefaultByUserId(userId);
    }

    @Override
    public AddressVO findDefaultByUserIdIntegrally(String userId) {
        return addressMapper.selectDefaultByUserIdIntegrally(userId);
    }
}
