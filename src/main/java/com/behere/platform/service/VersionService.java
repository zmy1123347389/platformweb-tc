package com.behere.platform.service;

import org.apache.ibatis.annotations.Param;

import com.behere.common.utils.Query;
import com.behere.platform.domain.Version;
import com.behere.platform.domain.VersionSwitch;

import java.util.List;

/**
 * @author: Behere
 */
public interface VersionService {

    /**
     * 查询版本列表
     * @return
     */
    List<Version> queryVersions();

    int updateVersion(Version version);

    Version queryVersion(int id);

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
    int updateVersionSwitchOnOff(int id, int onOff);

    int count();

    VersionSwitch queryVersionSwitchById(int id);

    int save(VersionSwitch versionSwitch);
}