import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Your implementation of an AVL.
 *
 * @author Jiaxuan Chen
 * @version 1.0
 * @userid jchen813
 * @GTID 903425077
 *
 * Collaborators: N/A
 *
 * Resources: my BST homework for reference
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot add a null data to the list");
        }
        for (T temp : data) {
            if (temp == null) {
                throw new java.lang.IllegalArgumentException("Cannot add a null data to the list");
            }
            add(temp);
        }
        size = data.size();

    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {

        root = addHelper(root, data);


    }

    /**
     *
     * @param temp the root of the tree for recursion
     * @param data the data to be added
     * @return the result node for recursion
     */
    private AVLNode<T> addHelper(AVLNode<T> temp, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot add a null data to the list");
        }
        if (temp == null) {
            size++;
            return new AVLNode<T>(data);
        }
        if (data.compareTo(temp.getData()) < 0) {
            temp.setLeft(addHelper(temp.getLeft(), data));
        }
        if (data.compareTo(temp.getData()) > 0) {
            temp.setRight(addHelper(temp.getRight(), data));
        }
        if (data.compareTo(temp.getData()) == 0) {
            return temp;
        }

        updateHandBF(temp);
        if (temp.getBalanceFactor() > 1) {
            if (temp.getLeft().getBalanceFactor() < 0) {
                temp.setLeft(leftRotation(temp.getLeft()));
            }
            temp = rightRotation(temp);
        }
        if (temp.getBalanceFactor() < -1) {
            if (temp.getRight().getBalanceFactor() > 0) {
                temp.setRight(rightRotation(temp.getRight()));
            }
            temp = leftRotation(temp);
        }
        return temp;
    }

    /**
     *
     * @param temp the head node to be recursed
     * @return the new head node after recursion
     */
    private AVLNode<T> leftRotation(AVLNode<T> temp) {
        AVLNode<T> dummy = temp.getRight();
        temp.setRight(dummy.getLeft());
        dummy.setLeft(temp);

        updateHandBF(temp);
        updateHandBF(dummy);

        temp = dummy;
        return temp;
    }
    /**
     *
     * @param temp the head node to be recursed
     * @return the new head node after recursion
     */
    private AVLNode<T> rightRotation(AVLNode<T> temp) {
        AVLNode<T> dummy = temp.getLeft();
        temp.setLeft(dummy.getRight());
        dummy.setRight(temp);

        updateHandBF(temp);
        updateHandBF(dummy);

        temp = dummy;
        return temp;
    }

    /**
     *
     * @param temp the node to be used to update its height and bf
     */
    private void updateHandBF(AVLNode<T> temp) {
        temp.setHeight(Math.max(checkHeight(temp.getLeft()), checkHeight(temp.getRight())) + 1);
        temp.setBalanceFactor(checkHeight(temp.getLeft()) - checkHeight(temp.getRight()));
    }

    /**
     * gets the height of the avl node, if no child, return - 1
     * @param temp the node to be calculated
     * @return the height of the given node
     */
    private int checkHeight(AVLNode<T> temp) {
        if (temp == null) {
            return -1;
        } else {
            return temp.getHeight();
        }
    }


    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        AVLNode<T> removed = new AVLNode<>(null);
        root = removeHelper(root, removed, data);
        return removed.getData();
    }

    /**
     *
     * @param temp the head node to start recursion with
     * @param helper the helper node to store the node which is to be removed
     * @param data input data to searching for the match
     * @return the recursive node
     */
    private AVLNode<T> removeHelper(AVLNode<T> temp, AVLNode<T> helper, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot pass a null value to the list.");
        }
        if (temp == null) {
            throw new java.util.NoSuchElementException("The data is not in the list");
        }
        if (temp.getData().compareTo(data) == 0) {
            helper.setData(temp.getData());
            size--;
            if (temp.getLeft() != null && temp.getRight() != null) {
                AVLNode<T> predDummy = new AVLNode<>(null);
                AVLNode<T> pred = predHelper(temp.getLeft(), predDummy);
                temp.setData(predDummy.getData());
                temp.setLeft(pred);
                updateHandBF(temp);
                if (temp.getBalanceFactor() > 1) {
                    if (temp.getLeft().getBalanceFactor() < 0) {
                        temp.setLeft(leftRotation(temp.getLeft()));
                    }
                    temp = rightRotation(temp);
                }
                if (temp.getBalanceFactor() < -1) {
                    if (temp.getRight().getBalanceFactor() > 0) {
                        temp.setRight(rightRotation(temp.getRight()));
                    }
                    temp = leftRotation(temp);
                }

                return temp;
            } else if (temp.getLeft() == null && temp.getRight() == null) {
                return null;
            } else if (temp.getRight() != null && temp.getLeft() == null) {
                return temp.getRight();
            } else if (temp.getLeft() != null && temp.getRight() == null) {
                return temp.getLeft();
            }
        }
        if (temp.getData().compareTo(data) < 0) {
            temp.setRight(removeHelper(temp.getRight(), helper, data));
        }
        if (temp.getData().compareTo(data) > 0) {
            temp.setLeft(removeHelper(temp.getLeft(), helper, data));
        }
        updateHandBF(temp);
        if (temp.getBalanceFactor() > 1) {
            if (temp.getLeft().getBalanceFactor() < 0) {
                temp.setLeft(leftRotation(temp.getLeft()));
            }
            temp = rightRotation(temp);
        }
        if (temp.getBalanceFactor() < -1) {
            if (temp.getRight().getBalanceFactor() > 0) {
                temp.setRight(rightRotation(temp.getRight()));
            }
            temp = leftRotation(temp);
        }
        return temp;

    }

    /**
     *
     * @param temp the head to to start the recursion with
     * @param helper the node to hold the removed node
     * @return the node after recursion
     */
    private AVLNode<T> predHelper(AVLNode<T> temp, AVLNode<T> helper) {
        if (temp.getRight() == null) {
            helper.setData(temp.getData());
            return temp.getLeft();
        }
        temp.setRight(predHelper(temp.getRight(), helper));
        updateHandBF(temp);
        if (temp.getBalanceFactor() > 1) {
            if (temp.getLeft().getBalanceFactor() < 0) {
                temp.setLeft(leftRotation(temp.getLeft()));
            }
            temp = rightRotation(temp);
        }
        if (temp.getBalanceFactor() < -1) {
            if (temp.getRight().getBalanceFactor() > 0) {
                temp.setRight(rightRotation(temp.getRight()));
            }
            temp = leftRotation(temp);
        }
        return temp;
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        return getHelper(root, data);
    }

    /**
     *
     * @param temp head node to search
     * @param data the data used to match the data in the list
     * @return data we trying to get.
     */
    private T getHelper(AVLNode<T> temp, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot pass a null data to the list");
        }
        if (temp == null) {
            throw new java.util.NoSuchElementException("The data is not in the tree");
        }
        if (temp.getData().compareTo(data) < 0) {
            return (getHelper(temp.getRight(), data));
        } else if (temp.getData().compareTo(data) > 0) {
            return (getHelper(temp.getLeft(), data));
        } else {
            return temp.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        return containsHelper(root, data);
    }

    /**
     *
     * @param temp the node used to start the recursion with.
     * @param data the data to look for.
     * @return wheter the data contains in the tree or not.
     */
    private boolean containsHelper(AVLNode<T> temp, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Null data is not in the list");
        }
        if (temp == null) {
            return false;
        }
        if (temp.getData().compareTo(data) < 0) {
            return (containsHelper(temp.getRight(), data));
        } else if (temp.getData().compareTo(data) > 0) {
            return (containsHelper(temp.getLeft(), data));
        } else {
            return true;
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     *
     * @param temp Node to recurse both end of the tree.
     * @return the height of the tree.
     */
    private int heightHelper(AVLNode<T> temp) {
        if (temp == null) {
            return -1;
        } else {
            return Math.max(heightHelper(temp.getLeft()), heightHelper(temp.getRight())) + 1;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Find all elements within a certain distance from the given data.
     * "Distance" means the number of edges between two nodes in the tree.
     *
     * To do this, first find the data in the tree. Keep track of the distance
     * of the current node on the path to the data (you can use the return
     * value of a helper method to denote the current distance to the target
     * data - but note that you must find the data first before you can
     * calculate this information). After you have found the data, you should
     * know the distance of each node on the path to the data. With that
     * information, you can determine how much farther away you can traverse
     * from the main path while remaining within distance of the target data.
     *
     * Use a HashSet as the Set you return. Keep in mind that since it is a
     * Set, you do not have to worry about any specific order in the Set.
     *
     * Note: We recommend 2 helper methods:
     * 1. One helper method should be for adding the nodes on the path (from
     * the root to the node containing the data) to the Set (if they are within
     * the distance). This helper method will also need to find the distance
     * between each node on the path and the target data node.
     * 2. One helper method should be for adding the children of the nodes
     * along the path to the Set (if they are within the distance). The
     * private method stub called elementsWithinDistanceBelow is intended to
     * be the second helper method. You do NOT have to implement
     * elementsWithinDistanceBelow. However, we recommend you use this method
     * to help implement elementsWithinDistance.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * elementsWithinDistance(37, 3) should return the set {12, 13, 15, 25,
     * 37, 40, 50, 75}
     * elementsWithinDistance(85, 2) should return the set {75, 80, 85}
     * elementsWithinDistance(13, 1) should return the set {12, 13, 15, 25}
     *
     * @param data     the data to begin calculating distance from
     * @param distance the maximum distance allowed
     * @return the set of all data within a certain distance from the given data
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   is the data is not in the tree
     * @throws java.lang.IllegalArgumentException if distance is negative
     */
    public Set<T> elementsWithinDistance(T data, int distance) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot find a null data");
        }
        if (distance < 0) {
            throw new java.lang.IllegalArgumentException("The distance cannot be negative");
        }
        if (!contains(data)) {
            throw new java.util.NoSuchElementException("The data is not in the tree");
        }
        Set<T> mySet = new HashSet<>();
        path(root, data, distance, mySet);
        return mySet;
    }

    /**
     * You do NOT have to implement this method if you choose not to.
     * However, this will help with the elementsWithinDistance method.
     *
     * Adds data to the Set if the current node is within the maximum distance
     * from the target node. Recursively call on the current node's children to
     * add their data too if the children's data are also within the maximum
     * distance from the target node.
     *
     * @param curNode         the current node
     * @param maximumDistance the maximum distance allowed
     * @param currentDistance the distance between the current node and the
     *                        target node
     * @param currentResult   the current set of data within the maximum
     *                        distance
     */
    private void elementsWithinDistanceBelow(AVLNode<T> curNode,
                                             int maximumDistance,
                                             int currentDistance,
                                             Set<T> currentResult) {
        // STUDENT CODE HERE
        if (curNode != null && currentDistance <= maximumDistance) {
            currentResult.add(curNode.getData());
        }
        if (currentDistance + 1 <= maximumDistance) {
            if (curNode.getLeft() != null) {
                elementsWithinDistanceBelow(curNode.getLeft(),
                        maximumDistance, currentDistance++, currentResult);
            }
            if (curNode.getRight() != null) {
                elementsWithinDistanceBelow(curNode.getRight(),
                        maximumDistance, currentDistance++, currentResult);
            }
        }
    }

    /**
     *
     * @param temp the head node to start the recursion with
     * @param data the input data used to search for the match
     * @param maxDistance the max distance from the input data
     * @param mySet the hash set to store the data
     * @return the current distance after recursion
     */
    private int path(AVLNode<T> temp, T data, int maxDistance, Set<T> mySet) {
        if (temp.getData().compareTo(data) > 0) {
            int currDistance = path(temp.getLeft(), data, maxDistance, mySet) + 1;
            elementsWithinDistanceBelow(temp, maxDistance, currDistance, mySet);
            return currDistance;
        } else if (temp.getData().compareTo(data) < 0) {
            int currDistance = path(temp.getRight(), data, maxDistance, mySet) + 1;
            elementsWithinDistanceBelow(temp, maxDistance, currDistance, mySet);
            return currDistance;
        } else {
            elementsWithinDistanceBelow(temp, maxDistance, 0, mySet);
            return 0;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
