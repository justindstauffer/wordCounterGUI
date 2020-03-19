package sample;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestClassTest {

    @Test
    void getTop20() {
        TestClass theTest = new TestClass();
        theTest.getTop20("http://shakespeare.mit.edu/macbeth/full.html");
        theTest.getTop20("https://martinsdeveloperworld.wordpress.com/2016/02/16/verify-html-documents-in-junit-tests-with-jsoup/");
    }
}