/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.map.mutable.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class SynchronizedByteShortMapSerializationTest
{
    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1hcC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRCeXRlU2hvcnRNYXAAAAAAAAAAAQIAAkwABGxvY2t0ABJMamF2YS9sYW5n\n"
                        + "L09iamVjdDtMAANtYXB0AD9Mb3JnL2VjbGlwc2UvY29sbGVjdGlvbnMvYXBpL21hcC9wcmltaXRp\n"
                        + "dmUvTXV0YWJsZUJ5dGVTaG9ydE1hcDt4cHEAfgADc3IAQ29yZy5lY2xpcHNlLmNvbGxlY3Rpb25z\n"
                        + "LmltcGwubWFwLm11dGFibGUucHJpbWl0aXZlLkJ5dGVTaG9ydEhhc2hNYXAAAAAAAAAAAQwAAHhw\n"
                        + "dwQAAAAAeA==",
                new SynchronizedByteShortMap(new ByteShortHashMap()));
    }

    @Test
    public void keySetSerializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAEZvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLnNldC5tdXRhYmxlLnByaW1pdGl2\n"
                        + "ZS5TeW5jaHJvbml6ZWRCeXRlU2V0AAAAAAAAAAECAAB4cgBcb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5jb2xsZWN0aW9uLm11dGFibGUucHJpbWl0aXZlLkFic3RyYWN0U3luY2hyb25pemVk\n"
                        + "Qnl0ZUNvbGxlY3Rpb24AAAAAAAAAAQIAAkwACmNvbGxlY3Rpb250AEhMb3JnL2VjbGlwc2UvY29s\n"
                        + "bGVjdGlvbnMvYXBpL2NvbGxlY3Rpb24vcHJpbWl0aXZlL011dGFibGVCeXRlQ29sbGVjdGlvbjtM\n"
                        + "AARsb2NrdAASTGphdmEvbGFuZy9PYmplY3Q7eHBzcgBTb3JnLmVjbGlwc2UuY29sbGVjdGlvbnMu\n"
                        + "aW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQWJzdHJhY3RNdXRhYmxlQnl0ZUtleVNldCRTZXJS\n"
                        + "ZXAAAAAAAAAAAQwAAHhwdwQAAAAAeHNyAEtvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLm1h\n"
                        + "cC5tdXRhYmxlLnByaW1pdGl2ZS5TeW5jaHJvbml6ZWRCeXRlU2hvcnRNYXAAAAAAAAAAAQIAAkwA\n"
                        + "BGxvY2txAH4AA0wAA21hcHQAP0xvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvbWFwL3ByaW1p\n"
                        + "dGl2ZS9NdXRhYmxlQnl0ZVNob3J0TWFwO3hwcQB+AAlzcgBDb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5tYXAubXV0YWJsZS5wcmltaXRpdmUuQnl0ZVNob3J0SGFzaE1hcAAAAAAAAAABDAAA\n"
                        + "eHB3BAAAAAB4",
                new SynchronizedByteShortMap(new ByteShortHashMap()).keySet());
    }
}
