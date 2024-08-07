/*
 * Copyright (c) 2024 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.collection.primitive.MutableBooleanCollection;
import org.eclipse.collections.api.collection.primitive.MutableByteCollection;
import org.eclipse.collections.api.collection.primitive.MutableCharCollection;
import org.eclipse.collections.api.collection.primitive.MutableDoubleCollection;
import org.eclipse.collections.api.collection.primitive.MutableFloatCollection;
import org.eclipse.collections.api.collection.primitive.MutableIntCollection;
import org.eclipse.collections.api.collection.primitive.MutableLongCollection;
import org.eclipse.collections.api.collection.primitive.MutableShortCollection;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.sorted.MutableSortedMap;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.partition.PartitionIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.api.set.sorted.MutableSortedSet;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.bag.mutable.HashBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.block.factory.Functions;
import org.eclipse.collections.impl.block.factory.IntegerPredicates;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.factory.Predicates2;
import org.eclipse.collections.impl.block.factory.PrimitiveFunctions;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.block.function.NegativeIntervalFunction;
import org.eclipse.collections.impl.block.function.PassThruFunction0;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.list.mutable.primitive.BooleanArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ByteArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.CharArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.FloatArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.IntArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.LongArrayList;
import org.eclipse.collections.impl.list.mutable.primitive.ShortArrayList;
import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.map.sorted.mutable.TreeSortedMap;
import org.eclipse.collections.impl.multimap.list.FastListMultimap;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.sorted.mutable.TreeSortedSet;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

import static org.eclipse.collections.impl.factory.Iterables.iList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractLazyIterableTestCase
{
    private final LazyIterable<Integer> lazyIterable = this.newWith(1, 2, 3, 4, 5, 6, 7);

    protected abstract <T> LazyIterable<T> newWith(T... elements);

    @Test
    public abstract void iterator();

    @Test
    public void toArray()
    {
        assertArrayEquals(
                FastList.newListWith(1, 2).toArray(),
                this.lazyIterable.select(Predicates.lessThan(3)).toArray());
        assertArrayEquals(
                FastList.newListWith(1, 2).toArray(),
                this.lazyIterable.select(Predicates.lessThan(3)).toArray(new Object[2]));
    }

    @Test
    public void contains()
    {
        assertTrue(this.lazyIterable.contains(3));
        assertFalse(this.lazyIterable.contains(8));
    }

    @Test
    public void containsAllIterable()
    {
        assertTrue(this.lazyIterable.containsAllIterable(FastList.newListWith(3)));
        assertFalse(this.lazyIterable.containsAllIterable(FastList.newListWith(8)));
    }

    @Test
    public void containsAllArray()
    {
        assertTrue(this.lazyIterable.containsAllArguments(3));
        assertFalse(this.lazyIterable.containsAllArguments(8));
    }

    @Test
    public void select()
    {
        assertEquals(
                FastList.newListWith(1, 2),
                this.lazyIterable.select(Predicates.lessThan(3)).toList());
    }

    @Test
    public void selectWith()
    {
        assertEquals(
                FastList.newListWith(1, 2),
                this.lazyIterable.selectWith(Predicates2.lessThan(), 3, FastList.newList()));
    }

    @Test
    public void selectWithTarget()
    {
        assertEquals(
                FastList.newListWith(1, 2),
                this.lazyIterable.select(Predicates.lessThan(3), FastList.newList()));
    }

    @Test
    public void reject()
    {
        assertEquals(FastList.newListWith(3, 4, 5, 6, 7), this.lazyIterable.reject(Predicates.lessThan(3)).toList());
    }

    @Test
    public void rejectWith()
    {
        assertEquals(
                FastList.newListWith(3, 4, 5, 6, 7),
                this.lazyIterable.rejectWith(Predicates2.lessThan(), 3, FastList.newList()));
    }

    @Test
    public void rejectWithTarget()
    {
        assertEquals(
                FastList.newListWith(3, 4, 5, 6, 7),
                this.lazyIterable.reject(Predicates.lessThan(3), FastList.newList()));
    }

    @Test
    public void partition()
    {
        PartitionIterable<Integer> partition = this.lazyIterable.partition(IntegerPredicates.isEven());
        assertEquals(iList(2, 4, 6), partition.getSelected());
        assertEquals(iList(1, 3, 5, 7), partition.getRejected());
    }

    @Test
    public void partitionWith()
    {
        PartitionIterable<Integer> partition = this.lazyIterable.partitionWith(Predicates2.in(), this.lazyIterable.select(IntegerPredicates.isEven()));
        assertEquals(iList(2, 4, 6), partition.getSelected());
        assertEquals(iList(1, 3, 5, 7), partition.getRejected());
    }

    @Test
    public void selectInstancesOf()
    {
        assertEquals(
                FastList.newListWith(1, 3, 5),
                this.newWith(1, 2.0, 3, 4.0, 5).selectInstancesOf(Integer.class).toList());
    }

    @Test
    public void collect()
    {
        assertEquals(
                FastList.newListWith("1", "2", "3", "4", "5", "6", "7"),
                this.lazyIterable.collect(String::valueOf).toList());
    }

    @Test
    public void collectBoolean()
    {
        assertEquals(
                BooleanArrayList.newListWith(true, true, true, true, true, true, true),
                this.lazyIterable.collectBoolean(PrimitiveFunctions.integerIsPositive()).toList());
    }

    @Test
    public void collectBooleanWithTarget()
    {
        MutableBooleanCollection target = new BooleanArrayList();
        MutableBooleanCollection result = this.lazyIterable.collectBoolean(PrimitiveFunctions.integerIsPositive(), target);
        assertEquals(
                BooleanArrayList.newListWith(true, true, true, true, true, true, true),
                result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectByte()
    {
        assertEquals(ByteArrayList.newListWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7), this.lazyIterable.collectByte(PrimitiveFunctions.unboxIntegerToByte()).toList());
    }

    @Test
    public void collectByteWithTarget()
    {
        MutableByteCollection target = new ByteArrayList();
        MutableByteCollection result = this.lazyIterable.collectByte(PrimitiveFunctions.unboxIntegerToByte(), target);
        assertEquals(ByteArrayList.newListWith((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectChar()
    {
        assertEquals(
                CharArrayList.newListWith((char) 1, (char) 2, (char) 3, (char) 4, (char) 5, (char) 6, (char) 7),
                this.lazyIterable.collectChar(PrimitiveFunctions.unboxIntegerToChar()).toList());
    }

    @Test
    public void collectCharWithTarget()
    {
        MutableCharCollection target = new CharArrayList();
        MutableCharCollection result = this.lazyIterable.collectChar(PrimitiveFunctions.unboxIntegerToChar(), target);
        assertEquals(CharArrayList.newListWith((char) 1, (char) 2, (char) 3, (char) 4, (char) 5, (char) 6, (char) 7), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectDouble()
    {
        assertEquals(DoubleArrayList.newListWith(1.0d, 2.0d, 3.0d, 4.0d, 5.0d, 6.0d, 7.0d), this.lazyIterable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble()).toList());
    }

    @Test
    public void collectDoubleWithTarget()
    {
        MutableDoubleCollection target = new DoubleArrayList();
        MutableDoubleCollection result = this.lazyIterable.collectDouble(PrimitiveFunctions.unboxIntegerToDouble(), target);
        assertEquals(
                DoubleArrayList.newListWith(1.0d, 2.0d, 3.0d, 4.0d, 5.0d, 6.0d, 7.0d), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectFloat()
    {
        assertEquals(
                FloatArrayList.newListWith(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f),
                this.lazyIterable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat()).toList());
    }

    @Test
    public void collectFloatWithTarget()
    {
        MutableFloatCollection target = new FloatArrayList();
        MutableFloatCollection result = this.lazyIterable.collectFloat(PrimitiveFunctions.unboxIntegerToFloat(), target);
        assertEquals(
                FloatArrayList.newListWith(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectInt()
    {
        assertEquals(IntArrayList.newListWith(1, 2, 3, 4, 5, 6, 7), this.lazyIterable.collectInt(PrimitiveFunctions.unboxIntegerToInt()).toList());
    }

    @Test
    public void collectIntWithTarget()
    {
        MutableIntCollection target = new IntArrayList();
        MutableIntCollection result = this.lazyIterable.collectInt(PrimitiveFunctions.unboxIntegerToInt(), target);
        assertEquals(IntArrayList.newListWith(1, 2, 3, 4, 5, 6, 7), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectLong()
    {
        assertEquals(
                LongArrayList.newListWith(1L, 2L, 3L, 4L, 5L, 6L, 7L),
                this.lazyIterable.collectLong(PrimitiveFunctions.unboxIntegerToLong()).toList());
    }

    @Test
    public void collectLongWithTarget()
    {
        MutableLongCollection target = new LongArrayList();
        MutableLongCollection result = this.lazyIterable.collectLong(PrimitiveFunctions.unboxIntegerToLong(), target);
        assertEquals(LongArrayList.newListWith(1L, 2L, 3L, 4L, 5L, 6L, 7L), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectShort()
    {
        assertEquals(
                ShortArrayList.newListWith((short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7),
                this.lazyIterable.collectShort(PrimitiveFunctions.unboxIntegerToShort()).toList());
    }

    @Test
    public void collectShortWithTarget()
    {
        MutableShortCollection target = new ShortArrayList();
        MutableShortCollection result = this.lazyIterable.collectShort(PrimitiveFunctions.unboxIntegerToShort(), target);
        assertEquals(ShortArrayList.newListWith((short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6, (short) 7), result.toList());
        assertSame(target, result, "Target list sent as parameter not returned");
    }

    @Test
    public void collectWith()
    {
        assertEquals(
                FastList.newListWith("1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 "),
                this.lazyIterable.collectWith((argument1, argument2) -> argument1 + argument2, " ", FastList.newList()));
    }

    @Test
    public void collectWithTarget()
    {
        assertEquals(
                FastList.newListWith("1", "2", "3", "4", "5", "6", "7"),
                this.lazyIterable.collect(String::valueOf, FastList.newList()));
    }

    @Test
    public void take()
    {
        LazyIterable<Integer> lazyIterable = this.lazyIterable;
        assertEquals(FastList.newList(), lazyIterable.take(0).toList());
        assertEquals(FastList.newListWith(1), lazyIterable.take(1).toList());
        assertEquals(FastList.newListWith(1, 2), lazyIterable.take(2).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), lazyIterable.take(lazyIterable.size() - 1).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.take(lazyIterable.size()).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.take(10).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.take(Integer.MAX_VALUE).toList());
    }

    @Test
    public void take_negative_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> this.lazyIterable.take(-1));
    }

    @Test
    public void drop()
    {
        LazyIterable<Integer> lazyIterable = this.lazyIterable;
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.drop(0).toList());
        assertEquals(FastList.newListWith(3, 4, 5, 6, 7), lazyIterable.drop(2).toList());
        assertEquals(FastList.newListWith(7), lazyIterable.drop(lazyIterable.size() - 1).toList());
        assertEquals(FastList.newList(), lazyIterable.drop(lazyIterable.size()).toList());
        assertEquals(FastList.newList(), lazyIterable.drop(10).toList());
        assertEquals(FastList.newList(), lazyIterable.drop(Integer.MAX_VALUE).toList());
    }

    @Test
    public void drop_negative_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> this.lazyIterable.drop(-1));
    }

    @Test
    public void takeWhile()
    {
        LazyIterable<Integer> lazyIterable = this.lazyIterable;
        assertEquals(FastList.newList(), lazyIterable.takeWhile(Predicates.alwaysFalse()).toList());
        assertEquals(FastList.newListWith(1), lazyIterable.takeWhile(each -> each <= 1).toList());
        assertEquals(FastList.newListWith(1, 2), lazyIterable.takeWhile(each -> each <= 2).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6), lazyIterable.takeWhile(each -> each <= lazyIterable.size() - 1).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.takeWhile(each -> each <= lazyIterable.size()).toList());
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.takeWhile(Predicates.alwaysTrue()).toList());
    }

    @Test
    public void takeWhile_null_throws()
    {
        assertThrows(IllegalStateException.class, () -> this.lazyIterable.takeWhile(null));
    }

    @Test
    public void dropWhile()
    {
        LazyIterable<Integer> lazyIterable = this.lazyIterable;
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5, 6, 7), lazyIterable.dropWhile(Predicates.alwaysFalse()).toList());
        assertEquals(FastList.newListWith(3, 4, 5, 6, 7), lazyIterable.dropWhile(each -> each <= 2).toList());
        assertEquals(FastList.newListWith(7), lazyIterable.dropWhile(each -> each <= lazyIterable.size() - 1).toList());
        assertEquals(FastList.newList(), lazyIterable.dropWhile(each -> each <= lazyIterable.size()).toList());
        assertEquals(FastList.newList(), lazyIterable.dropWhile(Predicates.alwaysTrue()).toList());
    }

    @Test
    public void dropWhile_null_throws()
    {
        assertThrows(IllegalStateException.class, () -> this.lazyIterable.dropWhile(null));
    }

    @Test
    public void detect()
    {
        assertEquals(Integer.valueOf(3), this.lazyIterable.detect(Integer.valueOf(3)::equals));
        assertNull(this.lazyIterable.detect(Integer.valueOf(8)::equals));
    }

    @Test
    public void detectWith()
    {
        assertEquals(Integer.valueOf(3), this.lazyIterable.detectWith(Object::equals, Integer.valueOf(3)));
        assertNull(this.lazyIterable.detectWith(Object::equals, Integer.valueOf(8)));
    }

    @Test
    public void detectOptional()
    {
        assertEquals(Optional.of(Integer.valueOf(3)), this.lazyIterable.detectOptional(Integer.valueOf(3)::equals));
        assertEquals(Optional.empty(), this.lazyIterable.detectOptional(Integer.valueOf(8)::equals));
    }

    @Test
    public void detectWithOptional()
    {
        assertEquals(Optional.of(Integer.valueOf(3)), this.lazyIterable.detectWithOptional(Object::equals, Integer.valueOf(3)));
        assertEquals(Optional.empty(), this.lazyIterable.detectWithOptional(Object::equals, Integer.valueOf(8)));
    }

    @Test
    public void detectWithIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(Integer.valueOf(1000));
        assertEquals(Integer.valueOf(3), this.lazyIterable.detectWithIfNone(Object::equals, Integer.valueOf(3), function));
        assertEquals(Integer.valueOf(1000), this.lazyIterable.detectWithIfNone(Object::equals, Integer.valueOf(8), function));
    }

    @Test
    public void min_empty_throws()
    {
        assertThrows(NoSuchElementException.class, () -> this.<Integer>newWith().min(Integer::compareTo));
    }

    @Test
    public void max_empty_throws()
    {
        assertThrows(NoSuchElementException.class, () -> this.<Integer>newWith().max(Integer::compareTo));
    }

    @Test
    public void min_null_throws()
    {
        assertThrows(NullPointerException.class, () -> this.newWith(1, null, 2).min(Integer::compareTo));
    }

    @Test
    public void max_null_throws()
    {
        assertThrows(NullPointerException.class, () -> this.newWith(1, null, 2).max(Integer::compareTo));
    }

    @Test
    public void min()
    {
        assertEquals(Integer.valueOf(1), this.newWith(1, 3, 2).min(Integer::compareTo));
    }

    @Test
    public void max()
    {
        assertEquals(Integer.valueOf(3), this.newWith(1, 3, 2).max(Integer::compareTo));
    }

    @Test
    public void minBy()
    {
        assertEquals(Integer.valueOf(1), this.newWith(1, 3, 2).minBy(String::valueOf));
    }

    @Test
    public void maxBy()
    {
        assertEquals(Integer.valueOf(3), this.newWith(1, 3, 2).maxBy(String::valueOf));
    }

    @Test
    public void min_empty_throws_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().min());
    }

    @Test
    public void max_empty_throws_without_comparator()
    {
        assertThrows(NoSuchElementException.class, () -> this.newWith().max());
    }

    @Test
    public void min_null_throws_without_comparator()
    {
        assertThrows(NullPointerException.class, () -> this.newWith(1, null, 2).min());
    }

    @Test
    public void max_null_throws_without_comparator()
    {
        assertThrows(NullPointerException.class, () -> this.newWith(1, null, 2).max());
    }

    @Test
    public void min_without_comparator()
    {
        assertEquals(Integer.valueOf(1), this.newWith(3, 1, 2).min());
    }

    @Test
    public void max_without_comparator()
    {
        assertEquals(Integer.valueOf(3), this.newWith(1, 3, 2).max());
    }

    @Test
    public void detectIfNone()
    {
        Function0<Integer> function = new PassThruFunction0<>(9);
        assertEquals(Integer.valueOf(3), this.lazyIterable.detectIfNone(Integer.valueOf(3)::equals, function));
        assertEquals(Integer.valueOf(9), this.lazyIterable.detectIfNone(Integer.valueOf(8)::equals, function));
    }

    @Test
    public void anySatisfy()
    {
        assertFalse(this.lazyIterable.anySatisfy(String.class::isInstance));
        assertTrue(this.lazyIterable.anySatisfy(Integer.class::isInstance));
    }

    @Test
    public void anySatisfyWith()
    {
        assertFalse(this.lazyIterable.anySatisfyWith(Predicates2.instanceOf(), String.class));
        assertTrue(this.lazyIterable.anySatisfyWith(Predicates2.instanceOf(), Integer.class));
    }

    @Test
    public void allSatisfy()
    {
        assertTrue(this.lazyIterable.allSatisfy(Integer.class::isInstance));
        assertFalse(this.lazyIterable.allSatisfy(Integer.valueOf(1)::equals));
    }

    @Test
    public void allSatisfyWith()
    {
        assertTrue(this.lazyIterable.allSatisfyWith(Predicates2.instanceOf(), Integer.class));
        assertFalse(this.lazyIterable.allSatisfyWith(Object::equals, 1));
    }

    @Test
    public void noneSatisfy()
    {
        assertFalse(this.lazyIterable.noneSatisfy(Integer.class::isInstance));
        assertTrue(this.lazyIterable.noneSatisfy(String.class::isInstance));
    }

    @Test
    public void noneSatisfyWith()
    {
        assertFalse(this.lazyIterable.noneSatisfyWith(Predicates2.instanceOf(), Integer.class));
        assertTrue(this.lazyIterable.noneSatisfyWith(Predicates2.instanceOf(), String.class));
    }

    @Test
    public void count()
    {
        assertEquals(7, this.lazyIterable.count(Integer.class::isInstance));
    }

    @Test
    public void collectIf()
    {
        assertEquals(
                FastList.newListWith("1", "2", "3"),
                this.newWith(1, 2, 3).collectIf(
                        Integer.class::isInstance,
                        String::valueOf).toList());
    }

    @Test
    public void collectIfWithTarget()
    {
        assertEquals(
                FastList.newListWith("1", "2", "3"),
                this.newWith(1, 2, 3).collectIf(
                        Integer.class::isInstance,
                        String::valueOf,
                        FastList.newList()));
    }

    @Test
    public void getFirst()
    {
        assertEquals(Integer.valueOf(1), this.newWith(1, 2, 3).getFirst());
        assertNotEquals(Integer.valueOf(3), this.newWith(1, 2, 3).getFirst());
    }

    @Test
    public void getLast()
    {
        assertNotEquals(Integer.valueOf(1), this.newWith(1, 2, 3).getLast());
        assertEquals(Integer.valueOf(3), this.newWith(1, 2, 3).getLast());
    }

    @Test
    public void getOnly()
    {
        assertEquals(Integer.valueOf(1), this.newWith(1).getOnly());
    }

    @Test
    public void getOnly_throws_when_empty()
    {
        assertThrows(IllegalStateException.class, () -> this.newWith().getOnly());
    }

    @Test
    public void getOnly_throws_when_multiple_values()
    {
        assertThrows(IllegalStateException.class, () -> this.newWith(1, 2, 3).getOnly());
    }

    @Test
    public void isEmpty()
    {
        assertTrue(this.newWith().isEmpty());
        assertTrue(this.newWith(1, 2).notEmpty());
    }

    @Test
    public void injectInto()
    {
        RichIterable<Integer> objects = this.newWith(1, 2, 3);
        Integer result = objects.injectInto(1, AddFunction.INTEGER);
        assertEquals(Integer.valueOf(7), result);
    }

    @Test
    public void toList()
    {
        MutableList<Integer> list = this.newWith(1, 2, 3, 4).toList();
        assertEquals(FastList.newListWith(1, 2, 3, 4), list);
    }

    @Test
    public void toSortedListNaturalOrdering()
    {
        RichIterable<Integer> integers = this.newWith(2, 1, 5, 3, 4);
        MutableList<Integer> list = integers.toSortedList();
        assertEquals(FastList.newListWith(1, 2, 3, 4, 5), list);
    }

    @Test
    public void toSortedList()
    {
        RichIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        MutableList<Integer> list = integers.toSortedList(Collections.reverseOrder());
        assertEquals(FastList.newListWith(4, 3, 2, 1), list);
    }

    @Test
    public void toSortedListBy()
    {
        LazyIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        MutableList<Integer> list = integers.toSortedListBy(String::valueOf);
        assertEquals(FastList.newListWith(1, 2, 3, 4), list);
    }

    @Test
    public void toSortedSet()
    {
        LazyIterable<Integer> integers = this.newWith(2, 4, 1, 3, 2, 1, 3, 4);
        MutableSortedSet<Integer> set = integers.toSortedSet();
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(1, 2, 3, 4), set);
    }

    @Test
    public void toSortedSet_with_comparator()
    {
        LazyIterable<Integer> integers = this.newWith(2, 4, 4, 2, 1, 4, 1, 3);
        MutableSortedSet<Integer> set = integers.toSortedSet(Collections.reverseOrder());
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(Collections.reverseOrder(), 1, 2, 3, 4), set);
    }

    @Test
    public void toSortedSetBy()
    {
        LazyIterable<Integer> integers = this.newWith(2, 4, 1, 3);
        MutableSortedSet<Integer> set = integers.toSortedSetBy(String::valueOf);
        Verify.assertSortedSetsEqual(TreeSortedSet.newSetWith(1, 2, 3, 4), set);
    }

    @Test
    public void toSet()
    {
        RichIterable<Integer> integers = this.newWith(1, 2, 3, 4);
        MutableSet<Integer> set = integers.toSet();
        assertEquals(UnifiedSet.newSetWith(1, 2, 3, 4), set);
    }

    @Test
    public void toMap()
    {
        RichIterable<Integer> integers = this.newWith(1, 2, 3, 4);
        MutableMap<String, String> map =
                integers.toMap(String::valueOf, String::valueOf);
        assertEquals(UnifiedMap.newWithKeysValues("1", "1", "2", "2", "3", "3", "4", "4"), map);
    }

    @Test
    public void toSortedMap()
    {
        LazyIterable<Integer> integers = this.newWith(1, 2, 3);
        MutableSortedMap<Integer, String> map = integers.toSortedMap(Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(TreeSortedMap.newMapWith(1, "1", 2, "2", 3, "3"), map);
        Verify.assertListsEqual(FastList.newListWith(1, 2, 3), map.keySet().toList());
    }

    @Test
    public void toSortedMap_with_comparator()
    {
        LazyIterable<Integer> integers = this.newWith(1, 2, 3);
        MutableSortedMap<Integer, String> map = integers.toSortedMap(Comparators.reverseNaturalOrder(),
                Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(TreeSortedMap.newMapWith(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3"), map);
        Verify.assertListsEqual(FastList.newListWith(3, 2, 1), map.keySet().toList());
    }

    @Test
    public void toSortedMapBy()
    {
        LazyIterable<Integer> integers = this.newWith(1, 2, 3);
        MutableSortedMap<Integer, String> map = integers.toSortedMapBy(key -> -key,
                Functions.getIntegerPassThru(), String::valueOf);
        Verify.assertMapsEqual(TreeSortedMap.newMapWith(Comparators.reverseNaturalOrder(), 1, "1", 2, "2", 3, "3"), map);
        Verify.assertListsEqual(FastList.newListWith(3, 2, 1), map.keySet().toList());
    }

    @Test
    public void testToString()
    {
        assertEquals("[1, 2, 3]", this.newWith(1, 2, 3).toString());
    }

    @Test
    public void makeString()
    {
        assertEquals("[1, 2, 3]", '[' + this.newWith(1, 2, 3).makeString() + ']');
    }

    @Test
    public void appendString()
    {
        Appendable builder = new StringBuilder();
        this.newWith(1, 2, 3).appendString(builder);
        assertEquals("1, 2, 3", builder.toString());
    }

    @Test
    public void groupBy()
    {
        Function<Integer, Boolean> isOddFunction = object -> IntegerPredicates.isOdd().accept(object);

        MutableMap<Boolean, RichIterable<Integer>> expected =
                UnifiedMap.newWithKeysValues(
                        Boolean.TRUE, FastList.newListWith(1, 3, 5, 7),
                        Boolean.FALSE, FastList.newListWith(2, 4, 6));

        Multimap<Boolean, Integer> multimap =
                this.lazyIterable.groupBy(isOddFunction);
        assertEquals(expected, multimap.toMap());

        Multimap<Boolean, Integer> multimap2 =
                this.lazyIterable.groupBy(isOddFunction, FastListMultimap.newMultimap());
        assertEquals(expected, multimap2.toMap());
    }

    @Test
    public void groupByEach()
    {
        MutableMultimap<Integer, Integer> expected = FastListMultimap.newMultimap();
        for (int i = 1; i < 8; i++)
        {
            expected.putAll(-i, Interval.fromTo(i, 7));
        }

        Multimap<Integer, Integer> actual =
                this.lazyIterable.groupByEach(new NegativeIntervalFunction());
        assertEquals(expected, actual);

        Multimap<Integer, Integer> actualWithTarget =
                this.lazyIterable.groupByEach(new NegativeIntervalFunction(), FastListMultimap.newMultimap());
        assertEquals(expected, actualWithTarget);
    }

    @Test
    public void groupByUniqueKey()
    {
        assertEquals(UnifiedMap.newWithKeysValues(1, 1, 2, 2, 3, 3), this.newWith(1, 2, 3).groupByUniqueKey(id -> id));
    }

    @Test
    public void groupByUniqueKey_throws()
    {
        assertThrows(IllegalStateException.class, () -> this.newWith(1, 2, 3).groupByUniqueKey(Functions.getFixedValue(1)));
    }

    @Test
    public void groupByUniqueKey_target()
    {
        MutableMap<Integer, Integer> integers = this.newWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(0, 0));
        assertEquals(UnifiedMap.newWithKeysValues(0, 0, 1, 1, 2, 2, 3, 3), integers);
    }

    @Test
    public void groupByUniqueKey_target_throws()
    {
        assertThrows(IllegalStateException.class, () -> this.newWith(1, 2, 3).groupByUniqueKey(id -> id, UnifiedMap.newWithKeysValues(2, 2)));
    }

    @Test
    public void zip()
    {
        List<Object> nulls = Collections.nCopies(this.lazyIterable.size(), null);
        List<Object> nullsPlusOne = Collections.nCopies(this.lazyIterable.size() + 1, null);
        List<Object> nullsMinusOne = Collections.nCopies(this.lazyIterable.size() - 1, null);

        LazyIterable<Pair<Integer, Object>> pairs = this.lazyIterable.zip(nulls);
        assertEquals(
                this.lazyIterable.toSet(),
                pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).toSet());
        assertEquals(
                nulls,
                pairs.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        LazyIterable<Pair<Integer, Object>> pairsPlusOne = this.lazyIterable.zip(nullsPlusOne);
        assertEquals(
                this.lazyIterable.toSet(),
                pairsPlusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).toSet());
        assertEquals(nulls, pairsPlusOne.collect((Function<Pair<?, Object>, Object>) Pair::getTwo, Lists.mutable.of()));

        LazyIterable<Pair<Integer, Object>> pairsMinusOne = this.lazyIterable.zip(nullsMinusOne);
        assertEquals(this.lazyIterable.size() - 1, pairsMinusOne.size());
        assertTrue(this.lazyIterable.containsAllIterable(pairsMinusOne.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne)));

        assertEquals(
                this.lazyIterable.zip(nulls).toSet(),
                this.lazyIterable.zip(nulls, UnifiedSet.newSet()));
    }

    @Test
    public void zipWithIndex()
    {
        LazyIterable<Pair<Integer, Integer>> pairs = this.lazyIterable.zipWithIndex();

        assertEquals(
                this.lazyIterable.toSet(),
                pairs.collect((Function<Pair<Integer, ?>, Integer>) Pair::getOne).toSet());
        assertEquals(
                Interval.zeroTo(this.lazyIterable.size() - 1).toSet(),
                pairs.collect((Function<Pair<?, Integer>, Integer>) Pair::getTwo, UnifiedSet.newSet()));

        assertEquals(
                this.lazyIterable.zipWithIndex().toSet(),
                this.lazyIterable.zipWithIndex(UnifiedSet.newSet()));
    }

    @Test
    public void chunk()
    {
        LazyIterable<RichIterable<Integer>> groups = this.lazyIterable.chunk(2);
        RichIterable<Integer> sizes = groups.collect(RichIterable::size);
        assertEquals(Bags.mutable.of(2, 2, 2, 1), sizes.toBag());
    }

    @Test
    public void chunk_zero_throws()
    {
        assertThrows(IllegalArgumentException.class, () -> this.lazyIterable.chunk(0));
    }

    @Test
    public void chunk_large_size()
    {
        assertEquals(this.lazyIterable.toBag(), this.lazyIterable.chunk(10).getFirst().toBag());
    }

    @Test
    public void tap()
    {
        StringBuilder tapStringBuilder = new StringBuilder();
        Procedure<Integer> appendProcedure = Procedures.append(tapStringBuilder);
        LazyIterable<Integer> list = this.lazyIterable.tap(appendProcedure);

        Verify.assertIterablesEqual(this.lazyIterable, list);
        assertEquals("1234567", tapStringBuilder.toString());
    }

    @Test
    public void asLazy()
    {
        assertSame(this.lazyIterable, this.lazyIterable.asLazy());
    }

    @Test
    public void flatCollect()
    {
        LazyIterable<Integer> collection = this.newWith(1, 2, 3, 4);
        Function<Integer, MutableList<String>> function = object -> FastList.newListWith(String.valueOf(object));

        Verify.assertListsEqual(
                FastList.newListWith("1", "2", "3", "4"),
                collection.flatCollect(function).toSortedList());

        Verify.assertSetsEqual(
                UnifiedSet.newSetWith("1", "2", "3", "4"),
                collection.flatCollect(function, UnifiedSet.newSet()));
    }

    @Test
    public void flatCollectWith()
    {
        LazyIterable<Integer> collection = this.newWith(4, 5, 6, 7);

        Verify.assertSetsEqual(
                Sets.mutable.with(1, 2, 3, 4, 5, 6, 7),
                collection.flatCollectWith(Interval::fromTo, 1).toSet());

        Verify.assertBagsEqual(
                Bags.mutable.with(4, 3, 2, 1, 5, 4, 3, 2, 1, 6, 5, 4, 3, 2, 1, 7, 6, 5, 4, 3, 2, 1),
                collection.flatCollectWith(Interval::fromTo, 1, Bags.mutable.empty()));
    }

    @Test
    public void distinct()
    {
        LazyIterable<Integer> integers = this.newWith(3, 2, 2, 4, 1, 3, 1, 5);
        assertEquals(
                HashBag.newBagWith(1, 2, 3, 4, 5),
                integers.distinct().toBag());
    }
}
