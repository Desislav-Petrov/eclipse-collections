/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.primitive;

import java.util.Arrays;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.iterator.BooleanIterator;
import org.eclipse.collections.impl.bag.mutable.primitive.BooleanHashBag;
import org.eclipse.collections.impl.block.factory.primitive.BooleanPredicates;
import org.eclipse.collections.impl.factory.primitive.BooleanLists;
import org.eclipse.collections.impl.factory.primitive.ByteLists;
import org.eclipse.collections.impl.factory.primitive.CharLists;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.eclipse.collections.impl.factory.primitive.FloatLists;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.LongLists;
import org.eclipse.collections.impl.factory.primitive.ShortLists;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.set.mutable.primitive.BooleanHashSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link LazyByteIterableAdapter}.
 */
public class LazyBooleanIterableAdapterTest
{
    private final LazyBooleanIterableAdapter iterable =
            new LazyBooleanIterableAdapter(BooleanArrayList.newListWith(true, false, true));

    @Test
    public void booleanIterator()
    {
        int sum = 0;
        for (BooleanIterator iterator = this.iterable.booleanIterator(); iterator.hasNext(); )
        {
            sum += iterator.next() ? 1 : 0;
        }
        assertEquals(2, sum);
    }

    @Test
    public void forEach()
    {
        int[] sum = new int[1];
        this.iterable.forEach(each -> sum[0] += each ? 1 : 0);
        assertEquals(2, sum[0]);
    }

    @Test
    public void size()
    {
        Verify.assertSize(3, this.iterable);
    }

    @Test
    public void empty()
    {
        assertFalse(this.iterable.isEmpty());
        assertTrue(this.iterable.notEmpty());
        Verify.assertNotEmpty(this.iterable);
    }

    @Test
    public void count()
    {
        assertEquals(2, this.iterable.count(BooleanPredicates.isTrue()));
        assertEquals(1, this.iterable.count(BooleanPredicates.isFalse()));
    }

    @Test
    public void anySatisfy()
    {
        assertTrue(this.iterable.anySatisfy(BooleanPredicates.isTrue()));
        assertTrue(this.iterable.anySatisfy(BooleanPredicates.isFalse()));
    }

    @Test
    public void allSatisfy()
    {
        assertTrue(this.iterable.allSatisfy(value -> true));
        assertFalse(this.iterable.allSatisfy(BooleanPredicates.isFalse()));
        assertFalse(this.iterable.allSatisfy(BooleanPredicates.isTrue()));
    }

    @Test
    public void select()
    {
        Verify.assertSize(2, this.iterable.select(BooleanPredicates.isTrue()));
        Verify.assertSize(1, this.iterable.select(BooleanPredicates.isFalse()));
    }

    @Test
    public void reject()
    {
        Verify.assertSize(1, this.iterable.reject(BooleanPredicates.isTrue()));
        Verify.assertSize(2, this.iterable.reject(BooleanPredicates.isFalse()));
    }

    @Test
    public void detectIfNone()
    {
        assertTrue(this.iterable.detectIfNone(BooleanPredicates.isTrue(), false));
        assertFalse(this.iterable.detectIfNone(BooleanPredicates.isFalse(), true));
        assertFalse(this.iterable.detectIfNone(value -> false, false));
    }

    @Test
    public void collect()
    {
        RichIterable<String> collect = this.iterable.collect(String::valueOf);
        Verify.assertIterableSize(3, collect);
        assertEquals("truefalsetrue", collect.makeString(""));
    }

    @Test
    public void lazyCollectPrimitives()
    {
        assertEquals(BooleanLists.immutable.of(false, true, false), this.iterable.collectBoolean(e -> !e).toList());
        assertEquals(CharLists.immutable.of((char) 1, (char) 0, (char) 1), this.iterable.asLazy().collectChar(e -> e ? (char) 1 : (char) 0).toList());
        assertEquals(ByteLists.immutable.of((byte) 1, (byte) 0, (byte) 1), this.iterable.asLazy().collectByte(e -> e ? (byte) 1 : (byte) 0).toList());
        assertEquals(ShortLists.immutable.of((short) 1, (short) 0, (short) 1), this.iterable.asLazy().collectShort(e -> e ? (short) 1 : (short) 0).toList());
        assertEquals(IntLists.immutable.of(1, 0, 1), this.iterable.asLazy().collectInt(e -> e ? 1 : 0).toList());
        assertEquals(FloatLists.immutable.of(1.0f, 0.0f, 1.0f), this.iterable.asLazy().collectFloat(e -> e ? 1.0f : 0.0f).toList());
        assertEquals(LongLists.immutable.of(1L, 0L, 1L), this.iterable.asLazy().collectLong(e -> e ? 1L : 0L).toList());
        assertEquals(DoubleLists.immutable.of(1.0, 0.0, 1.0), this.iterable.asLazy().collectDouble(e -> e ? 1.0 : 0.0).toList());
    }

    @Test
    public void toArray()
    {
        assertTrue(Arrays.equals(new boolean[]{true, false, true}, this.iterable.toArray()));
    }

    @Test
    public void contains()
    {
        assertTrue(this.iterable.contains(true));
        assertTrue(this.iterable.contains(false));
    }

    @Test
    public void containsAllArray()
    {
        assertTrue(this.iterable.containsAll(true, false));
        assertTrue(this.iterable.containsAll(false, true));
        assertTrue(this.iterable.containsAll());
    }

    @Test
    public void containsAllIterable()
    {
        assertTrue(this.iterable.containsAll(BooleanArrayList.newListWith(true)));
        assertTrue(this.iterable.containsAll(BooleanArrayList.newListWith(false)));
        assertTrue(this.iterable.containsAll(BooleanArrayList.newListWith(true, false)));
        assertTrue(this.iterable.containsAll(BooleanArrayList.newListWith(false, true)));
    }

    @Test
    public void toList()
    {
        assertEquals(BooleanArrayList.newListWith(true, false, true), this.iterable.toList());
    }

    @Test
    public void toSet()
    {
        assertEquals(BooleanHashSet.newSetWith(true, false), this.iterable.toSet());
    }

    @Test
    public void toBag()
    {
        assertEquals(BooleanHashBag.newBagWith(false, true, true), this.iterable.toBag());
    }
}
