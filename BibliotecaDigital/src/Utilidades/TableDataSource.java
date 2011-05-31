package Utilidades;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class TableDataSource extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private Vector< Vector<Object> > data;
	private Vector<String> columnNames;
	
	public TableDataSource()
	{
		data = new Vector< Vector<Object> >(0,1);
		columnNames = new Vector<String>(0,1);
	}
	
	@Override
	public int getColumnCount() {
		return this.columnNames.size();
	}

	@Override
	public int getRowCount() {
		return this.data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data.elementAt(rowIndex).elementAt(columnIndex);
	}
	
	public String getColumnName(int columnIndex)
	{
		return this.columnNames.elementAt(columnIndex);
	}
	
	public void addColumn(String columnName)
	{
		columnNames.add(columnName);
	}
	
	public void addRow(Vector<Object> row)
	{
		data.add(row);
	}
	
	public void setColumnName(int index, String columnNameNew)
	{	
		columnNames.setElementAt(columnNameNew, index);
	}

	@Override
	public String toString() 
	{
		return "TableDataSource [data=" + data + ", columnNames=" + columnNames
				+ "]";
	}
}