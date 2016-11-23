package com.project.jdbc.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.project.jdbc.domain.Type;

public class EquipmentManagerTest {
	EquipmentManager equipmentManager = new EquipmentManager();

	private final static String MODEL = "Voelkl Stroke";
	private final static String NEWMODEL = "Voelkl Dash";
	private final static double PRICE = 20.00;
	private final static double NEWPRICE = 25.50;
	private final static long TYPE_ID = 5;
	private final static long NEWTYPE_ID = 10;

	@Before
	public void clear_database() {
		equipmentManager.deleteAllEquipments();
	}
	
	@Test
	public void checkConnection() {
		assertNotNull(equipmentManager.getConnection());
	}
	
	
}
