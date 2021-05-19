package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.common.utils.Query;
import com.behere.platform.domain.Version;
import com.behere.platform.domain.VersionSwitch;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface VersionDao {

    /**
     * 查询版本列表
     * @return
     */
    List<Version> queryVersions();

    int updateVersion(Version version);

    Version queryVersion(@Param("id") int id);

    /**
     * IOS版本审核列表
     * @return
     */
    List<VersionSwitch> queryVersionSwitch(Query query);

    /**
     * 修改开关状态
     * @param id
     * @param onOff
     * @return
     */
    int updateVersionSwitchOnOff(@Param("id") int id, @Param("onOff") int onOff);

    int count();

    VersionSwitch queryVersionSwitchById(@Param("id") int id);

    int save(VersionSwitch versionSwitch);
}
