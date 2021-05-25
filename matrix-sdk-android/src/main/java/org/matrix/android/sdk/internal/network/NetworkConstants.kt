/*
 * Copyright 2020 The Matrix.org Foundation C.I.C.
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

package org.matrix.android.sdk.internal.network

internal object NetworkConstants {
    // Homeserver
    private const val URI_API_PREFIX_PATH = "_matrix/client"
    const val URI_API_PREFIX_PATH_ = "$URI_API_PREFIX_PATH/"
    const val URI_API_PREFIX_PATH_R0 = "$URI_API_PREFIX_PATH/r0/"
    const val URI_API_PREFIX_PATH_UNSTABLE = "$URI_API_PREFIX_PATH/unstable/"

    // Media
    private const val URI_API_MEDIA_PREFIX_PATH = "_matrix/media"
    const val URI_API_MEDIA_PREFIX_PATH_R0 = "$URI_API_MEDIA_PREFIX_PATH/r0/"

    // Identity server
    const val URI_IDENTITY_PREFIX_PATH = "_matrix/identity/v2"
    const val URI_IDENTITY_PATH_V2 = "$URI_IDENTITY_PREFIX_PATH/"

    // Push Gateway
    const val URI_PUSH_GATEWAY_PREFIX_PATH = "_matrix/push/v1/"

    // Integration
    const val URI_INTEGRATION_MANAGER_PATH = "_matrix/integrations/v1/"

    // Federation
    const val URI_FEDERATION_PATH = "_matrix/federation/v1/"

    // Cy Chat APIs
    const val USER_LOGIN = "/user-login"
    const val CHECK_OTP = "/check-otp"
    const val COUNTRY_LIST = "/get-settings"
    const val RESENT_OTP = "/resend-otp"

}
