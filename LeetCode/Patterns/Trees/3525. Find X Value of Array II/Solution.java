class Solution {

    static class Node {

        int total;
        int[] pref;

        Node(int k) {
            pref = new int[k];
        }
    }

    int n;
    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;
        this.n = nums.length;

        tree = new Node[4 * n];

        // Build the segment tree
        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Point update
            nums[index] = value;

            update(1, 0, n - 1, index, value % k);

            // Query the segment [start, n - 1]
            Node answer = query(1, 0, n - 1, start, n - 1);

            result[i] = answer.pref[x];
        }

        return result;
    }

    // ---------------------------------------
    // Build Segment Tree
    // ---------------------------------------

    private void build(int node, int left, int right) {

        if (left == right) {

            tree[node] = new Node(k);

            int remainder = nums[left] % k;

            // Single element is one non-empty prefix
            tree[node].pref[remainder] = 1;

            // Total product of one element
            tree[node].total = remainder;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);

        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    // ---------------------------------------
    // Merge Two Consecutive Segments
    // ---------------------------------------

    private Node merge(Node a, Node b) {

        Node result = new Node(k);

        // Total product of combined segment
        result.total = (a.total * b.total) % k;

        // Type 1:
        // Prefixes entirely inside segment A
        for (int r = 0; r < k; r++) {
            result.pref[r] += a.pref[r];
        }

        // Type 2:
        // Prefixes containing all of A and part of B
        for (int r = 0; r < k; r++) {

            int newRemainder = (a.total * r) % k;

            result.pref[newRemainder] += b.pref[r];
        }

        return result;
    }

    // ---------------------------------------
    // Point Update
    // ---------------------------------------

    private void update(
            int node,
            int left,
            int right,
            int index,
            int value) {

        if (left == right) {

            tree[node] = new Node(k);

            tree[node].total = value;

            tree[node].pref[value] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {

            update(
                    node * 2,
                    left,
                    mid,
                    index,
                    value
            );

        } else {

            update(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    index,
                    value
            );
        }

        // Recalculate the current segment
        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    // ---------------------------------------
    // Range Query
    // ---------------------------------------

    private Node query(
            int node,
            int left,
            int right,
            int queryLeft,
            int queryRight) {

        // No overlap
        if (right < queryLeft || left > queryRight) {
            return null;
        }

        // Complete overlap
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        Node leftResult = query(
                node * 2,
                left,
                mid,
                queryLeft,
                queryRight
        );

        Node rightResult = query(
                node * 2 + 1,
                mid + 1,
                right,
                queryLeft,
                queryRight
        );

        // Only the right segment exists
        if (leftResult == null) {
            return rightResult;
        }

        // Only the left segment exists
        if (rightResult == null) {
            return leftResult;
        }

        // Important: preserve array order
        return merge(leftResult, rightResult);
    }
}