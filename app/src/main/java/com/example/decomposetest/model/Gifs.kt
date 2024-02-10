package com.example.decomposetest.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleJson2KtKotlin (
    @SerialName("data") var data : ArrayList<Data> = arrayListOf(),
    //@SerialName("meta"       ) var meta       : Meta?           = Meta(),
    //@SerialName("pagination" ) var pagination : Pagination?     = Pagination()
)

@Serializable
data class Original (

    @SerialName("height"    ) var height   : String? = null,
    @SerialName("width"     ) var width    : String? = null,
    @SerialName("size"      ) var size     : String? = null,
    @SerialName("url"       ) var url      : String? = null,
    @SerialName("mp4_size"  ) var mp4Size  : String? = null,
    @SerialName("mp4"       ) var mp4      : String? = null,
    @SerialName("webp_size" ) var webpSize : String? = null,
    @SerialName("webp"      ) var webp     : String? = null,
    @SerialName("frames"    ) var frames   : String? = null,
    @SerialName("hash"      ) var hash     : String? = null

)

@Serializable
data class Downsized (

    @SerialName("height" ) var height : String? = null,
    @SerialName("width"  ) var width  : String? = null,
    @SerialName("size"   ) var size   : String? = null,
    @SerialName("url"    ) var url    : String? = null

)

@Serializable
data class DownsizedLarge (

    @SerialName("height" ) var height : String? = null,
    @SerialName("width"  ) var width  : String? = null,
    @SerialName("size"   ) var size   : String? = null,
    @SerialName("url"    ) var url    : String? = null

)

data class DownsizedMedium (

    @SerialName("height" ) var height : String? = null,
    @SerialName("width"  ) var width  : String? = null,
    @SerialName("size"   ) var size   : String? = null,
    @SerialName("url"    ) var url    : String? = null

)

@Serializable
data class DownsizedSmall (

    @SerialName("height"   ) var height  : String? = null,
    @SerialName("width"    ) var width   : String? = null,
    @SerialName("mp4_size" ) var mp4Size : String? = null,
    @SerialName("mp4"      ) var mp4     : String? = null

)

@Serializable
data class DownsizedStill (
    @SerialName("height" ) var height : String? = null,
    @SerialName("width"  ) var width  : String? = null,
    @SerialName("size"   ) var size   : String? = null,
    @SerialName("url"    ) var url    : String? = null
)

@Serializable
data class FixedHeight (
    @SerialName("height"    ) var height   : String? = null,
    @SerialName("width"     ) var width    : String? = null,
    @SerialName("size"      ) var size     : String? = null,
    @SerialName("url"       ) var url      : String? = null,
    @SerialName("mp4_size"  ) var mp4Size  : String? = null,
    @SerialName("mp4"       ) var mp4      : String? = null,
    @SerialName("webp_size" ) var webpSize : String? = null,
    @SerialName("webp"      ) var webp     : String? = null
)

@Serializable
data class FixedHeightDownsampled (
    @SerialName("height"    ) var height   : String? = null,
    @SerialName("width"     ) var width    : String? = null,
    @SerialName("size"      ) var size     : String? = null,
    @SerialName("url"       ) var url      : String? = null,
    @SerialName("webp_size" ) var webpSize : String? = null,
    @SerialName("webp"      ) var webp     : String? = null
)

@Serializable
data class FixedHeightSmall (
    @SerialName("height"    ) var height   : String? = null,
    @SerialName("width"     ) var width    : String? = null,
    @SerialName("size"      ) var size     : String? = null,
    @SerialName("url"       ) var url      : String? = null,
    @SerialName("mp4_size"  ) var mp4Size  : String? = null,
    @SerialName("mp4"       ) var mp4      : String? = null,
    @SerialName("webp_size" ) var webpSize : String? = null,
    @SerialName("webp"      ) var webp     : String? = null
)

@Serializable
data class Data (

    @SerialName("type"                       ) var type                     : String?    = null,
    @SerialName("id"                         ) var id                       : String?    = null,
    @SerialName("url"                        ) var url                      : String?    = null,
    @SerialName("slug"                       ) var slug                     : String?    = null,
    @SerialName("bitly_gif_url"              ) var bitlyGifUrl              : String?    = null,
    @SerialName("bitly_url"                  ) var bitlyUrl                 : String?    = null,
    @SerialName("embed_url"                  ) var embedUrl                 : String?    = null,
    @SerialName("username"                   ) var username                 : String?    = null,
    @SerialName("source"                     ) var source                   : String?    = null,
    @SerialName("title"                      ) var title                    : String?    = null,
    @SerialName("rating"                     ) var rating                   : String?    = null,
    @SerialName("content_url"                ) var contentUrl               : String?    = null,
    @SerialName("source_tld"                 ) var sourceTld                : String?    = null,
    @SerialName("source_post_url"            ) var sourcePostUrl            : String?    = null,
    @SerialName("is_sticker"                 ) var isSticker                : Int?       = null,
    @SerialName("import_datetime"            ) var importDatetime           : String?    = null,
    @SerialName("trending_datetime"          ) var trendingDatetime         : String?    = null,
    //@SerialName("images"                     ) var images                   : Images?    = Images(),
    @SerialName("analytics_response_payload" ) var analyticsResponsePayload : String?    = null,
    //@SerialName("analytics"                  ) var analytics                : Analytics? = Analytics()

)