package com.example.musicchallenge.data.localdatasource.proto


import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.musicchallenge.FavoriteSong
import com.example.musicchallenge.FavoriteSongsData
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object FavoriteSongSerializer : Serializer<FavoriteSong> {
    override val defaultValue: FavoriteSong = FavoriteSong.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): FavoriteSong {
        try {
            return FavoriteSong.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FavoriteSong, output: OutputStream) = t.writeTo(output)
}

object FavoriteSongsDataSerializer : Serializer<FavoriteSongsData> {
    override val defaultValue: FavoriteSongsData = FavoriteSongsData.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): FavoriteSongsData {
        try {
            return FavoriteSongsData.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }


    override suspend fun writeTo(t: FavoriteSongsData, output: OutputStream) = t.writeTo(output)
}