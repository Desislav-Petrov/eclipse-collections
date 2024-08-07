/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.stack.mutable.primitive;

import org.eclipse.collections.api.iterator.MutableBooleanIterator;
import org.eclipse.collections.api.stack.primitive.MutableBooleanStack;
import org.eclipse.collections.impl.stack.primitive.AbstractBooleanStackTestCase;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link UnmodifiableBooleanStack}.
 */
public class UnmodifiableBooleanStackTest extends AbstractBooleanStackTestCase
{
    @Override
    protected UnmodifiableBooleanStack classUnderTest()
    {
        return new UnmodifiableBooleanStack(BooleanArrayStack.newStackWith(true, false, true, false));
    }

    @Override
    protected UnmodifiableBooleanStack newWith(boolean... elements)
    {
        return new UnmodifiableBooleanStack(BooleanArrayStack.newStackWith(elements));
    }

    @Override
    protected UnmodifiableBooleanStack newMutableCollectionWith(boolean... elements)
    {
        return new UnmodifiableBooleanStack(BooleanArrayStack.newStackWith(elements));
    }

    @Override
    protected UnmodifiableBooleanStack newWithTopToBottom(boolean... elements)
    {
        return new UnmodifiableBooleanStack(BooleanArrayStack.newStackFromTopToBottom(elements));
    }

    @Test
    public void push()
    {
        MutableBooleanStack stack = new UnmodifiableBooleanStack(BooleanArrayStack.newStackFromTopToBottom(true, true, false, true, false));
        assertThrows(UnsupportedOperationException.class, () -> stack.push(true));
    }

    @Test
    public void pop()
    {
        MutableBooleanStack stack = new UnmodifiableBooleanStack(BooleanArrayStack.newStackFromTopToBottom(true, true, false, true, false));
        assertThrows(UnsupportedOperationException.class, () -> stack.pop());
    }

    @Test
    public void popWithCount()
    {
        MutableBooleanStack stack = new UnmodifiableBooleanStack(BooleanArrayStack.newStackFromTopToBottom(true, true, false, true, false));
        assertThrows(UnsupportedOperationException.class, () -> stack.pop(2));
    }

    @Test
    public void clear()
    {
        assertThrows(UnsupportedOperationException.class, () -> this.classUnderTest().clear());
    }

    @Test
    public void asUnmodifiable()
    {
        MutableBooleanStack stack1 = new UnmodifiableBooleanStack(BooleanArrayStack.newStackWith(true, false, true));
        assertSame(stack1, stack1.asUnmodifiable());
    }

    @Test
    public void asSynchronized()
    {
        MutableBooleanStack stack1 = new UnmodifiableBooleanStack(BooleanArrayStack.newStackWith(true, false, true));
        Verify.assertInstanceOf(SynchronizedBooleanStack.class, stack1.asSynchronized());
    }

    @Test
    public void booleanIterator_with_remove()
    {
        MutableBooleanIterator booleanIterator = (MutableBooleanIterator) this.classUnderTest().booleanIterator();
        assertTrue(booleanIterator.hasNext());
        booleanIterator.next();
        assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }

    @Test
    public void iterator_throws_on_invocation_of_remove_before_next()
    {
        MutableBooleanIterator booleanIterator = (MutableBooleanIterator) this.classUnderTest().booleanIterator();
        assertTrue(booleanIterator.hasNext());
        assertThrows(UnsupportedOperationException.class, booleanIterator::remove);
    }
}
