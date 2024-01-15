/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.showcase.elite.accountsdb.Transaction;
import com.showcase.elite.accountsdb.TransactionType;

/**
 * Service object for domain model class {@link TransactionType}.
 */
public interface TransactionTypeService {

    /**
     * Creates a new TransactionType. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TransactionType if any.
     *
     * @param transactionType Details of the TransactionType to be created; value cannot be null.
     * @return The newly created TransactionType.
     */
    TransactionType create(@Valid TransactionType transactionType);


	/**
     * Returns TransactionType by given id if exists.
     *
     * @param transactiontypeId The id of the TransactionType to get; value cannot be null.
     * @return TransactionType associated with the given transactiontypeId.
	 * @throws EntityNotFoundException If no TransactionType is found.
     */
    TransactionType getById(Integer transactiontypeId);

    /**
     * Find and return the TransactionType by given id if exists, returns null otherwise.
     *
     * @param transactiontypeId The id of the TransactionType to get; value cannot be null.
     * @return TransactionType associated with the given transactiontypeId.
     */
    TransactionType findById(Integer transactiontypeId);

	/**
     * Find and return the list of TransactionTypes by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param transactiontypeIds The id's of the TransactionType to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return TransactionTypes associated with the given transactiontypeIds.
     */
    List<TransactionType> findByMultipleIds(List<Integer> transactiontypeIds, boolean orderedReturn);


    /**
     * Updates the details of an existing TransactionType. It replaces all fields of the existing TransactionType with the given transactionType.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TransactionType if any.
     *
     * @param transactionType The details of the TransactionType to be updated; value cannot be null.
     * @return The updated TransactionType.
     * @throws EntityNotFoundException if no TransactionType is found with given input.
     */
    TransactionType update(@Valid TransactionType transactionType);


    /**
     * Partially updates the details of an existing TransactionType. It updates only the
     * fields of the existing TransactionType which are passed in the transactionTypePatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on TransactionType if any.
     *
     * @param transactiontypeId The id of the TransactionType to be deleted; value cannot be null.
     * @param transactionTypePatch The partial data of TransactionType which is supposed to be updated; value cannot be null.
     * @return The updated TransactionType.
     * @throws EntityNotFoundException if no TransactionType is found with given input.
     */
    TransactionType partialUpdate(Integer transactiontypeId, Map<String, Object> transactionTypePatch);

    /**
     * Deletes an existing TransactionType with the given id.
     *
     * @param transactiontypeId The id of the TransactionType to be deleted; value cannot be null.
     * @return The deleted TransactionType.
     * @throws EntityNotFoundException if no TransactionType found with the given id.
     */
    TransactionType delete(Integer transactiontypeId);

    /**
     * Deletes an existing TransactionType with the given object.
     *
     * @param transactionType The instance of the TransactionType to be deleted; value cannot be null.
     */
    void delete(TransactionType transactionType);

    /**
     * Find all TransactionTypes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TransactionTypes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<TransactionType> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all TransactionTypes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching TransactionTypes.
     *
     * @see Pageable
     * @see Page
     */
    Page<TransactionType> findAll(String query, Pageable pageable);

    /**
     * Exports all TransactionTypes matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all TransactionTypes matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the TransactionTypes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the TransactionType.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated transactions for given TransactionType id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Transaction instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Transaction> findAssociatedTransactions(Integer id, Pageable pageable);

}
