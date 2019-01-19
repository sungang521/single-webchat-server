package com.sungang.dao;

import com.sungang.model.MetaData;
import org.apache.ibatis.annotations.Param;

/**
 * Created by SGang on 2019/1/14.
 */
public interface MetaDataDao {
    /*
    根据比划数和性别生成结果
     */
    MetaData getDataByNameAndWordStep(@Param("wordStep") int wordStep, @Param("sex")String sex);
}
