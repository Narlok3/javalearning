package com.pendu.moteur;

import com.pendu.interfaces.PenduMoteur;

//C'est une implémentation
//Ne dois dépendre que d'interfaces
//Ou de classes dans le même package ou sous package

public class PenduMoteurImpl implements PenduMoteur {
	
	private String mot;
	
	private String mot_cache;

	private int pendu;
	
	@Override
	public void initialise() {
		mot_cache = "manger";
		mot = "";
		pendu = 0;
		for(int i=0;i<mot_cache.length();i++){
			mot += "_";
		}
	}

	@Override
	public void checkLetter(Character c) {
	      if(mot_cache.contains(c.toString())){
	    	  String new_mot = "";
	    	  for(int i=0;i<mot_cache.length();i++){
	    		  if(mot_cache.charAt(i) == c.charValue()){
	    			  new_mot += c.charValue();
	    		  }else{
	    			  new_mot += mot.charAt(i);
	    		  }
	    	  }
	    	  mot = new_mot;
	      }
	      else{
	    	  pendu++;
	      }
	}
	
	@Override
	public boolean isWin(){
		return !mot.contains("_");
	}
	
	@Override
	public boolean isLose(){
		return pendu >=8;
	}

	@Override
	public String getMot() {
		return mot;
	}

}
