package org.wr.tianji.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wr.tianji.pojo.entity.Notification;

@Mapper
public interface NotificationMapper {
    /**
     * 插入配置
     * @param notification
     */
    @Insert("insert into tianji.notification(notification_id, notification_name, notification_content) " +
            "values " +
            "(#{notificationId}, #{notificationName}, #{notificationContent})")
    void insert(Notification notification);

    @Select("select * from tianji.notification where notification_id = #{id}")
    Notification getById(Long i);

    void update(Notification notification);
}
