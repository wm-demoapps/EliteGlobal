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

import com.showcase.elite.accountsdb.PaymentMethod;
import com.showcase.elite.accountsdb.Transaction;
import com.showcase.elite.accountsdb.service.PaymentMethodService;


/**
 * Controller object for domain model class PaymentMethod.
 * @see PaymentMethod
 */
@RestController("AccountsDB.PaymentMethodController")
@Api(value = "PaymentMethodController", description = "Exposes APIs to work with PaymentMethod resource.")
@RequestMapping("/AccountsDB/PaymentMethod")
public class PaymentMethodController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentMethodController.class);

    @Autowired
	@Qualifier("AccountsDB.PaymentMethodService")
	private PaymentMethodService paymentMethodService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new PaymentMethod instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
		LOGGER.debug("Create PaymentMethod with information: {}" , paymentMethod);

		paymentMethod = paymentMethodService.create(paymentMethod);
		LOGGER.debug("Created PaymentMethod with information: {}" , paymentMethod);

	    return paymentMethod;
	}

    @ApiOperation(value = "Returns the PaymentMethod instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PaymentMethod getPaymentMethod(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting PaymentMethod with id: {}" , id);

        PaymentMethod foundPaymentMethod = paymentMethodService.getById(id);
        LOGGER.debug("PaymentMethod details with id: {}" , foundPaymentMethod);

        return foundPaymentMethod;
    }

    @ApiOperation(value = "Updates the PaymentMethod instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PaymentMethod editPaymentMethod(@PathVariable("id") Integer id, @RequestBody PaymentMethod paymentMethod) {
        LOGGER.debug("Editing PaymentMethod with id: {}" , paymentMethod.getId());

        paymentMethod.setId(id);
        paymentMethod = paymentMethodService.update(paymentMethod);
        LOGGER.debug("PaymentMethod details with id: {}" , paymentMethod);

        return paymentMethod;
    }
    
    @ApiOperation(value = "Partially updates the PaymentMethod instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public PaymentMethod patchPaymentMethod(@PathVariable("id") Integer id, @RequestBody @MapTo(PaymentMethod.class) Map<String, Object> paymentMethodPatch) {
        LOGGER.debug("Partially updating PaymentMethod with id: {}" , id);

        PaymentMethod paymentMethod = paymentMethodService.partialUpdate(id, paymentMethodPatch);
        LOGGER.debug("PaymentMethod details after partial update: {}" , paymentMethod);

        return paymentMethod;
    }

    @ApiOperation(value = "Deletes the PaymentMethod instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deletePaymentMethod(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting PaymentMethod with id: {}" , id);

        PaymentMethod deletedPaymentMethod = paymentMethodService.delete(id);

        return deletedPaymentMethod != null;
    }

    /**
     * @deprecated Use {@link #findPaymentMethods(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of PaymentMethod instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PaymentMethod> searchPaymentMethodsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering PaymentMethods list by query filter:{}", (Object) queryFilters);
        return paymentMethodService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PaymentMethod instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PaymentMethod> findPaymentMethods(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PaymentMethods list by filter:", query);
        return paymentMethodService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of PaymentMethod instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PaymentMethod> filterPaymentMethods(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering PaymentMethods list by filter", query);
        return paymentMethodService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportPaymentMethods(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return paymentMethodService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportPaymentMethodsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = PaymentMethod.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> paymentMethodService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of PaymentMethod instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countPaymentMethods( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting PaymentMethods");
		return paymentMethodService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getPaymentMethodAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return paymentMethodService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/transactions")
    @ApiOperation(value = "Gets the transactions instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Transaction> findAssociatedTransactions(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated transactions");
        return paymentMethodService.findAssociatedTransactions(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PaymentMethodService instance
	 */
	protected void setPaymentMethodService(PaymentMethodService service) {
		this.paymentMethodService = service;
	}

}
