/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.list.fixed;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.list.mutable.FastListSerializationTest;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class SextupletonListSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmxpc3QuZml4ZWQuU2V4dHVwbGV0\n"
                        + "b25MaXN0AAAAAAAAAAEMAAB4cHBwcHBwcHg=",
                Lists.fixedSize.of(null, null, null, null, null, null));
    }

    @Test
    public void subList()
    {
        Verify.assertSerializedForm(
                1L,
                FastListSerializationTest.FAST_LIST_WITH_ONE_NULL,
                Lists.fixedSize.of(null, null, null, null, null, null).subList(0, 1));
    }
}
