package org.wr.tianji.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.mapper.RiskMapper;
import org.wr.tianji.pojo.dto.RiskDTO;
import org.wr.tianji.pojo.dto.RiskPageQueryDTO;
import org.wr.tianji.pojo.entity.Risk;
import org.wr.tianji.pojo.vo.RiskVO;
import org.wr.tianji.service.RiskService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RiskServiceImpl implements RiskService {

    @Autowired
    private RiskMapper riskMapper;

    /**
     * 插入风险
     * @param riskDTO
     */
    @Override
    public void insert(RiskDTO riskDTO) {
        Risk risk = new Risk();
        BeanUtils.copyProperties(riskDTO,risk);
        riskMapper.insert(risk);
    }


    /***
     * 分页查询风险
     * @param riskPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(RiskPageQueryDTO riskPageQueryDTO) {
        PageHelper.startPage(riskPageQueryDTO.getPage(),riskPageQueryDTO.getPageSize());
        Page<Risk> page = riskMapper.pageQuery(riskPageQueryDTO);
        long total = page.getTotal();
        List<RiskVO> riskVOList =  page.getResult().stream().map(risk -> {
            RiskVO riskVO = new RiskVO();
            riskVO.setRiskId(risk.getRiskId());
            riskVO.setRiskName(risk.getRiskName());
            riskVO.setRepairPeriod(risk.getRepairPeriod());
            return riskVO;
        }).collect(Collectors.toList());
        return new PageResult(total,riskVOList);
    }

    @Override
    public void update(Risk risk) {
        riskMapper.update(risk);
    }

    @Override
    public void deleteBatch(List<Long> idList) {
        for(Long riskId : idList){
            riskMapper.delete(riskId);
        }
    }
}
