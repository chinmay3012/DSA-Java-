//LC - Maximum Depth of Binary Tree

class Solution {
    public int maxDepth(TreeNode root) {
        return height(root);
    }
    int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        int maxDep = Math.max(left , right) + 1 ;
        return maxDep;
    }
}


// LC- Flatten Binary Tree to Linked List

class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while(curr != null){
            if(curr.left != null){
                TreeNode temp = curr.left;
                while(temp.right != null){
                    temp = temp.right;
                }
                temp.right = curr.right ;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

