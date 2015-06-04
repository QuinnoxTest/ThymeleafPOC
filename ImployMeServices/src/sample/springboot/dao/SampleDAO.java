package sample.springboot.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

public class SampleDAO {

	@Autowired
	CassandraOperations cassandraOperations;

	public List<String> getUsernames() {
		Select selectUsernames = QueryBuilder.select("username").from("user");
		return null == cassandraOperations.queryForList(selectUsernames, String.class) ? new ArrayList<String>() : cassandraOperations.queryForList(selectUsernames, String.class);
	}
}
