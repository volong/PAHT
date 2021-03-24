package com.paht.service.impl;

import com.paht.model.Interface.IStaticalDep;
import com.paht.model.Interface.impl.StaticalDep;
import com.paht.repository.DepRepository;
import com.paht.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepServiceImpl implements DepService {

    @Autowired
    private DepRepository depRepository;

    @Override
    public List<IStaticalDep> findDep() {
        return depRepository.findDep();
    }
}
