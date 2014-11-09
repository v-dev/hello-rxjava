package co.legaspi.hello.rxjava.demo;

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

import co.legaspi.hello.rxjava.CheapLipsumServer;
import co.legaspi.hello.rxjava.LipsumSubscriber;
import co.legaspi.hello.rxjava.ReliableLipsumServer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Func1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void twoSubscribersToReliableService() throws InterruptedException {
        final Observable<String> lipsums = ReliableLipsumServer.getLipsum(1);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                lipsums.take(5).subscribe(new LipsumSubscriber());
            }
        });

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                lipsums.take(10).subscribe(new LipsumSubscriber());
            }
        });

        executorService.awaitTermination(11, TimeUnit.SECONDS);

        executorService.shutdownNow();
    }

    @Test
    public void twoResilientSubscribersToCheapService() throws InterruptedException {
        final Observable<String> lipsums = CheapLipsumServer.getLipsum(1);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lipsums.take(10).subscribe(new LipsumSubscriber());
                lipsums.onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        LOG.info("Service died.  Re-attaching...");
                        return CheapLipsumServer.getLipsum(1);
                    }
                }).subscribe(new LipsumSubscriber());
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                lipsums.take(10).subscribe(new LipsumSubscriber());
                lipsums.onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        LOG.info("Service died.  Re-attaching...");
                        return CheapLipsumServer.getLipsum(1);
                    }
                }).takeLastBuffer(10).subscribe();
            }
        });

        executorService.awaitTermination(11, TimeUnit.SECONDS);

        executorService.shutdownNow();
    }
}
