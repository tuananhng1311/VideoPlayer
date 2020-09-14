package com.example.videoplayerjava.model

class YoutubeVideo {
    var id: String? = null
    var snippet: SnippetYoutube? = null
    var contentDetails: ContentDetail? = null
    var statistics: Statistics? = null

    constructor(id: String?, snippet: SnippetYoutube?, contentDetail: ContentDetail?, statistics: Statistics?) {
        this.id = id
        this.snippet = snippet
        this.contentDetails = contentDetail
        this.statistics = statistics
    }
}