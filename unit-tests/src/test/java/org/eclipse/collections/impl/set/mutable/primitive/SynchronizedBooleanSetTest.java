/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable.primitive;

import org.eclipse.collections.api.set.primitive.MutableBooleanSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * JUnit test for {@link SynchronizedBooleanSet}.
 */
public class SynchronizedBooleanSetTest extends AbstractBooleanSetTestCase
{
    @Override
    protected SynchronizedBooleanSet classUnderTest()
    {
        return new SynchronizedBooleanSet(BooleanHashSet.newSetWith(true, false, true));
    }

    @Override
    protected SynchronizedBooleanSet newWith(boolean... elements)
    {
        return new SynchronizedBooleanSet(BooleanHashSet.newSetWith(elements));
    }

    @Override
    @Test
    public void asSynchronized()
    {
        super.asSynchronized();
        SynchronizedBooleanSet set = this.classUnderTest();
        MutableBooleanSet setWithLockObject = new SynchronizedBooleanSet(BooleanHashSet.newSetWith(true, false, true), new Object()).asSynchronized();
        assertEquals(set, setWithLockObject);
        assertSame(setWithLockObject, setWithLockObject.asSynchronized());
        assertSame(set, set.asSynchronized());
        assertEquals(set, set.asSynchronized());
    }
}
