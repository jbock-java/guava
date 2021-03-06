/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.testing.SerializableTester;

import java.util.Collection;
import java.util.Set;

import static com.google.common.testing.SerializableTester.reserialize;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Variant of {@link SerializableTester} that does not require the reserialized object's class to be
 * identical to the original.
 *
 * @author Chris Povirk
 */
/*
 * The whole thing is really @GwtIncompatible, but GwtJUnitConvertedTestModule doesn't have a
 * parameter for non-GWT, non-test files, and it didn't seem worth adding one for this unusual case.
 */
@GwtCompatible(emulated = true)
final class LenientSerializableTester {
    /*
     * TODO(cpovirk): move this to c.g.c.testing if we allow for c.g.c.annotations dependencies so
     * that it can be GWTified?
     */
    @GwtIncompatible // SerializableTester
    static <E> Set<E> reserializeAndAssertLenient(Set<E> original) {
        Set<E> copy = reserialize(original);
        assertEquals(original, copy);
        assertTrue(copy instanceof ImmutableSet);
        return copy;
    }

    @GwtIncompatible // SerializableTester
    static <E> Multiset<E> reserializeAndAssertLenient(Multiset<E> original) {
        Multiset<E> copy = reserialize(original);
        assertEquals(original, copy);
        assertTrue(copy instanceof ImmutableMultiset);
        return copy;
    }

    @GwtIncompatible // SerializableTester
    static <E> Collection<E> reserializeAndAssertElementsEqual(Collection<E> original) {
        Collection<E> copy = reserialize(original);
        assertTrue(Iterables.elementsEqual(original, copy));
        assertTrue(copy instanceof ImmutableCollection);
        return copy;
    }

    private LenientSerializableTester() {
    }
}
