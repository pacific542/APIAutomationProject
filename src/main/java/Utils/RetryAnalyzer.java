package Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int attempt = 0;
    private static final int maxRetry = 1;  // Retry only once

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < maxRetry) {
            attempt++;
            return true;  // Retry test
        }
        return false;
    }
}
