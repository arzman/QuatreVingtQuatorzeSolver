package qvqs.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordManager {

	private static WordManager _instance;

	private HashMap<Integer, List<String>> _wordMap;

	private WordManager() {

		_wordMap = new HashMap<Integer, List<String>>();

		// lecture du fichier texte

		InputStream in = getClass().getResourceAsStream("/qvqs/resources/dico_big_fr.txt");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String ligne;
			while ((ligne = br.readLine()) != null) {
				
				int length = ligne.trim().length();
				
				List<String> list = _wordMap.get(length);
				
				if(list==null){
					_wordMap.put(length, new ArrayList<String>());
					list = _wordMap.get(length);
				}
				
				list.add(ligne);
				
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public static WordManager getInstance() {

		if (_instance == null) {

			_instance = new WordManager();

		}

		return _instance;

	}
	
	
	
	public List<String> findWordForSubPattern(String pattern, String values){
		
		
		ArrayList<String> res = new ArrayList<String>();	
			
		
		String[] subPatternTable = pattern.split(" ");
		
		
		//on élimne les mots sans les lettre placée
		for(String candidat : _wordMap.get(pattern.length())){
			
			boolean carryOn=true;
			ArrayList<Integer> usedLetter = new ArrayList<Integer>();
			
			for(int i=0;i<candidat.length() && carryOn ;i++){
				
				//lettre placée
				if(pattern.charAt(i)!='*'){
					
					carryOn = pattern.charAt(i)==candidat.charAt(i);
					
				}else{
					
					carryOn = false;
					//lettre libre
					for(int j=0;j<values.length();j++){
						
						if(values.charAt(j)==candidat.charAt(i)){
							
							if(!usedLetter.contains(j)){
								carryOn = true;
								usedLetter.add(j);
								break;
							}
							
						}
						
					}
				}

			}
			
			if(carryOn){
				res.add(candidat);
			}
			
			
		}
			
		return res;
	}




	public List<String> findWordForPattern(String pattern, String values) {
		
		List<String> res = new ArrayList<String>();	
		
		String [] subPattern = pattern.split(" ");
			
		
		if(subPattern.length==1){
			// un seul mot à trouver, on sait le faire
			res = findWordForSubPattern(pattern, values);
			
		}else{
			
			// plusieur mot à trouver, on trouve le premier et on suit
			
			
			
			
		}
			
			
			
		
		
		
		
		return res;
	}
	

}
