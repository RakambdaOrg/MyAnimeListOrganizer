package fr.myanimelistorganizer.frames.components;

import javax.swing.table.DefaultTableModel;

/**
 * Used to create an uneditable JTable.
 *
 * @author COLEAU Victor, COUCHOUD Thomas
 */
public class JTableUneditableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 1595933236184593763L;

	/**
	 * Constructor.
	 *
	 * @param tableData The data of the table.
	 * @param columnsNames The columns name.
	 * @see DefaultTableModel#DefaultTableModel(Object[][], Object[])
	 */
	public JTableUneditableModel(Object[][] tableData, Object[] columnsNames)
	{
		super(tableData, columnsNames);
	}

	/**
	 * Used to know if the cell id editable.
	 *
	 * @return false as we want the table to be uneditable.
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
