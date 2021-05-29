package app.kato.nim.tasks

import io.realm.RealmObject

open class Task (
    open var name: String = "",
    open var time: String = ""
) : RealmObject()