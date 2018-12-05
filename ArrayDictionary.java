package Lab6;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDictionary <K, V> implements DictionaryInterface <K, V> {
	
    private Entry <K, V> [] entries ;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 10;
    
    @SuppressWarnings ("unchecked")
    public ArrayDictionary () {	
    	entries = (Entry <K, V> []) new Entry [DEFAULT_CAPACITY];
    	initialized = true;
    	numberOfEntries = 0;
    }

    private void checkInitialization() {
    	if (!initialized)
    		throw new SecurityException("Dictionary not initialized!!");
    }
    
    private void ensureCapacity() {
    	   if (numberOfEntries == entries.length) {// array is full
    	       Entry <K, V> [] newEntries = (Entry <K, V> []) Arrays.copyOf(entries, 
    	                                               2*numberOfEntries);
    	      entries = newEntries;
    	    }
    	}

    private int locateIndex (K key){ // the method returns numberOfEntries if key is not found
        int index = 0;
        while ((index < numberOfEntries) && 
                !key.equals(entries[index].getKey()))
            index ++;
        
        return index;
    }
    
	public V add (K key, V value) {
		checkInitialization();
		if (key == null || value == null)
		     throw new IllegalArgumentException();
		V oldValue = null; 
		int keyIndex = locateIndex (key);
		if (keyIndex < numberOfEntries){
		    oldValue = entries[keyIndex].getValue();
		    entries[keyIndex].setValue(value);
		}
		else {
		    entries[numberOfEntries ++] = new Entry(key, value);
		    ensureCapacity();
		}
		return oldValue;

	}
    
	  
	  
	 public V remove (K key) {
                 checkInitialization();
		 V oldValue = null;
		 int keyIndex = locateIndex (key);
		 if (keyIndex < numberOfEntries) {
			oldValue = entries[keyIndex].getValue();
	         	entries[keyIndex]= entries[numberOfEntries-1]; 
	         	entries[numberOfEntries-1] = null;
	         	numberOfEntries --;
		 }
		 return oldValue;
	 }
	    
	  
	 public V getValue (K key) {
		 int len = getSize();
		 for(int i = 0; i < len - 1; i++) {
			 if((entries[i].getKey()).equals(key))
				 return entries[i].getValue();
		 }
		 return null;
	 }
	    
	   
	 public boolean contains (K key) {
		 int len = entries.length;
		 for(int i = 0; i < len; i++) {
			 if(entries[i].getKey().equals(key))
				 return true;
		 }
		 return false;
	 }
	    
	  public int getSize() {
		  return numberOfEntries;
	  }
	  
	  public Iterator <K> getKeyIterator() {
		  return new KeyIterator();
	  }
	    

	 public Iterator <V> getValueIterator(){
		 return new ValueIterator();
	 }
	 
	 public boolean isEmpty() {
		 return (numberOfEntries == 0);
	 }
	 
	 public void clear() {
		 int len = entries.length;
		 for(int i = 0; i < len; i++) {
			 entries[i] = null;
		 }
	 }

     
     private class KeyIterator implements Iterator <K> {
    	public int cursor;
    		 
    	public KeyIterator () {
    		cursor = 0;
    	}
    		 
    	public boolean hasNext() { return (cursor < getSize()) ;}
    		 
    	public K next() {
    		K out = (K) entries[cursor++].getKey();
    		return out;
    	}
    		 
    	public void remove() {
    		throw new UnsupportedOperationException (
    			"No remove for dictionary iterator");
    	}
    }
     private class ValueIterator implements Iterator <V> {
    	 public int cursor;
		 
     	public ValueIterator () {
     		cursor = 0;
     	}
     		 
     	public boolean hasNext() { return (cursor < getSize()) ;}
     		 
     	public V next() {
     		V out = (V) entries[cursor++].getValue();
     		return out;
     	}
     		 
     	public void remove() {
     		throw new UnsupportedOperationException (
     			"No remove for dictionary iterator");
     	}
     }	 
}
