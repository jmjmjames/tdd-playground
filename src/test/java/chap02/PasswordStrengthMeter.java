package chap02;


public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty() || password.isBlank()) {
            return PasswordStrength.INVALID;
        }

        int metCounts = 0;
        boolean lengthEnough = password.length() >= 8;
        if (lengthEnough) {
            metCounts++;
        }
        boolean containsNum = meetsContainingNumberCriteria(password);
        if (containsNum) {
            metCounts++;
        }
        boolean containsUppercase = meetsContainingUppercaseCriteria(password);
        if (containsUppercase) {
            metCounts++;
        }

        if (metCounts == 1) {
            return PasswordStrength.WEAK;
        }

        if (metCounts == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String password) {
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
