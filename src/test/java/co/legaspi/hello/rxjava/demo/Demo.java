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

import co.legaspi.hello.rxjava.LipsumServer;
import co.legaspi.hello.rxjava.LipsumSubscriber;
import org.junit.Test;
import rx.Observable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void demo() throws InterruptedException {
        final Observable<String> lipsums = LipsumServer.getLipsum(1);

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
}
