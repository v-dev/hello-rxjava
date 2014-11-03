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
import rx.Subscriber;

public class LipsumSubscriber extends Subscriber<String> {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public void onCompleted() {
        LOG.info("Done.");
    }

    @Override
    public void onError(Throwable e) {
        LOG.error("An error happened: ", e);
    }

    @Override
    public void onNext(String lipsum) {
        LOG.info("[{}] lipsum: {}", hashCode(), lipsum);
    }
}
