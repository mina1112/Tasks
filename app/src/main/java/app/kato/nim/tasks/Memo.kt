package app.kato.nim.tasks

import io.realm.RealmObject

open class Memo (
    open var task: String = ""
) : RealmObject()