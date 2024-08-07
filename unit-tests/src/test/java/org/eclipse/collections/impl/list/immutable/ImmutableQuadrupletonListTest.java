/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.immutable;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableQuadrupletonListTest extends AbstractImmutableListTestCase
{
    @Override
    protected ImmutableList<Integer> classUnderTest()
    {
        return new ImmutableQuadrupletonList<>(1, 2, 3, 4);
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        ImmutableList<Integer> list = new ImmutableQuadrupletonList<>(2, 1, 1, 2);
        ImmutableList<Integer> distinctList = list.distinct();
        assertFalse(distinctList.isEmpty());
        Verify.assertInstanceOf(ImmutableDoubletonList.class, distinctList);
        assertEquals(FastList.newListWith(2, 1), distinctList);
    }

    @Test
    public void distinctWithHashingStrategies()
    {
        ImmutableList<String> list = new ImmutableQuadrupletonList<>("a", "a", "B", "c");
        ImmutableList<String> distinctList = list.distinct(HashingStrategies.fromFunction(String::toLowerCase));
        assertFalse(distinctList.isEmpty());
        assertEquals(FastList.newListWith("a", "B", "c"), distinctList);
    }

    @Test
    public void getOnly()
    {
        ImmutableList<Integer> list = this.classUnderTest();
        assertThrows(IllegalStateException.class, () -> list.getOnly());
    }
}
