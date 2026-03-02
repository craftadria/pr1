package edu.uoc.ds.adt;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PR1QueueTest {
    PR1Queue pr1q;

    private void fillQueue() {
        for (char c = '0'; c < '9'; c++) {
            pr1q.add(Character.valueOf(c));

        }
    }
    @Before
    public void setUp() {
        this.pr1q = new PR1Queue();

        assertNotNull(this.pr1q.getQueue());
        fillQueue();
    }

    @After
    public void release() {
        this.pr1q = null;
    }


    @org.junit.Test
    public void queueTest() {
        assertEquals(this.pr1q.CAPACITY-1, this.pr1q.getQueue().size());
        Assert.assertEquals(new Character('0'), pr1q.poll());
        Assert.assertEquals(new Character('1'), pr1q.poll());
        Assert.assertEquals(new Character('2'), pr1q.poll());
        Assert.assertEquals(new Character('3'), pr1q.poll());
        Assert.assertEquals(new Character('4'), pr1q.poll());
        Assert.assertEquals(new Character('5'), pr1q.poll());
        Assert.assertEquals(new Character('6'), pr1q.poll());
        Assert.assertEquals(new Character('7'), pr1q.poll());
        Assert.assertEquals(new Character('8'), pr1q.poll());
        assertEquals(0, this.pr1q.getQueue().size());
    }

}
