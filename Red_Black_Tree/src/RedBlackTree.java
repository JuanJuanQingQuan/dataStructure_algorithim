public class RedBlackTree {
    enum COLOR {
        RED,BLACK;
    }
    private Node root, NIL;

    public RedBlackTree() {
        NIL = new Node();
        NIL.color = COLOR.BLACK;
    }

    class Node{
        public Node parent = null;
        public Node left = null, right = null;
        public int val = 0;
        public COLOR color = COLOR.RED;

        public Node(){
        }

        public Node(Node parent, Node left, Node right, int val) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.val = val;
        }

        Node grandParent() {
            if (parent == null){
                return null;
            } else {
                return parent.parent;
            }
        }

        Node uncle() {
            if (parent == grandParent().left) {
                return grandParent().right;
            } else {
                return  grandParent().left;
            }
        }

        Node sibling() {
            if (this == parent.left) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

    }

    public void rotate_right(Node n) {

        Node gp = n.grandParent();
        Node p = n.parent;
        p.left = n.right;

        if (n.right != NIL) {
            n.right.parent = p;
        }

        n.right = p;
        p.parent = n;
        if (root == p) {
          root = n;
        }
        n.parent = gp;

        if (gp != null) {
            if (gp.left == n.parent) {
                gp.left = n;
            } else {
                gp.right = n;
            }
        }

    }

    public void rotate_left(Node n) {
        Node gp = n.grandParent();
        Node p = n.parent;
        p.right = n.left;

        if (n.left != NIL) {
            n.left.parent = p;
        }

        n.left = p;
        p.parent = n;
        if (root == p) {
            root = n;
        }
        n.parent = gp;

        if (gp != null) {
            if (gp.left == n.parent) {
                gp.left = n;
            } else {
                gp.right = n;
            }
        }
    }

    private void insert(Node n, int data) {
        if (n.val >= data) {
            if (n.left != NIL)
                insert(n.left, data);
            else {
                Node tmp = new Node();
                tmp.val = data;
                tmp.left = tmp.right = NIL;
                tmp.parent = n;
                n.left = tmp;
                insertAdjust(tmp);
            }
        } else {
            if (n.right != NIL)
                insert(n.right, data);
            else {
                Node tmp = new Node();
                tmp.val = data;
                tmp.left = tmp.right = NIL;
                tmp.parent = n;
                n.right = tmp;
                insertAdjust(tmp);
            }
        }
    }

    private void insertAdjust(Node n) {
        //情形1： 插入点为根结点
        if (n.parent == null){
            root = n;
            n.color = COLOR.BLACK;
            return;
        }
        //情形2： 父节点是黑色，插入红色节点不会有影响
        if (n.parent.color == COLOR.RED) {
            //情形3：父亲节点是红色，叔节点是红色====》变色
            if (n.uncle().color == COLOR.RED) {//
                n.grandParent().color = COLOR.RED;
                n.parent.color = n.uncle().color = COLOR.BLACK;
                insertAdjust(n.grandParent());
            } else {
                //情形4：父节点红色，叔节黑色。父节点是左子树，该节点是右子树 ====》先左旋，进入到情形5
                if (n.parent.right == n && n.grandParent().left == n.parent) {
                    rotate_left(n);
                    insertAdjust(n.left);
                } else if (n.parent.left == n &&  n.grandParent().right == n.parent) {
                    //情形4：父节点红色，叔节点黑色。父节点是右子树，该节点是左子树 ====》先右旋，进行到情形5
                    rotate_right(n);
                    insertAdjust(n.right);
                }else if (n.parent.left == n &&  n.grandParent().right == n.parent) {
                    //情形5：父节点红色，叔节点黑色。父节点是左子树，该节点是左子树 ====》变色，然后右旋
                    n.grandParent().color = COLOR.BLACK;
                    n.parent.color = COLOR.RED;
                    rotate_right(n.parent);
                } else {
                    n.grandParent().color = COLOR.BLACK;
                    n.parent.color = COLOR.RED;
                    rotate_left(n.parent);
                }
            }
        }
    }

    private void replaceNode(Node n, Node child) {
        child.parent = n.parent;
        if (n == n.parent.left) {
            n.parent.left = child;
        } else {
            n.parent.right = child;
        }
    }

    private Node getSmallestChild(Node n) {
        if (n.left== NIL) {
            return n;
        }
        return getSmallestChild(n.left);
    }

    private boolean delete(Node n, int data) {
        if (n.val > data) {
            if(n.left == NIL) {
                return false;
            }
            return delete(n.left, data);
        } else  if (n.val < data) {
            if (n.right == NIL) {
                return false;
            }
            return delete(n.right, data);
        } else if (n.val == data){
            if (n.right == NIL) {
                deleteOneChild(n);
                return true;
            }
            Node smallest = getSmallestChild(n.right);
            int tmp = n.val;
            n.val = smallest.val;
            smallest.val = tmp;
            deleteOneChild(smallest);
            return true;
        } else {
            return false;
        }
    }

    private void deleteOneChild(Node n) {
        Node child = n.left == NIL ? n.right:n.left;//n节点只有一个子树

        if (n.parent == null && n.left == NIL && n.right == NIL) {
            //情形1：删除根节点，且无左右子节点；
            n = null;
            root = n;
            return;
        }

        if (n.parent == null) {
            //情形1：删除根节点，且有左子节点或者右子节点（但只有一个子节点）。
            child.parent = null;
            root = child;
            root.color = COLOR.BLACK;
            return;
        }
        //删除节点X
        if(n.parent.left == n) {
            n.parent.left = child;
        } else {
            n.parent.right = child;
        }
        child.parent = n.parent;

        if(n.color == COLOR.BLACK) {
        //  被删除节点X是红色，其实不需要其他操作，如果是黑色，那么意味着通过该节点到叶子节点的路径会少一个黑色节点
            if (child.color == COLOR.RED) {
                //情形2：被删除节点X是黑色节点，且其子节点是红色节点，那么子节点变黑即可。
                child.color = COLOR.BLACK;
            } else {
                //调整树结构
                deleteAdjust(child);
            }
        }
    }

    private void deleteAdjust(Node n){
        if (n.parent == null) {
            n.color = COLOR.BLACK;
            return;
        }
        //情形3：被删除节点（现在已经被替换了）N是黑色节点，那么看其兄弟节点S的颜色
        //如果是红色，那么可以先旋转，然后再变色，S变黑，P变红，N依然是P的子节点。
        // 但是这样之后，从P开始，经过N与没有经过N到叶子节点的路径还是会相差一个节点（我们删除的节点） P-X-N =》 P-N
        //进入到后续情形
        if (n.sibling().color == COLOR.RED){
            n.parent.color = COLOR.RED;
            n.sibling().color = COLOR.BLACK;
            if (n == n.parent.left) {
                rotate_left(n.sibling());
            } else {
                rotate_right(n.sibling());
            }
        }
        //情形4：被删除节点（现在已经被替换了）N是黑色节点，父节点和兄弟节点S及S的子节点的颜色都是黑的,变色完，再对n的父节点进行调整
        if (n.parent.color == COLOR.BLACK && n.sibling().color == COLOR.BLACK
                && n.sibling().left.color == COLOR.BLACK && n.sibling().right.color == COLOR.BLACK) {
            n.sibling().color = COLOR.RED;
            deleteAdjust(n.parent);
        } else if (n.parent.color == COLOR.RED && n.sibling().color == COLOR.BLACK
                && n.sibling().left.color == COLOR.BLACK && n.sibling().right.color == COLOR.BLACK){
            //情形5：被删除节点（现在已经被替换了）N是黑色节点，父节点为红色，兄弟节点S及S的子节点的颜色都是黑的,那么改变P和S颜色即可，补足P-N路径中的黑色节点。
            n.sibling().color = COLOR.RED;
            n.parent.color = COLOR.BLACK;
        } else {
            if (n.sibling().color == COLOR.BLACK) {
                //情形6：N为P的左子节点，S节点的左子节点为红色，右子节点为黑色，那么变色+左旋，然后进入到情形7
                if (n == n.parent.left && n.sibling().left.color == COLOR.RED && n.sibling().right.color == COLOR.BLACK) {
                    n.sibling().color = COLOR.RED;
                    n.sibling().left.color = COLOR.BLACK;
                    rotate_left(n.sibling().left);
                } else if (n == n.parent.right && n.sibling().right.color == COLOR.RED && n.sibling().left.color == COLOR.BLACK) {
                    n.sibling().color = COLOR.RED;
                    n.sibling().right.color = COLOR.BLACK;
                    rotate_left(n.sibling().right);
                }
            }
            //情形7：N为P的左子节点，S节点的左子节点为黑色，右子节点为红色，那么右旋+变色
            n.sibling().color = n.parent.color;
            n.parent.color = COLOR.BLACK;
            if (n == n.parent.left) {
                n.sibling().right.color = COLOR.BLACK;
                rotate_left(n.sibling());
            } else {
                n.sibling().left.color = COLOR.BLACK;
                rotate_right(n.sibling());
            }
        }
    }

    public void inorder(Node n) {
        if (n == NIL) {
            return;
        }
        if (n.left !=NIL) {
            inorder(n.left);
        }
        System.out.println(n.val);
        if (n.right !=NIL) {
            inorder(n.right);
        }

    }

    public void insert(int x) {
        if (root == null) {
            root.color = COLOR.BLACK;
            root.left = root.right = NIL;
            root.val = x;
        } else {
            insert(root, x);
        }
    }

    public boolean delete(int data) {
        return delete(root, data);
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
    }
}
