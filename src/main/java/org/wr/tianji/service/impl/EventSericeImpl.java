package org.wr.tianji.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.mapper.LogMapper;
import org.wr.tianji.mapper.ReportMapper;
import org.wr.tianji.mapper.UserMapper;
import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.entity.Log;
import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.pojo.vo.LogVO;
import org.wr.tianji.pojo.vo.ReportVO;
import org.wr.tianji.service.EventSerice;
import org.wr.tianji.common.constant.AlertConstant;

import java.time.LocalDateTime;
import java.util.List;

import static org.wr.tianji.common.constant.AlertConstant.*;

@Service
@Slf4j
public class EventSericeImpl implements EventSerice {

    @Autowired
    private LogMapper logMapper;


    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取工单变更日志
     * @param id
     * @return
     */
    @Override
    public List<LogVO> getById(Long id) {
        List<LogVO> logVOList = logMapper.getById(id);
        return logVOList;
    }


    /**
     * 对比变化 插入日志
     * @param reportVO
     * @param reportDTO
     */
    @Override
    public void compare(ReportVO reportVO, ReportDTO reportDTO) {
        String res = "";
//        String name = UserDetailsServiceImpl.getCurrentUserDetails().getName();
        String name = "";
        if(!reportVO.getRiskId().equals(reportDTO.getRiskId())){
            res += ( name + AlertConstant.LOGRISKALERT +
                    reportMapper.getRiskNameById(reportVO.getRiskId()) +
                    AlertConstant.LOGTIPSALERT +
                    reportMapper.getRiskNameById(reportDTO.getRiskId()) +
                    AlertConstant.LOGENDALERT);
        }
        if (!reportVO.getVulnId().equals(reportDTO.getVulnId())){
            res += (name + AlertConstant.LOGVULNALERT +
                    reportMapper.getVulnNameById(reportVO.getVulnId())+
                    AlertConstant.LOGTIPSALERT +
                    reportMapper.getVulnNameById(reportDTO.getVulnId())+
                    AlertConstant.LOGENDALERT);
        }
//        if(!reportVO.getSecUserId().equals(reportDTO.getSecUserId())){
//            res += (name + AlertConstant.LOGSECALERT +
//                    reportMapper.getVulnNameById(reportVO.getVulnId())+
//                    AlertConstant.LOGTIPSALERT +
//                    reportMapper.getVulnNameById(reportDTO.getVulnId())+
//                    AlertConstant.LOGENDALERT);
//        }
//
//        if(!reportVO.getDevUserId().equals(reportDTO.getDevUserId())){
//            res += (name + AlertConstant.LOGDEVALERT +
//                    reportMapper.getVulnNameById(reportVO.getVulnId())+
//                    AlertConstant.LOGTIPSALERT +
//                    reportMapper.getVulnNameById(reportDTO.getVulnId())+
//                    AlertConstant.LOGENDALERT);
//        }

        Log log = new Log();
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(reportVO.getReportId());
        logMapper.insert(log);

    }

    @Override
    public void insertConfirm(Long id, String username) {
        User user = userMapper.findByUsername(username);
        Log log = new Log();
        String res = user.getName() + LOGCONFIRM;
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(id);
        logMapper.insert(log);
    }

    @Override
    public void updateUser(ReportUserDTO reportUserDTO, Integer typeId, String username) {
        User user = userMapper.findByUsername(username);
        String name = userMapper.findNameByUserId(Long.valueOf(reportUserDTO.getUserId()));
        Log log = new Log();
        String res= "";
        if(typeId==2){
             res = user.getName() + LOGSECALERT + LOGTIPSALERT + name + LOGENDALERT;
        }else {
             res = user.getName() + LOGDEVALERT + LOGTIPSALERT + name + LOGENDALERT;
        }
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(Long.valueOf(reportUserDTO.getReportId()));
        logMapper.insert(log);

    }

    @Override
    public void insertVerify(Long reportId, String username, String description) {
        User user = userMapper.findByUsername(username);
        Log log = new Log();
        String res = user.getName() + LOGCONFIRMREPAIR + description;
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(reportId);
        logMapper.insert(log);
    }

    @Override
    public void insertRepair(Long reportId, String username, String description) {
        User user = userMapper.findByUsername(username);
        Log log = new Log();
        String res = user.getName() + LOGCONFIRMDISPOSED + description;
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(reportId);
        logMapper.insert(log);
    }

    @Override
    public void insertToreject(Long reportId, String username, String description) {
        User user = userMapper.findByUsername(username);
        Log log = new Log();
        String res = user.getName() + LOGCONFIRMTOREJECT + description;
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(reportId);
        logMapper.insert(log);
    }

    @Override
    public void insertReject(Long reportId, String username, String description) {
        User user = userMapper.findByUsername(username);
        Log log = new Log();
        String res = user.getName() + LOGCONFIRMREJECT + description;
        log.setEvent(res);
        log.setLogTime(LocalDateTime.now());
        log.setReportId(reportId);
        logMapper.insert(log);
    }
}
