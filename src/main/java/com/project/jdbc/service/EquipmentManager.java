package com.project.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.jdbc.domain.Equipment;

public class EquipmentManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableEquipment = "CREATE TABLE Equipment(id bigint GENERATED BY DEFAULT AS IDENTITY, model varchar(25), price decimal, type_id int, PRIMARY KEY(id), FOREIGN KEY(type_id) REFERENCES Type(id) ON DELETE CASCADE ON UPDATE CASCADE)";

	private PreparedStatement addEquipmentStmt;
	private PreparedStatement deleteAllEquipmentStmt;
	private PreparedStatement deleteEquipmentByTypeStmt;
	private PreparedStatement updateEquipmentStmt;
	private PreparedStatement updateEquipmentTypeStmt;
	private PreparedStatement getAllEquipmentStmt;
	private PreparedStatement getEquipmentByTypeStmt;
	private PreparedStatement getEquipmentsTypeStmt;

	private Statement statement;

	public EquipmentManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Equipment".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTableEquipment);

			addEquipmentStmt = connection
					.prepareStatement("INSERT INTO Equipment(model, price, type_id) VALUES (?, ?, ?)");
			deleteAllEquipmentStmt = connection.prepareStatement("DELETE FROM Equipment");
			deleteEquipmentByTypeStmt = connection
					.prepareStatement("DELETE FROM Equipment WHERE type_id=(SELECT id FROM Type WHERE name=?)");
			updateEquipmentStmt = connection
					.prepareStatement("UPDATE Equipment SET model=?, price=?, type_id=? WHERE id=?");
			updateEquipmentTypeStmt = connection
					.prepareStatement("UPDATE Equipment SET type_id=(SELECT id FROM Type WHERE name=?) WHERE id=?");
			getAllEquipmentStmt = connection.prepareStatement("SELECT * FROM Equipment");
			getEquipmentByTypeStmt = connection
					.prepareStatement("SELECT * FROM Equipment WHERE type_id=(SELECT id FROM Type WHERE name=?)");
			getEquipmentsTypeStmt = connection
					.prepareStatement("SELECT name FROM Type WHERE id=(SELECT type_id FROM Equipment WHERE id=?)");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean addEquipment(Equipment equipment) {
		int count = 0;
		try {
			addEquipmentStmt.setString(1, equipment.getModel());
			addEquipmentStmt.setDouble(2, equipment.getPrice());
			addEquipmentStmt.setLong(3, equipment.getTypeId());
			count = addEquipmentStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count == 1 ? true : false;
	}

	public void clearEquipments() {
		try {
			deleteAllEquipmentStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteEquipmentsByType(String typeName) {
		try {
			deleteEquipmentByTypeStmt.setString(1, typeName);
			deleteEquipmentByTypeStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEquipment(long id, Equipment newEquipment) {
		try {
			updateEquipmentStmt.setString(1, newEquipment.getModel());
			updateEquipmentStmt.setDouble(2, newEquipment.getPrice());
			updateEquipmentStmt.setLong(3, newEquipment.getTypeId());
			updateEquipmentStmt.setLong(4, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEquipmentType(long id, String typeName) {
		try {
			updateEquipmentTypeStmt.setString(1, typeName);
			updateEquipmentTypeStmt.setLong(2, id);
			updateEquipmentTypeStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Equipment> getAllEquipment() {
		List<Equipment> equipments = new ArrayList<Equipment>();
		try {
			ResultSet rs = getAllEquipmentStmt.executeQuery();
			while (rs.next()) {
				Equipment eq = new Equipment();
				eq.setId(rs.getLong("id"));
				eq.setModel(rs.getString("model"));
				eq.setPrice(rs.getDouble("price"));
				eq.setTypeId(rs.getLong("type_id"));
				equipments.add(eq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipments;
	}

	public List<Equipment> getEquipmentsByType(String name) {
		List<Equipment> equipments = new ArrayList<Equipment>();
		try {
			getEquipmentByTypeStmt.setString(1, name);
			ResultSet rs = getEquipmentByTypeStmt.executeQuery();
			while (rs.next()) {
				Equipment eq = new Equipment();
				eq.setId(rs.getLong("id"));
				eq.setModel(rs.getString("model"));
				eq.setPrice(rs.getDouble("price"));
				eq.setTypeId(rs.getLong("type_id"));
				equipments.add(eq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipments;
	}

	public String getEquipmentsType(long id) {
		String name = "";
		try {
			getEquipmentsTypeStmt.setLong(1, id);
			ResultSet rs = getEquipmentsTypeStmt.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

}
