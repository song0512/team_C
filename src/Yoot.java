

import java.util.Random;

// ���� ������ ���ӿ��� �ν��Ͻ��� ���� �ʿ���� Ŭ���� ��ü�� �ѹ��� ��������� ���� ������ ��� ������ static���� ������ش�.
public class Yoot {
	
	// ���� ������ ���� ���� �ش��ϴ� �÷���
	public static final int BACKDO = -1;	// ����: 1ĭ �ڷ�
	public static final int DO = 1;			// ��: 1ĭ ����
	public static final int GAE = 2;		// ��: 2ĭ ����
	public static final int GUL = 3;		// ��: 3ĭ ����
	public static final int YOOT = 4;		// ��: 4ĭ ����
	public static final int MO = 5;			// ��: 5ĭ ����
	
	public static final int SIDE_FLAT = 1;	// ������ ���� ��Ÿ���� �÷��� ����
	public static final int SIDE_ROUND = 0;	// �ձ� ���� ��Ÿ���� �÷��� ����

	private static final float PROB_FLAT = 0.6f;	// ������ ���� ���� Ȯ��, ��ü��  ������ ���� ���� Ȯ���� 60%, �ձ� ���� ���� Ȯ���� 40%�� �Ѵ�.
	
	private static int[] yootSticks = new int[4];	// ���� ���Ǵ� ������ 4���� �迭�� �����Ѵ�. ���� ù��°�� �ش�Ǵ� ���� ������ �ش��ϴ� �������̴�.
	
	
	
	// ���� �������� ������ �޼ҵ�
	public static int throwing() {
		Random random = new Random();	// ���� �����ϰ� ������ �� �ֵ��� �������� �����Ѵ�.
		int sum = 0;					// ����� ���� Ȯ���ؼ� ���� ���� �����ֱ� ���ؼ� ���� ���� ������ ������ ����
		
		// �� 4��(�������� ����) �ݺ��ϸ鼭 ������ �������� �ش�Ǵ� ���� �����ϰ� �ٲ��ְ� ��� ���� �����ش�.
		for(int i = 0; i < 4; i++)
		{
			if(random.nextFloat() <= PROB_FLAT)
				// ������ ���� ���� ���
				yootSticks[i] = SIDE_FLAT;
			else
				// �ձ� ���� ���� ���
				yootSticks[i] = SIDE_ROUND;
			
			sum += yootSticks[i];
		}
		
		// ����� ���� Ȯ�� �ؼ� ������ ��쿡 �ش��ϴ� ���� ���� ��ȯ�Ѵ�.
		// ��: ��� �������� �ձ� ���� ���� ��� (������ 0�ΰ��)
		// ��: ��� �������� ������ ���� ���� ��� (������ 4�� ���)
		// ��: 3���� �������� ������ ���� ���� ��� (������ 3�� ���)
		// ��: 2���� �������� ������ ���� ���� ��� (������ 2�� ���)
		// ����: ������ �ش��ϴ� ������, �� ù��° �������� ������ ���� ���� ���(������ 1�� ��쿡�� ù��°�� ������ ��)
		// ��: ������ �ش��ϴ� �������� �ƴ� ������ ������ �� 1���� ������ ���� ���� ���(������ 1�� ��쿡�� ù��° ���� �ձ� ���� ���)
		if(sum == 4 * SIDE_FLAT)
		{
			// ���� ���� ���
			return YOOT;
		}
		else if(sum == 3 * SIDE_FLAT)
		{
			// ���� ���� ���
			return GUL;
		}
		else if(sum == 2 * SIDE_FLAT)
		{
			// ���� ���� ���
			return GAE;
		}
		else if(sum == 1 * SIDE_FLAT)
		{
			if(yootSticks[0] == SIDE_FLAT)
			{
				// ������ ���� ���
				return BACKDO;
			}
			else
			{
				// ���� ���� ���
				return DO;
			}
		}
		else
		{
			// �� ���� ���
			return MO;
		}
	}
	
	
	// ������ ������ �����ϴ� �޼ҵ�
	public static int throwBackdo() {
		yootSticks[0] = SIDE_FLAT;
		yootSticks[1] = SIDE_ROUND;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return BACKDO;
	}
	
	
	// ���� ������ �����ϴ� �޼ҵ�
	public static int throwDo() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return DO;
	}
	
	
	// ���� ������ �����ϴ� �޼ҵ�
	public static int throwGae() {
		yootSticks[0] = SIDE_FLAT; 
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return GAE;
	}
	
	
	// ���� ������ �����ϴ� �޼ҵ�
	public static int throwGul() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_FLAT;
		yootSticks[3] = SIDE_FLAT;
		
		return GUL;
	}
	
	
	// ���� ������ �����ϴ� �޼ҵ�
	public static int throwYoot() {
		yootSticks[0] = SIDE_FLAT;
		yootSticks[1] = SIDE_FLAT;
		yootSticks[2] = SIDE_FLAT;
		yootSticks[3] = SIDE_FLAT;
		
		return YOOT;
	}
	
	
	// �� ������ �����ϴ� �޼ҵ�
	public static int throwMo() {
		yootSticks[0] = SIDE_ROUND;
		yootSticks[1] = SIDE_ROUND;
		yootSticks[2] = SIDE_ROUND;
		yootSticks[3] = SIDE_ROUND;
		
		return MO;
	}
}