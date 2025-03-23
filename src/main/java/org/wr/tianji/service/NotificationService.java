package org.wr.tianji.service;

import org.wr.tianji.pojo.dto.NotificationDTO;
import org.wr.tianji.pojo.vo.NotificationVO;

public interface NotificationService {
    /**
     * 插入配置
     * @param notificationDTO
     */
    void insert(NotificationDTO notificationDTO);

    /**
     * 查询
     *
     * @param i
     * @return
     */
    NotificationVO getById(Long i);

    /**
     * 更新
     * @param notificationDTO
     */
    void update(NotificationDTO notificationDTO,Long id);


    /**
     * 查询判断
     */
    Boolean selectById(Long id);
}
