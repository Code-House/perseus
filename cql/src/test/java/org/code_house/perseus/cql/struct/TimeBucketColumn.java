/*
 * (C) Copyright 2016 Code-House and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.code_house.perseus.cql.struct;

import com.datastax.driver.core.Row;
import org.code_house.perseus.Column;
import org.code_house.perseus.cql.column.*;
import org.code_house.perseus.dsl.Insertion;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class TimeBucketColumn extends CompoundCassandraColumn<Date, Tweet> {

    private NamedCassandraColumn<Long, ?> year = new NamedCassandraColumn<>("year", new PartitionKeyColumn<>(new LongColumn()));
    private NamedCassandraColumn<Long, ?> month = new NamedCassandraColumn<>("month", new PartitionKeyColumn<>(new LongColumn()));
    private NamedCassandraColumn<Long, ?> timestamp = new NamedCassandraColumn<>("timestamp", new ClusteringKeyColumn<>(new LongColumn()));

    public TimeBucketColumn() {
        super(Date.class);
    }

    @Override
    public Date fromRow(Row row) {
        return new Date(timestamp.fromRow(row));
    }

    @Override
    protected Date fromObjectInternal(Tweet object) {
        return object.getDate();
    }

    @Override
    public List<Column<?>> getColumns() {
        return Arrays.asList(year, month, timestamp);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void store(Date value, Insertion<Tweet> insertion) {
        insertion.insert(year, (long) value.getYear())
            .insert(month, (long) value.getMonth())
            .insert(timestamp, value.getTime());
    }

}
