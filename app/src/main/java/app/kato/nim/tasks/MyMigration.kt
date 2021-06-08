package app.kato.nim.tasks

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

class MyMigration : RealmMigration {
        // Migration code here

        override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
            val realmSchema = realm.schema // Realmのスキーマ
            var oldVersion = oldVersion

            if (oldVersion == 0L) { // スキーマのバージョンが以前のものであるとき
//                val userSchema = realmSchema.get("Schedule")
//                userSchema!!.addField("task", String::class.java)
//                oldVersion++

                realmSchema.create("Memo") // NewModelを新規作成した
//                    .addField("task", String::class.java, FieldAttribute.PRIMARY_KEY) // @PrimaryKeyアノテーション付きのプロパティ
//                    .addField("text", String::class.java, FieldAttribute.REQUIRED) // nullを許容しない場合、REQUIREDが必要らしい（ソースがない……）
                    .addField("task", String::class.java)
//                realmSchema.get("ExistModel")!! //  ExistModelにプロパティを追加した
//                    .addField("id", Long::class.javaObjectType) // class.javaObjectTypeはnullを許容する
//                    .addField("text", String::class.javaObjectType)
                oldVersion++ // マイグレーションしたためスキーマバージョンを上げる
            }
        }
    }