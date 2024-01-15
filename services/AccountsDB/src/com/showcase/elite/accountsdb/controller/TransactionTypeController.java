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
import com.showcase.elite.accountsdb.TransactionType;
import com.showcase.elite.accountsdb.service.TransactionTypeService;


/**
 * Controller object for domain model class TransactionType.
 * @see TransactionType
 */
@RestController("AccountsDB.TransactionTypeController")
@Api(value = "TransactionTypeController", description = "Exposes APIs to work with TransactionType resource.")
@RequestMapping("/AccountsDB/TransactionType")
public class TransactionTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionTypeController.class);

    @Autowired
	@Qualifier("AccountsDB.TransactionTypeService")
	private TransactionTypeService transactionTypeService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new TransactionType instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionType createTransactionType(@RequestBody TransactionType transactionType) {
		LOGGER.debug("Create TransactionType with information: {}" , transactionType);

		transactionType = transactionTypeService.create(transactionType);
		LOGGER.debug("Created TransactionType with information: {}" , transactionType);

	    return transactionType;
	}

    @ApiOperation(value = "Returns the TransactionType instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionType getTransactionType(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting TransactionType with id: {}" , id);

        TransactionType foundTransactionType = transactionTypeService.getById(id);
        LOGGER.debug("TransactionType details with id: {}" , foundTransactionType);

        return foundTransactionType;
    }

    @ApiOperation(value = "Updates the TransactionType instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionType editTransactionType(@PathVariable("id") Integer id, @RequestBody TransactionType transactionType) {
        LOGGER.debug("Editing TransactionType with id: {}" , transactionType.getId());

        transactionType.setId(id);
        transactionType = transactionTypeService.update(transactionType);
        LOGGER.debug("TransactionType details with id: {}" , transactionType);

        return transactionType;
    }
    
    @ApiOperation(value = "Partially updates the TransactionType instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public TransactionType patchTransactionType(@PathVariable("id") Integer id, @RequestBody @MapTo(TransactionType.class) Map<String, Object> transactionTypePatch) {
        LOGGER.debug("Partially updating TransactionType with id: {}" , id);

        TransactionType transactionType = transactionTypeService.partialUpdate(id, transactionTypePatch);
        LOGGER.debug("TransactionType details after partial update: {}" , transactionType);

        return transactionType;
    }

    @ApiOperation(value = "Deletes the TransactionType instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteTransactionType(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting TransactionType with id: {}" , id);

        TransactionType deletedTransactionType = transactionTypeService.delete(id);

        return deletedTransactionType != null;
    }

    /**
     * @deprecated Use {@link #findTransactionTypes(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of TransactionType instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionType> searchTransactionTypesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering TransactionTypes list by query filter:{}", (Object) queryFilters);
        return transactionTypeService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionType instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionType> findTransactionTypes(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionTypes list by filter:", query);
        return transactionTypeService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of TransactionType instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<TransactionType> filterTransactionTypes(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering TransactionTypes list by filter", query);
        return transactionTypeService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportTransactionTypes(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return transactionTypeService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportTransactionTypesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = TransactionType.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> transactionTypeService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of TransactionType instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countTransactionTypes( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting TransactionTypes");
		return transactionTypeService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getTransactionTypeAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return transactionTypeService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/transactions")
    @ApiOperation(value = "Gets the transactions instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Transaction> findAssociatedTransactions(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated transactions");
        return transactionTypeService.findAssociatedTransactions(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TransactionTypeService instance
	 */
	protected void setTransactionTypeService(TransactionTypeService service) {
		this.transactionTypeService = service;
	}

}
