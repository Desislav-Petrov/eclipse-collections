/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure.checked.primitive;

import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

public class CheckedShortObjectProcedureSerializationTest
{
    private static final CheckedShortObjectProcedure<?> CHECKED_SHORT_OBJECT_PROCEDURE = new CheckedShortObjectProcedure<Object>()
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void safeValue(short value, Object object)
        {
        }
    };

    @Test
    public void serializedForm()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyAG1vcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2NrLnByb2NlZHVyZS5jaGVj\n"
                        + "a2VkLnByaW1pdGl2ZS5DaGVja2VkU2hvcnRPYmplY3RQcm9jZWR1cmVTZXJpYWxpemF0aW9uVGVz\n"
                        + "dCQxAAAAAAAAAAECAAB4cgBab3JnLmVjbGlwc2UuY29sbGVjdGlvbnMuaW1wbC5ibG9jay5wcm9j\n"
                        + "ZWR1cmUuY2hlY2tlZC5wcmltaXRpdmUuQ2hlY2tlZFNob3J0T2JqZWN0UHJvY2VkdXJlAAAAAAAA\n"
                        + "AAECAAB4cA==",
                CHECKED_SHORT_OBJECT_PROCEDURE);
    }
}
