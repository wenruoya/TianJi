package org.wr.tianji.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StatusMapper {


    @Update("update tianji.report set status = #{recognizedriskCode} where report_id=#{id}")
    void changeStatus(Long id, Integer recognizedriskCode);
    @Update("update tianji.report set status = #{recognizedriskCode},feedback=#{description} where report_id=#{reportId}")
    void chageDevStatus(Long reportId, String description, Integer recognizedriskCode);
    @Update("update tianji.report set status=#{processedCode},remark=#{description} where report_id=#{reportId}")
    void chageSecStatus(Long reportId, String description, Integer processedCode);
}
