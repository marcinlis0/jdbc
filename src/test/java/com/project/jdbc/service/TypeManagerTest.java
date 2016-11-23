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
		Type type = new Type(NAME, PURPOSE);
		typeManager.addType(type);
	}

	@Test
	public void checkConnection() {
		assertNotNull(typeManager.getConnection());
	}

	@Test
	public void checkClear() {
		typeManager.clearType();
		assertEquals(0, typeManager.getAllTypes().size());
	}

	@Test
	public void checkClearByName() {
		typeManager.clearTypeByName(NAME);
		assertEquals(0, typeManager.getAllTypes().size());
	}

	@Test
	public void checkAdd() {
		Type type = new Type(NEWNAME, PURPOSE);
		assertEquals(true, typeManager.addType(type));
	}

	@Test
	public void checkGetAllTypes() {
		List<Type> types = typeManager.getAllTypes();
		Type typeRetrieved = types.get(0);
		assertEquals(NAME, typeRetrieved.getName());
		assertEquals(PURPOSE, typeRetrieved.getPurpose());
	}

	@Test
	public void checkGetOneType() {
		Type typeRetrieved = typeManager.getOneType(NAME);
		assertEquals(NAME, typeRetrieved.getName());
		assertEquals(PURPOSE, typeRetrieved.getPurpose());
	}

	@Test
	public void checkUpdateType() {
		Type newType = new Type(NEWNAME, NEWPURPOSE);
		assertEquals(true, typeManager.updateType(NAME, newType));
		Type typeRetrieved = typeManager.getOneType(NEWNAME);
		assertEquals(NEWNAME, typeRetrieved.getName());
		assertEquals(NEWPURPOSE, typeRetrieved.getPurpose());
	}

}
