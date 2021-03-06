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

package org.iotope.nfc.reader.pn532;

import java.nio.ByteBuffer;

import org.iotope.nfc.reader.pn532.struct.PN532Status;
import org.iotope.util.IOUtil;

public class PN532TgGetDataResponse extends PN532AbstractResponse<PN532TgGetData> {
    
    public PN532TgGetDataResponse(PN532TgGetData command, ByteBuffer buffer) {
        super(command, buffer);
        checkInstruction(0x87, buffer.get());
        status = new PN532Status(buffer);
        data = new byte[buffer.remaining()];
        buffer.get(data);
    }
    
    
    public PN532Status getStatus() {
        return status;
    }
    
    
    public boolean hasError() {
        return status.hasError();
    }
    
    
    public boolean hasMoreInformation() {
        return status.hasMoreInformation();
    }
    
    
    public boolean hasNodeAddress() {
        return status.hasNodeAddress();
    }
        
    @Override
    public String toString() {
        return "<< TgGetData " + status + " " + IOUtil.hex(data);
    }
    
    PN532Status status;
    private byte[] data;
}
