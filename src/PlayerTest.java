

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class PlayerTest{
	
	PlayGame playgame = new PlayGame(2, 4); // Player 2�� Player ������ ���� 4���� ���� ���� Test
	Player player0 = new Player(0,4); // id ���� 0(-> P0), ���� ������ 4���� Player0 ��ü ����
	Player player1 = new Player(1,4); // id ���� 1(-> P1), ���� ������ 4���� Player1 ��ü ����
	
	@Test
	public void playerInfoTest() { // Player�� id����, ���� ������ �ùٸ��� Ȯ��
		
		//player0 info test
		
		assertEquals(0, player0.getId());
		assertEquals(4, player0.getPieceNum());
		
		//player1 info test
		
		assertEquals(1, player1.getId());
		assertEquals(4, player1.getPieceNum());
	}
	
	@Test
	public void pieceMoveCaseOne() { // Player0�� �� 1���� ������ ���� ��Ģ�� �°� �� �����̴��� Ȯ��(Case 1)
		
		// ������ ���� ��ġ ������ ��Ȯ�� � ������� �̷��������� ���� Document ���� �ٶ�
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, �ʱ� X ��ǥ 0���� Ȯ�� 
		assertEquals(0, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, �ʱ� Y ��ǥ 0���� Ȯ��
		
		player0.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=2)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(2, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 2���� Ȯ��
		
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=2) -> (X=0, Y=5)�� �ٲ�

		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(5, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 5���� Ȯ��
		
		// (X=0, Y=5)�� �������� ���� ������ ��, ������ ��ܿ��� �����ϴ� �밢������ �� ������ Ȯ��(�̶� X�� ��ǥ�� 1�� �ٲ�)
		// (X=0, Y=5) = (X=1, Y=0)
		// ����, '��'�� �����ϸ� (X=1, Y=1)�̶�� ����� ���;� ��
		
		player0.getPieces().get(0).setPos(1); // ù ��° ��, '��'��ŭ �̵�(1ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=5) = (X=1, Y=0) -> (X=1, Y=1)
		
		assertEquals(1, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 1���� Ȯ��
		assertEquals(1, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 1���� Ȯ��
		
		// ������ ���� �� ��� ��ġ�� ���� �� ��ǥ�� X=2, Y=3�� �������� �ٲ���� Ȯ��
		// ������ (X=1, Y=1) ��ġ���� '��'�� �����ϸ� (X=1, Y=3)���� �ٲ�
		// (X=1, Y=3) = (X=2, Y=3)
		
		player0.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=1, Y=1) -> (X=1, Y=3) = (X=2, Y=3)
		
		assertEquals(2, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 2���� Ȯ��
		assertEquals(3, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 3���� Ȯ��
		
		// ������ (X=2, Y=3) ��ġ���� '��'�� �����ϸ� (X=2, Y=6)���� �ٲ�(�����)
		// (X=2, Y=6) = (X=0, Y=20)
		
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=2, Y=3) -> (X=2, Y=6) = (X=0, Y=20) = �����
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(20, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 20���� Ȯ��
		
		// ������ (X=0, Y=20) ��ġ���� '��'�� �����ϸ� (X=0, Y=22)(Y�� 20���� ũ��, ����� ���)
		
		player0.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �߰��� �̵�)
		
		// ��������� '��'�� ������ ���̹Ƿ� ���� ������ ���� �� ���� ���� ó��
		
		assertEquals(1, player0.checkPiecein()); // player0�� �� �� ������ ���� �� ���� ���� ���� �ִ��� Ȯ��
		
		assertEquals(3, player0.getPieceNum()); // player0�� ���� ���� 3������ Ȯ��
	}
	
	@Test
	public void pieceMoveCaseTwo() { // Player1�� �� 1���� ������ ���� ��Ģ�� �°� �� �����̴��� Ȯ��(Case 2)
		
		// ������ ���� ��ġ ������ ��Ȯ�� � ������� �̷��������� ���� Document ���� �ٶ�
		
		player1.createPiece(); // player1�� ù ��° ��, �� ���� �ø���
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, �ʱ� X ��ǥ 0���� Ȯ�� 
		assertEquals(0, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, �ʱ� Y ��ǥ 0���� Ȯ��
		
		player1.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=2)�� �ٲ�
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(2, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 2���� Ȯ��
		
		player1.getPieces().get(0).setPos(5); // ù ��° ��, '��'��ŭ �̵�(5ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=2) -> (X=0, Y=7)�� �ٲ�

		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(7, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 7���� Ȯ��
		
		player1.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=7) -> (X=0, Y=10)���� �ٲ�
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(10, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 10���� Ȯ��
		
		// (X=0, Y=10)�� �������� ���� ������ ��, ���� ��ܿ��� �����ϴ� �밢������ �� ������ Ȯ��(�̶� X�� ��ǥ�� 2�� �ٲ�)
		// (X=0, Y=10) = (X=2, Y=0)
		// ����, '��'�� �����ϸ� (X=2, Y=1)�̶�� ����� ���;� ��
		
		player1.getPieces().get(0).setPos(1); // ù ��° ��, '��'��ŭ �̵�(1ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=10) = (X=2, Y=0) -> (X=2, Y=1)
		
		assertEquals(2, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 2���� Ȯ��
		assertEquals(1, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 1���� Ȯ��
		
		player1.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=2, Y=1) -> (X=2, Y=3)
		
		assertEquals(2, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 2���� Ȯ��
		assertEquals(3, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 3���� Ȯ��
		
		player1.getPieces().get(0).setPos(4); // ù ��° ��, '��'��ŭ �̵�(4ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=2, Y=3) -> (X=2, Y=7) = (X=0, Y=21) = ����� ���
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(21, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 21���� Ȯ��
		
		assertEquals(1, player1.checkPiecein()); // player1�� �� �� ������ ���� �� ���� ���� ���� �ִ��� Ȯ��
		
		assertEquals(3, player1.getPieceNum()); // player1�� ���� ���� 3������ Ȯ��
	}
	
	@Test
	public void pieceMoveCaseThree() { // Player0�� �� 1���� ������ ���� ��Ģ�� �°� �� �����̴��� Ȯ��(Case 3)
		
		// ������ ���� ��ġ ������ ��Ȯ�� � ������� �̷��������� ���� Document ���� �ٶ�
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, �ʱ� X ��ǥ 0���� Ȯ�� 
		assertEquals(0, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, �ʱ� Y ��ǥ 0���� Ȯ��
		
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=3)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(3, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 3���� Ȯ��
		
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=3) -> (X=0, Y=6)���� �ٲ�

		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(6, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 6���� Ȯ��
		
		player0.getPieces().get(0).setPos(2); // ù ��° ��, '��'��ŭ �̵�(2ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=6) -> (X=0, Y=8)���� �ٲ�

		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(8, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 8���� Ȯ��
		
		player0.getPieces().get(0).setPos(5); // ù ��° ��, '��'��ŭ �̵�(5ĭ �߰��� �̵�)
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=8) -> (X=0, Y=16)���� �ٲ�

		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(16, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 16���� Ȯ��
		
		player0.getPieces().get(0).setPos(5); // ù ��° ��, '��'��ŭ �̵�(5ĭ �߰��� �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=16) -> (X=0, Y=21)���� �ٲ�
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(21, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 21���� Ȯ��
		
		assertEquals(1, player0.checkPiecein()); // player0�� �� �� ������ ���� �� ���� ���� ���� �ִ��� Ȯ��
		
		assertEquals(3, player0.getPieceNum()); // player0�� ���� ���� 3������ Ȯ��
	}
	
	@Test
	public void pieceGroupTest() {
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		
		player0.getPieces().get(0).setPos(3); // ù ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=3)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(3, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 3���� Ȯ��
		
		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		
		player0.getPieces().get(1).setPos(2); // �� ��° ��, '��'��ŭ �̵�(2ĭ �̵�)

		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=2)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(1).getX()); // player0�� �� ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(2, player0.getPieces().get(1).getY()); // player0�� �� ��° ��, Y ��ǥ 2���� Ȯ��
		
		assertEquals(0, player0.checkUpda()); // player0�� ù ��° ��, �� ��° ���� ������ ���� ���±� ������ 0 ����
		
		player0.getPieces().get(1).setPos(1); // �� ��° ��, '��'��ŭ �̵�(1ĭ �߰� �̵�)

		// �׷��� ���� ��ǥ�� (X=0, Y=2) -> (X=0, Y=3)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(1).getX()); // player0�� �� ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(3, player0.getPieces().get(1).getY()); // player0�� �� ��° ��, Y ��ǥ 3���� Ȯ��
		
		// �׷��� ���� player0�� ù ���� ���� �� ��° ���� ��� (X=0, Y=3)�� ��ġ��, �� �������� üũ
		
		assertEquals(1, player0.checkUpda()); // player0�� ù ��° ��, �� ��° �� �� ������ ������ 1 ����
		
		assertEquals(2, player0.getPieces().get(0).getPoint()); // ���� ���� ������ �� 2��, ���� ���� ������ �������ִ� getPoint() ���� 2���� Ȯ��
		
		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		
		player0.getPieces().get(1).setPos(3); // �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		assertEquals(1, player0.checkUpda()); // player0�� ù ��° ��, �� ��° ��, �� ��° �� ��� �� ������ ������ 1 ����
		
		assertEquals(3, player0.getPieces().get(0).getPoint()); // ���� ���� ������ �� 3��, ���� ���� ������ �������ִ� getPoint() ���� 3���� Ȯ��
	}
	
	@Test
	public void pieceCatchTest() {
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		
		player0.getPieces().get(0).setPos(4); // player0�� ù ��° ��, '��'��ŭ �̵�(4ĭ �̵�)
		
		// �׷��� ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=4)�� �ٲ�
		
		assertEquals(0, player0.getPieces().get(0).getX()); // player0�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(4, player0.getPieces().get(0).getY()); // player0�� ù ��° ��, Y ��ǥ 4���� Ȯ��
		
		assertEquals(1, player0.getPieces().size()); // ������ �ǿ� ���� �ִ� player1�� ���� 1������ Ȯ��
		
		player1.createPiece(); // player1�� ù ��° ��, �� ���� �ø���
		
		player1.getPieces().get(0).setPos(3); // player1�� ù ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		// �׷��� player1�� ù ��° ���� ��ǥ�� (X=0, Y=0) -> (X=0, Y=3)���� �ٲ�
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(3, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 3���� Ȯ��
		
		player1.getPieces().get(0).setPos(1); // player1�� ù ��° ��, '��'��ŭ �̵�(1ĭ �̵�)
		
		// �׷��� player1�� ù ��° ���� ��ǥ�� (X=0, Y=3) -> (X=0, Y=4)�� �ٲ�
		
		assertEquals(0, player1.getPieces().get(0).getX()); // player1�� ù ��° ��, X ��ǥ 0���� Ȯ��
		assertEquals(4, player1.getPieces().get(0).getY()); // player1�� ù ��° ��, Y ��ǥ 4���� Ȯ��
		
		assertEquals(1, player0.checkCatch(player1.getPieces().get(0).getX(), player1.getPieces().get(0).getY())); // player0�� ���� player1�� ���� ���� �������� Ȯ��
		
		assertEquals(0, player0.getPieces().size()); // �׷��� ������ �ǿ� ���� �ִ� player0�� ���� 0���� �Ǵ��� Ȯ��
		
		// ������ player0�� ������ ���� ���¿���, player1�� ���� ���� ������ �� �� �۵��ϴ��� Ȯ��
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		player0.move(0, 0, 3); // player0�� ù ��° ��, '��'��ŭ �̵�(3ĭ �̵�)

		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		player0.move(0, 0, 3); // player0�� �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)

		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		player0.move(0, 0, 3); // player0�� �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		player1.createPiece(); // player1�� �� ��° ��, �� ���� �ø���
		
		player1.getPieces().get(1).setPos(3); // player1�� �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		playgame.checkCatch(1);
		assertEquals(1, player0.checkCatch(player1.getPieces().get(1).getX(), player1.getPieces().get(1).getY())); // player0�� �� 3���� player1�� �� ��° ���� ���� �������� Ȯ��
		
		assertEquals(0, player0.getPieces().size()); // ������ �ǿ� ���� �ִ� player0�� ���� 0������ Ȯ��
	}
	
	@Test
	public void playerPointTest() {
		
		player0.createPiece(); // player0�� ù ��° ��, �� ���� �ø���
		player0.getPieces().get(0).setPos(22); // player0�� ù ��° ��, 22ĭ �̵�(����� ���)

		assertEquals(1, player0.checkPiecein()); // ������� ����� ���� �ֱ� ������ 1 ����
		assertEquals(1, player0.getPoint()); // ������� ����� ���� 1���̱� ������ player0�� ����Ʈ�� 1��
		
		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		player0.getPieces().get(0).setPos(21); // player0�� �� ��° ��, 21ĭ �̵�(����� ���)
		
		assertEquals(1, player0.checkPiecein()); // ������� ����� ���� �ֱ� ������ 1 ����
		assertEquals(2, player0.getPoint()); // ������� ����� ���� �� 2���̱� ������ player0�� ����Ʈ�� 2��
		
		// �Ʒ��� ������ �� �� ���� ��� ������� ����� ���
		
		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		player0.getPieces().get(0).setPos(3); // player0�� �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		player0.createPiece(); // player0�� �� ��° ��, �� ���� �ø���
		player0.getPieces().get(1).setPos(3); // player0�� �� ��° ��, '��'��ŭ �̵�(3ĭ �̵�)
		
		assertEquals(1, player0.checkUpda()); // player0�� �� ��° ��, �� ��° �� ��� �� ������ ������ 1 ����

		player0.getPieces().get(0).setPos(19); // player0�� �� ��°, �� ��° ��, 19ĭ �߰� �̵�(����� ���)
		
		assertEquals(1, player0.checkPiecein()); // ������� ����� ���� �ֱ� ������ 1 ����
		assertEquals(4, player0.getPoint()); // ������� ����� ���� �� 4���̱� ������ player0�� ����Ʈ�� 4��
	}
	
	@After
	public void teardown() {
		System.out.println("teardown!");
	}
}