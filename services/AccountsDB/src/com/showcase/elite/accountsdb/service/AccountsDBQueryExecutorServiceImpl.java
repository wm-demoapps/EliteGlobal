/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.data.model.QueryProcedureInput;

import com.showcase.elite.accountsdb.models.query.*;

@Service
public class AccountsDBQueryExecutorServiceImpl implements AccountsDBQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountsDBQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("AccountsDBWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public GetUserByEmailResponse executeGetUserByEmail(String email) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("email", email);

        return queryExecutor.executeNamedQuery("getUserByEmail", params, GetUserByEmailResponse.class);
    }

    @Transactional(value = "AccountsDBTransactionManager")
    @Override
    public Integer executeUpdateMultipleTransactionStatus(UpdateMultipleTransactionStatusRequest updateMultipleTransactionStatusRequest) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("statusId", updateMultipleTransactionStatusRequest.getStatusId());
        params.put("ids", updateMultipleTransactionStatusRequest.getIds());
        params.put("corporateId", updateMultipleTransactionStatusRequest.getCorporateId());

        return queryExecutor.executeNamedQueryForUpdate("updateMultipleTransactionStatus", params);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public Page<GetFrequentTransactionsResponse> executeGetFrequentTransactions(String beneficiaryId, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("beneficiaryId", beneficiaryId);

        return queryExecutor.executeNamedQuery("getFrequentTransactions", params, GetFrequentTransactionsResponse.class, pageable);
    }

    @Transactional(value = "AccountsDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetFrequentTransactions(String beneficiaryId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("beneficiaryId", beneficiaryId);

        QueryProcedureInput<GetFrequentTransactionsResponse> queryInput = new QueryProcedureInput<>("getFrequentTransactions", params, GetFrequentTransactionsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public Page<GetMostFrequentTransactionsResponse> executeGetMostFrequentTransactions(Integer corporateId, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        return queryExecutor.executeNamedQuery("getMostFrequentTransactions", params, GetMostFrequentTransactionsResponse.class, pageable);
    }

    @Transactional(value = "AccountsDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetMostFrequentTransactions(Integer corporateId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        QueryProcedureInput<GetMostFrequentTransactionsResponse> queryInput = new QueryProcedureInput<>("getMostFrequentTransactions", params, GetMostFrequentTransactionsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public Page<GetAccountsSummaryByTypeResponse> executeGetAccountsSummaryByType(Integer corporateId, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        return queryExecutor.executeNamedQuery("getAccountsSummaryByType", params, GetAccountsSummaryByTypeResponse.class, pageable);
    }

    @Transactional(value = "AccountsDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetAccountsSummaryByType(Integer corporateId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        QueryProcedureInput<GetAccountsSummaryByTypeResponse> queryInput = new QueryProcedureInput<>("getAccountsSummaryByType", params, GetAccountsSummaryByTypeResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public Page<GetOverallTransactionsResponse> executeGetOverallTransactions(Integer corporateId, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        return queryExecutor.executeNamedQuery("getOverallTransactions", params, GetOverallTransactionsResponse.class, pageable);
    }

    @Transactional(value = "AccountsDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetOverallTransactions(Integer corporateId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        QueryProcedureInput<GetOverallTransactionsResponse> queryInput = new QueryProcedureInput<>("getOverallTransactions", params, GetOverallTransactionsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public GetTotalAccountBalanceForCorporateResponse executeGetTotalAccountBalanceForCorporate(Integer corporateId) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        return queryExecutor.executeNamedQuery("getTotalAccountBalanceForCorporate", params, GetTotalAccountBalanceForCorporateResponse.class);
    }

    @Transactional(value = "AccountsDBTransactionManager", readOnly = true)
    @Override
    public Page<GetTotalTransactionsResponse> executeGetTotalTransactions(Integer corporateId, Pageable pageable) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        return queryExecutor.executeNamedQuery("getTotalTransactions", params, GetTotalTransactionsResponse.class, pageable);
    }

    @Transactional(value = "AccountsDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetTotalTransactions(Integer corporateId, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(1);

        params.put("corporateId", corporateId);

        QueryProcedureInput<GetTotalTransactionsResponse> queryInput = new QueryProcedureInput<>("getTotalTransactions", params, GetTotalTransactionsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

}
