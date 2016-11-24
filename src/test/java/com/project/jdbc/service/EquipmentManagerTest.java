package com.project.jdbc.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.project.jdbc.crud.domain.Equipment;
import com.project.jdbc.crud.domain.Type;
import com.project.jdbc.crud.service.EquipmentManager;
import com.project.jdbc.crud.service.TypeManager;



public class EquipmentManagerTest {
	TypeManager typeManager = new TypeManager();
	EquipmentManager equipmentManager = new EquipmentManager();

	private final static String NAME = "Narty";
	private final static String NEWNAME = "Snowboard";
	private final static String PURPOSE = "Wąski stok";

	private final static String MODEL = "Voelkl Stroke";
	private final static String NEWMODEL = "Voelkl Dash";
	private final static double PRICE = 20.00;

	@Before
	public void clearDatabase() {
		equipmentManager.clearEquipments();
		typeManager.clearType();
	}

	@Test
	public void checkConnection() {
		assertNotNull(equipmentManager.getConnection());
	}

	@Test
	public void checkAdd() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		assertEquals(true, equipmentManager.addEquipment(equipment));
	}

	@Test
	public void checkClear() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		equipmentManager.addEquipment(equipment);
		equipmentManager.clearEquipments();
		assertEquals(0, equipmentManager.getAllEquipment().size());
	}

	@Test
	public void checkDeleteEquipmentsByType() {
		Type type = new Type(NAME, PURPOSE);
		Type type1 = new Type(NEWNAME, PURPOSE);
		typeManager.addType(type);
		typeManager.addType(type1);

		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		Equipment equipment1 = new Equipment(NEWMODEL, PRICE, typeManager.getOneType(NEWNAME).getId());
		equipmentManager.addEquipment(equipment);
		equipmentManager.addEquipment(equipment1);

		equipmentManager.deleteEquipmentsByType(NAME);

		assertEquals(1, equipmentManager.getAllEquipment().size());
		assertEquals(0, equipmentManager.getEquipmentsByType(NAME).size());
	}

	@Test
	public void checkUpdateEquipment() {
		Type type = new Type(NAME, PURPOSE);
		Type type1 = new Type(NEWNAME, PURPOSE);
		typeManager.addType(type);
		typeManager.addType(type1);

		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		Equipment equipment1 = new Equipment(NEWMODEL, PRICE, typeManager.getOneType(NEWNAME).getId());
		equipmentManager.addEquipment(equipment);
		
		assertEquals(true, equipmentManager.updateEquipment(equipmentManager.getAllEquipment().get(0).getId(), equipment1));
		assertEquals(NEWMODEL, equipmentManager.getAllEquipment().get(0).getModel());
		assertEquals(typeManager.getOneType(NEWNAME).getId(), equipmentManager.getAllEquipment().get(0).getTypeId());
	}
	
	@Test
	public void checkUpdateEquipmentType() {
		Type type = new Type(NAME, PURPOSE);
		Type type1 = new Type(NEWNAME, PURPOSE);
		typeManager.addType(type);
		typeManager.addType(type1);

		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		Equipment equipment1 = new Equipment(NEWMODEL, PRICE, typeManager.getOneType(NEWNAME).getId());
		equipmentManager.addEquipment(equipment);
		equipmentManager.updateEquipmentType(equipmentManager.getAllEquipment().get(0).getId(), NEWNAME);
		
		assertEquals(NEWNAME, equipmentManager.getEquipmentsType(equipmentManager.getAllEquipment().get(0).getId()));
	}
	
	@Test
	public void checkGetAllEquipment() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		Equipment equipment1 = new Equipment(NEWMODEL, PRICE, typeManager.getOneType(NAME).getId());
		equipmentManager.addEquipment(equipment);
		equipmentManager.addEquipment(equipment1);
		assertEquals(2, equipmentManager.getAllEquipment().size());
	}
	
	@Test
	public void checkGetEquipmentsByType() {
		Type type = new Type(NAME, PURPOSE);
		Type type1 = new Type(NEWNAME, PURPOSE);
		typeManager.addType(type);
		typeManager.addType(type1);
		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		Equipment equipment1 = new Equipment(NEWMODEL, PRICE, typeManager.getOneType(NEWNAME).getId());
		equipmentManager.addEquipment(equipment);
		equipmentManager.addEquipment(equipment1);
		
		assertEquals(MODEL, equipmentManager.getEquipmentsByType(NAME).get(0).getModel());
		assertEquals(1, equipmentManager.getEquipmentsByType(NAME).size());
	}
	
	@Test
	public void checkGetEquipmentsType() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		Equipment equipment = new Equipment(MODEL, PRICE, typeManager.getOneType(NAME).getId());
		equipmentManager.addEquipment(equipment);
		
		assertEquals(NAME, equipmentManager.getEquipmentsType(equipmentManager.getAllEquipment().get(0).getId()));
	}

}
