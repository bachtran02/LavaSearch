package com.github.topi314.lavasearch.protocol

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic

@Serializable
enum class SearchType(val value: String) {
    TRACK("track"),
    ALBUM("album"),
    ARTIST("artist"),
    PLAYLIST("playlist"),
    TEXT("text");

    companion object {

        @JvmStatic
        fun fromString(value: String): SearchType {
            entries.forEach {
                if (it.value == value.lowercase()) {
                    return it
                }
            }
            throw IllegalArgumentException("Unknown SearchType: $value")
        }

    }
}

@Serializable
data class SearchResult(
    val albums: List<SearchAlbum>,
    val artists: List<SearchArtist>,
    val playlists: List<SearchPlaylist>,
    val tracks: List<SearchTrack>,
    val texts: List<SearchText>
) {
    companion object {
        @JvmField
        val EMPTY = SearchResult(emptyList(), emptyList(), emptyList(), emptyList(), emptyList())
    }
}

@Serializable
data class SearchAlbum(
    val identifier: String,
    val name: String,
    val artist: String,
    val url: String,
    val trackCount: Int,
    val artworkUrl: String?,
    val isrc: String?
)


@Serializable
data class SearchArtist(
    val identifier: String,
    val name: String,
    val url: String,
    val artworkUrl: String?,
)

@Serializable
data class SearchPlaylist(
    val identifier: String,
    val name: String,
    val url: String,
    val artworkUrl: String?,
    val trackCount: Int
)


@Serializable
data class SearchText(val text: String)

@Serializable
data class SearchTrack(
    val title: String,
    val author: String,
    val length: Long,
    val identifier: String,
    val isStream: Boolean,
    val uri: String,
    val artworkUrl: String?,
    val isrc: String?
)
