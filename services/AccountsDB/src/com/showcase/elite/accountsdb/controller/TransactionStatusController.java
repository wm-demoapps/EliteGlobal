/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.commons.file.manager.ExportedFileManager;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.showcase.elite.accountsdb.Transaction;
import com.showcase.elite.accountsdb.TransactionStatus;
import com.showcase.elite.accountsdb.service.TransactionStatusService;


/**
 * Controller object for domain model class TransactionStatus.
 * @see TransactionStatus
 */
@RestController("AccountsDB.TransactionStatusController")
@Api(value = "TransactionStatusController", description = "Exposes APIs to work with TransactionStatus resource.")
@RequestMapping("/AccountsDB/TransactionStatus")
public class TransactionStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionStatusController.class);

    @Autowired
	@Qualifier("AccountsDB.TransactionStatusService")
	private TransactionStatusService transactionStatusService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new TransactionStatus instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionStatus createTransactionStatus(@RequestBody TransactionStatus transactionStatus) {
		LOGGER.debug("Create TransactionStatus with information: {}" , transactionStatus);

		transactionStatus = transactionStatusService.create(transactionStatus);
		LOGGER.debug("Created TransactionStatus with information: {}" , transactionStatus);

	    return transactionStatus;
	}

    @ApiOperation(value = "Returns the TransactionStatus instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionStatus getTransactionStatus(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting TransactionStatus with id: {}" , id);

        TransactionStatus foundTransactionStatus = transactionStatusService.getById(id);
        LOGGER.debug("TransactionStatus details with id: {}" , foundTransactionStatus);

        return foundTransactionStatus;
    }

    @ApiOperation(value = "Updates the TransactionStatus instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionStatus editTransactionStatus(@PathVariable("id") Integer id, @RequestBody TransactionStatus transactionStatus) {
        LOGGER.debug("Editing TransactionStatus with id: {}" , transactionStatus.getId());

        transactionStatus.setId(id);
        transactionStatus = transactionStatusService.update(transactionStatus);
        LOGGER.debug("TransactionStatus details with id: {}" , transactionStatus);

        return transactionStatus;
    }
    
    @ApiOperation(value = "Partially updates the TransactionStatus instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionStatus patchTransactionStatus(@PathVariable("id") Integer id, @RequestBody @MapTo(TransactionStatus.class) Map<String, Object> transactionStatusPatch) {
        LOGGER.debug("Partially updating TransactionStatus with id: {}" , id);

        TransactionStatus transactionStatus = transactionStatusService.partialUpdate(id, transactionStatusPatch);
        LOGGER.debug("TransactionStatus details after partial update: {}" , transactionStatus);

        return transactionStatus;
    }

    @ApiOperation(value = "Deletes the TransactionStatus instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTransactionStatus(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting TransactionStatus with id: {}" , id);

        TransactionStatus deletedTransactionStatus = transactionStatusService.delete(id);

        return deletedTransactionStatus != null;
    }

    /**
     * @deprecated Use {@link #findTransactionStatuses(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of TransactionStatus instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionStatus> searchTransactionStatusesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering TransactionStatuses list by query filter:{}", (Object) queryFilters);
        return transactionStatusService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionStatus instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionStatus> findTransactionStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionStatuses list by filter:", query);
        return transactionStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionStatus instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionStatus> filterTransactionStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionStatuses list by filter", query);
        return transactionStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTransactionStatuses(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return transactionStatusService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportTransactionStatusesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = TransactionStatus.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> transactionStatusService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of TransactionStatus instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countTransactionStatuses( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting TransactionStatuses");
		return transactionStatusService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getTransactionStatusAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return transactionStatusService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/transactions")
    @ApiOperation(value = "Gets the transactions instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Transaction> findAssociatedTransactions(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated transactions");
        return transactionStatusService.findAssociatedTransactions(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TransactionStatusService instance
	 */
	protected void setTransactionStatusService(TransactionStatusService service) {
		this.transactionStatusService = service;
	}

}
