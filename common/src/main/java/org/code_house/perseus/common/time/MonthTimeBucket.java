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
package org.code_house.perseus.common.time;

import org.code_house.perseus.common.TimeBucket;

import java.util.Calendar;

/**
 * @author Łukasz Dywicki <luke@code-house.org>
 */
public class MonthTimeBucket implements TimeBucket {

    @Override
    public long getTimeBucket(long timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timestamp);
        // java uses 0 in place of first month, so we need to increase returned value
        return instance.get(Calendar.MONTH) + 1;
    }

}
