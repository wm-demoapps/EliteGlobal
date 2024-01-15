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

import com.showcase.elite.accountsdb.CorporateUser;
import com.showcase.elite.accountsdb.service.CorporateUserService;


/**
 * Controller object for domain model class CorporateUser.
 * @see CorporateUser
 */
@RestController("AccountsDB.CorporateUserController")
@Api(value = "CorporateUserController", description = "Exposes APIs to work with CorporateUser resource.")
@RequestMapping("/AccountsDB/CorporateUser")
public class CorporateUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorporateUserController.class);

    @Autowired
	@Qualifier("AccountsDB.CorporateUserService")
	private CorporateUserService corporateUserService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new CorporateUser instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateUser createCorporateUser(@RequestBody CorporateUser corporateUser) {
		LOGGER.debug("Create CorporateUser with information: {}" , corporateUser);

		corporateUser = corporateUserService.create(corporateUser);
		LOGGER.debug("Created CorporateUser with information: {}" , corporateUser);

	    return corporateUser;
	}

    @ApiOperation(value = "Returns the CorporateUser instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateUser getCorporateUser(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting CorporateUser with id: {}" , id);

        CorporateUser foundCorporateUser = corporateUserService.getById(id);
        LOGGER.debug("CorporateUser details with id: {}" , foundCorporateUser);

        return foundCorporateUser;
    }

    @ApiOperation(value = "Updates the CorporateUser instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateUser editCorporateUser(@PathVariable("id") Integer id, @RequestBody CorporateUser corporateUser) {
        LOGGER.debug("Editing CorporateUser with id: {}" , corporateUser.getId());

        corporateUser.setId(id);
        corporateUser = corporateUserService.update(corporateUser);
        LOGGER.debug("CorporateUser details with id: {}" , corporateUser);

        return corporateUser;
    }
    
    @ApiOperation(value = "Partially updates the CorporateUser instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateUser patchCorporateUser(@PathVariable("id") Integer id, @RequestBody @MapTo(CorporateUser.class) Map<String, Object> corporateUserPatch) {
        LOGGER.debug("Partially updating CorporateUser with id: {}" , id);

        CorporateUser corporateUser = corporateUserService.partialUpdate(id, corporateUserPatch);
        LOGGER.debug("CorporateUser details after partial update: {}" , corporateUser);

        return corporateUser;
    }

    @ApiOperation(value = "Deletes the CorporateUser instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCorporateUser(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting CorporateUser with id: {}" , id);

        CorporateUser deletedCorporateUser = corporateUserService.delete(id);

        return deletedCorporateUser != null;
    }

    @GetMapping(value = "/username-email" )
    @ApiOperation(value = "Returns the matching CorporateUser with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CorporateUser getByUsernameAndEmail(@RequestParam("username") String username, @RequestParam("email") String email) {
        LOGGER.debug("Getting CorporateUser with uniques key UsernameAndEmail");
        return corporateUserService.getByUsernameAndEmail(username, email);
    }

    /**
     * @deprecated Use {@link #findCorporateUsers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of CorporateUser instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateUser> searchCorporateUsersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering CorporateUsers list by query filter:{}", (Object) queryFilters);
        return corporateUserService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CorporateUser instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateUser> findCorporateUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CorporateUsers list by filter:", query);
        return corporateUserService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CorporateUser instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CorporateUser> filterCorporateUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CorporateUsers list by filter", query);
        return corporateUserService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCorporateUsers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return corporateUserService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportCorporateUsersAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = CorporateUser.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> corporateUserService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of CorporateUser instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCorporateUsers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting CorporateUsers");
		return corporateUserService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getCorporateUserAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return corporateUserService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CorporateUserService instance
	 */
	protected void setCorporateUserService(CorporateUserService service) {
		this.corporateUserService = service;
	}

}