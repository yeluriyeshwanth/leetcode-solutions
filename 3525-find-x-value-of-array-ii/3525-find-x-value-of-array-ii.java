
class Solution {

    static class Node {
        int product;
        int[] prefix;

        Node(int k) {
            prefix = new int[k];
            product = 1 % k;
        }
    }

    int[] nums;
    int k;
    Node[] tree;

    // Merge two nodes
    Node merge(Node left, Node right) {

        Node result = new Node(k);

        // Product of the combined segment
        result.product = (left.product * right.product) % k;

        // Prefixes entirely inside the left segment
        for (int r = 0; r < k; r++) {
            result.prefix[r] += left.prefix[r];
        }

        // Prefixes that include the entire left segment
        // and a prefix of the right segment
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.product * r) % k;

            result.prefix[newRemainder] += right.prefix[r];
        }

        return result;
    }

    // Build the segment tree
    void build(int node, int start, int end) {

        if (start == end) {

            tree[node] = new Node(k);

            int remainder = nums[start] % k;

            tree[node].product = remainder;
            tree[node].prefix[remainder] = 1;

            return;
        }

        int mid = start + (end - start) / 2;

        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Point update
    void update(int node, int start, int end,
                int index, int value) {

        if (start == end) {

            tree[node] = new Node(k);

            int remainder = value % k;

            tree[node].product = remainder;
            tree[node].prefix[remainder] = 1;

            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {

            update(2 * node, start, mid, index, value);

        } else {

            update(2 * node + 1, mid + 1, end, index, value);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    // Query a range
    Node query(int node, int start, int end,
               int queryStart, int queryEnd) {

        // No overlap
        if (queryEnd < start || end < queryStart) {

            return new Node(k);
        }

        // Complete overlap
        if (queryStart <= start && end <= queryEnd) {

            return tree[node];
        }

        int mid = start + (end - start) / 2;

        Node left = query(
            2 * node,
            start,
            mid,
            queryStart,
            queryEnd
        );

        Node right = query(
            2 * node + 1,
            mid + 1,
            end,
            queryStart,
            queryEnd
        );

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.nums = nums;
        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        // Build segment tree
        build(1, 0, n - 1);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update persists for future queries
            update(1, 0, n - 1, index, value);

            // Query all prefixes starting at 'start'
            Node answer = query(
                1,
                0,
                n - 1,
                start,
                n - 1
            );

            result[q] = answer.prefix[x];
        }

        return result;
    }
}