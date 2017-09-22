public class RangeUpdatePointQueries {
	
	public static void main(String[] args) {
		RangeUpdatePointQueries d=new RangeUpdatePointQueries();
		//input array
		int[] inArr={0,0,0,0,0,0};
		//Store the fenwick tree
		int[] feniTree=new int[inArr.length+1];
		//Update range using method 2
		d.updateRangeMeth2(inArr, 2, 4, 10);
		//Point query method 2
		System.out.println(d.getPointMeth2(inArr, 3));
		//Mehth 3 call
		//Construct fenwick tree 
		d.contructFeniTree(inArr);
		//Update a range with same logic applied in method 2
		d.updateRange(feniTree, 2, 4, 10);
		//Point query using logic from method 2
		System.out.println(d.getPoint(feniTree, 3));
	}
	
	//Method 2-We are increasing left position value by the update val. Reducing the right+1 pos val
	//by update val
	public void updateRangeMeth2(int[] inArr, int left, int right, int val){
		//Add update val to left most index of the range to be updated
		inArr[left]=inArr[left]+val;
		//Substract update val from right most+1 index of the range to be updated
		inArr[right+1]=inArr[right+1]-val;
	}
	//When doing point query, we add all the values from start of array to the point
	public int getPointMeth2(int[] inArr, int index){
		int sum=0;
		//Add all the values from start of array till the point which is queried
		for(int i=0;i<=index;i++)
			sum+=inArr[i];
		return sum;
	}
	
	//Method 3- Used fenwick tree with same logic used in method 2	
	public int[] contructFeniTree(int[] inArr){
		int[] feniTree=new int[inArr.length+1];
		for(int i=1;i<feniTree.length;i++){
			updateTree(feniTree, i, inArr[i-1]);
		}
		return feniTree;
	}
	
	public void updateTree(int[] feniTree, int i, int val){
		while(i<feniTree.length){
			feniTree[i]+=val;
			i=getNext(i);
		}
	}
	
	//this equation finds next node to be updated(not going to be 
	//parent node but next sibling and next branch's parent).
	//it finds the next node by addding one to the right most set bit.
	public int getNext(int index){
		return index+(index & -index);
	}
	
	//Update the left and right+1 index in fenwick tree with the update value
	public void updateRange(int[] feniTree, int left ,int right, int val){
		updateTree(feniTree, left, val);
		updateTree(feniTree, right+1, -val);
	}
	
	//To get the point/index value, sum from starting of array till the point and return.
	//The logic behind it is same as to find a prefix sum in a fenwick tree
	public int getPoint(int[] feniTree, int index){
		index=index+1;
		int sum=0;
		while(index>0){
			sum+=feniTree[index];
			index=getParent(index);
		}
		return sum;
	}
	
	//This equation finds parent of the current node by
	//flipping the righ most set bit. It will keep on finding parent
	//till it reaches its own parent 2^x node.
	public int getParent(int index){
		return index-(index & -index);
	}

}
