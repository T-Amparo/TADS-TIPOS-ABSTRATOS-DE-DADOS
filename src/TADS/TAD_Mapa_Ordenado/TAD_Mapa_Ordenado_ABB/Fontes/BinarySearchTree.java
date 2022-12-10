package TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Fontes;

import java.util.Comparator;

import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidEntryException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidKeyException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Excecoes.InvalidPositionException;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.BTPosition;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.Entry;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.Map;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.Position;
import TADS.TAD_Mapa_Ordenado.TAD_Mapa_Ordenado_ABB.Interfaces.PositionList;

/**
 * @author T-Amparo
 *
 * TADS-TIPOS-ABSTRATOS-DE-DADOS
 */

public class BinarySearchTree<KEY, VALUE> extends LinkedBinaryTree<Entry<KEY, VALUE>> implements Map<KEY, VALUE> {
	
	protected Comparator<KEY> comparator;
	protected Position<Entry<KEY, VALUE>> position;
	protected int size = 0;
	
	public BinarySearchTree() {
		
		comparator = new DefaultComparator<KEY>();
		addRoot(null);
	}	
	
	public BinarySearchTree(Comparator<KEY> comparator) {
		
		this.comparator = comparator;
		addRoot(null);
	}
	
	protected static class BSTEntry<KEY, VALUE> implements Entry<KEY, VALUE> {
		
		protected KEY key;
		protected VALUE value;
		protected Position<Entry<KEY, VALUE>> position;
		
		BSTEntry(){}
		
		BSTEntry(KEY key, VALUE value, Position<Entry<KEY, VALUE>> position){
			
			this.key = key;
			this.value = value;
			this.position = position;
		}
		
		public KEY getKey() {return key;}
		
		public VALUE getValue() {return value;}
		
		public Position<Entry<KEY, VALUE>> position() {return position;}
		
	}
	
	protected KEY key(Position<Entry<KEY, VALUE>> position) {return position.element().getKey();}
	
	protected VALUE value(Position<Entry<KEY, VALUE>> position) {return position.element().getValue();}
	
	protected Entry<KEY, VALUE> entry(Position<Entry<KEY, VALUE>> position) {return position.element();}
	
	protected VALUE replaceEntry(Position<Entry<KEY, VALUE>> position, Entry<KEY, VALUE> entry) {
		
		((BSTEntry<KEY, VALUE>) entry).position = position;
		
		return replace(position, entry).getValue();
	}
	
	protected void validateKey(KEY key) throws InvalidKeyException {
		if(key == null) {throw new InvalidKeyException("A chave está nula!");}		
	}
	
	protected void validateEntry(Entry<KEY, VALUE> entry) throws InvalidEntryException {
		if(entry == null || !(entry instanceof BSTEntry)) {throw new InvalidEntryException("O elemento é invalido!");}		
	}
	
	protected Entry<KEY, VALUE> insertExternal(Position<Entry<KEY, VALUE>> position, Entry<KEY, VALUE> entry) {
		
		expandExternal(position, null, null);		
		replace(position, entry);
		
		size++;
		return entry;
	}
	
	protected void removeExternal(Position<Entry<KEY, VALUE>> position) {
		
		removeAboveExternal(position);
		
		size--;
	}
	
	protected Position<Entry<KEY, VALUE>> treeSearch(KEY key, Position<Entry<KEY, VALUE>> position) {
		
		if(isExternal(position)) {return position;}
		else {
			
			KEY currentKey = key(position);			
			int comparator = this.comparator.compare(key, currentKey);
			
			if(comparator < 0) {return treeSearch(key, left(position));}
			else if(comparator > 0) {return treeSearch(key, right(position));}
			
			return position;			
		}
	}
	
	public int size() {return size;}
	
	public boolean isEmpty() {return size() == 0;}
	
	public VALUE put(KEY key, VALUE value) {
		
		validateKey(key);
		
		Position<Entry<KEY, VALUE>> position = treeSearch(key, root());
		BSTEntry<KEY, VALUE> entry = new BSTEntry<KEY, VALUE>(key, value, position);
		
		this.position = position;
		
		if(isExternal(position)) {
			
			insertExternal(position, entry);
			
			return null;
		}
		
		return replaceEntry(position, entry);
	}
	
	public VALUE get(KEY key) throws InvalidKeyException {
		
		validateKey(key);
		
		Position<Entry<KEY, VALUE>> position = treeSearch(key, root());
		this.position = position;
		
		if(isInternal(position)) {return value(position);}
		return null;		
	}
	
	public VALUE remove(KEY key) throws InvalidEntryException {
		
		validateKey(key);
		
		Position<Entry<KEY, VALUE>> position = treeSearch(key, root());
		
		if(isExternal(position)) {return null;}
		
		Entry<KEY, VALUE> removed = entry(position);
		
		if(isExternal(left(position))) {position = left(position);}
		else if(isExternal(right(position) )) {position = right(position);}
		else {
			
			Position<Entry<KEY, VALUE>> changed = position;			
			position = right(changed);
			
			do position = left(position); while(isInternal(position));
			
			replaceEntry(changed, (Entry<KEY, VALUE>) parent(position).element());
		}
		
		this.position = sibling(position);		
		removeExternal(position);
		
		return removed.getValue();
	}
	
