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

import com.showcase.elite.accountsdb.PartialAccountTypeMappping;

/**
 * Service object for domain model class {@link PartialAccountTypeMappping}.
 */
public interface PartialAccountTypeMapppingService {

    /**
     * Creates a new PartialAccountTypeMappping. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on PartialAccountTypeMappping if any.
     *
     * @param partialAccountTypeMappping Details of the PartialAccountTypeMappping to be created; value cannot be null.
     * @return The newly created PartialAccountTypeMappping.
     */
    PartialAccountTypeMappping create(@Valid PartialAccountTypeMappping partialAccountTypeMappping);


	/**
     * Returns PartialAccountTypeMappping by given id if exists.
     *
     * @param partialaccounttypemapppingId The id of the PartialAccountTypeMappping to get; value cannot be null.
     * @return PartialAccountTypeMappping associated with the given partialaccounttypemapppingId.
	 * @throws EntityNotFoundException If no PartialAccountTypeMappping is found.
     */
    PartialAccountTypeMappping getById(Integer partialaccounttypemapppingId);

    /**
     * Find and return the PartialAccountTypeMappping by given id if exists, returns null otherwise.
     *
     * @param partialaccounttypemapppingId The id of the PartialAccountTypeMappping to get; value cannot be null.
     * @return PartialAccountTypeMappping associated with the given partialaccounttypemapppingId.
     */
    PartialAccountTypeMappping findById(Integer partialaccounttypemapppingId);

	/**
     * Find and return the list of PartialAccountTypeMapppings by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param partialaccounttypemapppingIds The id's of the PartialAccountTypeMappping to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return PartialAccountTypeMapppings associated with the given partialaccounttypemapppingIds.
     */
    List<PartialAccountTypeMappping> findByMultipleIds(List<Integer> partialaccounttypemapppingIds, boolean orderedReturn);

    /**
     * Find and return the PartialAccountTypeMappping for given accountTypeId  if exists.
     *
     * @param accountTypeId value of accountTypeId; value cannot be null.
     * @return PartialAccountTypeMappping associated with the given inputs.
     * @throws EntityNotFoundException if no matching PartialAccountTypeMappping found.
     */
    PartialAccountTypeMappping getByAccountTypeId(Integer accountTypeId);

    /**
     * Updates the details of an existing PartialAccountTypeMappping. It replaces all fields of the existing PartialAccountTypeMappping with the given partialAccountTypeMappping.
     *
     * This method overrides the input field values using Server side or database managed properties defined on PartialAccountTypeMappping if any.
     *
     * @param partialAccountTypeMappping The details of the PartialAccountTypeMappping to be updated; value cannot be null.
     * @return The updated PartialAccountTypeMappping.
     * @throws EntityNotFoundException if no PartialAccountTypeMappping is found with given input.
     */
    PartialAccountTypeMappping update(@Valid PartialAccountTypeMappping partialAccountTypeMappping);


    /**
     * Partially updates the details of an existing PartialAccountTypeMappping. It updates only the
     * fields of the existing PartialAccountTypeMappping which are passed in the partialAccountTypeMapppingPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on PartialAccountTypeMappping if any.
     *
     * @param partialaccounttypemapppingId The id of the PartialAccountTypeMappping to be deleted; value cannot be null.
     * @param partialAccountTypeMapppingPatch The partial data of PartialAccountTypeMappping which is supposed to be updated; value cannot be null.
     * @return The updated PartialAccountTypeMappping.
     * @throws EntityNotFoundException if no PartialAccountTypeMappping is found with given input.
     */
    PartialAccountTypeMappping partialUpdate(Integer partialaccounttypemapppingId, Map<String, Object> partialAccountTypeMapppingPatch);

    /**
     * Deletes an existing PartialAccountTypeMappping with the given id.
     *
     * @param partialaccounttypemapppingId The id of the PartialAccountTypeMappping to be deleted; value cannot be null.
     * @return The deleted PartialAccountTypeMappping.
     * @throws EntityNotFoundException if no PartialAccountTypeMappping found with the given id.
     */
    PartialAccountTypeMappping delete(Integer partialaccounttypemapppingId);

    /**
     * Deletes an existing PartialAccountTypeMappping with the given object.
     *
     * @param partialAccountTypeMappping The instance of the PartialAccountTypeMappping to be deleted; value cannot be null.
     */
    void delete(PartialAccountTypeMappping partialAccountTypeMappping);

    /**
     * Find all PartialAccountTypeMapppings matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching PartialAccountTypeMapppings.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<PartialAccountTypeMappping> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all PartialAccountTypeMapppings matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching PartialAccountTypeMapppings.
     *
     * @see Pageable
     * @see Page
     */
    Page<PartialAccountTypeMappping> findAll(String query, Pageable pageable);

    /**
     * Exports all PartialAccountTypeMapppings matching the given input query to the given exportType format.
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
     * Exports all PartialAccountTypeMapppings matching the given input query to the given exportType format.
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
     * Retrieve the count of the PartialAccountTypeMapppings in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the PartialAccountTypeMappping.
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


}
