import java.util.*;

public class EfficientMarkov extends BaseMarkov{
	
	Map<String,ArrayList<String>> myMap;
	
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
		myMap = new HashMap<>();//delete this
		String trainText = text;
		for(int i = 0; i < text.length()-getOrder()+1;i++)//loop through text
		//make sure to subtract order so we don't get out of bounds exception
		{
			String sub = trainText.substring(i,i + getOrder());
			int track = text.length()-getOrder();
			String lastCh;
			if(i == track)
				lastCh = "PSEUDO_EOS";
			else
				lastCh = trainText.substring(i+getOrder(),i+getOrder()+1);
			
			if(!myMap.containsKey(sub))
			{
				myMap.put(sub, new ArrayList<String>());
			}
			
			if(i == track)
				myMap.get(sub).add(lastCh);
			else
				myMap.get(sub).add(lastCh);
			
		}
		
	}
	
	@Override
	public ArrayList<String> getFollows(String key)
	{
		if(!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		else
			return myMap.get(key);
	}
	
	
}
