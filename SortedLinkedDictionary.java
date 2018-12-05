package Lab6;

import java.util.Iterator;

public class SortedLinkedDictionary <K extends Comparable <? super K> , V> 
                implements DictionaryInterface <K, V> {
    private DictNode head;
    private DictNode tail;
    private int numberOfEntries;
    
    public SortedLinkedDictionary () {
    	head = null; 
        tail = null; 
        numberOfEntries = 0;
    }
    
    public V getValue (K key) {
    	DictNode curr = head;
    	while (curr != null) {
    		if(curr.getKey().equals(key)) {
    			return curr.getValue();
    		}
    		else {
    			DictNode temp = curr.getNext();
    			curr = temp;
    		}
    	}
    	return null;
    }
    
    public int getSize() {
    	return numberOfEntries;
    }
    
    public void clear() {
    	DictNode curr = head;
    	while (curr != null) {
    		DictNode temp = curr.getNext();
    		curr = null;
    		curr = temp;
    	}
    }
    
    public boolean isEmpty () {
    	return numberOfEntries == 0;
    }
    
    public boolean contains (K aKey) {
    	DictNode curr = head;
    	while (curr != null) {
    		if(curr.getKey().equals(aKey))
    			return true;
    		else {
    			DictNode temp = curr.getNext();
    			curr = temp;
    		}
    	}
    	return false;
    }

    private DictNode getNodeAfterKey(K aKey) {
    	DictNode curr = head;
    	while (curr != null && aKey.compareTo (curr.getKey()) > 0)
    		curr = curr.getNext();
    	return curr;	
    }

    public V add (K newKey, V newValue ) {
    	DictNode nodeAfter;	
    	DictNode toAdd = new DictNode (newKey, newValue);
    	
    	if (isEmpty()) {
    		head = tail = toAdd;
    		numberOfEntries = 1;
    		return null;
    	}  
    	
    	nodeAfter = getNodeAfterKey(newKey); 
    	
    	if (null == nodeAfter) {
    		tail.setNext (toAdd);
    		toAdd.setPrev (tail);
    		tail = toAdd;
    	}
    	else if (newKey.equals (nodeAfter.getKey())) {
    		//System.out.println ("Replacing value for " + newKey);
    		V oldValue = nodeAfter.getValue();
    		nodeAfter.setValue(newValue);
    		return oldValue;
    	}	
    	else if (nodeAfter == head) {
    		toAdd.setNext(head);
    		head.setPrev (toAdd);
    		head = toAdd;
    	}
    	else {
    		DictNode prevNode = nodeAfter.getPrev();
    		prevNode.setNext(toAdd);
    		toAdd.setPrev(prevNode);
    		toAdd.setNext(nodeAfter);
    		nodeAfter.setPrev(toAdd);
    	}
    	numberOfEntries ++;
    	return null;	
    }
     
    
    public V remove (K aKey) {
    	DictNode prev = null;
    	DictNode curr = head;
    	while (curr != null) {
    		if(curr.getKey().equals(aKey)) {
    			V val = curr.getValue();
    			if(prev == null)
    				head = curr.getNext();
    			else
    				prev.setNext(curr.getNext());
    			curr = null;
    			return val;
    		}
    		else {
    			DictNode temp = curr.getNext();
    			curr = temp;
    		}
    	}
    	return null;
    }
    
    
    public Iterator <K> getKeyIterator () {
    	return new KeyIteratorForLinkedDictionary ();
    }
    public Iterator <V> getValueIterator () {
    	return new ValueIteratorForLinkedDictionary ();
    }
    private class KeyIteratorForLinkedDictionary implements Iterator <K> {
    	private DictNode nextNode;
    	
    	public KeyIteratorForLinkedDictionary () {
    		nextNode = head;
    	}
    	
    	public boolean hasNext () {
    		return (nextNode != null);
    	}
    	
    	public K next() {
    		if (!hasNext())
    			throw new IllegalStateException ("Iteration after the list");
    		K result = nextNode.getKey();
    		nextNode = nextNode.getNext();
    		return result;
    	}
    	
    	public void remove ()  {
    		throw new UnsupportedOperationException ("No remove in dictionary iterator");
    	}
    }
    
    private class ValueIteratorForLinkedDictionary implements Iterator <V> {
    	private DictNode nextNode;

    	public ValueIteratorForLinkedDictionary () {
    		nextNode = head;
    	}

    	public boolean hasNext () {
    		return (nextNode != null);
    	}

    	public V next() {
    		if (!hasNext())
    			throw new IllegalStateException ("Iteration after the list");
    		V result = nextNode.getValue();
    		nextNode = nextNode.getNext();
    		return result;
    	}

    	public void remove ()  {
    		throw new UnsupportedOperationException ("No remove in dictionary iterator");
    	}
    }
    private class DictNode {
	private K key;
	private V value;
	private DictNode next;
	private DictNode prev;
		
	public DictNode (K newKey, V newValue) {
		key = newKey; value = newValue;
		next = null; prev = null;
	}
		
	public K getKey() { return key;}
	public V getValue() {return value;}
	public void setValue (V newValue) { value = newValue; }
	public DictNode getNext() {return next;}
	public DictNode getPrev() {return prev;}
	public void setNext (DictNode n) {next = n;}
	public void setPrev (DictNode p) {prev = p;}
    }

}
