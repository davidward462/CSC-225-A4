public class TreeNode {
	
	private Entry data;
	protected TreeNode left;
	protected TreeNode right;
	
	public TreeNode(Entry e) {
		this.data = e;
		left = null;
		right = null;
	}
	
	public int compareTo(String other) {
		//get the word stored in the entry in this tree node
		String thisWord = data.getWord();
		return thisWord.compareTo(other);
	}
	
	public void addToFrequency() {
		data.addToFrequency();
	}
	
	public Entry getData() {
		return data;
	}
	
	public String toString() {
		return data.toString();
	}
}