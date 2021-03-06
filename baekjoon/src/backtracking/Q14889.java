package backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class Q14889 {
	static int n;
	static int gap = Integer.MAX_VALUE;
	static int[][] field;
	static boolean[] team;
	
	public static void soccer_team(int depth, int last_val) {
		if(depth == n/2) {
			int[] start = new int[n/2];
			int[] link = new int[n/2];
			int start_index = 0;
			int link_index = 0;
			
			for(int j = 1; j <= n; j++) {
				if(team[j] == true) {
					start[start_index++] = j;
				}else {
					link[link_index++] = j;
				}
			}
			
			int startVal = getValue(start);
			int linkVal = getValue(link);
			
			gap = Math.min(gap, Math.abs(startVal-linkVal));
		}
		
		for(int i = last_val + 1; i <= n; i++) {
			team[i] = true;
			soccer_team(depth+1, i);
			team[i] = false;
		}
	}
	public static int getValue(int[] team) {
		int result = 0;
		
		for(int i=0;i<team.length;i++) { // 두 사람을 선택해 능력치를 계산
			for(int j=i+1;j<team.length;j++) { // 조합이므로 앞선 사람의 다음 사람부터 탐색
				result = result + field[team[i]][team[j]] +field[team[j]][team[i]] ; // 두 경우를 모두 더해준다.
			}			
		}
		
		return result; // 능력치 결과 리턴
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		field = new int[n+1][n+1];
		team = new boolean[n+1];
		
        for(int i = 1; i <= n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 1; j <= n; j++) {
        		field[i][j] = Integer.parseInt(st.nextToken());
        	}
        	
        }
        
        soccer_team(0, 0);
        System.out.println(gap);
        
        br.close();
	}
	
}