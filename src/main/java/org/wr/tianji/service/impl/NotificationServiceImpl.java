package org.wr.tianji.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.mapper.NotificationMapper;
import org.wr.tianji.pojo.dto.NotificationDTO;
import org.wr.tianji.pojo.entity.Notification;
import org.wr.tianji.pojo.vo.NotificationVO;
import org.wr.tianji.service.NotificationService;
import static org.wr.tianji.common.constant.NotificationConstant.NOTIFICATIONNUM;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 插入
     * @param notificationDTO
     */
    @Override
    public void insert(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationDTO, notification);
        notification.setNotificationId(NOTIFICATIONNUM);
        notificationMapper.insert(notification);
    }
    /**
     * 查询
     *
     * @param i
     * @return
     */
    @Override
    public NotificationVO getById(Long i) {
        Notification notification = notificationMapper.getById(i);
        return NotificationVO.builder()
                .notificationName(notification.getNotificationName())
                .notificationContent(notification.getNotificationContent())
                .build();
    }

    /**
     * 查询返回
     * @param id
     * @return
     */
    public Boolean selectById(Long id) {
        Notification notification = notificationMapper.getById(id);
        if (notification == null) {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 更新
     * @param notificationDTO
     * @param id
     */
    @Override
    public void update(NotificationDTO notificationDTO,Long id) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationDTO,notification);
        notification.setNotificationId(id);
        notificationMapper.update(notification);
    }
}
