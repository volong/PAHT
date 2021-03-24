package com.paht.service;

import com.paht.model.Interface.IStaticalDep;

import java.util.List;

public interface DepService {
    List<IStaticalDep> findDep();
}
