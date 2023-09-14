package nespresso;

import org.openqa.selenium.JavascriptExecutor;

public class JsExecutor {
    private JavascriptExecutor javascriptExecutor;

    public JsExecutor(JavascriptExecutor javascriptExecutor) {

        this.javascriptExecutor = javascriptExecutor;
    }


    public void runJs(String script) {
        javascriptExecutor.executeScript(script);
    }


}
