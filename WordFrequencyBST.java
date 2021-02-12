// Name: David Ward
// Student number: V00920409

public class WordFrequencyBST {
	private TreeNode root;
	private int numElements;
	
	public WordFrequencyBST() {
		root = null;
		numElements = 0;
	}
	
	public TreeNode getNode(TreeNode root, String s)
	{
		if(root==null)//not found
		{
			return root;
		}
		if(root.getData().getWord().compareTo(s) == 0) //found
		{
			return root;
		} 
		else if(root.getData().getWord().compareTo(s) > 0)//root is larger
		{
			return getNode(root.left,s);
		}
		else
		{
			return getNode(root.right,s);
		}
		
	}
	
	public TreeNode insertString(TreeNode root, String s)
	{
		//insert this
		Entry e = new Entry(s);
		TreeNode t = new TreeNode(e);
		
		if(root==null || (root.getData().getWord().compareTo(s) == 0) )
		{
			if(root==null)
			{
				//root = t;
				return t;//root;
			}
			root.getData().addToFrequency();
			return root;
		}
		
		if(root.getData().getWord().compareTo(s) > 0)//root > s
		{
			root.left = insertString(root.left, s);
		}
		else if(root.getData().getWord().compareTo(s) < 0)
		{
			root.right = insertString(root.right, s);
		}
		
		return root;
	}
	
	
	/*
	 * Purpose: Update the BST by handling input word 
	 * Parameters: String word - The new word to handle
	 * Returns: Nothing
	 * Explanation: If there is no entry in the tree 
	 *   representing the word, then the a new entry 
	 *   should be created and placed into the correct 
	 *   location of the BST. Otherwise, the existing
	 *   entry for the word should have its frequency
	 *   value incremented. 
	 */	
	public void handleWord(String word) {
		// TODO: Complete this method
		root = insertString(root, word);
		return;
		
	}

	
	/*
	 * Purpose: Get the frequency value of the given word
	 * Parameters: String word - the word to find
	 * Returns: int - the word's associated frequency
	 * Returns -1 if the word is not found (maybe change this later)
	 */	
	public int getFrequency(String word)
	{
		TreeNode t = getNode(root, word);
		if(t==null)//not in tree
		{
			return -1;
		}
		
		return t.getData().getFrequency();
	}

	/****************************************************
	* Helper functions for Insertion and Search testing *
	****************************************************/
	
	public String inOrder() {
		if (root == null) {
			return "empty";
		}
		return "{" + inOrderRecursive(root) + "}";
	}
	
	public String inOrderRecursive(TreeNode cur) {
		String result = "";
		if (cur.left != null) {
			result = inOrderRecursive(cur.left) + ",";
		} 
		result += cur.getData().getWord();
		if (cur.right != null) {
			result += "," + inOrderRecursive(cur.right);
		}
		return result;
	}
	
	public String preOrder() {
		if (root == null) {
			return "empty";
		}
		return "{" + preOrderRecursive(root) + "}";
	}
	
	public String preOrderRecursive(TreeNode cur) {
		String result = cur.getData().getWord();
		if (cur.left != null) {
			result += "," + preOrderRecursive(cur.left);
		} 
		if (cur.right != null) {
			result += "," + preOrderRecursive(cur.right);
		}
		return result;
	}
	
	/****************************************************
	* Helper functions to populate a Heap from this BST *
	****************************************************/
	
	public MaxFrequencyHeap createHeapFromTree() {
		MaxFrequencyHeap maxHeap = new MaxFrequencyHeap(numElements+1);
		addToHeap(maxHeap, root);
		return maxHeap;
	}
	
	public void addToHeap(MaxFrequencyHeap h, TreeNode n) {
		if (n != null) {
			addToHeap(h, n.left);
			h.insert(n.getData());
			addToHeap(h, n.right);
		}
	}		
}