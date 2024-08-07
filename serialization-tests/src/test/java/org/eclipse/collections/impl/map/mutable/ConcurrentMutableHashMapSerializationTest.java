/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class ConcurrentMutableHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEFvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLkNvbmN1cnJl\n"
                        + "bnRNdXRhYmxlSGFzaE1hcAAAAAAAAAABAgABTAAIZGVsZWdhdGV0ACRMamF2YS91dGlsL2NvbmN1\n"
                        + "cnJlbnQvQ29uY3VycmVudE1hcDt4cHA=",
                new ConcurrentMutableHashMap<>(null));
    }
}
