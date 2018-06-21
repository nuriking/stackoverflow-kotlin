package com.ilnur.steckoverflow_kotlin.ui.result

import com.ilnur.steckoverflow_kotlin.ui.IBaseView

interface IResult : IBaseView {
    fun fillLayout(title: String, site: String, created: String, ownerName: String,
                   imageUrl: String, ownerReputation: String)

    fun isExist(title: String)

    fun onBack()

    fun lookSite(site: String)
}
