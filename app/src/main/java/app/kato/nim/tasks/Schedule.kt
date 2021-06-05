package app.kato.nim.tasks

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Schedule : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var time: String = ""
    var title: String = ""
    var detail: String = ""
}