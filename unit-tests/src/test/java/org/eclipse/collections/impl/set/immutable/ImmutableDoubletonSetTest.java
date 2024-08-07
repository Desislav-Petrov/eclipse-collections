/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.immutable;

import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableDoubletonSetTest
        extends AbstractImmutableSetTestCase
{
    @Override
    protected ImmutableSet<Integer> classUnderTest()
    {
        return new ImmutableDoubletonSet<>(1, 2);
    }

    @Override
    @Test
    public void newWithout()
    {
        ImmutableSet<Integer> immutable = this.classUnderTest();
        Verify.assertSize(1, immutable.newWithout(2).castToSet());
        Verify.assertSize(1, immutable.newWithout(1).castToSet());
        Verify.assertSize(2, immutable.newWithout(0).castToSet());
    }

    @Test
    public void getOnly()
    {
        assertThrows(IllegalStateException.class, () -> this.classUnderTest().getOnly());
    }
}
