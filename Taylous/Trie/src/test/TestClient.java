package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import algorithms.TST;
import algorithms.TrieST;

public class TestClient {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("tale.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
		StringTokenizer st;
		
		TrieST trieST = new TrieST();
		TST tst = new TST();
		HashMap<String, Integer> words = new HashMap<>();
		
		String buffer, word;
		double trieST_put = 0, trieST_get = 0, tst_put = 0, tst_get = 0, start;
		int trieST_error = 0, tst_error = 0, frequency;
		
		while(br.ready()) {
			
			buffer = br.readLine();
			
			st = new StringTokenizer(buffer);
			
			while(st.hasMoreTokens()) {
				
				word = st.nextToken();
				
				start = System.currentTimeMillis();
				trieST.put(word);
				trieST_put += (System.currentTimeMillis() - start);

				start = System.currentTimeMillis();
				tst.put(word);
				tst_put += (System.currentTimeMillis() - start);
				
				if(words.containsKey(word))
					words.put(word, words.get(word) + 1);
				else
					words.put(word, 1);
			}
		}
		bw.write("�ܾ� �� ����> " + words.size() + "\n");
		bw.write("TrieST�� ����� �ܾ�> " + trieST.size() + "\n");
		bw.write("PUT> " + (trieST_put / 1000.0)+"��\n\n");

		bw.write("TST�� ����� �ܾ�> " + trieST.size() + "\n");
		bw.write("PUT> " + (tst_put / 1000.0)+"��\n\n");
		
		Iterator<String> it = words.keySet().iterator();
		while(it.hasNext()) {
			
			word = it.next();
			
			bw.write("ã�� �ܾ�> " + word + "\n");
			
			start = System.currentTimeMillis();
			frequency = trieST.get(word);
			trieST_get += (System.currentTimeMillis() - start);

			start = System.currentTimeMillis();
			frequency = tst.get(word);
			tst_get += (System.currentTimeMillis() - start);
			
			bw.write("Java HashMap> " + words.get(word) + "\n");
			bw.write("TrieST> " + trieST.get(word) + "\n");
			bw.write("TST> " + tst.get(word) + "\n");
			bw.write("\n");
			
			if(words.get(word) != trieST.get(word)) {
				
				bw.write("\n\tTreiST����ġ�߻�> " + word + "\n\n");
				trieST_error++;
			}
			if(words.get(word) != tst.get(word)) {
				
				bw.write("\n\tTST����ġ�߻�> " + word + "\n\n");
				tst_error++;
			}
		}
		bw.write("\n[�߻��� ����ġ] TrieST: " + trieST_error + ", TST: " + tst_error + "\n");
		bw.write("TrieST get> " + trieST_get + "\n");
		bw.write("TST get> " + tst_get + "\n");
		
		System.out.println("FINISHED...");
		br.close();
		bw.close();
	}
}
