package org.wr.tianji.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wr.tianji.pojo.entity.Log;
import org.wr.tianji.pojo.vo.LogVO;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select event,log_time from tianji.log where report_id=#{id} order by log_time desc")
    List<LogVO> getById(Long id);
    @Insert("insert into tianji.log(log_id, report_id, event, log_time) " +
            "VALUES" +
            "(#{id},#{reportId},#{event},#{logTime})")
    void insert(Log log);
}
