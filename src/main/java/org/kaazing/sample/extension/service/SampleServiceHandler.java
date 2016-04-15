/**
 * Copyright 2007-2016, Kaazing Corporation. All rights reserved.
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
package org.kaazing.sample.extension.service;

import java.nio.ByteBuffer;

import org.kaazing.gateway.transport.IoHandlerAdapter;
import org.kaazing.gateway.util.LoggingUtils;
import org.kaazing.mina.core.buffer.IoBufferAllocatorEx;
import org.kaazing.mina.core.buffer.IoBufferEx;
import org.kaazing.mina.core.session.IoSessionEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SampleServiceHandler extends IoHandlerAdapter<IoSessionEx> {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String whatToSay;

    SampleServiceHandler(String whatToSay) {
        this.whatToSay = whatToSay;
    }

    @Override
    protected void doMessageReceived(final IoSessionEx session, Object message) throws Exception {
        IoBufferAllocatorEx<?> allocator = session.getBufferAllocator();
        IoBufferEx buffer = allocator.wrap(ByteBuffer.wrap(whatToSay.getBytes()), IoBufferEx.FLAG_SHARED);
        session.write(buffer);
    }

    @Override
    protected void doExceptionCaught(IoSessionEx session, Throwable cause) throws Exception {
        LoggingUtils.log(logger, cause);
    }
}
