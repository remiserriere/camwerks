package com.shamwerks.camwerks.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.shamwerks.camwerks.CamWerks;

public class Lang {

	private static Lang instance = new Lang();

	private HashMap<String,String> langMap = new HashMap<String,String>();
	
	public static String getText(LangEntry key){
		return instance.langMap.get(key.getValue());
	}
	
	public Lang(){
		//Locale currentLocale = Locale.ENGLISH;
		
		Properties props = new Properties();
		try {
		    props.load( new FileInputStream( "config/Language_" + CamWerks.getInstance().getConfig().getLanguage() + ".properties") );
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Object key : props.keySet() ) {
		    String value = props.getProperty(key.toString());
		    langMap.put(key.toString(), value);
		}

	}
}
