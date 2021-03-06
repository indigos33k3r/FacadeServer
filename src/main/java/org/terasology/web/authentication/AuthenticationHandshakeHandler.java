/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.web.authentication;

public interface AuthenticationHandshakeHandler {
    /**
     * @return the initial handshake that must be sent from the server to the client.
     */
    HandshakeHello initServerHello();

    /**
     * Tries to finalize the authentication process.
     * If no exception is thrown, the authentication has been successfully completed.
     * @param authenticationMessage the authentication data sent by the client, which must consist of:
     * - the hello message sent by the client
     * - the verification data sent by the client, which on the client side must be built
     * by concatenating the received server hello with the built client hello (see {@link HandshakeHello#concat(HandshakeHello, HandshakeHello)})
     * and signing the result with the private client identity certificate (see {@link org.terasology.identity.PrivateIdentityCertificate#sign(byte[])}).
     * @return the server verification data, which is the concatenation of the server hello with the received client hello signed with the server
     * private certificate; the client can optionally verify the server's identity by verifying this data is signed with the server public certificate
     * and disconnect if the signature is not valid.
     * @throws AuthenticationFailedException if the authentication fails
     */
    byte[] authenticate(ClientAuthenticationMessage authenticationMessage) throws AuthenticationFailedException;
}
