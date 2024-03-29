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
import org.springframework.context.annotation.Lazy;
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

import com.showcase.elite.accountsdb.Transaction;
import com.showcase.elite.accountsdb.TransactionInstruction;


/**
 * ServiceImpl object for domain model class Transaction.
 *
 * @see Transaction
 */
@Service("AccountsDB.TransactionService")
@Validated
@EntityService(entityClass = Transaction.class, serviceId = "AccountsDB")
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("AccountsDB.TransactionInstructionService")
    private TransactionInstructionService transactionInstructionService;

    @Autowired
    @Qualifier("AccountsDB.TransactionDao")
    private WMGenericDao<Transaction, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Transaction, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public Transaction create(Transaction transaction) {
        LOGGER.debug("Creating a new Transaction with information: {}", transaction);

        Transaction transactionCreated = this.wmGenericDao.create(transaction);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(transactionCreated);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Transaction getById(Integer transactionId) {
        LOGGER.debug("Finding Transaction by id: {}", transactionId);
        return this.wmGenericDao.findById(transactionId);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Transaction findById(Integer transactionId) {
        LOGGER.debug("Finding Transaction by id: {}", transactionId);
        try {
            return this.wmGenericDao.findById(transactionId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Transaction found with id: {}", transactionId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public List<Transaction> findByMultipleIds(List<Integer> transactionIds, boolean orderedReturn) {
        LOGGER.debug("Finding Transactions by ids: {}", transactionIds);

        return this.wmGenericDao.findByMultipleIds(transactionIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "AccountsDBTransactionManager")
    @Override
    public Transaction update(Transaction transaction) {
        LOGGER.debug("Updating Transaction with information: {}", transaction);

        this.wmGenericDao.update(transaction);
        this.wmGenericDao.refresh(transaction);

        return transaction;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public Transaction partialUpdate(Integer transactionId, Map<String, Object>transactionPatch) {
        LOGGER.debug("Partially Updating the Transaction with id: {}", transactionId);

        Transaction transaction = getById(transactionId);

        try {
            ObjectReader transactionReader = this.objectMapper.reader().forType(Transaction.class).withValueToUpdate(transaction);
            transaction = transactionReader.readValue(this.objectMapper.writeValueAsString(transactionPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", transactionPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        transaction = update(transaction);

        return transaction;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public Transaction delete(Integer transactionId) {
        LOGGER.debug("Deleting Transaction with id: {}", transactionId);
        Transaction deleted = this.wmGenericDao.findById(transactionId);
        if (deleted == null) {
            LOGGER.debug("No Transaction found with id: {}", transactionId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Transaction.class.getSimpleName(), transactionId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public void delete(Transaction transaction) {
        LOGGER.debug("Deleting Transaction with {}", transaction);
        this.wmGenericDao.delete(transaction);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<Transaction> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Transactions");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<Transaction> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Transactions");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service AccountsDB for table Transaction to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service AccountsDB for table Transaction to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "AccountsDBTransactionManager")
    @Override
    public Page<TransactionInstruction> findAssociatedTransactionInstructions(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated transactionInstructions");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("transaction.id = '" + id + "'");

        return transactionInstructionService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service TransactionInstructionService instance
     */
    protected void setTransactionInstructionService(TransactionInstructionService service) {
        this.transactionInstructionService = service;
    }

}
