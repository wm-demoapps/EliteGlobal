/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.annotations.EntityService;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.showcase.elite.accountsdb.CorporateUser;


/**
 * ServiceImpl object for domain model class CorporateUser.
 *
 * @see CorporateUser
 */
@Service("AccountsDB.CorporateUserService")
@Validated
@EntityService(entityClass = CorporateUser.class, serviceId = "AccountsDB")
public class CorporateUserServiceImpl implements CorporateUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorporateUserServiceImpl.class);


    @Autowired
    @Qualifier("AccountsDB.CorporateUserDao")
    private WMGenericDao<CorporateUser, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<CorporateUser, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser create(CorporateUser corporateUser) {
        LOGGER.debug("Creating a new CorporateUser with information: {}", corporateUser);

        CorporateUser corporateUserCreated = this.wmGenericDao.create(corporateUser);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(corporateUserCreated);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser getById(Integer corporateuserId) {
        LOGGER.debug("Finding CorporateUser by id: {}", corporateuserId);
        return this.wmGenericDao.findById(corporateuserId);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser findById(Integer corporateuserId) {
        LOGGER.debug("Finding CorporateUser by id: {}", corporateuserId);
        try {
            return this.wmGenericDao.findById(corporateuserId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No CorporateUser found with id: {}", corporateuserId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public List<CorporateUser> findByMultipleIds(List<Integer> corporateuserIds, boolean orderedReturn) {
        LOGGER.debug("Finding CorporateUsers by ids: {}", corporateuserIds);

        return this.wmGenericDao.findByMultipleIds(corporateuserIds, orderedReturn);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser getByUsernameAndEmail(String username, String email) {
        Map<String, Object> usernameAndEmailMap = new HashMap<>();
        usernameAndEmailMap.put("username", username);
        usernameAndEmailMap.put("email", email);

        LOGGER.debug("Finding CorporateUser by unique keys: {}", usernameAndEmailMap);
        return this.wmGenericDao.findByUniqueKey(usernameAndEmailMap);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class, value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser update(CorporateUser corporateUser) {
        LOGGER.debug("Updating CorporateUser with information: {}", corporateUser);

        this.wmGenericDao.update(corporateUser);
        this.wmGenericDao.refresh(corporateUser);

        return corporateUser;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser partialUpdate(Integer corporateuserId, Map<String, Object>corporateUserPatch) {
        LOGGER.debug("Partially Updating the CorporateUser with id: {}", corporateuserId);

        CorporateUser corporateUser = getById(corporateuserId);

        try {
            ObjectReader corporateUserReader = this.objectMapper.reader().forType(CorporateUser.class).withValueToUpdate(corporateUser);
            corporateUser = corporateUserReader.readValue(this.objectMapper.writeValueAsString(corporateUserPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", corporateUserPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        corporateUser = update(corporateUser);

        return corporateUser;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public CorporateUser delete(Integer corporateuserId) {
        LOGGER.debug("Deleting CorporateUser with id: {}", corporateuserId);
        CorporateUser deleted = this.wmGenericDao.findById(corporateuserId);
        if (deleted == null) {
            LOGGER.debug("No CorporateUser found with id: {}", corporateuserId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), CorporateUser.class.getSimpleName(), corporateuserId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public void delete(CorporateUser corporateUser) {
        LOGGER.debug("Deleting CorporateUser with {}", corporateUser);
        this.wmGenericDao.delete(corporateUser);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<CorporateUser> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all CorporateUsers");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<CorporateUser> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all CorporateUsers");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AccountsDB for table CorporateUser to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service AccountsDB for table CorporateUser to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}
