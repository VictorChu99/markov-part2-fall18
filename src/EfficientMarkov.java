import java.util.*;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{//extends base markov. 
	//myText issue. myText is used for text generation, since we need to pick a starting point
	//base markov chooses a random starting point
	//we get passed the full text, but need to save this somewhere
		//deals with instance vs local variables
	
	private Map<String,ArrayList<String>> myMap;
	
	
	
	public EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
	public EfficientMarkov()
	{
		this(3);
	}
	
	
	@Override
	public void setTraining(String text)
	{
		myText= text;
		for(int i = 0; i < text.length()-getOrder()+1;i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		//+1 is important
		{
			String sub = myText.substring(i, i + getOrder());//gets the key. starts at i, then goes to i + getOrder()
			int track = myText.length()-getOrder();
			String lastCh;
			if(i == track)
				lastCh = PSEUDO_EOS;//inherits PSEUDO_EOS which is "". Trying to compare apples
									//to oranges if I used "PSEUDO_EOS" since PSEUDO_EOS is inherited from BaseMarkov
			else
				lastCh = myText.substring(i+getOrder(),i+getOrder()+1);
			
			if(!myMap.containsKey(sub))
			{
				myMap.put(sub, new ArrayList<String>());//create  a map and add arrayLists
			}
			
			myMap.get(sub).add(lastCh);//add the last character to the arrayList
			
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(String key)
	{
		
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		
		return myMap.get(key);
	}
	
	
}
