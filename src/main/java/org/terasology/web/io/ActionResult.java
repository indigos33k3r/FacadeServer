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
package org.terasology.web.io;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ActionResult {

    public static final ActionResult OK = new ActionResult(Status.OK);
    public static final ActionResult JSON_PARSE_ERROR = new ActionResult(Status.BAD_REQUEST, "Failed to parse JSON message");

    public enum Status {
        OK,
        BAD_REQUEST,
        UNAUTHORIZED
    }

    private Status status;
    private String message;
    private JsonElement data;

    public ActionResult(Status status, String message, JsonElement data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ActionResult(Status status, String message) {
        this(status, message, null);
    }

    public ActionResult(Status status) {
        this(status, "");
    }

    public ActionResult(JsonElement data) {
        this(Status.OK, null, data);
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonElement getData() {
        return data;
    }

    public String toJsonString(Gson gson) {
        return gson.toJson(this);
    }
}