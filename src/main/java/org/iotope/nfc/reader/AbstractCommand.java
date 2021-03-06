/*
 * Copyright (C) 2012 IOTOPE (http://www.iotope.com/)
 *
 * Licensed to IOTOPE under one or more contributor license 
 * agreements.  See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * IOTOPE licenses this file to you under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except 
 * in compliance with the License.  You may obtain a copy of the 
 * License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.iotope.nfc.reader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public abstract class AbstractCommand<COMMAND extends ReaderCommand<?, ?>, RESPONSE extends ReaderResponse<?>> implements ReaderCommand<COMMAND, RESPONSE> {
    
    protected Class<RESPONSE> responseClass;
    
    public AbstractCommand(Class<RESPONSE> responseClass) {
        this.responseClass = responseClass;
    }
    
    public RESPONSE transmit(ReaderChannel channel) {
        return null;
    }
    
    public RESPONSE receive(ByteBuffer buffer) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<RESPONSE> constructor = responseClass.getConstructor(getClass(), ByteBuffer.class);
        return constructor.newInstance(this, buffer);
    }
}
