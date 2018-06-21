package com.ilnur.steckoverflow_kotlin.ui.result

import com.ilnur.steckoverflow_kotlin.data.AppDataBase
import com.ilnur.steckoverflow_kotlin.data.FavoriteData
import com.ilnur.steckoverflow_kotlin.model.AnswerModel
import io.reactivex.Completable
import javax.inject.Inject

class ResultModel @Inject constructor(var appDataBase: AppDataBase) {

    fun getFavoriteData(question_id: Int?): FavoriteData? {
        return appDataBase.favoriteDao().getFavoriteData(question_id)
    }

    fun addToDB(answer: AnswerModel): Completable {
        return Completable.fromAction {
            appDataBase.favoriteDao().insert(
                    FavoriteData(
                            answer.viewCount,
                            answer.answerCount,
                            answer.scope,
                            answer.creationDate,
                            answer.questionId,
                            answer.link,
                            answer.title,
                            answer.owner.reputation,
                            answer.owner.userId,
                            answer.owner.profileImage,
                            answer.owner.displayName
                    )
            )
        }
    }


    fun deleteFromDB(answer: AnswerModel): Completable {
        return Completable.fromAction {
            appDataBase.favoriteDao().deleteFavoriteData(
                    FavoriteData(
                            answer.viewCount,
                            answer.answerCount,
                            answer.scope,
                            answer.creationDate,
                            answer.questionId,
                            answer.link,
                            answer.title,
                            answer.owner.reputation,
                            answer.owner.userId,
                            answer.owner.profileImage,
                            answer.owner.displayName
                    )
            )
        }
    }
}
