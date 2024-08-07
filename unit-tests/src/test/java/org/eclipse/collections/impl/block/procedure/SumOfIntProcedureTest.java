/*
 * Copyright (c) 2022 The Bank of New York Mellon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.block.procedure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfIntProcedureTest
{
    @Test
    public void getResult()
    {
        SumOfIntProcedure<Integer> procedure = new SumOfIntProcedure<>(Integer::intValue);
        procedure.value(1);
        assertEquals(1, procedure.getResult());

        procedure.value(2);
        assertEquals(3, procedure.getResult());

        procedure.value(3);
        assertEquals(6, procedure.getResult());
    }
}
