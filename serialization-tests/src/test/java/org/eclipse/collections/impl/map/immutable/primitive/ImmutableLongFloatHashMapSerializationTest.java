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

import org.eclipse.collections.impl.map.mutable.primitive.LongFloatHashMap;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class ImmutableLongFloatHashMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAHZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5pbW11dGFibGUucHJpbWl0\n"
                        + "aXZlLkltbXV0YWJsZUxvbmdGbG9hdEhhc2hNYXAkSW1tdXRhYmxlTG9uZ0Zsb2F0TWFwU2VyaWFs\n"
                        + "aXphdGlvblByb3h5AAAAAAAAAAEMAAB4cHccAAAAAgAAAAAAAAABP4AAAAAAAAAAAAACQAAAAHg=\n",
                new ImmutableLongFloatHashMap(LongFloatHashMap.newWithKeysValues(1L, 1.0f, 2L, 2.0f)));
    }
}
