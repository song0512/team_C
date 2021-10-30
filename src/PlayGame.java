
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGame implements ActionListener{
	private Player []players;
	private YotBoard board;
	private int pieceNum;
	private int playerNum;
	private int turn=0;
	private int winner=-1;
	private int result=0;
	private Player nowPlayer;
	private int control=1;
	
	PlayGame(int people, int mal)
	{
		players = new Player[people];
		for(int i=0;i<people;i++)
		{
			players[i] = new Player(i,mal);
		}
		playerNum = people;
		pieceNum = mal;
		board = new YotBoard(this);//yotboard���� ��ư�� Ŭ�� �Ǿ��ٴ� ������ �ޱ����� ���� ��ü�� ����
		
		for(int i=0; i<playerNum; i++) {
			board.setplayerInfo(i, players[i].playerPiece());
		}
	}
	
	public int getPlayerNum() {
		
		return playerNum;
	}
	
	int checkFinish()
	{
		for(int i=0;i<players.length;i++)
		{
			if(players[i].getPoint() == pieceNum)
			{
				players = null;
				board.finishMessage(i);
				return i;//i��° �÷��̾� �¸�
			}
		}
		return -1;//���� ���� �ȳ���
	}
	int checkCatch(int index)//���� �÷��̾� �ε�����
	{
		Player catcher = players[index];//catcher�� ������ �÷��̾�
		int posx,posy;
		for(int q=0;q<catcher.getPieces().size();q++)//���� �÷��̾��� ��� ��
		{
			posx = catcher.getPieces().get(q).getX();
			posy = catcher.getPieces().get(q).getY();//���� �÷��̾��� q��° �� ��ġ
			for(int i=0;i<players.length;i++)
			{
				if(i!=index)
				{
					if(players[i].checkCatch(posx,posy)==1)//i��° Player�� ����� ���ؼ� ������ ����
					{
						board.message("P"+index+"�� P"+i+"�� ���� ��Ҵ�");
						return 1;//��ĭ�� ���� �ٸ� �÷��̾��� ���� �������� �����Ƿ� �׳� ������ ���� ����
					}
				}
			}
		}
		return 0;
	}
	
	void boardMessage(String s) {
		board.message(s);
	}
	
	void boardRefreashFrame() {
		board.refreashFrame();
	}
	
	void phaze1ThrowYot()
	{
		if(control==1) {
			result =0;
			nowPlayer = players[turn];
			if(nowPlayer.getPieces().size()==0 && nowPlayer.getPieceNum()>0) {//������ ���� ���� ������� ���� �ִٸ� 0,0�� ���� �����
				boardMessage("�� ���� �ö� �ִ� �� ����");
			}
			else
			{
				boardMessage("");
			}
			board.changePlayer(turn);
			result=Yoot.throwing();//������ ��ư Ŭ��
			board.printResult(result);//���� ��� ȭ�鿡 ���
			phaze1changeBtncolor();//UI ��ư ���� ����
			
			for(int i=0; i<playerNum; i++) {
				board.setplayerInfo(i, players[i].playerPiece());
			}

			control = 3;
		}
	}
	
	void phaze2PutOnBoard()
	{
		if(control==2)
		{
			//���� ������ Player���� �˾Ƽ� ã�� ������ ����� �̵���
			if(nowPlayer.createPiece()==1) //������� ���� ���� �ִٸ� ���� ���� ����
			{
				nowPlayer.move(0, 0, result); //���⼭ �˾Ƽ� ������� �Ǵ�����
				
				for(int i=0; i<playerNum; i++) {
					board.setplayerInfo(i, players[i].playerPiece());
				}
				
				board.printPiece(turn,0,result,nowPlayer.getPieceUpdaNum(0,result));//�÷��̾�, �̵� ���� ��ǥ
				phaze2changeBtncolor();//UI ��ư ���� ����
				if(checkCatch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
				{
					control=1;
					phaze1ThrowYot();
				}
				else
				{
					phaze4NextTurn();//���� �÷��̾� �� ����
				}
			}
			else
			{
				boardMessage("�� �̻� ���� ���� �� �� �����ϴ�.");
				control=3;//�� ���� �ѵ��� �Ѿ�� phaze3Pieceact�� �۵� �� �� �ֵ��� �Ѵ�.
			}
		}
	}
	
	void phaze3Pieceact(int posx, int posy)
	{
		int index;
		int x,y,point;
		
		if(control==3)
		{// if(���� �÷��̾ �� ���� �÷� ���� ���� ������ 0 && ���� �÷��̾��� ���� �� 0 �̻�(���� ��=��ü �� - ������ ��))
			if(nowPlayer.getPieces().size()==0 && nowPlayer.getPieceNum()>0)//������ ���� ���� ������� ���� �ִٸ� 0,0�� ���� �����
			{
				control=2;
				phaze2PutOnBoard();
			}
			else
			{
				index = nowPlayer.checkEnable(posx, posy);//�ش� ��ư�� ���� �ִ��� Ȯ�� ������ �� �迭�� �ε��� ��ȯ
				if(index!=-1)
				{
					//���� ������ Player���� �˾Ƽ� ã�� ������ ����� �̵���
					board.printPiece(4, posx, posy, 0);//�������� ������� ���� ���� �� �̵�
					if(nowPlayer.move(posx, posy, result)==1) //���⼭ �˾Ƽ� ������� �Ǵ�����
					{//���ų� ���������� ȭ�鿡 ǥ�ø� ���Ѵ� �̰Ͷ����� �ڲ� ������ ����.
						boardMessage("P "+turn+" �� �ϳ��� �������ϴ�");
						if(posx == 0 && posy == 5) {
							posx = 1;
							posy = 0;
						}
						else if(posx == 0 && posy==10){
							posx=2;
							posy=0;
						}
						posy=posy+result;
						if(result > 0) {
							if(posx == 1 && posy==3)
							{
								posx=2;
								posy=3;
							}
							else if(posx==1 && posy>5){
								posx=0;
								posy+=9;
							}
							else if(posx==2 && posy>5) {
								posx=0;
								posy+=14;
							}
						}else {
							if(posx==1 && posy<1) {
								posx=0;
								posy=5+posy;
							}else if(posx==1 && posy==3) {
								posx=2;
								posy=3;
							}else if(posx==2 && posy<1) {
								posx=0;
								posy=10+posy;
							}else if(posx==0 && posy<1) {
								posy=20+posy;
							}
						}
						index = nowPlayer.checkEnable(posx, posy);
						//�� �������� ����� ����: �� A�� �̵��� �� B ���� ������. �׷� �� B point += �� A point �ϰ� �� A ��ü ����
						//�� A�� �����Ǿ����� piece(==��)�� Arraylist�� index�� ���� ��� ���� �ͼ��� �� �׷��� index�� ������ �����
						x = nowPlayer.getPieces().get(index).getX();
						y = nowPlayer.getPieces().get(index).getY();
						point = nowPlayer.getPieces().get(index).getPoint();
						board.printPiece(turn,x,y,point);
					}
					else if(nowPlayer.checkPiecein() ==1)
					{
						boardMessage("P "+turn+" �� �ϳ��� �����߽��ϴ�");
					}
					else
					{
						x = nowPlayer.getPieces().get(index).getX();
						y = nowPlayer.getPieces().get(index).getY();
						point = nowPlayer.getPieces().get(index).getPoint();
						board.printPiece(turn,x,y,point);//�÷��̾�, �̵� ���� ��ǥ

					}

					for(int i=0; i<playerNum; i++) {
						board.setplayerInfo(i, players[i].playerPiece());
					}
					
					phaze2changeBtncolor();//UI ��ư ���� ����
					if(nowPlayer.getPieceNum()<=0 && nowPlayer.getPieces().size()<=0)//������� ���� ������ ���� ������
					{
						control=-1;//��� ����
						System.out.println("��� ����");
						phaze4NextTurn();//�ش� �÷��̾� �� ����
					}
					else//������ ������ �ʾҴٸ� (�̷��� �س��� �ڹ� �ͼ��� �ȶ�)
					{
						if(checkCatch(turn)==1 || result == 4 || result ==5)//�ٽ� �� ������ ����
						{
							control=1;
							phaze1ThrowYot();
						}
						else
						{
							phaze4NextTurn();//�ش� �÷��̾� �� ����
						}
					}
				}
				else
				{
					boardMessage("������ ��ư Ŭ����"+posx +" , "+posy);
				}
			}
		}
		else
		{
			//���� ���� �������� �ʾҴµ� �� Ŭ���ϸ� �ƹ� ���� ����
		}
	}

	void phaze4NextTurn()
	{
		winner = checkFinish();
		if(winner != -1)
		{
			control=5;//�ƹ� ���� ����
			System.out.println(winner+ " ��° �÷��̾ �¸��Ͽ����ϴ�.");
		}
		else 
		{
			turn++;
			if(turn >= playerNum)
			{
				turn =0;
			}
			boardMessage("P "+turn+" ����");
			boardRefreashFrame();//���� �÷��̾�� �Ѿ�ϱ� Piece �׸� �ٲ���
			initBtncolor();
			control = 1;
		}
	}
	
	void phaze1changeBtncolor() {
		board.buttonColor("throwBtnOFF");
		if(nowPlayer.getPieceNum()>0)//������� ���� �ִٸ� 
		{
			board.buttonColor("newPieceBtnON");//���ο� �� ��ư Ȱ��ȭ
		}
		if(nowPlayer.getPieces().size()>0)//�ǿ� ���� �ִٸ� 
		{
			board.buttonColor("clickBoardON");//�� Ŭ�� ��ư Ȱ��ȭ
		}
	}
	
	void phaze2changeBtncolor() {
		if(nowPlayer.getPieces().size()>0)
		{
			board.buttonColor("clickBoardON");
		}
		else
		{
			board.buttonColor("clickBoardOFF");
		}
		if(nowPlayer.getPieceNum()>0)
		{
			board.buttonColor("newPieceBtnON");
		}
		else
		{
			board.buttonColor("newPieceBtnOFF");
		}
	}
	
	void initBtncolor() {
		board.buttonColor("throwBtnON");
		board.buttonColor("newPieceBtnOFF");
		board.buttonColor("clickBoardOFF");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==board.throwButton && control == 1)
		{
			phaze1ThrowYot();
		}
		if(e.getSource()==board.newPiece && control == 3)
		{
			control=2;
			phaze2PutOnBoard();
		}
		if(control==3) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==board.panButton[0][i])
				{
					phaze3Pieceact(0,i);
				}
			}
			for(int p=1;p<6;p++) {
				if(e.getSource()==board.panButton[1][p])
				{
					phaze3Pieceact(1,p);
				}	
			}
			for(int q=1;q<6;q++) {
				if(e.getSource()==board.panButton[2][q])
				{
					phaze3Pieceact(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==board.testButton[r] && control == 1)
			{
				phaze1ThrowYot();
				r--;
				if(r==0)
				{
					result = 5;
				}
				else
				{
					result = r;
				}
				board.printResult(result);//���� ��� ȭ�鿡 ���
			}
		}
	}
}