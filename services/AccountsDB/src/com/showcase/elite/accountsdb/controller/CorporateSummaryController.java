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

import com.showcase.elite.accountsdb.CorporateSummary;
import com.showcase.elite.accountsdb.service.CorporateSummaryService;


/**
 * Controller object for domain model class CorporateSummary.
 * @see CorporateSummary
 */
@RestController("AccountsDB.CorporateSummaryController")
@Api(value = "CorporateSummaryController", description = "Exposes APIs to work with CorporateSummary resource.")
@RequestMapping("/AccountsDB/CorporateSummary")
public class CorporateSummaryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorporateSummaryController.class);

    @Autowired
	@Qualifier("AccountsDB.CorporateSummaryService")
	private CorporateSummaryService corporateSummaryService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new CorporateSummary instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateSummary createCorporateSummary(@RequestBody CorporateSummary corporateSummary) {
		LOGGER.debug("Create CorporateSummary with information: {}" , corporateSummary);

		corporateSummary = corporateSummaryService.create(corporateSummary);
		LOGGER.debug("Created CorporateSummary with information: {}" , corporateSummary);

	    return corporateSummary;
	}

    @ApiOperation(value = "Returns the CorporateSummary instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateSummary getCorporateSummary(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting CorporateSummary with id: {}" , id);

        CorporateSummary foundCorporateSummary = corporateSummaryService.getById(id);
        LOGGER.debug("CorporateSummary details with id: {}" , foundCorporateSummary);

        return foundCorporateSummary;
    }

    @ApiOperation(value = "Updates the CorporateSummary instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateSummary editCorporateSummary(@PathVariable("id") Integer id, @RequestBody CorporateSummary corporateSummary) {
        LOGGER.debug("Editing CorporateSummary with id: {}" , corporateSummary.getId());

        corporateSummary.setId(id);
        corporateSummary = corporateSummaryService.update(corporateSummary);
        LOGGER.debug("CorporateSummary details with id: {}" , corporateSummary);

        return corporateSummary;
    }
    
    @ApiOperation(value = "Partially updates the CorporateSummary instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateSummary patchCorporateSummary(@PathVariable("id") Integer id, @RequestBody @MapTo(CorporateSummary.class) Map<String, Object> corporateSummaryPatch) {
        LOGGER.debug("Partially updating CorporateSummary with id: {}" , id);

        CorporateSummary corporateSummary = corporateSummaryService.partialUpdate(id, corporateSummaryPatch);
        LOGGER.debug("CorporateSummary details after partial update: {}" , corporateSummary);

        return corporateSummary;
    }

    @ApiOperation(value = "Deletes the CorporateSummary instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCorporateSummary(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting CorporateSummary with id: {}" , id);

        CorporateSummary deletedCorporateSummary = corporateSummaryService.delete(id);

        return deletedCorporateSummary != null;
    }

    @GetMapping(value = "/corporateId/{corporateId}" )
    @ApiOperation(value = "Returns the matching CorporateSummary with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateSummary getByCorporateId(@PathVariable("corporateId") Integer corporateId) {
        LOGGER.debug("Getting CorporateSummary with uniques key CorporateId");
        return corporateSummaryService.getByCorporateId(corporateId);
    }

    /**
     * @deprecated Use {@link #findCorporateSummaries(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of CorporateSummary instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateSummary> searchCorporateSummariesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering CorporateSummaries list by query filter:{}", (Object) queryFilters);
        return corporateSummaryService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CorporateSummary instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateSummary> findCorporateSummaries(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CorporateSummaries list by filter:", query);
        return corporateSummaryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CorporateSummary instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateSummary> filterCorporateSummaries(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CorporateSummaries list by filter", query);
        return corporateSummaryService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCorporateSummaries(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return corporateSummaryService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportCorporateSummariesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = CorporateSummary.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> corporateSummaryService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of CorporateSummary instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCorporateSummaries( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting CorporateSummaries");
		return corporateSummaryService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getCorporateSummaryAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return corporateSummaryService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CorporateSummaryService instance
	 */
	protected void setCorporateSummaryService(CorporateSummaryService service) {
		this.corporateSummaryService = service;
	}

}
