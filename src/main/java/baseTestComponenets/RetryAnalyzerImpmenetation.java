package baseTestComponenets;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImpmenetation implements IRetryAnalyzer{
    int count=0;
    int maxRetry=1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRetry)
		{
			count++;
			return true;
		}
		return false;
	}

}
