package co.legaspi.hello.rxjava;

/*
The MIT License (MIT)

Copyright (c) 2014 legaspi.co

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import co.legaspi.hello.rxjava.LipsumSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LipsumSubscriberTest {

    @InjectMocks private LipsumSubscriber subscriber;
    @Mock private Appender mockAppender;

    private Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @Before
    public void setup() {
        subscriber = new LipsumSubscriber();

        MockitoAnnotations.initMocks(this);

        when(mockAppender.getName()).thenReturn("MOCK");
        root.addAppender(mockAppender);
    }

    @Test
    public void testOnCompleted() throws Exception {
        String expectedOutput = "Done.";
        subscriber.onCompleted();

        verifyLog(expectedOutput);
    }

    @Test
    public void testOnError() throws Exception {
        String expectedOutput = "An error happened: ";
        subscriber.onError(new Exception("you got an error."));

        verifyLog(expectedOutput);
    }

    @Test
    public void testOnNext() throws Exception {
        String mockLipsum = "blah";
        subscriber.onNext(mockLipsum);

        verifyLog(mockLipsum);
    }

    /**
     * Thanks, http://jsoftbiz.wordpress.com/2011/11/29/unit-testing-asserting-that-a-line-was-logged-by-logback
     */
    private void verifyLog(final String expectedOutput) {
        verify(mockAppender).doAppend(argThat(new ArgumentMatcher() {
            @Override
            public boolean matches(Object argument) {
                return ((LoggingEvent) argument).getFormattedMessage().contains(expectedOutput);
            }
        }));
    }
}