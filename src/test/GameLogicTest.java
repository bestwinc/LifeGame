package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.GameLogic;

public class GameLogicTest {

	private GameLogic gameLogic;

	@Before
	public void before() {
		// TODO Auto-generated method stub
		gameLogic = new GameLogic(5, 5);
	}

	@Test
	public void test() {
		gameLogic.updateMap();
		assertEquals(5,gameLogic.getCol());
		assertEquals(5,gameLogic.getRow());
	}

	@After
	public void after() {
		// TODO Auto-generated method stub

	}

}