	public void expandExternal(Position<Entry<KEY, VALUE>> position, Entry<KEY, VALUE> entryOne, Entry<KEY, VALUE> entryTwo) throws InvalidPositionException {

		if(!isExternal(position)) throw new InvalidPositionException("O nó não é externo!");

		insertLeft(position, entryOne);
		insertRight(position, entryTwo);
	}
	
	public void removeAboveExternal(Position<Entry<KEY, VALUE>> position) throws InvalidPositionException {

		if(!isExternal(position)) throw new InvalidPositionException("O nó não é externo!");

		if(isRoot(position)) {remove(position);}
		else {
	
			Position<Entry<KEY, VALUE>> parent = parent(position);
	
			remove(position);	
			remove(parent);
		}
	}
	
	public Iterable<KEY> keySet() {

		PositionList<KEY> keys = new NodePositionList<KEY>();

		Iterable<Position<Entry<KEY, VALUE>>> inorder = positionsInorder();
		for (Position<Entry<KEY, VALUE>> position : inorder) if(isInternal(position)) {keys.addLast(key(position));}

		return keys;
	}
	
	public Iterable<VALUE> values() {

		PositionList<VALUE> values = new NodePositionList<VALUE>();

		Iterable<Position<Entry<KEY, VALUE>>> inorder = positionsInorder();
		for (Position<Entry<KEY, VALUE>> position : inorder) if(isInternal(position)) {values.addLast(value(position));}

		return values;
	}
	
	public Iterable<Entry<KEY, VALUE>> entrySet() {

		PositionList<Entry<KEY, VALUE>> entries = new NodePositionList<Entry<KEY, VALUE>>();

		Iterable<Position<Entry<KEY, VALUE>>> inorder = positionsInorder();
		for (Position<Entry<KEY, VALUE>> position : inorder) if(isInternal(position)) {entries.addLast(position.element());}

		return entries;
	}	

	protected Position<Entry<KEY, VALUE>> restructure(Position<Entry<KEY, VALUE>> x) {
		
		BTPosition<Entry<KEY, VALUE>> a, b, c, t0, t1, t2, t3;
		
		Position<Entry<KEY, VALUE>> y = parent(x);
		Position<Entry<KEY, VALUE>> z = parent(y);

		BTPosition<Entry<KEY, VALUE>> xx = (BTPosition<Entry<KEY, VALUE>>) x;
		BTPosition<Entry<KEY, VALUE>> yy = (BTPosition<Entry<KEY, VALUE>>) y;
		BTPosition<Entry<KEY, VALUE>> zz = (BTPosition<Entry<KEY, VALUE>>) z;

		boolean xLeft = (x == left(y));
		boolean yLeft = (y == left(z));

		if (xLeft && yLeft) {
	
			a = xx; b = yy; c = zz;	
			t0 = a.getLeft(); t1 = a.getRight(); t2 = b.getRight(); t3 = c.getRight();

		} else if (!xLeft && yLeft) {
	
			a = yy; b = xx; c = zz; t0 = a.getLeft();	
			t1 = b.getLeft(); t2 = b.getRight(); t3 = c.getRight();

		} else if (xLeft && !yLeft) {
	
			a = zz; b = xx; c = yy;	
			t0 = a.getLeft(); t1 = b.getLeft(); t2 = b.getRight(); t3 = c.getRight();

		} else {
	
			a = zz; b = yy; c = xx;	
			t0 = a.getLeft(); t1 = b.getLeft(); t2 = c.getLeft(); t3 = c.getRight();
		}
		
		if (isRoot(z)) {

			root = b;

			b.setParent(null);

		} else {

			BTPosition<Entry<KEY, VALUE>> zParent = (BTPosition<Entry<KEY, VALUE>>) parent(z);

			if (z == left(zParent)) {
	
				b.setParent(zParent);	
				zParent.setLeft(b);

			} else {
	
				b.setParent(zParent);	
				zParent.setRight(b);

			}

		}
		
		b.setLeft(a); a.setParent(b); a.setLeft(t0); t0.setParent(a); a.setRight(t1); t1.setParent(a);
		b.setRight(c); c.setParent(b); c.setLeft(t2); t2.setParent(c); c.setRight(t3); t3.setParent(c);
		
		((BSTEntry<KEY, VALUE>) a.element()).position = a;
		((BSTEntry<KEY, VALUE>) b.element()).position = b;
		((BSTEntry<KEY, VALUE>) c.element()).position = c;
		
		return b;		
	}	
	
	public String printExpression(Position<Entry<KEY, VALUE>> position) {

		String elements = "";

		if(isInternal(position)) {elements += "(";}
		if(hasLeft(position)) {elements += printExpression(left(position));}
		if(position.element() != null) {elements += position.element().getKey().toString();}
		if(hasRight(position)) {elements += printExpression(right(position));}
		if(isInternal(position)) {elements += ")";}

		return elements;
	}

}