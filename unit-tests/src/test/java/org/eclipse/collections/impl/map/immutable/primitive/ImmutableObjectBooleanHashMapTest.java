/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.immutable.primitive;

import org.eclipse.collections.api.map.primitive.ImmutableObjectBooleanMap;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.eclipse.collections.impl.map.mutable.primitive.ObjectBooleanHashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * JUnit test for {@link ImmutableObjectBooleanHashMap}.
 */
public class ImmutableObjectBooleanHashMapTest extends AbstractImmutableObjectBooleanMapTestCase
{
    @Override
    protected ImmutableObjectBooleanMap<String> classUnderTest()
    {
        return ObjectBooleanHashMap.newWithKeysValues("0", true, "1", true, "2", false).toImmutable();
    }

    @Test
    public void newWithKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected = ObjectBooleanHashMap.newWithKeysValues("0", true, "1", true, "2", false, "3", false).toImmutable();
        assertEquals(expected, map1.newWithKeyValue("3", false));
        assertNotSame(map1, map1.newWithKeyValue("3", false));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutKeyValue()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected = this.newWithKeysValues("0", true, "1", true);
        assertEquals(expected, map1.newWithoutKey("2"));
        assertNotSame(map1, map1.newWithoutKey("2"));
        assertEquals(this.classUnderTest(), map1);
    }

    @Test
    public void newWithoutAllKeys()
    {
        ImmutableObjectBooleanMap<String> map1 = this.classUnderTest();
        ImmutableObjectBooleanMap<String> expected = this.newWithKeysValues("1", true);
        assertEquals(expected, map1.newWithoutAllKeys(FastList.newListWith("0", "2")));
        assertNotSame(map1, map1.newWithoutAllKeys(FastList.newListWith("0", "2")));
        assertEquals(this.classUnderTest(), map1);
    }
}
