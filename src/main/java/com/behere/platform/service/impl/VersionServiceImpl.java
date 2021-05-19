package com.behere.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.common.utils.Query;
import com.behere.platform.dao.VersionDao;
import com.behere.platform.domain.Version;
import com.behere.platform.domain.VersionSwitch;
import com.behere.platform.service.VersionService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionDao versionDao;

    @Override
    public List<Version> queryVersions() {
        return versionDao.queryVersions();
    }

    @Override
    public int updateVersion(Version version) {
        return versionDao.updateVersion(version);
    }

    @Override
    public Version queryVersion(int id) {
        return versionDao.queryVersion(id);
    }

    @Override
    public List<VersionSwitch> queryVersionSwitch(Query query) {
        return versionDao.queryVersionSwitch(query);
    }

    @Override
    public int updateVersionSwitchOnOff(int id, int onOff) {
        return versionDao.updateVersionSwitchOnOff(id, onOff);
    }

    @Override
    public int count() {
        return versionDao.count();
    }

    @Override
    public VersionSwitch queryVersionSwitchById(int id) {
        return versionDao.queryVersionSwitchById(id);
    }

    @Override
    public int save(VersionSwitch versionSwitch) {
        return versionDao.save(versionSwitch);
    }
}
