/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.set.mutable;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetLogicTest
{
    private MutableSet<Integer> setA;
    private MutableSet<Integer> setB;

    @BeforeEach
    public void setUp()
    {
        this.setA = UnifiedSet.newSetWith(1, 2, 3, 4).asUnmodifiable();
        this.setB = UnifiedSet.newSetWith(3, 4, 5, 6).asUnmodifiable();
    }

    @Test
    public void inOnlyInAMutable()
    {
        MutableSet<Integer> onlyInA = this.setA.reject(Predicates.in(this.setB), UnifiedSet.newSet());
        assertEquals(UnifiedSet.newSetWith(1, 2), onlyInA);
    }

    @Test
    public void onlyInAJdkLike()
    {
        MutableSet<Integer> onlyInA = UnifiedSet.newSet(this.setA);
        onlyInA.removeAll(this.setB);
        assertEquals(UnifiedSet.newSetWith(1, 2), onlyInA);
    }

    @Test
    public void inBothAAndBMutable()
    {
        assertEquals(UnifiedSet.newSetWith(3, 4), this.setA.select(Predicates.in(this.setB)));
    }

    @Test
    public void inAOrBButNotInBoth()
    {
        MutableSet<Integer> nonOverlappingSet = UnifiedSet.newSet();
        this.setA.select(Predicates.notIn(this.setB), nonOverlappingSet);
        this.setB.select(Predicates.notIn(this.setA), nonOverlappingSet);
        assertEquals(UnifiedSet.newSetWith(1, 2, 5, 6), nonOverlappingSet);
    }
}
