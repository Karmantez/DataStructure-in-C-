import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int N = 0, x = 0, y = 0, d = 0, k = 0, nextX = 0, nextY = 0, sum = 0, nextA = 0;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[][] atom, map;
		Queue<Node> deleteQ;
		Node temp;
		
		for (int testCase = 1; testCase <= T; testCase++) {
		    N = sc.nextInt();
		    
		    atom = new int[N + 1][4];
		    map = new int[2001][2001];
		    
		    deleteQ = new LinkedList<Solution.Node>();
		    
		    for (int i = 1; i <= N; i++) {
		    	x = 1000 + sc.nextInt();
		    	y = 1000 - sc.nextInt();
		    	d = sc.nextInt();
		    	k = sc.nextInt();
		    	
		    	atom[i][0] = y;
		    	atom[i][1] = x;
		    	atom[i][2] = d;
		    	atom[i][3] = k;
		    	
		    	map[y][x] = i;
		    }
		    
		    while (N > 0) {
		    	for (int i = 1; i < atom.length; i++) {
		    		if (atom[i][0] == -1)
		    			continue;
		    		
		    		nextX = atom[i][0] + dx[atom[i][2]];
		    		nextY = atom[i][1] + dy[atom[i][2]];
		    		
		    		if (nextX < 0 || nextX > 2000 || nextY < 0 || nextY > 2000) {
		    			N--;
		    			map[atom[i][0]][atom[i][1]] = 0;
		    			atom[i][0] = -1;
		    			continue;
		    		}
		    		
		    		if (map[nextX][nextY] != 0) { // ������ ���⿡ ���ڰ� �ִ� ���
		    			nextA = map[nextX][nextY];
		    			if (i < nextA) { // ������ �����̴� ������ ���
		    				if (atom[i][2] + atom[nextA][2] == 1 || atom[i][2] + atom[nextA][2] == 5) { // 0.5�ʿ� ������ ���
		    					N -= 2;
		    					sum += (atom[i][3] + atom[nextA][3]);
		    					
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					map[nextX][nextY] = 0;
		    					
		    					atom[i][0] = -1;
		    					atom[nextA][0] = -1;
		    				}
		    				else { // ������ �����̴� ���ڿ� �������� ������ �ʴ� ���
		    					map[nextX][nextY] = i; // ���� �̵�������
		    					if (map[atom[i][0]][atom[i][1]] == i) // ������ �ִ� ��ġ�� �ٸ� ���ڰ� ���� ���� ���
		    						map[atom[i][0]][atom[i][1]] = 0; // �ʱ�ȭ
		    					atom[i][0] = nextX; // x��ǥ ����
		    					atom[i][1] = nextY; // y��ǥ ����
		    				}
		    			}
		    			else { // ������ ������ ������ ��� - ��������
		    				if (atom[nextA][0] != -1) { // ó�� ������ ���
		    					N -= 2; // ó�� ������ ������ 2�� ���� ����
		    					sum += (atom[i][3] + atom[nextA][3]);
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					atom[i][0] = -1;
		    					atom[nextA][0] = -1;
		    					
		    					deleteQ.add(new Node(nextX, nextY)); // �� �̵��ϰ� ���� �������� ��� ��ġ �߰�
		    				}
		    				else { // ������ �ٸ� ģ���� �����ִ� ���
		    					N--; // �ش� ���ڸ� ������ ��
		    					sum += atom[i][3];
		    					map[atom[i][0]][atom[i][1]] = 0;
		    					atom[i][0] = -1;
		    				}
		    			}
		    		}
		    		else { // �� ������ ��� ���� �̵�������
		    			map[nextX][nextY] = i;
		    			if (map[atom[i][0]][atom[i][1]] == i)
		    				map[atom[i][0]][atom[i][1]] = 0;
		    			
		    			atom[i][0] = nextX;
		    			atom[i][1] = nextY;
		    		}
		    	}
		    	
		    	// ���� �� �������� map�� �������� ��ǥ ������
		    	while (!deleteQ.isEmpty()) {
		    		temp = deleteQ.poll();
		    		
		    		map[temp.x][temp.y] = 0;
		    	}
		    }
		    
		    System.out.println("#" + testCase + " " + sum);
		    sum = 0;
		}
		
		sc.close();
	}
	
	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
