package com.eriks.growth.service;

import com.eriks.growth.dao.SetDao;
import com.eriks.growth.domain.Set;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SetService {

    private final SetDao setDao;

    public SetService(SetDao setDao) {
        this.setDao = setDao;
    }

    public Set add(Set set) {
        return setDao.save(set);
    }

    public void deleteByUuid(UUID setUuid) {
        setDao.deleteByUuid(setUuid);
    }
}
