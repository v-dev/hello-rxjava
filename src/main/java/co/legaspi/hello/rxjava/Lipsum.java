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

import java.util.ArrayList;
import java.util.List;

public class Lipsum {

    private List<String> lipsum;
    private int currentIndex = 0;

    public Lipsum() {
        setupLipsumList();
    }

    /**
     * How many lipsum entries are there?
     *
     * @return The size of the Lipsum list.
     */
    public int size() {
        return lipsum.size();
    }

    /**
     * Get a lipsum phrase at the given index.
     *
     * @param index The index, starting with 0, to lookup a lipsum phrase.
     * @return  The Lipsum phrase at the given index.  Otherwise, return an empty String.
     */
    public String get(int index) {
        if (index >= 0 && index <= lipsum.size()) {
            return lipsum.get(index);
        } else {
            return "";
        }
    }

    public String next() {
        if (currentIndex < size()) {
            return get(currentIndex++);
        } else {
            return get(currentIndex = 0);
        }
    }

    private void setupLipsumList() {
        lipsum = new ArrayList<String>();

        lipsum.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        lipsum.add("Quisque varius sem ac ex fringilla, non dignissim felis porta.");
        lipsum.add("Sed blandit nibh enim, a fringilla mauris pulvinar eget.");
        lipsum.add("Aliquam efficitur leo ut orci convallis, non venenatis nisi sodales.");
        lipsum.add("Nam eget elementum purus.");
        lipsum.add("Etiam iaculis sapien et turpis convallis, ut laoreet felis dapibus.");
        lipsum.add("Donec placerat libero ac lectus auctor, sit amet mollis felis sodales.");
        lipsum.add("Suspendisse auctor at lorem vitae viverra.");
        lipsum.add("Curabitur porttitor nisi at lorem venenatis luctus. Vestibulum sodales tincidunt diam lacinia placerat.");
        lipsum.add("Proin eget fermentum erat.");
        lipsum.add("Nullam hendrerit sem id lectus aliquam, sit amet elementum lacus scelerisque.");
        lipsum.add("Pellentesque libero sapien, eleifend a eleifend eu, bibendum congue tortor.");
    }

}
