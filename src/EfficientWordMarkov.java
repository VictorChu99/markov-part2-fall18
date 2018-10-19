import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.*;

/*
 * 
 * Author: Victor CHu
 * Net ID: vic4
 * Date: 10.18.18
 * 
 */

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
			WordGram key = new WordGram(myWords, i, getOrder());//constructs the wordgram
			//need an arraylist, starting point, and a size.
			//we use myWords for the arrayList, index i for the starting point in myWords,
			//then the order for the size of the wordgram. 
			
			int track = myWords.length-getOrder();//tells us when we are at the end
			
			String ch; 
			if(i == track)//
			{
				ch = PSEUDO_EOS;//inherited BaseWordMarkov
			}
			else{
				ch = myWords[i+getOrder()];//gets the last word for value
			}
			
			if(!myMap.containsKey(key))
			{
				myMap.put(key, new ArrayList<String>());//makes a new key
			}
			
			myMap.get(key).add(ch);//adds values to the arraylist
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(WordGram key)//straightforward. Returns arraylist
	{
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		else {
			ArrayList<String> returnVal = myMap.get(key);
			
			return returnVal;
		}
	}
	
	
}
