package com.arobs.weather;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseUtils {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class); 

	@Autowired
	private EntityManager entityManager;

	public Integer getNextSequenceId() {
		Query query = entityManager.createNativeQuery("SELECT nextval('openweather_id_seq')");
		BigInteger value = (BigInteger) query.getSingleResult();
		logger.info("Entity manager s-a instantiat");
		return value.intValue();
	}

}
