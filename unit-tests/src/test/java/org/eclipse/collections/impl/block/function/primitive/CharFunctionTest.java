/*
 * Copyright (c) 2021 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.function.primitive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit test for {@link CharFunction}.
 *
 * @deprecated in 6.2
 */
@Deprecated
public class CharFunctionTest
{
    @Test
    public void toUppercase()
    {
        assertEquals('A', CharFunction.TO_UPPERCASE.valueOf('a'));
        assertEquals('A', CharFunction.TO_UPPERCASE.valueOf('A'));
        assertEquals('1', CharFunction.TO_UPPERCASE.valueOf('1'));
    }

    @Test
    public void toLowercase()
    {
        assertEquals('a', CharFunction.TO_LOWERCASE.valueOf('a'));
        assertEquals('a', CharFunction.TO_LOWERCASE.valueOf('A'));
        assertEquals('1', CharFunction.TO_LOWERCASE.valueOf('1'));
    }
}
