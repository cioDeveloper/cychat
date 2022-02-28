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

object NetworkConstants {
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

    const val DEV_URL = "https://cychat-dev.cioinfotech.com"
    const val QA_URL = "https://cyberiaqa-api.cioinfotech.com"
    const val UAT_URL = "https://cychat-ct.cioinfotech.com"
    const val PRODUCTION_URL = "https://cyverse-production1.cioinfotech.com"
    const val CENTRAL_SERVER_URL = "https://dev.cyverse.api.cioinfotech.com"
    const val DEV = "Development"
    const val QA = "QA"
    const val UAT = "UAT"
    const val PRODUCTION = "PRODUCTION"

    // Cy Central Server APIs

    const val ROOT_API = "/api/root_api.php"
    const val GET_GROUPS_API = "getGroups"
    const val GET_USER_TYPE_API = "getUserType"
    const val GET_SETTINGS_API = "getSettings"
    const val RE_CHECK_REF_CODE = "reCheckRefCode"
    const val VALIDATE_CODE = "validateCode"
    const val CHECK_CODE_API = "checkCode"
    const val CHECK_OTP_API = "checkOtp"
    const val USER_LOGIN_API = "userLogin"
    const val GENERAL_DATA = "general_data"
    const val CY_VERSE_ANDROID = "cyVerseAndroid"
    const val LIVE = "LIVE"
    const val OP = "op"
    const val SERVICE_NAME = "serviceName"
    const val CLIENT_NAME = "clientName"
    const val USER_TYPE = "userType"
    const val REF_CODE = "refCode"
    const val REF_CODE_DASH = "ref_code"
    const val CLID = "clid"
    const val GROUP_VALUE = "groupValue"
    const val INDIVIDUAL = "Individual"
    const val NONE = "None"
    const val COMMON = "Common"
    const val USERTYPE_DATA = "usertype_data"
    const val GET_SETTINGS = "get_settings"
    const val CY_VERSE_API_CLID = "96e9a8b6be83be41955ab39c8e738c2c"
    const val CY_INSTANCE_API_CLID = "21d835b600d0cd31e9144e66f73b5b7e"

    // Cy Chat APIs
    const val LOGIN = "login"
    const val USER_LOGIN = "/user-login"
    const val CHECK_OTP = "/check-otp"

    //    const val GET_SETTINGS = "/get-settings"
    const val RESENT_OTP = "/resend-otp"
    const val VALIDATE_SECURITY_CODE = "/val-secr-code"
    const val GET_DOMAIN_DETAILS = "/get-company-name-and-logo"
    const val UPDATE_RECOVERY_KEY = "/setsecretkey"
    const val LIST_FEDERATED_DOMAINS = "/list-federated"
    const val DELETE_OLD_SESSION = "/delete-session"
    const val CY_CHAT_ENV = "CY_CHAT_ENV"
    const val BASE_URL = "BASE_URL"
    const val USER_ID = "USER_ID"
    const val USER_ID_SMALL = "user_id"
    const val ACCESS_TOKEN = "ACCESS_TOKEN"
    const val EMAIL = "EMAIL"
    const val ACCESS_TOKEN_SMALL = "access_token"
    const val REQ_ID = "req_id"
    const val JITSI = "JITSI"
    const val SYGNAL = "SYGNAL"
    const val DEFAULT_URLS = "/get-jitsi-url"

    //    const val REQ_ID_SMALL = "req_id"
    const val DOMAIN_NAME = "DOMAIN_NAME"
    const val DOMAIN_IMAGE = "DOMAIN_IMAGE"
    const val SECRET_KEY = "SECRET_KEY"
    const val API_SERVER = "API_SERVER"
    const val SECRET_KEY_SMALL = "secret_key"
    const val SIGN_UP_SMALL = "sign-up"
    const val SIGN_IN_SMALL = "sign-in"
    const val SIGNING_MODE = "SIGNING_MODE" // true - Sign Up & false - Sign In
    const val AUTH_KEY = "Bearer Avdhut"
    const val BEARER = "Bearer "
    const val FULL_NAME = "FULL_NAME"
    const val DEVICE_ID = "dev_id"
    const val CURRENT_DOMAIN = "curr_domain"
    const val SESSION_UPDATED = "SESSION_UPDATED"
    const val MOBILE = "mobile"
    const val EMAIL_SMALL = "email"
    const val IMEI = "imei"
    const val COUNTRY_CODE = "country_code"

    const val F_NAME = "fname"
    const val L_NAME = "lname"
    const val FIRST_NAME = "first_name"
    const val LAST_NAME = "last_name"
    const val TYPE = "type"
    const val EMAIL_OTP = "email_otp"
    const val MOBILE_OTP = "mobile_otp"
    const val EMAIL_VAL = "email_val"
}
