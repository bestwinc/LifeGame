package game;

/**
 * @author sukai
 */
public class GameLogic {

	private static final int THREE = 3;
	private int[][] map;
	private int[][] tempMap;
	private int row;
	private int col;

	/**
	 * @param row
	 * @param col
	 */
	public GameLogic(final int row, final int col) {
		this.setRow(row);
		this.setCol(col);
		map = new int[row][col];
		tempMap = new int[row][col];

		initMap();
	}

	/**
	 * @param map
	 */
	public GameLogic(int[][] map) {
		if (map == null || map.length == 0) {
			throw new IllegalArgumentException();
		}
		this.map = map;
		
		
		tempMap = new int[row][col];
		this.row = map.length;
		this.col = map[0].length;
	}

	/**
	 */
	public final void initMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = (int) (Math.random() * 1000) % 2;
			}
		}
	}

	/**
	 * 
	 */
	public final void updateMap() {
		int count = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				count = 0;
				try {
					count += map[i - 1][j - 1];
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					count += map[i + 1][j + 1];
				} catch (Exception e) {

				}
				try {
					count += map[i - 1][j + 1];
				} catch (Exception e) {

				}
				
				
				try {
					count += map[i + 1][j - 1];
				} catch (ArrayIndexOutOfBoundsException e) {
					e.getCause();
				}
				
				
				try {
					count += map[i][j - 1];
				} catch (Exception e) {

				}
				try {
					count += map[i - 1][j];
				} catch (Exception e) {

				}
				try {
					count += map[i + 1][j];
				} catch (Exception e) {

				}
				try {
					count += map[i][j + 1];
				} catch (Exception e) {

				}

				if (count == THREE) {
					tempMap[i][j] = 1;
				} else if (count == 2) {
					tempMap[i][j] = map[i][j];
				} else {
					tempMap[i][j] = 0;
				}
			}
		}
		final int[][] temp = map;
		map = tempMap;
		tempMap = temp;
	}

	/**
	 * @param row 行
	 * @param col 列
	 * @return 返回row行col列的元素
	 */
	public final int getElement(final int row, final int col) {
		return map[row][col];
	}

	/**
	 * @param row
	 * @param col
	 * @param value
	 */
	public final void setElement(final int row, final int col, final int value) {
		if (value != 0 && value != 1) {
			throw new IllegalArgumentException("the value must be 0 or 1");
		}
		if (row >= map.length || col >= map[0].length) {
			return;
		}
		map[row][col] = value;
	}

	/**
	 * @return 行数
	 */
	public final int getRow() {
		return row;
	}

	/**
	 * @param row 行数
	 */
	public final void setRow(final int row) {
		this.row = row;
	}
	
	/**
	 * @return
	 */
	public final int getCol() {
		return col;
	}

	/**
	 * @param col
	 */
	public final void setCol(final int col) {
		this.col = col;
	}
}
