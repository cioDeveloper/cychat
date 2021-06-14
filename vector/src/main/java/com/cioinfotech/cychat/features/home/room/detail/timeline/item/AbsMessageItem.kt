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

package com.cioinfotech.cychat.features.home.room.detail.timeline.item

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.cioinfotech.cychat.R
import com.cioinfotech.cychat.core.utils.DebouncedClickListener
import com.cioinfotech.cychat.features.home.AvatarRenderer
import com.cioinfotech.cychat.features.home.HomeActivity.Companion.isOneToOneChatOpen
import com.cioinfotech.cychat.features.home.room.detail.timeline.MessageColorProvider
import com.cioinfotech.cychat.features.home.room.detail.timeline.TimelineEventController
import com.cioinfotech.cychat.features.themes.ThemeUtils

/**
 * Base timeline item that adds an optional information bar with the sender avatar, name, time, send state
 * Adds associated click listeners (on avatar, displayname)
 */
abstract class AbsMessageItem<H : AbsMessageItem.Holder> : AbsBaseMessageItem<H>() {

    override val baseAttributes: AbsBaseMessageItem.Attributes
        get() = attributes

    @EpoxyAttribute
    lateinit var attributes: Attributes

    private val _avatarClickListener = DebouncedClickListener({
        attributes.avatarCallback?.onAvatarClicked(attributes.informationData)
    })
    private val _memberNameClickListener = DebouncedClickListener({
        attributes.avatarCallback?.onMemberNameClicked(attributes.informationData)
    })

    override fun bind(holder: H) {
        super.bind(holder)
//        if (attributes.informationData.showInformation) {
//        holder.avatarImageView.layoutParams = holder.avatarImageView.layoutParams?.apply {
//            height = attributes.avatarSize
//            width = attributes.avatarSize
//        }
//            holder.memberNameView.visibility = View.VISIBLE
//            holder.memberNameView.setOnClickListener(_memberNameClickListener)
//            holder.timeView.visibility = View.VISIBLE
//            holder.timeView.text = attributes.informationData.time
        if (!attributes.informationData.sentByMe) {
            holder.memberNameView.text = attributes.informationData.memberName
            holder.memberNameView.setOnLongClickListener(attributes.itemLongClickListener)
            holder.memberNameView.setOnClickListener(_avatarClickListener)
            holder.memberNameView.setTextColor(attributes.getMemberNameColor())
        }
//        attributes.avatarRenderer.render(attributes.informationData.matrixItem, holder.avatarImageView)
//            holder.memberNameView.setOnLongClickListener(attributes.itemLongClickListener)
//        } else {
//            holder.avatarImageView.setOnClickListener(null)
//            holder.memberNameView.setOnClickListener(null)
//            holder.avatarImageView.visibility = View.VISIBLE
//            if (attributes.informationData.forceShowTimestamp) {
//                holder.memberNameView.isInvisible = true
//                holder.timeView.isVisible = true
//                holder.timeView.text = attributes.informationData.time
//            } else {
//                holder.memberNameView.isVisible = false
//                holder.timeView.isVisible = false
//            }
//            holder.avatarImageView.setOnLongClickListener(null)
//            holder.memberNameView.setOnLongClickListener(null)
//        }
        val constraintSet = ConstraintSet()
        constraintSet.clone(holder.clParent)
        if (attributes.informationData.sentByMe) {
            holder.clStubContainer.setBackgroundResource(R.drawable.sent_bg)
            holder.clStubContainer.backgroundTintList = ColorStateList.valueOf(ThemeUtils.getColor(holder.clStubContainer.context, R.attr.cy_chat_bg_send))
            constraintSet.setHorizontalBias(holder.clStubContainer.id, 1f)
        } else {
            holder.clStubContainer.setBackgroundResource(R.drawable.received_bg)
            holder.clStubContainer.backgroundTintList = ColorStateList.valueOf(ThemeUtils.getColor(holder.clStubContainer.context, R.attr.cy_chat_bg_receive))
            constraintSet.setHorizontalBias(holder.clStubContainer.id, 0f)
        }
        constraintSet.applyTo(holder.clParent)

        // Render send state indicator
//        holder.sendStateImageView.render(attributes.informationData.sendStateDecoration)
        holder.eventSendingIndicator.isVisible = attributes.informationData.sendStateDecoration == SendStateDecoration.SENDING_MEDIA
        holder.memberNameView.isVisible = !isOneToOneChatOpen && !attributes.informationData.sentByMe
    }

    override fun unbind(holder: H) {
//        attributes.avatarRenderer.clear(holder.avatarImageView)
        holder.memberNameView.setOnClickListener(null)
        holder.memberNameView.setOnLongClickListener(null)
//        holder.memberNameView.setOnClickListener(null)
//        holder.memberNameView.setOnLongClickListener(null)
        super.unbind(holder)
    }

    private fun Attributes.getMemberNameColor() = messageColorProvider.getMemberNameTextColor(informationData.matrixItem)

    abstract class Holder(@IdRes stubId: Int) : AbsBaseMessageItem.Holder(stubId) {
//        val avatarImageView by bind<ImageView>(R.id.messageAvatarImageView)

        val memberNameView by bind<TextView>(R.id.messageMemberNameView)

        //        val timeView by bind<TextView>(R.id.messageTimeView)
//        val sendStateImageView by bind<SendStateImageView>(R.id.messageSendStateImageView)
        val eventSendingIndicator by bind<ProgressBar>(R.id.eventSendingIndicator)
        val clParent by bind<ConstraintLayout>(R.id.clParent)
        val clStubContainer by bind<ConstraintLayout>(R.id.clStubContainer)
    }

    /**
     * This class holds all the common attributes for timeline items.
     */
    data class Attributes(
            val avatarSize: Int,
            override val informationData: MessageInformationData,
            override val avatarRenderer: AvatarRenderer,
            override val messageColorProvider: MessageColorProvider,
            override val itemLongClickListener: View.OnLongClickListener? = null,
            override val itemClickListener: View.OnClickListener? = null,
            val memberClickListener: View.OnClickListener? = null,
            override val reactionPillCallback: TimelineEventController.ReactionPillCallback? = null,
            val avatarCallback: TimelineEventController.AvatarCallback? = null,
            override val readReceiptsCallback: TimelineEventController.ReadReceiptsCallback? = null,
            val emojiTypeFace: Typeface? = null
    ) : AbsBaseMessageItem.Attributes {

        // Have to override as it's used to diff epoxy items
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Attributes

            if (avatarSize != other.avatarSize) return false
            if (informationData != other.informationData) return false

            return true
        }

        override fun hashCode(): Int {
            var result = avatarSize
            result = 31 * result + informationData.hashCode()
            return result
        }
    }
}
