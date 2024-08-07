/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.bag.sorted.mutable;

import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.impl.test.Verify;
import org.junit.jupiter.api.Test;

/**
 * @since 4.2
 */
public class TreeBagSerializationTest
{
    @Test
    public void serializedForm_comparator()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQubXV0YWJsZS5U\n"
                        + "cmVlQmFnAAAAAAAAAAEMAAB4cHNyAFNvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxvY2su\n"
                        + "ZmFjdG9yeS5TZXJpYWxpemFibGVDb21wYXJhdG9ycyRSZXZlcnNlQ29tcGFyYXRvcgAAAAAAAAAB\n"
                        + "AgABTAAKY29tcGFyYXRvcnQAOkxvcmcvZWNsaXBzZS9jb2xsZWN0aW9ucy9hcGkvYmxvY2svU2Vy\n"
                        + "aWFsaXphYmxlQ29tcGFyYXRvcjt4cHNyAFhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5hcGkuYmxv\n"
                        + "Y2suZmFjdG9yeS5TZXJpYWxpemFibGVDb21wYXJhdG9ycyROYXR1cmFsT3JkZXJDb21wYXJhdG9y\n"
                        + "AAAAAAAAAAECAAB4cHcEAAAAAHg=",
                TreeBag.newBag(Comparators.reverseNaturalOrder()));
    }

    @Test
    public void serializedForm_comparator_old()
    {
        Verify.assertSerializedForm(
                1L,
                "rO0ABXNyADdvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJhZy5zb3J0ZWQubXV0YWJsZS5U\n"
                        + "cmVlQmFnAAAAAAAAAAEMAAB4cHNyAEhvcmcuZWNsaXBzZS5jb2xsZWN0aW9ucy5pbXBsLmJsb2Nr\n"
                        + "LmZhY3RvcnkuQ29tcGFyYXRvcnMkUmV2ZXJzZUNvbXBhcmF0b3IAAAAAAAAAAQIAAUwACmNvbXBh\n"
                        + "cmF0b3J0ABZMamF2YS91dGlsL0NvbXBhcmF0b3I7eHBzcgBNb3JnLmVjbGlwc2UuY29sbGVjdGlv\n"
                        + "bnMuaW1wbC5ibG9jay5mYWN0b3J5LkNvbXBhcmF0b3JzJE5hdHVyYWxPcmRlckNvbXBhcmF0b3IA\n"
                        + "AAAAAAAAAQIAAHhwdwQAAAAAeA==",
                TreeBag.newBag(Comparators.originalReverseNaturalOrder()));
    }
}
