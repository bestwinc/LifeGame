package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

public class GameView extends JPanel implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private long interval = 100;

	
	private int row;
	private int col;

	private int cellSize;

	private GameLogic gameLogic;

	private boolean isRunning = false;

	/**
	 * @wbp.parser.constructor
	 */
	public GameView(final int row, final int col) {
		this(row, col, 1);
	}

	public GameView(final int row, final int col, final int size) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		gameLogic = new GameLogic(row, col);
		this.row = row;
		this.col = col;
		this.setCellSize(size);
		start();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(Color.BLACK);
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int state = gameLogic.getElement(i, j);
				if (state == 1) {
					g.fillRect(i * getCellSize(), j * getCellSize(), getCellSize(), getCellSize());
				}
			}
		}
	}

	public void start() {
		isRunning = true;
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (isRunning) {
					GameView.this.gameLogic.updateMap();
					publish();
					Thread.sleep(GameView.this.interval);
				}
				return null;
			}

			@Override
			protected void process(List<Void> chunks) {
				super.process(chunks);
				GameView.this.repaint();
			}
		}.execute();
	}

	/**
	 * 停止更新
	 */
	public void stop() {
		isRunning = false;
	}

	/**
	 * @return 返回行数
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row 新的行数
	 */
	public void setRow(final int row) {
		this.row = row;
	}

	/**
	 * @return 返回列数
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col 新的列数
	 */
	public void setCol(final int col) {
		this.col = col;
	}

	/**
	 * @return 返回间隔时间
	 */
	public long getInterval() {
		return interval;
	}
	
	/**
	 * @param interval 新的时间间隔
	 */
	public void setInterval(final long interval) {
		this.interval = interval;
	}

	/**
	 * @return 返回一个方块的大小
	 */
	public int getCellSize() {
		return cellSize;
	}

	/**
	 * @param cellSize 设置新的方块的大小
	 */
	public void setCellSize(final int cellSize) {
		this.cellSize = cellSize;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		final int clickX = e.getX();
		final int clickY = e.getY();
		final int row = clickX / cellSize;
		final int col = clickY / cellSize;
		gameLogic.setElement(row, col, 1);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		final int clickX = e.getX();
		final int clickY = e.getY();
		final int row = clickX / cellSize;
		final int col = clickY / cellSize;
		gameLogic.setElement(row, col, 1);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
