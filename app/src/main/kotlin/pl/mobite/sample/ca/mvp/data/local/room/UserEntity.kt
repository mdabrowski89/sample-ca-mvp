package pl.mobite.sample.ca.mvp.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import pl.mobite.sample.ca.mvp.data.models.User


@Entity(tableName = "users")
data class UserEntity(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name="user_name") var name: String,
        @ColumnInfo(name="user_age") var age: Int
) {
    constructor(): this(null, "", 0)
}

fun UserEntity.toUser() =
        User(this.id, this.name, this.age)

fun User.toUserEntity() =
        UserEntity(this.id, this.name, this.age)

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): DataSource.Factory<Int, UserEntity>

    @Query("SELECT count(*) FROM users")
    fun count(): Int

    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    fun getRange(offset: Int, limit: Int): List<UserEntity>

    @Query("SELECT * FROM users where id=:userId")
    fun get(userId: Long): UserEntity

    @Insert
    fun insert(user: UserEntity)

    @Insert
    fun insertAll(users: List<UserEntity>)

    @Update
    fun update(user: UserEntity)

    @Update
    fun updateAll(users: List<UserEntity>)

    @Delete
    fun delete(user: UserEntity)

    @Query("DELETE FROM users")
    fun deleteAll()
}