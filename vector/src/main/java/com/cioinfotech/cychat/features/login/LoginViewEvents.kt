/*
 * Copyright (c) 2021 New Vector Ltd
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

package com.cioinfotech.cychat.features.login

import com.cioinfotech.cychat.core.platform.VectorViewEvents
import org.matrix.android.sdk.api.auth.registration.FlowResult
import org.matrix.android.sdk.internal.cy_auth.data.GroupParent
import org.matrix.android.sdk.internal.cy_auth.data.OrganizationParent
import org.matrix.android.sdk.internal.cy_auth.data.UserTypeParent

/**
 * Transient events for Login
 */
sealed class LoginViewEvents : VectorViewEvents {
    data class Loading(val message: CharSequence? = null) : LoginViewEvents()
    data class Failure(val throwable: Throwable) : LoginViewEvents()

    data class RegistrationFlowResult(val flowResult: FlowResult, val isRegistrationStarted: Boolean) : LoginViewEvents()
    object OutdatedHomeserver : LoginViewEvents()
    object OnHomeserverSelection : LoginViewEvents()
    // Navigation event

    object OpenServerSelection : LoginViewEvents()
    data class OnServerSelectionDone(val serverType: ServerType) : LoginViewEvents()
    object OnLoginFlowRetrieved : LoginViewEvents()
    data class OnSignModeSelected(val signMode: SignMode) : LoginViewEvents()
    object OnForgetPasswordClicked : LoginViewEvents()
    object OnResetPasswordSendThreePidDone : LoginViewEvents()
    object OnResetPasswordMailConfirmationSuccess : LoginViewEvents()
    object OnResetPasswordMailConfirmationSuccessDone : LoginViewEvents()

    data class OnSendEmailSuccess(val email: String) : LoginViewEvents()
    object OnSendOTPs : LoginViewEvents()
    object OnOTPSendSuccess : LoginViewEvents()
    data class OnSendMsisdnSuccess(val msisdn: String) : LoginViewEvents()
    object OnResendOTP : LoginViewEvents()
    object OnSupplierConfirmed : LoginViewEvents()
    object OnMappingConfirmed : LoginViewEvents()
    data class OnGetGroupsConfirmed(val groupParent: GroupParent) : LoginViewEvents()
    data class OnUserTypeConfirmed(val userTypeParent: UserTypeParent) : LoginViewEvents()
    data class OnOrganizationConfirmed(val orgParent: OrganizationParent) : LoginViewEvents()
    data class OnIndividualConfirmed(val userTypeParent: UserTypeParent) : LoginViewEvents()
    data class OnWebLoginError(val errorCode: Int, val description: String, val failingUrl: String) : LoginViewEvents()
    object OnTokenSentConfirmed : LoginViewEvents()
}
