/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
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

import com.showcase.elite.accountsdb.TransactionInstruction;


/**
 * ServiceImpl object for domain model class TransactionInstruction.
 *
 * @see TransactionInstruction
 */
@Service("AccountsDB.TransactionInstructionService")
@Validated
@EntityService(entityClass = TransactionInstruction.class, serviceId = "AccountsDB")
public class TransactionInstructionServiceImpl implements TransactionInstructionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionInstructionServiceImpl.class);


    @Autowired
    @Qualifier("AccountsDB.TransactionInstructionDao")
    private WMGenericDao<TransactionInstruction, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<TransactionInstruction, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction create(TransactionInstruction transactionInstruction) {
        LOGGER.debug("Creating a new TransactionInstruction with information: {}", transactionInstruction);

        TransactionInstruction transactionInstructionCreated = this.wmGenericDao.create(transactionInstruction);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(transactionInstructionCreated);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction getById(Integer transactioninstructionId) {
        LOGGER.debug("Finding TransactionInstruction by id: {}", transactioninstructionId);
        return this.wmGenericDao.findById(transactioninstructionId);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction findById(Integer transactioninstructionId) {
        LOGGER.debug("Finding TransactionInstruction by id: {}", transactioninstructionId);
        try {
            return this.wmGenericDao.findById(transactioninstructionId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No TransactionInstruction found with id: {}", transactioninstructionId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public List<TransactionInstruction> findByMultipleIds(List<Integer> transactioninstructionIds, boolean orderedReturn) {
        LOGGER.debug("Finding TransactionInstructions by ids: {}", transactioninstructionIds);

        return this.wmGenericDao.findByMultipleIds(transactioninstructionIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction update(TransactionInstruction transactionInstruction) {
        LOGGER.debug("Updating TransactionInstruction with information: {}", transactionInstruction);

        this.wmGenericDao.update(transactionInstruction);
        this.wmGenericDao.refresh(transactionInstruction);

        return transactionInstruction;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction partialUpdate(Integer transactioninstructionId, Map<String, Object>transactionInstructionPatch) {
        LOGGER.debug("Partially Updating the TransactionInstruction with id: {}", transactioninstructionId);

        TransactionInstruction transactionInstruction = getById(transactioninstructionId);

        try {
            ObjectReader transactionInstructionReader = this.objectMapper.reader().forType(TransactionInstruction.class).withValueToUpdate(transactionInstruction);
            transactionInstruction = transactionInstructionReader.readValue(this.objectMapper.writeValueAsString(transactionInstructionPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", transactionInstructionPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        transactionInstruction = update(transactionInstruction);

        return transactionInstruction;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public TransactionInstruction delete(Integer transactioninstructionId) {
        LOGGER.debug("Deleting TransactionInstruction with id: {}", transactioninstructionId);
        TransactionInstruction deleted = this.wmGenericDao.findById(transactioninstructionId);
        if (deleted == null) {
            LOGGER.debug("No TransactionInstruction found with id: {}", transactioninstructionId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), TransactionInstruction.class.getSimpleName(), transactioninstructionId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public void delete(TransactionInstruction transactionInstruction) {
        LOGGER.debug("Deleting TransactionInstruction with {}", transactionInstruction);
        this.wmGenericDao.delete(transactionInstruction);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<TransactionInstruction> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all TransactionInstructions");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<TransactionInstruction> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all TransactionInstructions");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AccountsDB for table TransactionInstruction to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service AccountsDB for table TransactionInstruction to {} format", options.getExportType());
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
