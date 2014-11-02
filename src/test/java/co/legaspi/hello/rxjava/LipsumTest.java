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

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LipsumTest {

    private Lipsum lipsum;

    @Before
    public void setup() {
        lipsum = new Lipsum();
    }

    @Test
    public void hasExpectedSize() {
        final int expectedSize = 12;
        assertThat(lipsum.size()).isEqualTo(expectedSize);
    }

    @Test
    public void getIsEmptyWhenNegativeIndex() {
        assertThat(lipsum.get(-1)).isEmpty();
    }

    @Test
    public void getIsEmptyWhenGreaterThanSize() {
        int tooLarge = lipsum.size() + 1;
        assertThat(lipsum.get(tooLarge)).isEmpty();
    }

    @Test
    public void firstElementIsLoremIpsum() {
        final String firstElement = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        assertThat(lipsum.get(0)).isEqualTo(firstElement);
    }

    @Test
    public void fifthElementIsNamEget() {
        final String fifthElement = "Nam eget elementum purus.";
        assertThat(lipsum.get(4)).isEqualTo(fifthElement);
    }

    @Test
    public void lastElementIsPellentesque() {
        int lastIndex = lipsum.size() - 1;
        final String lastElement = "Pellentesque libero sapien, eleifend a eleifend eu, bibendum congue tortor.";

        assertThat(lipsum.get(lastIndex)).isEqualTo(lastElement);
    }

}
