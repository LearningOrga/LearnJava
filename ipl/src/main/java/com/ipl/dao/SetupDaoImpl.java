package com.ipl.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//TODO: import com.ibatis.common.jdbc.ScriptRunner;

@Repository("setupDao")
public class SetupDaoImpl extends AbstractDao implements SetUpDao {

	@Autowired
	DataSource dataSource;

	@Override
	public void setup(String fileName) {
		//("setup"+fileName);
		
		fileName=fileName+".sql";
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		String aSQLScriptFilePath = file.getAbsolutePath();
		//("path-"+aSQLScriptFilePath);

		Connection con = null;
		Statement stmt = null;

		try {
			con = dataSource.getConnection();
		} catch (SQLException e1) {
			//("Errorin getting connection in setupDao");
		}

		try {
			// Initialize object for ScripRunner
			//ScriptRunner sr = new ScriptRunner(con, false, false);

			// Give the input file to Reader
			Reader reader = new BufferedReader(new FileReader(
					aSQLScriptFilePath));

			// Exctute script
			//sr.runScript(reader);

		} catch (Exception e) {
			System.err.println("Failed to Execute" + aSQLScriptFilePath
					+ " The error is " + e.getMessage());
		}

	}

}
