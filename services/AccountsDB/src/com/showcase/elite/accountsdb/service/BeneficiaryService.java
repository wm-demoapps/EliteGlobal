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

import com.showcase.elite.accountsdb.Beneficiary;
import com.showcase.elite.accountsdb.Transaction;

/**
 * Service object for domain model class {@link Beneficiary}.
 */
public interface BeneficiaryService {

    /**
     * Creates a new Beneficiary. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Beneficiary if any.
     *
     * @param beneficiary Details of the Beneficiary to be created; value cannot be null.
     * @return The newly created Beneficiary.
     */
    Beneficiary create(@Valid Beneficiary beneficiary);


	/**
     * Returns Beneficiary by given id if exists.
     *
     * @param beneficiaryId The id of the Beneficiary to get; value cannot be null.
     * @return Beneficiary associated with the given beneficiaryId.
	 * @throws EntityNotFoundException If no Beneficiary is found.
     */
    Beneficiary getById(Integer beneficiaryId);

    /**
     * Find and return the Beneficiary by given id if exists, returns null otherwise.
     *
     * @param beneficiaryId The id of the Beneficiary to get; value cannot be null.
     * @return Beneficiary associated with the given beneficiaryId.
     */
    Beneficiary findById(Integer beneficiaryId);

	/**
     * Find and return the list of Beneficiaries by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param beneficiaryIds The id's of the Beneficiary to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Beneficiaries associated with the given beneficiaryIds.
     */
    List<Beneficiary> findByMultipleIds(List<Integer> beneficiaryIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Beneficiary. It replaces all fields of the existing Beneficiary with the given beneficiary.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Beneficiary if any.
     *
     * @param beneficiary The details of the Beneficiary to be updated; value cannot be null.
     * @return The updated Beneficiary.
     * @throws EntityNotFoundException if no Beneficiary is found with given input.
     */
    Beneficiary update(@Valid Beneficiary beneficiary);


    /**
     * Partially updates the details of an existing Beneficiary. It updates only the
     * fields of the existing Beneficiary which are passed in the beneficiaryPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Beneficiary if any.
     *
     * @param beneficiaryId The id of the Beneficiary to be deleted; value cannot be null.
     * @param beneficiaryPatch The partial data of Beneficiary which is supposed to be updated; value cannot be null.
     * @return The updated Beneficiary.
     * @throws EntityNotFoundException if no Beneficiary is found with given input.
     */
    Beneficiary partialUpdate(Integer beneficiaryId, Map<String, Object> beneficiaryPatch);

    /**
     * Deletes an existing Beneficiary with the given id.
     *
     * @param beneficiaryId The id of the Beneficiary to be deleted; value cannot be null.
     * @return The deleted Beneficiary.
     * @throws EntityNotFoundException if no Beneficiary found with the given id.
     */
    Beneficiary delete(Integer beneficiaryId);

    /**
     * Deletes an existing Beneficiary with the given object.
     *
     * @param beneficiary The instance of the Beneficiary to be deleted; value cannot be null.
     */
    void delete(Beneficiary beneficiary);

    /**
     * Find all Beneficiaries matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Beneficiaries.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Beneficiary> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Beneficiaries matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Beneficiaries.
     *
     * @see Pageable
     * @see Page
     */
    Page<Beneficiary> findAll(String query, Pageable pageable);

    /**
     * Exports all Beneficiaries matching the given input query to the given exportType format.
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
     * Exports all Beneficiaries matching the given input query to the given exportType format.
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
     * Retrieve the count of the Beneficiaries in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Beneficiary.
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
     * Returns the associated transactions for given Beneficiary id.
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
