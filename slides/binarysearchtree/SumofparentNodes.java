public class SumofparentNodes {
	static int sum=0;
	static class Node
	{
		int data;
		Node left=null;
		Node right=null;
		
		
		
	};
	public static Node NewNode(int n)
	{
		Node new_node=new Node();
		new_node.data=n;
		new_node.left=null;
		new_node.right=null;
		return new_node;
	}
	
	public static void parentnodesum(Node root,int x)
	{
		
		if(root==null)
		{
			return;
		}
			if(root.left!=null && root.left.data==x || root.right!=null && root.right.data==x)
			{
			     sum=sum+root.data;
			    
			}
			
			parentnodesum(root.left,x);
			 
			parentnodesum(root.right,x);
		System.out.println(sum);
			
		
	}
	static int sumOfParentOfXUtil(Node root,     
            int x)  
{  
 sum=0;
parentnodesum(root, x);  

// required sum of parent nodes  
return sum;  
} 
public static void main(String args [])
{
	Node root=NewNode(4);
	root.left= NewNode(2);
	root.right=NewNode(5);
	root.left.left=NewNode(7);
	root.left.right=NewNode(2);
	root.right.left=NewNode(2);
	root.right.right=NewNode(3);
	int x=2;
	
	int result=sumOfParentOfXUtil(root,x);
	System.out.println(result);
	
	
}
}
