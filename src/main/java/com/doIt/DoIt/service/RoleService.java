package com.doIt.DoIt.service;

import com.doIt.DoIt.dao.RoleRepository;
import com.doIt.DoIt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/**
 * Service class for the role entity
 * This class has all the methods involving roles
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;



}
