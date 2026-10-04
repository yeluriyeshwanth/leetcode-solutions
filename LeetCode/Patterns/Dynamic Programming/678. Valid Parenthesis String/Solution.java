class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                low++;
                high++;
            }

            else if (ch == ')') {
                low--;
                high--;
            }

            else { // '*'
                low--;
                high++;
            }

            // We cannot have negative minimum opens
            if (low < 0) {
                low = 0;
            }

            // Even the maximum possible opens is negative
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}