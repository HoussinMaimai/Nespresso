import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {
    private ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTestThreadLocal.set(TestNespresso.extent.createTest(result.getName(), "Test Started"));
        System.out.println("Test started: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThreadLocal.get().fail(result.getThrowable());
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThreadLocal.get().skip(result.getThrowable());
        System.out.println("Test skipped: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //extentTestThreadLocal.get().pass(result.getMethod().getDescription());
        extentTestThreadLocal.set(TestNespresso.extent.createTest(result.getName(), "Test succeful"));
        System.out.println("Test passed: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentTestThreadLocal.remove();
        System.out.println("All tests finished in suite: " + context.getName());
    }

}
