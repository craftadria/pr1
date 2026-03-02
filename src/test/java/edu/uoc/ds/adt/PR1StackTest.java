package edu.uoc.ds.adt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PR1StackTest {

    PR1Stack pr1q;

    private void fillStack() {
        for (char c = '0'; c < '9'; c++) {
            pr1q.push(Character.valueOf(c));
        }
    }

    @Before
    public void setUp() {
        this.pr1q = new PR1Stack();

        assertNotNull(this.pr1q.getStack());
        this.fillStack();

    }

    @After
    public void release() {
        this.pr1q = null;
    }


    @org.junit.Test
    public void stackTest() {

        assertEquals(this.pr1q.CAPACITY-1, this.pr1q.getStack().size());
        Assert.assertEquals(new Character('8'), pr1q.pop());
        Assert.assertEquals(new Character('7'), pr1q.pop());
        Assert.assertEquals(new Character('6'), pr1q.pop());
        Assert.assertEquals(new Character('5'), pr1q.pop());
        Assert.assertEquals(new Character('4'), pr1q.pop());
        Assert.assertEquals(new Character('3'), pr1q.pop());
        Assert.assertEquals(new Character('2'), pr1q.pop());
        Assert.assertEquals(new Character('1'), pr1q.pop());
        Assert.assertEquals(new Character('0'), pr1q.pop());
        assertEquals(0, this.pr1q.getStack().size());
    }
}
