package Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClassTableModel<T> extends AbstractTableModel {
	
	private boolean initialized = false;
	
	private List<String> columnNames;
	
	private Class clazz;
	
	private List<T> objects;
	
	public ClassTableModel(Class<? extends T> clazz, List<T> objects)
	{
		this.clazz = clazz;
		this.objects = objects;
		columnNames = new LinkedList<>();
		setHeaderRow(objects);
		
		initialized = true;
	}
	
	private void setHeaderRow(List<T> objects)
	{
		for(Field field : clazz.getDeclaredFields())
		{
			if(field.getDeclaredAnnotation(TableColumn.class) != null)
			{
				TableColumn name = field.getAnnotation(TableColumn.class);
				columnNames.add(name.name());
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return objects.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			return clazz.getMethod("get" + columnNames.get(columnIndex)).invoke(objects.get(rowIndex));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return !initialized;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}
}
