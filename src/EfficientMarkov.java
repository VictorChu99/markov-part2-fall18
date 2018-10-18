import java.util.*;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{//extends base markov. 
	//myText issue. myText is used for text generation, since we need to pick a starting point
	//base markov chooses a random starting point
	//we get passed the full text, but need to save this somewhere
		//deals with instance vs local variables
	
	private Map<String,ArrayList<String>> myMap;
	
	
	//constructs our map, and order for later on 
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
		myMap.clear();//clear the map first. 
		
		
		myText= text;//stores the text. 
		for(int i = 0; i <= text.length()-getOrder();i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		//+1 is important. makes sure we don't get a null pointer
		{
			int end = i + getOrder();//useful since we need the end of the gram for the key, and values
			String sub = myText.substring(i, end);//gets the key. starts at i, then goes to end
			int track = myText.length()-getOrder();//tells us when we are at the end of the text, and need there are no more values
			String lastCh;//values for our map
			if(i == track)
				lastCh = PSEUDO_EOS;//inherits PSEUDO_EOS which is "". Trying to compare apples
									//to oranges if I used "PSEUDO_EOS" since PSEUDO_EOS is inherited from BaseMarkov
			else
				lastCh = myText.substring(end, end +1);//last character is stored
			
			if(!myMap.containsKey(sub))//create the map and put keys in
			{
				myMap.put(sub, new ArrayList<String>());//create  a map and add arrayLists
			}
			
			myMap.get(sub).add(lastCh);//add the last character to the arrayList in the map
			
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(String key)
	{
		
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");//throw this if we don't find it in our map
		else {
			ArrayList<String> returnVal = myMap.get(key);//retrieves the arrayList
			
			return returnVal;//returns our arrayList
		}
	}
	
	
}
