/*
 * Copyright (c) 2020, Red Hat Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.jol;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jol.info.GraphLayout;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class GraphInstanceBench {

    @Param({"1", "100", "10000"})
    int size;

    private Integer[] arr;

    @Setup
    public void setup() {
        arr = new Integer[size];
        for (int c = 0; c < size; c++) {
            arr[c] = new Integer(c);
        }
    }

    @Benchmark
    public GraphLayout onlyCollect() {
        return GraphLayout.parseInstance((Object) arr);
    }

    @Benchmark
    public String toFootprint() {
        return GraphLayout.parseInstance((Object) arr).toFootprint();
    }

    @Benchmark
    public String toPrintable() {
        return GraphLayout.parseInstance((Object) arr).toPrintable();
    }

    @Benchmark
    public long totalCount() {
        return GraphLayout.parseInstance((Object) arr).totalCount();
    }

    @Benchmark
    public long totalSize() {
        return GraphLayout.parseInstance((Object) arr).totalSize();
    }

}
