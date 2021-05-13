
package com.backend.dao;

import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import javax.sql.DataSource;


@ContextConfiguration
public abstract class IPLDaoImplBaseTest //extends AbstractTransactionalTestNGSpringContextTests
{

	@Autowired
	DataSource dataSource;

	@BeforeTestMethod
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(
				dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}
	protected abstract IDataSet getDataSet() throws Exception;


}
