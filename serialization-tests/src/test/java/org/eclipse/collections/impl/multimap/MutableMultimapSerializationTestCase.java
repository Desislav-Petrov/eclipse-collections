/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.multimap;

import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.junit.jupiter.api.BeforeEach;

public abstract class MutableMultimapSerializationTestCase
        extends MultimapSerializationTestCase
{
    private MutableMultimap<String, String> mutableMultimap;

    @BeforeEach
    public void buildUnderTest()
    {
        this.mutableMultimap = this.createEmpty();
        this.mutableMultimap.put("A", "A");
        this.mutableMultimap.put("A", "B");
        this.mutableMultimap.put("A", "B");
        this.mutableMultimap.put("B", "A");
    }

    @Override
    protected Multimap<String, String> getMultimapUnderTest()
    {
        return this.mutableMultimap;
    }
}
