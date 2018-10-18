import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	//more or less the same as EfficientMarkov
	//but using wordgrams as the keys instead.
	//as such, have to call the wordgram class 
	//to make wordgram objects
	
	
	private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();//create a map but with wordgram objects now
	}
	
	public EfficientWordMarkov()//default constructor
	{
		this(3);
	}
	
	
	
	@Override
	public void setTraining(String text)
	{
		myWords= text.split("\\s+");//why do we have to do this????
		//We are going to be using an array
		//as per our wordgram class for later. 
		
		
		myMap.clear();
		
		for(int i = 0; i <= myWords.length-getOrder();i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		{
			WordGram key = new WordGram(myWords, i, getOrder());
			
			int track = myWords.length-getOrder();
			//this if else statement here will identify the correct 
			String ch; 
			if(i == track)//must be the last character
			{
				ch = PSEUDO_EOS;
			}
			else{
				ch = myWords[i+getOrder()];
			}
			
			if(!myMap.containsKey(key))
			{
				myMap.put(key, new ArrayList<String>());
			}
			
			myMap.get(key).add(ch);
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key)
	{
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		else {
			ArrayList<String> returnVal = myMap.get(key);
			
			return returnVal;
		}
	}
	
	
}
