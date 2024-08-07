/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.math.IntegerSum;
import org.eclipse.collections.impl.math.Sum;
import org.eclipse.collections.impl.math.SumProcedure;
import org.eclipse.collections.impl.utility.LazyIterate;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectInstancesOfIterableTest extends AbstractLazyIterableTestCase
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectInstancesOfIterableTest.class);

    @Override
    protected <T> LazyIterable<T> newWith(T... elements)
    {
        return (LazyIterable<T>) LazyIterate.selectInstancesOf(FastList.newListWith(elements), Object.class);
    }

    @Test
    public void forEach()
    {
        LazyIterable<Integer> select = new SelectInstancesOfIterable<>(FastList.newListWith(1, 2.0, 3, 4.0, 5), Integer.class);
        Sum sum = new IntegerSum(0);
        select.forEach(new SumProcedure<>(sum));
        assertEquals(9, sum.getValue().intValue());
    }

    @Test
    public void forEachWithIndex()
    {
        LazyIterable<Integer> select = new SelectInstancesOfIterable<>(FastList.newListWith(1, 2.0, 3, 4.0, 5), Integer.class);
        Sum sum = new IntegerSum(0);
        select.forEachWithIndex((object, index) -> {
            sum.add(object);
            sum.add(index);

            LOGGER.info("value={} index={}", object, index);
        });
        assertEquals(12, sum.getValue().intValue());
    }

    @Override
    @Test
    public void iterator()
    {
        LazyIterable<Integer> select = new SelectInstancesOfIterable<>(FastList.newListWith(1, 2.0, 3, 4.0, 5), Integer.class);
        Sum sum = new IntegerSum(0);
        for (Integer each : select)
        {
            sum.add(each);
        }
        assertEquals(9, sum.getValue().intValue());
    }

    @Test
    public void forEachWith()
    {
        LazyIterable<Integer> select = new SelectInstancesOfIterable<>(FastList.newListWith(1, 2.0, 3, 4.0, 5), Integer.class);
        Sum sum = new IntegerSum(0);
        select.forEachWith((each, aSum) -> aSum.add(each), sum);
        assertEquals(9, sum.getValue().intValue());
    }

    @Override
    @Test
    public void min_null_throws()
    {
        // Impossible for SelectInstancesOfIterable to contain null
    }

    @Override
    @Test
    public void max_null_throws()
    {
        // Impossible for SelectInstancesOfIterable to contain null
    }

    @Override
    @Test
    public void min_null_throws_without_comparator()
    {
        // Impossible for SelectInstancesOfIterable to contain null
    }

    @Override
    @Test
    public void max_null_throws_without_comparator()
    {
        // Impossible for SelectInstancesOfIterable to contain null
    }

    @Override
    @Test
    public void distinct()
    {
        super.distinct();
        LazyIterable<Double> iterable = new SelectInstancesOfIterable<>(FastList.newListWith(3.0, 2.0, 3, 2.0, 4.0, 5, 1.0, 3.0, 1.0, 5.0), Double.class);
        assertEquals(
                FastList.newListWith(3.0, 2.0, 4.0, 1.0, 5.0),
                iterable.distinct().toList());
    }
}
