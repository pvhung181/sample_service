package com.spark.tapbi.sampleservice.common

object Constant {
    const val NEW_USING = "NEW_USING"
    const val DB_NAME = "TapbiArtEditor.db"
    const val DB_SMS = "Sms.db"
    const val DB_VERSION = 1
    const val CONNECT_S = 30
    const val READ_S = 30
    const val WRITE_S = 30

    const val LANGUAGE_EN = "en"
    const val LANGUAGE_VN = "vi"



    /*SharePreference constant*/
    const val PREF_SETTING_LANGUAGE = "pref_setting_language"

    /*switch type db, this only use in this sample*/
    const val DB_TYPE_ROOM = 1
    const val DB_TYPE = DB_TYPE_ROOM


    /*api*/
    const val BASE_URL = "https://api.unsplash.com/"
    const val API_KEY = "c492Pb928b5FaBfOLRoR6O3ZdewnUhPPepctQeRN_eo"
    const val TOTAL_PAGES = "total_pages"

    const val DEFAULT_PER_PAGE = 10

    const val QUERY_FASHION = "Fashion"
    const val QUERY_BEAUTY = "Beauty"
    const val QUERY_FILM= "Film"
    const val QUERY_PARTY = "Party"
    const val QUERY_NATURE = "Nature"
    const val CATEGORY = "Category"

    /*Photo*/
    const val EDITED_PHOTO_ID = "edited_photo_id"
    
    const val BACKGROUND_BRIGHTNESS = "background_brightness"
    const val BACKGROUND_CONTRAST = "background_contrast"
    const val BACKGROUND_OPACITY = "background_opacity"

    const val FOREGROUND_BRIGHTNESS = "foreground_brightness"
    const val FOREGROUND_CONTRAST = "foreground_contrast"
    const val FOREGROUND_OPACITY = "foreground_opacity"

    const val FOREGROUND = "foreground"
    const val BACKGROUND = "background"


    /*Table*/
    const val TABLE_EDITED_PHOTO = "edited_photo"
    const val TABLE_PHOTO = "photo"

    /*Column*/
    const val COLUMN_PHOTO_ID = "photo_id"
    const val COLUMN_IS_FAVOURITE = "is_favourite"
}