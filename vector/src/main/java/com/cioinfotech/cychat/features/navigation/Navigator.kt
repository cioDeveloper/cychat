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

package com.cioinfotech.cychat.features.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.util.Pair
import com.cioinfotech.cychat.features.crypto.recover.SetupMode
import com.cioinfotech.cychat.features.media.AttachmentData
import com.cioinfotech.cychat.features.pin.PinMode
import com.cioinfotech.cychat.features.roomdirectory.roompreview.RoomPreviewData
import com.cioinfotech.cychat.features.settings.VectorSettingsActivity
import com.cioinfotech.cychat.features.share.SharedData
import org.matrix.android.sdk.api.session.room.model.roomdirectory.PublicRoom
import org.matrix.android.sdk.api.session.room.model.thirdparty.RoomDirectoryData
import org.matrix.android.sdk.api.session.terms.TermsService
import org.matrix.android.sdk.api.session.widgets.model.Widget
import org.matrix.android.sdk.api.util.MatrixItem

interface Navigator {

    fun openRoom(context: Context, roomId: String, eventId: String? = null, buildTask: Boolean = false)

    fun performDeviceVerification(context: Context, otherUserId: String, sasTransactionId: String)

    fun requestSessionVerification(context: Context, otherSessionId: String)

    fun requestSelfSessionVerification(context: Context)

    fun waitSessionVerification(context: Context)

    fun upgradeSessionSecurity(context: Context, initCrossSigningOnly: Boolean)

    fun openRoomForSharingAndFinish(activity: Activity, roomId: String, sharedData: SharedData)

    fun openRoomPreview(context: Context, publicRoom: PublicRoom, roomDirectoryData: RoomDirectoryData)

    fun openRoomPreview(context: Context, roomPreviewData: RoomPreviewData)

    fun openCreateRoom(context: Context, initialName: String = "")

    fun openCreateDirectRoom(context: Context)

    fun openInviteUsersToRoom(context: Context, roomId: String)

    fun openRoomDirectory(context: Context, initialFilter: String = "")

    fun openRoomsFiltering(context: Context)

    fun openSettings(context: Context, directAccess: Int = VectorSettingsActivity.EXTRA_DIRECT_ACCESS_ROOT)

    fun openKeysBackupSetup(context: Context, showManualExport: Boolean)

    fun open4SSetup(context: Context, setupMode: SetupMode)

    fun openKeysBackupManager(context: Context)

    fun openGroupDetail(groupId: String, context: Context, buildTask: Boolean = false)

    fun openRoomMemberProfile(userId: String, roomId: String?, context: Context, buildTask: Boolean = false)

    fun openRoomProfile(context: Context, roomId: String, directAccess: Int? = null)

    fun openBigImageViewer(activity: Activity, sharedElement: View?, matrixItem: MatrixItem) {
        openBigImageViewer(activity, sharedElement, matrixItem.avatarUrl, matrixItem.getBestName())
    }

    fun openBigImageViewer(activity: Activity, sharedElement: View?, mxcUrl: String?, title: String?)

    fun openPinCode(context: Context,
                    activityResultLauncher: ActivityResultLauncher<Intent>,
                    pinMode: PinMode)

    fun openTerms(context: Context,
                  activityResultLauncher: ActivityResultLauncher<Intent>,
                  serviceType: TermsService.ServiceType,
                  baseUrl: String,
                  token: String?)

    fun openStickerPicker(context: Context,
                          activityResultLauncher: ActivityResultLauncher<Intent>,
                          roomId: String,
                          widget: Widget)

    fun openIntegrationManager(context: Context,
                               activityResultLauncher: ActivityResultLauncher<Intent>,
                               roomId: String,
                               integId: String?,
                               screen: String?)

    fun openRoomWidget(context: Context, roomId: String, widget: Widget, options: Map<String, Any>? = null)

    fun openMediaViewer(activity: Activity,
                        roomId: String,
                        mediaData: AttachmentData,
                        view: View,
                        inMemory: List<AttachmentData> = emptyList(),
                        options: ((MutableList<Pair<View, String>>) -> Unit)?)

    fun openSearch(context: Context, roomId: String)

    fun openDevTools(context: Context, roomId: String)

    fun openCallTransfer(context: Context, callId: String)
}
