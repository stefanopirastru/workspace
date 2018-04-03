package DividiEtImpera;

public class MyBinaryTree {	
	TreeNode root;
	public void inOrderTreeWalk(MyBinaryTree t){
		inOrderWalk(t.root);
	}
	public void preOrderTreeWalk(MyBinaryTree t){
		preOrderWalk(t.root);
	}
	public void postOrderTreeWalk(MyBinaryTree t){
		postOrderWalk(t.root);
	}
	public void inOrderWalk(TreeNode x){
		if(x!=null){
			inOrderWalk(x.left);
			process(x);
			inOrderWalk(x.right);
		}
	}
	public void preOrderWalk(TreeNode x){
		if(x!=null){
			process(x);
			preOrderWalk(x.left);
			preOrderWalk(x.right);
		}
	}
	public void postOrderWalk(TreeNode x){
		if(x!=null){
			postOrderWalk(x.left);
			postOrderWalk(x.right);
			process(x);
		}
	}
	public void process(TreeNode x){
		System.out.println(x.key);
	}
}
