/*
 * Copyright 2020 The Android Open Source Project
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

package com.anangkur.synrgychapter3.data.datasource.local.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.anangkur.synrgychapter3.AuthPreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

/**
 * Serializer for the [UserPreferences] object defined in user_prefs.proto.
 */
//object UserPreferencesSerializer : Serializer<AuthPreferences> {
//    override val defaultValue: AuthPreferences = AuthPreferences.getDefaultInstance()
//
//    @Suppress("BlockingMethodInNonBlockingContext")
//    override suspend fun readFrom(input: InputStream): AuthPreferences {
//        try {
//            return AuthPreferences.parseFrom(input)
//        } catch (exception: InvalidProtocolBufferException) {
//            throw CorruptionException("Cannot read proto.", exception)
//        }
//    }
//
//    @Suppress("BlockingMethodInNonBlockingContext")
//    override suspend fun writeTo(t: AuthPreferences, output: OutputStream) = t.writeTo(output)
//}
