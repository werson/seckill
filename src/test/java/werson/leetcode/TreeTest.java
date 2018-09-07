package werson.leetcode;

import java.util.*;

/**
 * @author Administrator
 * @version 1.0.0
 * @since 2018/8/24
 */
public class TreeTest {

	public static void showTree(TreeNode node,int deep, List<Integer> nodeList){
		System.out.println(node.val);
		if(node.left != null) showTree(node.left, deep++, nodeList);
		if(node.right != null) showTree(node.right, deep++, nodeList);
		if(node.left == null && node.right == null){
			nodeList.add(deep);
		}
	}

	public static void getLeafNode(TreeNode tree, Integer deep, Map<Integer, List<TreeNode>> map){
		if(tree.left == null && tree.right == null){
			List<TreeNode> list = map.get(deep);
			if(list == null){
				list = new ArrayList<>();
				list.add(tree);
				map.put(deep, list);
			}else{
				list.add(tree);
			}
		}
		deep++;
		if(tree.left != null) getLeafNode(tree.left, deep, map);
		if(tree.right != null) getLeafNode(tree.right, deep, map);
	}

	public static TreeNode findMaxLeafTree(TreeNode root){
		Map<Integer, List<TreeNode>> map = new HashMap<>();
		getLeafNode(root, 1, map);
		Integer maxDeep = Collections.max(map.keySet());
		List<TreeNode> leafNodeList = map.get(maxDeep);
		return findMinTreeContainsAll(root, leafNodeList);
	}

	public static TreeNode findMinTreeContainsAll(TreeNode tree, List<TreeNode> list){
		if(isContainsAll(tree, list)){
			if(!isContainsAll(tree.left, list) && !isContainsAll(tree.right, list)){
				return tree;
			}
			if(isContainsAll(tree.left, list) && !isContainsAll(tree.right, list)){
				return findMinTreeContainsAll(tree.left, list);
			}
			if(!isContainsAll(tree.left, list) && isContainsAll(tree.right, list)){
				return findMinTreeContainsAll(tree.right, list);
			}
		}
		return null;
	}

	public static boolean isContainsAll(TreeNode tree, List<TreeNode> list){
		for(TreeNode node : list){
			if(!isNodeInTree(tree, node)){
				return false;
			}
		}
		return true;
	}


	//判断节点是否在数中
	public static boolean isNodeInTree(TreeNode treeNode,TreeNode node){
		if(treeNode == null) return false;
		if(treeNode.val == node.val) return true;
		if(treeNode.left == null && treeNode.right == null){
			return false;
		}else if(treeNode.left == null){
			return isNodeInTree(treeNode.right, node);
		}else if(treeNode.right == null){
			return isNodeInTree(treeNode.left, node);
		}else{
			return isNodeInTree(treeNode.left, node)||isNodeInTree(treeNode.right, node);
		}
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(3);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(5);
		//node.left = left;
		//node.right = right;
		findMaxLeafTree(left);
		//List<Integer> nodeList = new ArrayList<>();
		//showTree(node,1, nodeList);
		//System.out.println("树的最大深度："+Collections.max(nodeList));
	}

	//任意两节点间 最小绝对值
	public int getMinimumDifference(TreeNode root) {
		List<Integer> valList = new ArrayList<>();
		toMap(root, valList);
		int size = valList.size();
		int minNum = -1;
		for(int i = 0; i < size; i++){
			for(int j = i + 1; j < size; j++){
				if (minNum == -1) minNum=Math.abs(valList.get(i)-valList.get(j));
				minNum = Math.min(minNum, Math.abs(valList.get(i)-valList.get(j)));
			}
		}
		return minNum;
	}

	public void toMap(TreeNode root, List<Integer> valList) {
		valList.add(root.val);
		if(root.left != null) toMap(root.left, valList);
		if(root.right != null) toMap(root.right, valList);
	}

}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}