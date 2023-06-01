package com.example.musicchallenge.domain.utils.common

import android.content.Context
import androidx.core.graphics.drawable.IconCompat
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession
import com.example.musicchallenge.domain.utils.common.MusicAction

internal fun MusicAction.asNotificationAction(
    context: Context,
    mediaSession: MediaSession,
    actionFactory: MediaNotification.ActionFactory
) = actionFactory.createMediaAction(
    mediaSession,
    IconCompat.createWithResource(context, iconResource),
    context.getString(titleResource),
    command
)
