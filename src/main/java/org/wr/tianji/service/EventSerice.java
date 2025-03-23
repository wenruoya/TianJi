package org.wr.tianji.service;

import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.vo.LogVO;
import org.wr.tianji.pojo.vo.ReportVO;

import java.util.List;

public interface EventSerice {
    List<LogVO> getById(Long id);

    void compare(ReportVO reportVO, ReportDTO reportDTO);

    void insertConfirm(Long id, String username);

    void updateUser(ReportUserDTO reportUserDTO, Integer typeId, String username);

    void insertVerify(Long reportId, String username, String description);

    void insertRepair(Long reportId, String username, String description);

    void insertToreject(Long reportId, String username, String description);

    void insertReject(Long reportId, String username, String description);
}
