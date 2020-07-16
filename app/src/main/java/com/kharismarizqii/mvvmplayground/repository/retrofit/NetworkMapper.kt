package com.kharismarizqii.mvvmplayground.repository.retrofit

import com.kharismarizqii.mvvmplayground.model.User
import com.kharismarizqii.mvvmplayground.util.EntityMapper

class NetworkMapper : EntityMapper<UserObject, User>{
    override fun mapFromEntity(entity: UserObject): User {
        return User(
            id = entity.id,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            avatar = entity.avatar
        )
    }

    override fun mapToEntity(domainModel: User): UserObject {
        return UserObject(
            id = domainModel.id,
            email = domainModel.email,
            firstName = domainModel.firstName,
            lastName = domainModel.lastName,
            avatar = domainModel.avatar
        )
    }

    fun mapFromEntityList(entities: ArrayList<UserObject>): ArrayList<User>{
        return entities.map { mapFromEntity(it) } as ArrayList<User>
    }
}