package com.example.musicchallenge.domain.utils.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession
import com.example.musicchallenge.R
import com.example.musicchallenge.presentation.util.AppIcons
import com.google.common.collect.ImmutableList
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import javax.inject.Inject

@UnstableApi class MusicNotificationProvider @Inject constructor(

    @ApplicationContext private val context: Context,

) : MediaNotification.Provider, Parcelable {
    private val notificationManager = checkNotNull(context.getSystemService<NotificationManager>())
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Context::class.java.classLoader)!!
    ) {
    }

    override fun createNotification(
        session: MediaSession,
        customLayout: ImmutableList<CommandButton>,
        actionFactory: MediaNotification.ActionFactory,
        onNotificationChangedCallback: MediaNotification.Provider.Callback
    ): MediaNotification {
        ensureNotificationChannel()

        val player = session.player
        val metadata = player.mediaMetadata

        val builder = NotificationCompat.Builder(context, MusicNotificationChannelId)
            .setContentTitle(metadata.title)
            .setContentText(metadata.artist)
            .setSmallIcon(AppIcons.Music.resourceId)

        getNotificationActions(
            mediaSession = session,
            customLayout = customLayout,
            actionFactory = actionFactory,
            playWhenReady = player.playWhenReady
        ).forEach(builder::addAction)

        setupArtwork(
            uri = metadata.artworkUri,
            setLargeIcon = builder::setLargeIcon,
            updateNotification = {
                val notification = MediaNotification(MusicNotificationId, builder.build())
                onNotificationChangedCallback.onNotificationChanged(notification)
            }
        )

        return MediaNotification(MusicNotificationId, builder.build())
    }

    override fun handleCustomCommand(session: MediaSession, action: String, extras: Bundle) = false

    fun cancelCoroutineScope() = coroutineScope.cancel()

    private fun ensureNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O ||
            notificationManager.getNotificationChannel(MusicNotificationChannelId) != null
        ) {
            return
        }

        val notificationChannel = NotificationChannel(
            MusicNotificationChannelId,"Globant-Challenge" ,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun getNotificationActions(
        mediaSession: MediaSession,
        customLayout: ImmutableList<CommandButton>,
        actionFactory: MediaNotification.ActionFactory,
        playWhenReady: Boolean
    ) = listOf(
        MusicActions.getRepeatShuffleAction(mediaSession, customLayout, actionFactory),
        MusicActions.getSkipPreviousAction(context, mediaSession, actionFactory),
        MusicActions.getPlayPauseAction(context, mediaSession, actionFactory, playWhenReady),
        MusicActions.getSkipNextAction(context, mediaSession, actionFactory)
    )

    private fun setupArtwork(
        uri: Uri?,
        setLargeIcon: (Bitmap?) -> Unit,
        updateNotification: () -> Unit
    ) = coroutineScope.launch {
        val bitmap = loadArtworkBitmap(uri)
        setLargeIcon(bitmap)
        updateNotification()
    }

    private suspend fun loadArtworkBitmap(uri: Uri?) =
        withContext(Dispatchers.IO) { uri?.asArtworkBitmap(context) }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MusicNotificationProvider> {
        override fun createFromParcel(parcel: Parcel): MusicNotificationProvider {
            return MusicNotificationProvider(parcel)
        }

        override fun newArray(size: Int): Array<MusicNotificationProvider?> {
            return arrayOfNulls(size)
        }
    }
}

private const val MusicNotificationId = 1001
private const val MusicNotificationChannelId = "MusicNotificationChannel"
