import java.util.*;
public class TreeBFS {
    class TreeNode<E> {
        E val;
        TreeNode left;
        TreeNode right;

        public TreeNode(E data){
            this.val = data;
            this.left = null;
            this.right = null;

        }
        public ArrayList<ArrayList<Integer>> BFS (TreeNode<Integer> root) {
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            if(root == null){
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                int levelSize = queue.size();
                ArrayList<Integer> currLevel = new ArrayList<>(levelSize);
                for(int i=0;i<levelSize;i++){
                    TreeNode currNode = queue.poll();

                    currLevel.add((Integer) currNode.val);

                    if(currNode.left != null){
                        queue.offer(currNode.left);
                    }
                    if(currNode.right != null) {
                        queue.offer(currNode.right);
                    }
                }
                res.add(currLevel);
            }
            return res;
        }

        //Finding successor node
        public TreeNode findSuccessor(TreeNode root , int key ){
            if(root==null){
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                if((Integer) node.val == key){
                    break;
                }
            }
            return queue.peek();
        }


        //103. Binary Tree Zigzag Level Order Traversal
        class Solution {
            public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
                List<List<Integer>> res = new ArrayList<>();
                if (root == null) return res;

                Deque<TreeNode> queue = new LinkedList<>();
                queue.offer(root);

                boolean reverse = false;

                while (!queue.isEmpty()) {
                    int levelSize = queue.size();
                    List<Integer> currLevel = new ArrayList<>(levelSize);

                    for (int i = 0; i < levelSize; i++) {
                        if (!reverse) {
                            TreeNode currNode = queue.pollFirst();
                            currLevel.add((Integer) currNode.val);

                            if (currNode.left != null) queue.addLast(currNode.left);
                            if (currNode.right != null) queue.addLast(currNode.right);

                        } else {
                            TreeNode currNode = queue.pollLast();
                            currLevel.add((Integer) currNode.val);

                            if (currNode.right != null) queue.addFirst(currNode.right);
                            if (currNode.left != null) queue.addFirst(currNode.left);
                        }
                    }

                    // toggle AFTER finishing the level
                    reverse = !reverse;
                    res.add(currLevel);
                }

                return res;
            }
        }

        //LC-116 Populating Next right Pointer in each node
        class Solution{
            public Node connect(Node root){
                if(root == null){
                    return null;
                }
                Node leftmost = root;

                while(leftmost.left != null){
                    Node curr = leftmost;
                    while(curr != null){
                        curr.left.next = curr.right;

                        if(curr.next !=null){
                            curr.right.next = curr.next.left;
                        }
                        curr = curr.next;
                    }
                    leftmost = leftmost.left;
                }
                return root;
            }
        }

        //LC-199 Binary Tree Right Side View
        class Solution {
            public List<Integer> rightSideView(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                if(root == null){
                    return res;
                }
                Queue<TreeNode> queue = new LinkedList<>();
                queue.offer(root);
                while(!queue.isEmpty()){
                    int levelSize = queue.size();

                    for(int i=0;i<levelSize;i++) {
                        TreeNode currNode = queue.poll();

                        if(i == levelSize - 1){
                            res.add(currLevel.val);
                        }

                        if (currNode.left != null) {
                            queue.offer(currNode.left);
                        }
                        if (currNode.right != null) {
                            queue.offer(currNode.right);
                        }
                    }
                }
                return res;
            }
        }


        //LC-100 Same TREE  : https://leetcode.com/problems/same-tree/description/
        class Solution {
            public boolean isSameTree(TreeNode p, TreeNode q) {
                if(p== null && q !=null){
                    return false;
                }
                if(p!= null && q ==null){
                    return false;
                }
                if(p==null && q==null){
                    return true;
                }

                Queue<TreeNode> queue1 = new LinkedList<>();
                Queue<TreeNode> queue2 = new LinkedList<>();
                queue1.offer(p);
                queue2.offer(q);

                while(!queue1.isEmpty() && !queue2.isEmpty()){
                    TreeNode n1 = queue1.poll();
                    TreeNode n2 = queue2.poll();

                    if( n1==null && n2 == null ) continue;
                    if( n1==null || n2 == null ) return false;

                    if(n1.val != n2.val)return false;

                    queue1.offer(n1.left);
                    queue1.offer(n1.right);
                    queue2.offer(n2.left);
                    queue2.offer(n2.right);

                }
                return true;
            }
        }

        //230. Kth Smallest Element in a BST
        class Solution {
            int count =0;
            public int kthSmallest(TreeNode root, int k) {
                return helper(root,k).val;
            }

            public TreeNode helper(TreeNode root , int k){
                if(root == null){
                    return null;
                }

                TreeNode left = helper( root.left,  k);
                if(left != null){
                    return left;
                }
                count++;

                if(count == k){
                    return root ;
                }

                return helper(root.right , k);
            }
        }

        //Binary Tree Maximum Path Sum
        class Solution {
            int ans = Integer.MIN_VALUE;
            public int maxPathSum(TreeNode root) {
                helper(root);
                return ans;
            }
            public int helper(TreeNode node ){
                if(node == null){
                    return 0;
                }

                int left = helper(node.left);
                int right = helper(node.right);

                left = Math.max(0,left);
                right = Math.max(0,right);
                int pathSum = left + right + node.val;

                ans = Math.max(ans , pathSum);
                return Math.max(left,right) + node.val;
            }
        }


        //129. Sum Root to Leaf Numbers
        class Solution {
            public int sumNumbers(TreeNode root) {
                return helper(root , 0);
            }
            public int helper(TreeNode root , int sum){
                if(root == null){
                    return 0;
                }

                sum = sum*10 + root.val;

                if(root.left == null && root.right ==null){
                    return sum;
                }

                return helper(root.left , sum) + helper(root.right , sum);

            }
        }

        //112. Path Sum
        class Solution {
            public boolean hasPathSum(TreeNode root, int targetSum) {

                return cntDiff(root , targetSum);

            }
            public boolean cntDiff(TreeNode root , int targetSum){
                if(root==null){
                    return false;
                }

                int sumLeft = targetSum - root.val;

                if(root.left == null && root.right == null){
                    return sumLeft == 0;
                }

                return cntDiff(root.left , sumLeft) || cntDiff(root.right , sumLeft);

            }
        }



    }
}
