public class BST<T extends Comparable<T>> {

    public BinaryNode<T> root;

    public BST() {
        this.root = null;
    }
    //-----------------------------------------------------------------------------------------------------------------
    public void delete(T data) {
        root = deleteRecursive(root, data);
    }

    private BinaryNode<T> deleteRecursive(BinaryNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = deleteRecursive(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteRecursive(node.right, data);
        } else { // Node found
            // Handle cases f0r deletion
            if (node.left == null) {
                return node.right; // Node with only right child or no children
            } else if (node.right == null) {
                return node.left; // Node with only left child
            } else {
                // Node with two children
                // Find the minimum value in the right subtree
                // Replace the node's data with the minimum value
                // Delete the minimum node from the right subtree
                T minData = findMin(node.right);
                node.data = minData;
                node.right = deleteRecursive(node.right, minData);
            }
        }

        return node;
    }

    private T findMin(BinaryNode<T> node) {
        if (node.left == null) {
            return node.data;
        } else {
            return findMin(node.left);
        }
    }
    //-----------------------------------------------------------------------------------------------------------------

    public boolean contains(T data) {
        return containsRecursive(root, data);
    }

    //HELPER of contains
    private boolean containsRecursive(BinaryNode<T> node, T data) {
        if (node == null) {
            return false; // Node not found
        }

        if (data.compareTo(node.data) < 0) {
            return containsRecursive(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return containsRecursive(node.right, data);
        } else {
            return true; // Node found
        }
    }
    //-----------------------------------------------------------------------------------------------------------------

    public void insert(T data) {
        if (root == null) {
            root = new BinaryNode<>(data); // Empty tree, create the root node
            return;
        }

        insertRecursive(root, data);
    }

    //HELPER of insert
    private BinaryNode<T> insertRecursive(BinaryNode<T> node, T data) {
        if (data.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new BinaryNode<>(data);
            } else {
                insertRecursive(node.left, data);
            }
        } else if (data.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new BinaryNode<>(data);
            } else {
                insertRecursive(node.right, data);
            }
        } else {
            // nothing happens
        }

