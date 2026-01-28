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


    }
}
