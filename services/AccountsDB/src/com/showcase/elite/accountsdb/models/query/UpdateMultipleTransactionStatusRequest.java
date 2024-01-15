/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateMultipleTransactionStatusRequest implements Serializable {


    @JsonProperty("statusId")
    @NotNull
    private Integer statusId;

    @JsonProperty("ids")
    @NotNull
    private List<Integer> ids;

    @JsonProperty("corporateId")
    @NotNull
    private Integer corporateId;

    public Integer getStatusId() {
        return this.statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public List<Integer> getIds() {
        return this.ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getCorporateId() {
        return this.corporateId;
    }

    public void setCorporateId(Integer corporateId) {
        this.corporateId = corporateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateMultipleTransactionStatusRequest)) return false;
        final UpdateMultipleTransactionStatusRequest updateMultipleTransactionStatusRequest = (UpdateMultipleTransactionStatusRequest) o;
        return Objects.equals(getStatusId(), updateMultipleTransactionStatusRequest.getStatusId()) &&
                Objects.equals(getIds(), updateMultipleTransactionStatusRequest.getIds()) &&
                Objects.equals(getCorporateId(), updateMultipleTransactionStatusRequest.getCorporateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusId(),
                getIds(),
                getCorporateId());
    }
}