        return node;
    }
    //-----------------------------------------------------------------------------------------------------------------

    public int getHeight() {
        return getHeightRecursive(root);
    }

    //HELPER of getHeight
    private int getHeightRecursive(BinaryNode<T> node) {
        if (node == null) {
            return 0; // Empty subtree has height 0
        }

        // Recursively find the heights of the left and right subtrees
        int leftHeight = getHeightRecursive(node.left);
        int rightHeight = getHeightRecursive(node.right);

        // Return the height of the taller subtree + 1 (f0r the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
    //-----------------------------------------------------------------------------------------------------------------
  
    public String printSearchPath(T data) {
        StringBuilder path = new StringBuilder();
        return printSearchPathRecursive(root, data, path) ? path.toString().trim() : path.toString();
    }

    //HELPER of printSearchPath
    private boolean printSearchPathRecursive(BinaryNode<T> node, T data, StringBuilder path) {
        if (node == null) {
            return false; // Node not found
        }

        path.append(node.data).append(" -> "); // Append current node data and separator

        if (data.compareTo(node.data) == 0) {
            path.delete(path.length() - 4, path.length()); // Remove the last " -> "
            return true; // Node found, return true and trimmed path
        }

        boolean found = false;
        if (data.compareTo(node.data) < 0) {
            found = printSearchPathRecursive(node.left, data, path);
        } else {
            found = printSearchPathRecursive(node.right, data, path);
        }

        if (!found) {
            path.delete(path.length() - 4, path.length()); // Remove the last " -> " if data not found
        }

        return found;
    }
    //-----------------------------------------------------------------------------------------------------------------

    public int getNumLeaves() {
        return getNumLeavesRecursive(root);
    }

    //HELPER of getNumLeaves
    private int getNumLeavesRecursive(BinaryNode<T> node) {
        if (node == null) {
            return 0; // Empty subtree
        }

        // If the node is a leaf (no children), count it as 1 leaf
        if (node.left == null && node.right == null) {
            return 1;
        } else {
            // Recursively count leaves in the left and right subtrees
            return getNumLeavesRecursive(node.left) + getNumLeavesRecursive(node.right);
        }
    }
    //-----------------------------------------------------------------------------------------------------------------

    public BST<T> extractBiggestSuperficiallyBalancedSubTree() {
        if (root == null) {
            return null;
        }
        Object[] leftResult = extractBiggestSuperficiallyBalancedSubTreeRecursive(root.left);
        Object[] rightResult = extractBiggestSuperficiallyBalancedSubTreeRecursive(root.right);
        return ((int)leftResult[1] >= (int)rightResult[1]) ? (BST<T>)leftResult[0] : (BST<T>)rightResult[0];
    }

    private Object[] extractBiggestSuperficiallyBalancedSubTreeRecursive(BinaryNode<T> node) {
        if (node == null) {
            return new Object[]{new BST<>(), 0};
        }

        Object[] leftResult = extractBiggestSuperficiallyBalancedSubTreeRecursive(node.left);
        Object[] rightResult = extractBiggestSuperficiallyBalancedSubTreeRecursive(node.right);

        if ((int)leftResult[1] == (int)rightResult[1]) {
            BST<T> tree = new BST<>();
            tree.root = new BinaryNode<>(node.data);
            tree.root.left = ((BST<T>)leftResult[0]).root;
            tree.root.right = ((BST<T>)rightResult[0]).root;
            return new Object[]{tree, (int)leftResult[1] + (int)rightResult[1] + 1};
        } else if ((int)leftResult[1] > (int)rightResult[1]) {
            return leftResult;
        } else {
            return rightResult;
        }
    }
    //-----------------------------------------------------------------------------------------------------------------

    public BinaryNode<T> getNode(T data) {
        return getNodeRecursive(root, data);
    }

    //HELPER of getNode
    private BinaryNode<T> getNodeRecursive(BinaryNode<T> node, T data) {
        if (node == null) {
            return null; // Node not found
        }

        if (data.compareTo(node.data) < 0) {
            return getNodeRecursive(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return getNodeRecursive(node.right, data);
        } else {
            return node; // Node found
        }
    }
    //-----------------------------------------------------------------------------------------------------------------

    public boolean isSuperficiallyBalanced() {
        if (root == null) {
            return false;
        }
        return countNodes(root.left) == countNodes(root.right);
    }

    private int countNodes(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }


    //-----------------------------------------------------------------------------------------------------------------

    public BinaryNode<T> findMax() {
        return findMaxRecursive(root);
    }

    //HELPER of findMax
    private BinaryNode<T> findMaxRecursive(BinaryNode<T> node) {
        if (node == null) {
            return null; // Empty tree
        }

        // Recursively find the maximum node in the right subtree
        BinaryNode<T> maxNode = findMaxRecursive(node.right);

        // If the current node's data is greater than the maximum found in the right subtree, it's the maximum
        return maxNode == null || node.data.compareTo(maxNode.data) > 0 ? node : maxNode;
    }
    //-----------------------------------------------------------------------------------------------------------------

    public BinaryNode<T> findMin() {
        return findMinRecursive(root);
    }

    //HELPER of findMin
    private BinaryNode<T> findMinRecursive(BinaryNode<T> node) {
        if (node == null) {
            return null; // Empty tree
        }

        // Recursively find the minimum node in the left subtree
        BinaryNode<T> minNode = findMinRecursive(node.left);

        // If the current node's data is less than the minimum found in the left subtree, it's the minimum
        return minNode == null || node.data.compareTo(minNode.data) < 0 ? node : minNode;
    }
    //-----------------------------------------------------------------------------------------------------------------


    ///////////////

    private StringBuilder toString(BinaryNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (node.right != null) {
            toString(node.right, new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.data.toString()).append("\n");
        if (node.left != null) {
            toString(node.left, new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return root == null ? "Empty tree" : toString(root, new StringBuilder(), true, new StringBuilder()).toString();
    }

}
