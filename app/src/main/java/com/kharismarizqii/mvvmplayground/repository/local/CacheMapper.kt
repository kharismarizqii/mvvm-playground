package com.kharismarizqii.mvvmplayground.repository.local

import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.repository.retrofit.UserObject
import com.kharismarizqii.mvvmplayground.util.EntityMapper

class CacheMapper : EntityMapper<UserCache, User>{
    override fun mapFromEntity(entity: UserCache): User {
        return User(
            id = entity.id,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            avatar = entity.avatar
        )
    }

    override fun mapToEntity(domainModel: User): UserCache {
        return UserCache(
            id = domainModel.id,
            email = domainModel.email,
            firstName = domainModel.firstName,
            lastName = domainModel.lastName,
            avatar = domainModel.avatar
        )
    }

    fun mapFromEntityList(entities: ArrayList<UserCache>): ArrayList<User>{
        return entities.map { mapFromEntity(it) } as ArrayList<User>
    }

}