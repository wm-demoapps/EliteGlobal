/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.showcase.elite.accountsdb.dao;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wavemaker.runtime.data.dao.WMGenericDaoImpl;
import com.wavemaker.runtime.data.dao.query.types.wmql.WMQLTypeHelper;

import com.showcase.elite.accountsdb.CorporateSummary;

/**
 * Specifies methods used to obtain and modify CorporateSummary related information
 * which is stored in the database.
 */
@Repository("AccountsDB.CorporateSummaryDao")
public class CorporateSummaryDao extends WMGenericDaoImpl<CorporateSummary, Integer> {

    @Autowired
    @Qualifier("AccountsDBTemplate")
    private HibernateTemplate template;

    @Autowired
    @Qualifier("AccountsDBWMQLTypeHelper")
    private WMQLTypeHelper wmqlTypeHelper;


    @Override
    public HibernateTemplate getTemplate() {
        return this.template;
    }

    @Override
    public WMQLTypeHelper getWMQLTypeHelper() {
        return this.wmqlTypeHelper;
    }

}
