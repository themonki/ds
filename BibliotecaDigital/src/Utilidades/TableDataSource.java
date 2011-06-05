/**
 * TablaDataSource.java
 * 
 * Clase que podria ser usada para almacenar los resultados de
 * una consulta a una base da datos, que siendo así, podria ser 
 * usado para construir un Data Source con el que se ingresaran 
 * datos a los reportes
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Cristian Leonardo Rios
 * Version:   4.0
 */
package Utilidades;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * @author Cristian Leonardo Rios.
 *
 */
public class TableDataSource extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private Vector<Vector<Object>> data;
	private Vector<String> columnNames;

	/**
	 * 
	 */
	public TableDataSource() {
		data = new Vector<Vector<Object>>(0, 1);
		columnNames = new Vector<String>(0, 1);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return this.columnNames.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data.elementAt(rowIndex).elementAt(columnIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		return this.columnNames.elementAt(columnIndex);
	}

	/**
	 * @param columnName
	 */
	public void addColumn(String columnName) {
		columnNames.add(columnName);
	}

	/**
	 * @param row
	 */
	public void addRow(Vector<Object> row) {
		data.add(row);
	}

	/**
	 * @param index
	 * @param columnNameNew
	 */
	public void setColumnName(int index, String columnNameNew) {
		columnNames.setElementAt(columnNameNew, index);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TableDataSource [data=" + data + ", columnNames=" + columnNames
				+ "]";
	}
}
