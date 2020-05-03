package com.codimiracle.application.platform.huidu.service.impl;

import com.codimiracle.application.platform.huidu.entity.po.LogisticsInformation;
import com.codimiracle.application.platform.huidu.entity.po.PassingPoint;
import com.codimiracle.application.platform.huidu.entity.vo.LogisticsInformationVO;
import com.codimiracle.application.platform.huidu.mapper.LogisticsInformationMapper;
import com.codimiracle.application.platform.huidu.service.LogisticsInformationService;
import com.codimiracle.application.platform.huidu.service.PassingPointService;
import com.codimiracle.web.mybatis.contract.ServiceException;
import com.codimiracle.web.mybatis.contract.support.vo.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;


/**
 * @author Codimiracle
 */
@Service
@Transactional
public class LogisticsInformationServiceImpl extends AbstractService<String, LogisticsInformation, LogisticsInformationVO> implements LogisticsInformationService {
    @Resource
    private LogisticsInformationMapper logisticsInformationMapper;

    @Resource
    private PassingPointService passingPointService;

    @Override
    public void save(LogisticsInformation model) {
        super.save(model);
        Date now = new Date();
        model.getPassingPoints().stream().forEach((e) -> {
            e.setLogisticsInformationId(model.getId());
            e.setCreateTime(now);
            e.setUpdateTime(now);
            passingPointService.save(e);
        });
    }

    @Override
    public void update(LogisticsInformation model) {
        super.update(model);
        Date now = new Date();
        model.getPassingPoints().forEach((e) -> {
            if (Objects.isNull(e.getId())) {
                e.setCreateTime(now);
                e.setUpdateTime(now);
                e.setLogisticsInformationId(model.getId());
                passingPointService.save(e);
            } else {
                PassingPoint passingPoint = passingPointService.findById(e.getId());
                if (!Objects.equals(passingPoint.getLogisticsInformationId(), model.getId())) {
                    throw new ServiceException("未知途经点!");
                }
                if (passingPoint.getStatus() == e.getStatus()) {
                    return;
                }
                e.setName(null);
                e.setUpdateTime(now);
                passingPointService.update(e);
            }
        });
    }

    @Override
    protected LogisticsInformationVO mutate(LogisticsInformationVO logisticsInformationVO) {
        logisticsInformationVO.setPassingPoints(passingPointService.findByLogisticsInformationId(logisticsInformationVO.getId()));
        return logisticsInformationVO;
    }

    @Override
    public LogisticsInformationVO findByIdIntegrally(String id) {
        LogisticsInformationVO logisticsInformationVO = logisticsInformationMapper.selectByIdIntegrally(id);
        mutate(logisticsInformationVO);
        return logisticsInformationVO;
    }

    @Override
    public LogisticsInformationVO findByOrderNumberIntegrally(String orderNumber) {
        LogisticsInformationVO logisticsInformationVO = logisticsInformationMapper.selectByOrderNumberIntegrally(orderNumber);
        mutate(logisticsInformationVO);
        return logisticsInformationVO;
    }
}
