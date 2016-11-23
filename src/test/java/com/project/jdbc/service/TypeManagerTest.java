package com.project.jdbc.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.project.jdbc.domain.Type;
import com.project.jdbc.service.TypeManager;

public class TypeManagerTest {
	TypeManager typeManager = new TypeManager();

	private final static String NAME = "Narty";
	private final static String NEWNAME = "Snowboard";
	private final static String PURPOSE = "Wąski stok";
	private final static String NEWPURPOSE = "Szeroki stok";

	@Before
	public void clear_database() {
		typeManager.clearType();
	}

	@Test
	public void checkConnection() {
		assertNotNull(typeManager.getConnection());
	}

	@Test
	public void checkClear() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		typeManager.clearType();
		assertEquals(0, typeManager.getAllTypes().size());
	}

	@Test
	public void checkClearByName() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		typeManager.clearTypeByName(NAME);
		assertEquals(0, typeManager.getAllTypes().size());
	}

	@Test
	public void checkAdd() {
		Type type = new Type(NAME, PURPOSE);
		assertEquals(true, typeManager.addType(type));
	}

	@Test
	public void checkGetAllTypes() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		List<Type> types = typeManager.getAllTypes();
		Type typeRetrieved = types.get(0);
		assertEquals(NAME, typeRetrieved.getName());
		assertEquals(PURPOSE, typeRetrieved.getPurpose());
	}

	@Test
	public void checkGetOneType() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		Type typeRetrieved = typeManager.getOneType(NAME);
		assertEquals(NAME, typeRetrieved.getName());
		assertEquals(PURPOSE, typeRetrieved.getPurpose());
	}

	@Test
	public void checkUpdateType() {
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
		assertEquals(true, typeManager.updateType(NAME, NEWNAME, NEWPURPOSE));
		Type typeRetrieved = typeManager.getOneType(NEWNAME);
		assertEquals(NEWNAME, typeRetrieved.getName());
		assertEquals(NEWPURPOSE, typeRetrieved.getPurpose());
	}

}
