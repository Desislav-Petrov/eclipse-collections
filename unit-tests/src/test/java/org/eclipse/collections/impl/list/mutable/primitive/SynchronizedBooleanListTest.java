/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.mutable.primitive;

import org.eclipse.collections.api.list.primitive.MutableBooleanList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * JUnit test for {@link SynchronizedBooleanList}.
 */
public class SynchronizedBooleanListTest extends AbstractBooleanListTestCase
{
    @Override
    protected SynchronizedBooleanList classUnderTest()
    {
        return new SynchronizedBooleanList(BooleanArrayList.newListWith(true, false, true));
    }

    @Override
    protected SynchronizedBooleanList newWith(boolean... elements)
    {
        return new SynchronizedBooleanList(BooleanArrayList.newListWith(elements));
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        SynchronizedBooleanList list = this.classUnderTest();
        MutableBooleanList listWithLockObject = new SynchronizedBooleanList(BooleanArrayList.newListWith(true, false, true), new Object()).asSynchronized();
        assertEquals(list, listWithLockObject);
        assertSame(listWithLockObject, listWithLockObject.asSynchronized());
        assertSame(list, list.asSynchronized());
        assertEquals(list, list.asSynchronized());
    }
}
