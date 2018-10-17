import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {
	
	private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram,ArrayList<String>>();//create a map but with wordgram objects now
	}
	
	public EfficientWordMarkov()
	{
		this(3);
	}
	
	
	
	@Override
	public void setTraining(String text)
	{
		myWords= text.split("\\s+");//why do we have to do this????
		
		
		for(int i = 0; i < myWords.length-getOrder()+1;i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		{
			WordGram key = new WordGram(myWords, i, getOrder());
			
			int track = myWords.length-getOrder();
			//this if else statement here will identify the correct 
			if(!myMap.containsKey(key))
			{
				myMap.put(key, new ArrayList<String>());
			}
			
			if(i == track)
			{
				myMap.get(key).add(PSEUDO_EOS);
			}
			else{
				myMap.get(key).add(myWords[i+getOrder()]);
			}
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
