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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

public class ReliableLipsumServer {
    private static final Logger LOG = LoggerFactory.getLogger(ReliableLipsumServer.class);

    public static Observable<String> getLipsum(final int secondsPerLipsum) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                while (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(Lipsum.singleton().next());
                    sleep(secondsPerLipsum);
                }
            }
        });
    }

    private static void sleep(int secondsToSleep) {
        try {
            Thread.sleep(secondsToSleep * 1000L);
        } catch (InterruptedException e) {
            LOG.error("Sleep interrupted: ", e);
        }
    }
}
