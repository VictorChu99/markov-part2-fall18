import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	
	Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();//create a map but with wordgram objects now
	}
	
	public EfficientWordMarkov()
	{
		this(3);
	}
	
	private String[] stringToArray(String text)
	{
		String[] arrayString = new String[text.length()];
		for(int i = 0; i < text.length();i++)
		{
			arrayString[i] = text.substring(i, i + 1);
		}
		
		return arrayString;
	}
	
	
	@Override
	public void setTraining(String text)
	{
		String trainText = text;
		String[] textArray = stringToArray(trainText);
		
		
		for(int i = 0; i < textArray.length-getOrder()+1;i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		{
			WordGram key = new WordGram(textArray, i, getOrder());
			
			int track = textArray.length-getOrder();
			String lastCh;
			
			//this if else statement here will identify the correct 
			if(i == track)
				lastCh = PSEUDO_EOS;
			else
				lastCh = trainText.substring(i+getOrder(),i+getOrder()+1);
			
			if(!myMap.containsKey(key))
			{
				myMap.put(key, new ArrayList<String>());
			}
			
			if(i == track)
				myMap.get(key).add(lastCh);
			else
				myMap.get(key).add(lastCh);
			
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key)
	{
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		else
			return myMap.get(key);
	}
	
	
}
